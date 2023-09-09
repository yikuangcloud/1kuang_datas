package com.ruoyi.project.datav.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.datav.domain.DatavCustomChart;
import com.ruoyi.project.datav.service.IDatavCustomChartService;
import com.ruoyi.project.datav.util.BASE64DecodedMultipartFile;
import com.ruoyi.project.datav.util.DatavConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 自定义组件库Controller
 * 
 * @author 刘佳男【风清扬】
 * @date 2021-02-08
 */
@RestController
@RequestMapping("/datav/customchart")
public class DatavCustomChartController extends BaseController
{
    @Autowired
    private IDatavCustomChartService datavCustomChartService;

    /**
     * 查询自定义组件库列表
     */
    //@PreAuthorize("@ss.hasPermi('datav:customchart:list')")
    @GetMapping("/list")
    public TableDataInfo list(DatavCustomChart datavCustomChart)
    {
    	//获取用户账户
        String username = SecurityUtils.getUsername();
        //管理员用户查询全部，其他账户只查询自己创建的模板分类
        if(!username.equals("admin")) {
        	datavCustomChart.setCreateBy(username);
        }
    	startPage();
        List<DatavCustomChart> list = datavCustomChartService.selectDatavCustomChartList(datavCustomChart);
        return getDataTable(list);
    }

    /**
     * 导出自定义组件库列表
     */
    //@PreAuthorize("@ss.hasPermi('datav:customchart:export')")
    @Log(title = "自定义组件库", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DatavCustomChart datavCustomChart)
    {
        List<DatavCustomChart> list = datavCustomChartService.selectDatavCustomChartList(datavCustomChart);
        ExcelUtil<DatavCustomChart> util = new ExcelUtil<DatavCustomChart>(DatavCustomChart.class);
        return util.exportExcel(list, "customchart");
    }

    /**
     * 获取自定义组件库详细信息
     */
    //@PreAuthorize("@ss.hasPermi('datav:customchart:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(datavCustomChartService.selectDatavCustomChartById(id));
    }

    /**
     * 新增自定义组件库
     */
    //@PreAuthorize("@ss.hasPermi('datav:customchart:add')")
    @Log(title = "自定义组件库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DatavCustomChart datavCustomChart) throws IOException {
        //上传大屏截图图片
        MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipart(datavCustomChart.getThumbnail());
        String customChartPath = FileUploadUtils.upload(DatavConfig.getCustomChartPath(), file);
        datavCustomChart.setThumbnail(customChartPath);
        if(datavCustomChart.getIsOpen().equals("0")){
        	datavCustomChart.setIsOpen("Y");
        }
        datavCustomChart.setCreateBy(SecurityUtils.getUsername());
        return toAjax(datavCustomChartService.insertDatavCustomChart(datavCustomChart));
    }

    /**
     * 修改自定义组件库
     */
    //@PreAuthorize("@ss.hasPermi('datav:customchart:edit')")
    @Log(title = "自定义组件库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DatavCustomChart datavCustomChart)
    {
        return toAjax(datavCustomChartService.updateDatavCustomChart(datavCustomChart));
    }

    /**
     * 删除自定义组件库
     */
    //@PreAuthorize("@ss.hasPermi('datav:customchart:remove')")
    @Log(title = "自定义组件库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Integer id)
    {
        return toAjax(datavCustomChartService.deleteDatavCustomChartById(id));
    }
    
//    @Log(title = "自定义组件库", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable Integer[] ids)
//    {
//        return toAjax(datavCustomChartService.deleteDatavCustomChartByIds(ids));
//    }
}
