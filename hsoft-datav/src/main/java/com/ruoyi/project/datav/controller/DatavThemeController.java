package com.ruoyi.project.datav.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.datav.domain.DatavTheme;
import com.ruoyi.project.datav.service.IDatavThemeService;
import com.ruoyi.project.datav.util.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据大屏自定义主题配置Controller
 * 
 * @author 刘佳男【风清扬】
 * @date 2021-01-29
 */
@RestController
@RequestMapping("/datav/theme")
public class DatavThemeController extends BaseController
{
    @Autowired
    private IDatavThemeService datavThemeService;

    /**
     * 查询数据大屏自定义主题配置列表
     */
    @PreAuthorize("@ss.hasPermi('datav:theme:list')")
    @GetMapping("/list")
    public TableDataInfo list(DatavTheme datavTheme)
    {
    	//获取用户账户
        String username = SecurityUtils.getUsername();
        //管理员用户查询全部，其他账户只查询自己创建的模板分类
        if(!username.equals("admin")) {
        	datavTheme.setCreateBy(username);
        }
    	startPage();
        List<DatavTheme> list = datavThemeService.selectDatavThemeList(datavTheme);
        return getDataTable(list);
    }

    /**
     * 导出数据大屏自定义主题配置列表
     */
    @PreAuthorize("@ss.hasPermi('datav:theme:export')")
    @Log(title = "数据大屏自定义主题配置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DatavTheme datavTheme)
    {
        List<DatavTheme> list = datavThemeService.selectDatavThemeList(datavTheme);
        ExcelUtil<DatavTheme> util = new ExcelUtil<DatavTheme>(DatavTheme.class);
        return util.exportExcel(list, "theme");
    }

    /**
     * 获取数据大屏自定义主题配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('datav:theme:query')")
    @GetMapping(value = "/{themeId}")
    public AjaxResult getInfo(@PathVariable("themeId") String themeId)
    {
        return AjaxResult.success(datavThemeService.selectDatavThemeById(themeId));
    }

    /**
     * 新增数据大屏自定义主题配置
     */
    @PreAuthorize("@ss.hasPermi('datav:theme:add')")
    @Log(title = "数据大屏自定义主题配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DatavTheme datavTheme)
    {
        datavTheme.setThemeId(IdGen.uuid());
        datavTheme.setCreateBy(SecurityUtils.getUsername());
        return toAjax(datavThemeService.insertDatavTheme(datavTheme));
    }

    /**
     * 修改数据大屏自定义主题配置
     */
    @PreAuthorize("@ss.hasPermi('datav:theme:edit')")
    @Log(title = "数据大屏自定义主题配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DatavTheme datavTheme)
    {
        return toAjax(datavThemeService.updateDatavTheme(datavTheme));
    }

    /**
     * 删除数据大屏自定义主题配置
     */
    @PreAuthorize("@ss.hasPermi('datav:theme:remove')")
    @Log(title = "数据大屏自定义主题配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{themeId}")
    public AjaxResult remove(@PathVariable String themeId)
    {
        return toAjax(datavThemeService.deleteDatavThemeById(themeId));
    }
    
//    @PreAuthorize("@ss.hasPermi('datav:theme:remove')")
//    @Log(title = "数据大屏自定义主题配置", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{themeIds}")
//    public AjaxResult remove(@PathVariable String[] themeIds)
//    {
//        return toAjax(datavThemeService.deleteDatavThemeByIds(themeIds));
//    }
    
    
    
}
