package com.ruoyi.project.system.controller;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.user.UserLicenseException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.license.LicenseVerify;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.security.LoginBody;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.security.ViewerDto;
import com.ruoyi.framework.security.service.SysLoginService;
import com.ruoyi.framework.security.service.SysPermissionService;
import com.ruoyi.framework.security.service.TokenService;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.datav.domain.CheckExtractioncodeDto;
import com.ruoyi.project.datav.domain.ShareTokenLog;
import com.ruoyi.project.datav.service.IDatavShareTokenService;
import com.ruoyi.project.datav.util.IdGen;
import com.ruoyi.project.system.domain.SysMenu;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.ISysMenuService;
import com.ruoyi.project.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private ISysUserService userService;

    @Autowired
    private IDatavShareTokenService sysShareTokenService;

    /**
     * 是否需要license认证
     */
    @Value("${license.isLicense}")
    private boolean isLicense;
    
    /**
     * 登录方法
     *
     * @param loginBody
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {

        if (isLicense) {
            LicenseVerify licenseVerify = new LicenseVerify();
            //校验证书是否有效
            boolean verifyResult = licenseVerify.verify();
            if (!verifyResult) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginBody.getUsername(), Constants.LOGIN_FAIL, MessageUtils.message("user.license.error")));
                throw new UserLicenseException();
            }
        }

        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getLoginType(),loginBody.getTelphone(),loginBody.getTelphoneCode(),loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }
    
	/**
	 * 查看者的登录验证
	 * 
	 * @param viewerDto
	 * @return
	 */
    @PostMapping("/viewerLogin")
    public AjaxResult viewerLogin(@RequestBody ViewerDto viewerDto)
    {
        AjaxResult ajax = AjaxResult.success();
        
        // ExpireTime的时间颗粒度：分钟
        int time = viewerDto.getTime() * 24 * 60;
        // 生成令牌
        String token = loginService.viewerLogin(time);
        String id = IdGen.uuid();
        
        ShareTokenLog shareTokenLog = new ShareTokenLog();
        shareTokenLog.setId(id);
        shareTokenLog.setScreenId(viewerDto.getScreenId());
        shareTokenLog.setScreenName(viewerDto.getScreenName());
        shareTokenLog.setExtractionNumber(viewerDto.getNumber());
        shareTokenLog.setEffectiveTime(viewerDto.getTime());
        shareTokenLog.setTokenStr(token);
        shareTokenLog.setSenderId(SecurityUtils.getUsername());
        SysUser user = userService.selectUserByUserName("viewer");
        shareTokenLog.setCreateBy(user.getUserName());
        shareTokenLog.setCreateTime(new Date());
        Date date1 = new Date();
        date1.setTime(System.currentTimeMillis() + (Long.valueOf(time) * 60L * 1000L));
        shareTokenLog.setExpirationTime(date1);
        sysShareTokenService.addTokenLog(shareTokenLog);
        
        ajax.put(Constants.TOKEN, token);
        ajax.put("id", id);
        ajax.put("screenId", viewerDto.getScreenId());
        
        return ajax;
    }

    /**
     * 伪单点登录接口
     * @return
     */
    @PostMapping("/ssoLogin")
    public AjaxResult ssoLogin() {
        AjaxResult ajax = AjaxResult.success();

        // ExpireTime的时间颗粒度：分钟，10个小时有效期
        int time = 10 * 60;
        // 生成令牌
        String token = loginService.viewerLogin(time);
        ajax.put(Constants.TOKEN, token);

        return ajax;
    }

    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
        return AjaxResult.success(menuService.buildMenus(menus));
    }
    
    /**
     * 根据id获取信息
     * @param id
     * @return
     */
    @GetMapping("getShareToken")
    public AjaxResult findById(String id)
    {
    	return AjaxResult.success(sysShareTokenService.findById(id));
    }
    
    /**
     * 提取码核验
     * @param dto
     * @return
     */
    @GetMapping("checkExtractionCode")
    public AjaxResult checkExtractionCode(@RequestBody CheckExtractioncodeDto dto) 
    {
    	ShareTokenLog result = sysShareTokenService.findById(dto.getId());
    	
    	if(result.getExtractionNumber().equals(dto.getExtractionCode())) {
    		return AjaxResult.success();
    	} else {
    		return AjaxResult.error("验证码错误，请重新输入！");
    	}
    	
    }
    
    
}
