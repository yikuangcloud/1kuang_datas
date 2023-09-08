package com.ruoyi.project.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.user.CaptchaException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.datav.util.IdGen;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.ISysEmailService;
import com.ruoyi.project.system.service.ISysUserRegisterService;
import com.ruoyi.project.system.service.ISysUserService;

/**
 * @author zxy
 * @Description: 用户注册
 *
 */
@RestController
@RequestMapping("/system/register")
public class SysUserRegisterController extends BaseController {

	@Autowired
    private ISysUserService userService;
	
	@Autowired
	private ISysUserRegisterService sysUserRegisterservice;
	
	@Autowired
	private ISysEmailService iSysEmailService;

    @Autowired
    private RedisCache redisCache;
	
	/**
     * 是否需要邮箱认证
     */
    @Value("${spring.mail.isEmail}")
    private boolean isEmail;
	
    /**
     * 服务器前缀
     */
    @Value("${spring.mail.serverPre}")
    private String serverPre;
    
    /**
	 * 用户信息注册
	 * @param sysUser
	 * @return
	 */
	@PostMapping("/register")
	public AjaxResult insertUser(@RequestBody SysUser sysUser) {
		//验证手机验证码是否正确
		String captcha = redisCache.getCacheObject(sysUser.getPhonenumber());
		
		if (!sysUser.getPhonecode().equalsIgnoreCase(captcha))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(sysUser.getUserName(), Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
		
		if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(sysUser.getUserName()))) {
			
            return AjaxResult.error("新增用户'" + sysUser.getUserName() + "'失败，登录账号已存在");
            
        } else if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(sysUser))) {
        	
            return AjaxResult.error("新增用户'" + sysUser.getUserName() + "'失败，手机号码已存在");
            
        } else if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(sysUser))) {
        	
            return AjaxResult.error("新增用户'" + sysUser.getUserName() + "'失败，邮箱账号已存在");
            
        }
		sysUser.setCode(IdGen.uuid());
		sysUser.setPassword(SecurityUtils.encryptPassword(sysUser.getPassword()));

		if(isEmail) {
			sysUser.setDelFlag("1");
		} else {
			sysUser.setDelFlag("0");
		}
		sysUserRegisterservice.insertUser(sysUser);
		
		//发认证邮件
		//获取激活码
        String code = sysUser.getCode();
        System.err.println("code:" + code);
        
        //主题
        String subject = "来自激活用户认证的邮件";

        //user/checkCode?code=code(激活码)是我们点击邮件链接之后根据激活码查询用户，如果存在说明一致，将用户状态修改为“1”激活
        //上面的激活码发送到用户注册邮箱
        //**************************************本地测试的路径 会失效。**************************************************************
        //String context = "<a href=\"/user/checkCode?code="+code+"\">激活请点击:"+code+"</a>";
        //String context = "<a href=\"http://localhost:8080/user/checkCode?code="+code+"\">激活请点击:"+code+"</a>";
        //*************************************************************************************************************************

        //user/checkCode?code=code(激活码)是我们点击邮件链接之后根据激活码查询用户，如果存在说明一致，将用户状态修改为“1”激活
        //上面的激活码发送到用户注册邮箱
        //注意:此处的链接地址,是项目内部地址,如果我们没有正式的服务器地址,暂时无法从qq邮箱中跳转到我们自己项目的激活页面

        //方式1 注意看上述说明(如不服务器测试 把上面本地代码链接注释)
        String context = "您好，您已成功注册【智能数据大屏平台】，继续激活用户认证请点击=>" + "<a href=\""+ serverPre +"/system/register/activation?code=" + code + "\">" + code + "</a>。";
        //方式2 注意看上述说明(如不服务器测试 把上面本地代码取消注释)
        //String context = "<a href='服务端地址/user/checkCode?code='+code+'>激活用户认证请点击=> '+code+'</a>";

        //发送激活邮件
        if(isEmail) {
        	iSysEmailService.sendHtmlMail(sysUser.getEmail(), subject, context);
        }
		
        return AjaxResult.success();
	}
	
	/**
	 * 修改用户激活标识
	 * @param sysUser
	 * @return
	 */
	@GetMapping("/activation")
	public AjaxResult activation(String code) {
	  	
	    return toAjax(sysUserRegisterservice.updateUserFlag(code));
	}
	
}
