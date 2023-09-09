package com.ruoyi.project.datav.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.datav.domain.DatavClassification;
import com.ruoyi.project.datav.service.IDatavClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 数据大屏模板分类Controller
 * 
 * @author 刘佳男【风清扬】
 * @date 2021-01-30
 */
@RestController
@RequestMapping("/datav/classification")
public class DatavClassificationController extends BaseController
{
    @Autowired
    private IDatavClassificationService datavClassificationService;

    /**
     * 查询数据大屏模板分类列表
     */
    @PreAuthorize("@ss.hasPermi('datav:classification:list')")
    @GetMapping("/list")
    public TableDataInfo list(DatavClassification datavClassification)
    {
        startPage();
        //获取用户账户
        String username = SecurityUtils.getUsername();
        //管理员用户查询全部，其他账户只查询自己创建的模板分类
        if(!username.equals("admin")) {
        	datavClassification.setCreateBy(username);
        }
        List<DatavClassification> list = datavClassificationService.selectDatavClassificationList(datavClassification);
        return getDataTable(list);
    }

    /**
     * 导出数据大屏模板分类列表
     */
    @PreAuthorize("@ss.hasPermi('datav:classification:export')")
    @Log(title = "数据大屏模板分类", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DatavClassification datavClassification)
    {
        List<DatavClassification> list = datavClassificationService.selectDatavClassificationList(datavClassification);
        ExcelUtil<DatavClassification> util = new ExcelUtil<DatavClassification>(DatavClassification.class);
        return util.exportExcel(list, "classification");
    }

    /**
     * 获取数据大屏模板分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('datav:classification:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(datavClassificationService.selectDatavClassificationById(id));
    }

    /**
     * 新增数据大屏模板分类
     */
    @PreAuthorize("@ss.hasPermi('datav:classification:add')")
    @Log(title = "数据大屏模板分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DatavClassification datavClassification)
    {
        datavClassification.setCreateBy(SecurityUtils.getUsername());
        return toAjax(datavClassificationService.insertDatavClassification(datavClassification));
    }

    /**
     * 修改数据大屏模板分类
     */
    @PreAuthorize("@ss.hasPermi('datav:classification:edit')")
    @Log(title = "数据大屏模板分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DatavClassification datavClassification)
    {
        datavClassification.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(datavClassificationService.updateDatavClassification(datavClassification));
    }

    /**
     * 删除数据大屏模板分类
     */
    @PreAuthorize("@ss.hasPermi('datav:classification:remove')")
    @Log(title = "数据大屏模板分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(datavClassificationService.deleteDatavClassificationByIds(ids));
    }

    /**
     * 获取部门下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(DatavClassification datavClassification)
    {
    	//获取用户账户
        String username = SecurityUtils.getUsername();
        //管理员用户查询全部，其他账户只查询自己创建的模板分类
        if(!username.equals("admin")) {
        	datavClassification.setCreateBy(username);
        }
    	List<DatavClassification> list = datavClassificationService.selectDatavClassificationList(datavClassification);
        return AjaxResult.success(datavClassificationService.buildTreeSelect(list));
    }
}
