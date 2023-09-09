package com.ruoyi.framework.security.service;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import com.ruoyi.common.constant.CacheConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.exception.user.CaptchaException;
import com.ruoyi.common.exception.user.CaptchaExpireException;
import com.ruoyi.common.exception.user.UserNotExistsException;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.ISysUserService;

/**
 * 登录校验方法
 * 
 * @author ruoyi
 */
@Component
public class SysLoginService
{
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;
    
    @Autowired
    private ISysUserService userService;
    
    @Autowired
    private SysPermissionService permissionService;
    
    @Autowired
    private ISysUserService iSysUserService;

    private int maxRetryCount = CacheConstants.passwordMaxRetryCount;

    private Long lockTime = CacheConstants.passwordLockTime;

    /**
     * 登录验证
     *
     * @param loginType
     * @param telphone
     * @param telphoneCode
     * @param username
     * @param password
     * @param code
     * @param uuid
     * @return 结果
     */
    public String login(String loginType,String telphone,String telphoneCode,String username, String password, String code, String uuid)
    {
        String verifyKey = "";
        //用户名密码登录
        if(loginType.equals("0")){
        	verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        }
        //手机短信验证码登录
        else{
        	verifyKey = telphone;
        }
        String captcha = redisCache.getCacheObject(verifyKey);
        
        if (captcha == null)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
            throw new CaptchaExpireException();
        }
        //用户名密码登录方式
        if(loginType.equals("0")){
        	if (!code.equalsIgnoreCase(captcha))
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
                throw new CaptchaException();
            }
        	redisCache.deleteObject(verifyKey);
        }
        //短信登录方式
        if(loginType.equals("1")){
        	//判断验证码是否输入正确
        	if (!telphoneCode.equalsIgnoreCase(captcha))
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(telphone, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
                throw new CaptchaException();
            }
        	redisCache.deleteObject(verifyKey);
	    	//通过手机号获取数据库中用户信息
	        SysUser sysUser = iSysUserService.selectUserByTel(telphone);
	        if(sysUser != null){
	        	username = sysUser.getUserName();
	        	password = "hsoft@AI";
	        	
	        	//redis中存放用户名以及加密后的密码
	        	redisCache.setCacheObject(username+"hsoft@AI", SecurityUtils.encryptPassword(password), Long.valueOf("5"), TimeUnit.MINUTES);
	        	
	        }else{
	        	//查询无此用户信息则抛出异常错误
	        	AsyncManager.me().execute(AsyncFactory.recordLogininfor(telphone, Constants.LOGIN_FAIL, MessageUtils.message("user.telphone.notbind")));
	            throw new UserNotExistsException();
	        }
            
        }
        //获取登录失败缓存次数
        String aa = getCacheKey(username);
        Integer retryCount = redisCache.getCacheObject(getCacheKey(username));
        if (retryCount == null)
        {
            retryCount = 0;
        }
        if(retryCount >= Integer.valueOf(maxRetryCount).intValue())
        {
            String errMsg = String.format("密码输入错误%s次，帐户锁定%s分钟", maxRetryCount, lockTime);

            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, errMsg));

            throw new CustomException(errMsg);
        }
        // 用户验证
        Authentication authentication = null;
        try
        {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));


        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                //缓存登录错误次数+1
                retryCount = retryCount + 1;
                redisCache.setCacheObject(getCacheKey(username), retryCount, lockTime, TimeUnit.MINUTES);

                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));


                throw new UserPasswordNotMatchException();
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new CustomException(e.getMessage());
            }
        }
        clearLoginRecordCache(username);
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        
        //手机登录方式删除第二次缓存信息
        if(loginType.equals("1")){
            redisCache.deleteObject(username);
        }
        // 生成token
        return tokenService.createToken(loginUser);
    }
    /**
     * 登录账户密码错误次数缓存键名
     *
     * @param username 用户名
     * @return 缓存键key
     */
    private String getCacheKey(String username)
    {
        return CacheConstants.PWD_ERR_CNT_KEY + username;
    }

    public void clearLoginRecordCache(String loginName)
    {
        Integer retryCount = redisCache.getCacheObject(getCacheKey(loginName));
        if (retryCount != null && retryCount > 0)
        {
            redisCache.deleteObject(getCacheKey(loginName));
        }
    }

    public String viewerLogin(Integer time)
    {
    	SysUser user = userService.selectUserByUserName("viewer");
    	LoginUser loginUser = new LoginUser(user, permissionService.getMenuPermission(user));
    	loginUser.setExpireTime(time.longValue());
    	
    	return tokenService.shareUrlToken(loginUser);
    }
    
}
