package com.ruoyi.project.datav.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.security.service.SysPermissionService;
import com.ruoyi.framework.security.service.TokenService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.datav.domain.DatavShareDto;
import com.ruoyi.project.datav.domain.ShareTokenLog;
import com.ruoyi.project.datav.service.IDatavShareTokenService;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: DatavShareTokenController
 * @Description: 分享token管理controller
 * @Author: sunyan
 * @Date: 2022/2/24 13:34
 */
@RestController
@RequestMapping("/datav/token")
public class DatavShareTokenController extends BaseController {
   @Autowired
    IDatavShareTokenService datavShareTokenService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    @PreAuthorize("@ss.hasPermi('tool:shareToken:list')")
    @GetMapping("list")
    //获取分享的token列表 （未过期但不在redis中）
    public TableDataInfo getTokenList(ShareTokenLog shareTokenLog){
       startPage();
       List<ShareTokenLog> list = datavShareTokenService.getTokenList(shareTokenLog);
       List<ShareTokenLog> ret = new ArrayList<>();
       //判断是否在redis中存在，不在就返回前台展示
       for(ShareTokenLog st : list){
           if(redisCache.getCacheObject(tokenService.getTokenStr(st.getTokenStr())) == null){
               ret.add(st);
           }
       }
       return getDataTable(ret);
   }

   //redis中token意外消失恢复
    @PostMapping ("/recover")
    public AjaxResult recoverToken(@RequestBody ShareTokenLog shareTokenLog){
        //拼接用户信息
        SysUser user = userService.selectUserByUserName("viewer");
        LoginUser loginUser = new LoginUser(user, permissionService.getMenuPermission(user));
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(shareTokenLog.getExpirationTime().getTime());

        //从数据库中获取tokenStr 解密放到redis中
        redisCache.setCacheObject(tokenService.getTokenStr(shareTokenLog.getTokenStr()), loginUser,
                shareTokenLog.getExpirationTime().getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);

        //更新数据库中的更新时间
        shareTokenLog.setUpdateBy(SecurityUtils.getUsername());
        shareTokenLog.setUpdateTime(new Date());
        datavShareTokenService.updateTokenLog(shareTokenLog);

        //返回成功信息
        return AjaxResult.success();
    }

}
