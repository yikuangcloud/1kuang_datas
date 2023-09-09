package com.ruoyi.project.system.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.ruoyi.project.system.domain.dto.DashboardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.datav.service.IDatavScreenService;
import com.ruoyi.project.monitor.domain.SysUserOnline;
import com.ruoyi.project.system.service.ISysUserOnlineService;
import com.ruoyi.project.system.service.ISysUserService;

/**
 * @author zxy
 * @Description: 首页-dashboard
 *
 */
@RestController
@RequestMapping("/system/dashboard")
public class SysDashboardController {
	
	@Autowired
	private ISysUserService userService;
	
	@Autowired
    private ISysUserOnlineService userOnlineService;

    @Autowired
    private RedisCache redisCache;
    
    @Autowired
    private IDatavScreenService screenService;
    
	/** 查找已注册用户人数 **/
	@PostMapping("/findRegister")
	public AjaxResult findRegister() {
		return AjaxResult.success(userService.findRegister());
	}
	
	/** 查找在线用户人数 **/
	@PostMapping("/findOnlineUsers")
	public AjaxResult findOnlineUsers(String ipaddr, String userName) {
		Collection<String> keys = redisCache.keys(Constants.LOGIN_TOKEN_KEY + "*");
        List<SysUserOnline> userOnlineList = new ArrayList<SysUserOnline>();
        for (String key : keys)
        {
            LoginUser user = redisCache.getCacheObject(key);
            if (StringUtils.isNotEmpty(ipaddr) && StringUtils.isNotEmpty(userName))
            {
                if (StringUtils.equals(ipaddr, user.getIpaddr()) && StringUtils.equals(userName, user.getUsername()))
                {
                    userOnlineList.add(userOnlineService.selectOnlineByInfo(ipaddr, userName, user));
                }
            }
            else if (StringUtils.isNotEmpty(ipaddr))
            {
                if (StringUtils.equals(ipaddr, user.getIpaddr()))
                {
                    userOnlineList.add(userOnlineService.selectOnlineByIpaddr(ipaddr, user));
                }
            }
            else if (StringUtils.isNotEmpty(userName) && StringUtils.isNotNull(user.getUser()))
            {
                if (StringUtils.equals(userName, user.getUsername()))
                {
                    userOnlineList.add(userOnlineService.selectOnlineByUserName(userName, user));
                }
            }
            else
            {
                userOnlineList.add(userOnlineService.loginUserToUserOnline(user));
            }
        }
        Collections.reverse(userOnlineList);
        userOnlineList.removeAll(Collections.singleton(null));
        
		return AjaxResult.success(userOnlineList.size());
	}
	
	/** 查询大屏模板个数 **/
	@PostMapping("/findScreenCount")
	public AjaxResult findScreenCount() {
		return AjaxResult.success(screenService.findScreenCount());
	}
	
	/** 查询组件个数 **/
	@PostMapping("/findAssemblyCount")
	public AjaxResult findAssemblyCount() {
		Integer count = 81 + screenService.findAssemblyCount();
		return AjaxResult.success(count);
	}
	
	/** 近一周每日新增用户数变化趋势 **/
	@PostMapping("/findFirstList")
	public AjaxResult findFirstList(@Validated @RequestBody DashboardDto dashboardDto) {
		return AjaxResult.success(screenService.findFirstList(dashboardDto));
	}
	
	/** 近一周大屏模板个数变化趋势 **/
	@PostMapping("/findThirdList")
	public AjaxResult findThirdList(@Validated @RequestBody DashboardDto dashboardDto) {
		return AjaxResult.success(screenService.findThirdList(dashboardDto));
	}
	
	/** 近一周组件个数变化趋势 **/
	@PostMapping("/findForthList")
	public AjaxResult findForthList(@Validated @RequestBody DashboardDto dashboardDto) {
		return AjaxResult.success(screenService.findForthList(dashboardDto));
	}
	
	/** 查询是否公开的集合信息 **/
	@PostMapping("/findIsPublic")
	public AjaxResult findIsPublic() {
		return AjaxResult.success(screenService.findIsPublic());
	}
	
	/** 查询自定义组件数量变化 **/
	@PostMapping("/findCustom")
	public AjaxResult findCustom() {
		return AjaxResult.success(screenService.findCustom());
	}

	/** 查询自定义组件分类 **/
	@PostMapping("/findCustomPie")
	public AjaxResult findCustomPie() {
		return AjaxResult.success(screenService.findCustomPie());
	}
	
	/** 根据用户名称查询模板 **/
	@PostMapping("/findIsTemplate")
	public AjaxResult findIsTemplate() {
		return AjaxResult.success(screenService.findIsTemplate());
	}
	
}
