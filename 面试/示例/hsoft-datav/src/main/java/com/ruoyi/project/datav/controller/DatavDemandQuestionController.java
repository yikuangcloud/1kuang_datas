package com.ruoyi.project.datav.controller;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.project.datav.domain.DatavDemandQuestion;
import com.ruoyi.project.datav.service.IDatavDemandQuestionService;
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
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: DatavDemandQuestionController
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 王莹
 * @Date: 2022/7/20 16:32
 */
@RestController
@RequestMapping("/datav/demand")
public class DatavDemandQuestionController extends BaseController
{
    @Autowired
    private IDatavDemandQuestionService datavDemandQuestionService;

    /**
     * 查询需求问卷列表
     */
    @PreAuthorize("@ss.hasPermi('datav:demand:list')")
    @GetMapping("/list")
    public TableDataInfo list(DatavDemandQuestion datavDemandQuestion)
    {
        startPage();
        List<DatavDemandQuestion> list = datavDemandQuestionService.selectDatavDemandQuestionList(datavDemandQuestion);
        return getDataTable(list);
    }

    /**
     * 导出需求问卷列表
     */
    @PreAuthorize("@ss.hasPermi('datav:demand:export')")
    @Log(title = "需求问卷", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DatavDemandQuestion datavDemandQuestion)
    {
        List<DatavDemandQuestion> list = datavDemandQuestionService.selectDatavDemandQuestionList(datavDemandQuestion);
        ExcelUtil<DatavDemandQuestion> util = new ExcelUtil<DatavDemandQuestion>(DatavDemandQuestion.class);
        return util.exportExcel(list, "question");
    }

    /**
     * 获取需求问卷详细信息
     */
    @PreAuthorize("@ss.hasPermi('datav:demand:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(datavDemandQuestionService.selectDatavDemandQuestionById(id));
    }

    /**
     * 新增需求问卷
     */
    @PreAuthorize("@ss.hasPermi('datav:demand:add')")
    @Log(title = "需求问卷", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DatavDemandQuestion datavDemandQuestion)
    {
        return toAjax(datavDemandQuestionService.insertDatavDemandQuestion(datavDemandQuestion));
    }

    /**
     * 修改需求问卷
     */
    @PreAuthorize("@ss.hasPermi('datav:demand:edit')")
    @Log(title = "需求问卷", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DatavDemandQuestion datavDemandQuestion)
    {
        return toAjax(datavDemandQuestionService.updateDatavDemandQuestion(datavDemandQuestion));
    }

    /**
     * 删除需求问卷
     */
    @PreAuthorize("@ss.hasPermi('datav:demand:remove')")
    @Log(title = "需求问卷", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(datavDemandQuestionService.deleteDatavDemandQuestionByIds(ids));
    }

    @GetMapping("/templateType/count")
    public AjaxResult getTemplateTypeCount(){
        return  AjaxResult.success(datavDemandQuestionService.getTemplateTypeCount());
    }

    @GetMapping("/templateDemand/count")
    public AjaxResult gettemplateDemandCount(){
        return  AjaxResult.success(datavDemandQuestionService.getTemplateDemandCount());
    }

    @GetMapping("/postType/count")
    public AjaxResult getpostTypeCount(){
        return  AjaxResult.success(datavDemandQuestionService.getPostTypeCount());
    }

    @GetMapping("/postAndDemand/count")
    public AjaxResult getpostAndDemandCount(){
        return  AjaxResult.success(datavDemandQuestionService.getpostAndDemandCount());
    }
}
