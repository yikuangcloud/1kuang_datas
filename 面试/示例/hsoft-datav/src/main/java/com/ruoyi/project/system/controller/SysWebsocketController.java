package com.ruoyi.project.system.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.domain.SysWebsocket;
import com.ruoyi.project.system.service.ISysWebsocketService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * websocket通讯接口管理Controller
 * 
 * @author sunyan
 * @date 2022-02-08
 */
@RestController
@RequestMapping("/system/SysWebsocket")
public class SysWebsocketController extends BaseController
{
    @Autowired
    private ISysWebsocketService sysWebsocketService;

    /**
     * 查询websocket通讯接口管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:SysWebsocket:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysWebsocket sysWebsocket)
    {
        startPage();
        List<SysWebsocket> list = sysWebsocketService.selectSysWebsocketList(sysWebsocket);
        return getDataTable(list);
    }

    /**
     * 导出websocket通讯接口管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:SysWebsocket:export')")
    @Log(title = "websocket通讯接口管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysWebsocket sysWebsocket)
    {
        List<SysWebsocket> list = sysWebsocketService.selectSysWebsocketList(sysWebsocket);
        ExcelUtil<SysWebsocket> util = new ExcelUtil<SysWebsocket>(SysWebsocket.class);
        return util.exportExcel(list, "SysWebsocket");
    }

    /**
     * 获取websocket通讯接口管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:SysWebsocket:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysWebsocketService.selectSysWebsocketById(id));
    }

    /**
     * 新增websocket通讯接口管理
     */
    @PreAuthorize("@ss.hasPermi('system:SysWebsocket:add')")
    @Log(title = "websocket通讯接口管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysWebsocket sysWebsocket)
    {
        return toAjax(sysWebsocketService.insertSysWebsocket(sysWebsocket));
    }

    /**
     * 修改websocket通讯接口管理
     */
    @PreAuthorize("@ss.hasPermi('system:SysWebsocket:edit')")
    @Log(title = "websocket通讯接口管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysWebsocket sysWebsocket)
    {
        return toAjax(sysWebsocketService.updateSysWebsocket(sysWebsocket));
    }

    /**
     * 删除websocket通讯接口管理
     */
    @PreAuthorize("@ss.hasPermi('system:SysWebsocket:remove')")
    @Log(title = "websocket通讯接口管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysWebsocketService.deleteSysWebsocketByIds(ids));
    }
}
