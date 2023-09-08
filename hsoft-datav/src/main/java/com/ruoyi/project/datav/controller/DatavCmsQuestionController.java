package com.ruoyi.project.datav.controller;
import java.util.List;

import com.ruoyi.project.datav.domain.DatavCmsQuestion;
import com.ruoyi.project.datav.domain.DatavCmsQuestionDto;
import com.ruoyi.project.datav.service.IDatavCmsQuestionService;
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
 * @ClassName: DatavCmsQuestionController
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 王莹
 * @Date: 2022/8/3 11:26
 */
@RestController
@RequestMapping("/datav/question")
public class DatavCmsQuestionController extends BaseController
{
    @Autowired
    private IDatavCmsQuestionService datavCmsQuestionService;

    /**
     * 查询社区问答列表
     */
//    @PreAuthorize("@ss.hasPermi('datav:question:list')")
    @GetMapping("/list")
    public TableDataInfo list(DatavCmsQuestion datavCmsQuestion)
    {
        startPage();
        List<DatavCmsQuestion> list = datavCmsQuestionService.selectDatavCmsQuestionList(datavCmsQuestion);
        return getDataTable(list);
    }

    @GetMapping("/list/user")
    public TableDataInfo getList(DatavCmsQuestionDto datavCmsQuestionDto)
    {
        if(!datavCmsQuestionDto.getTags().isEmpty()){

            datavCmsQuestionDto.setTagArr(datavCmsQuestionDto.getTags().split(","));
        }
        startPage();
        List<DatavCmsQuestion> list = datavCmsQuestionService.selectQuestionList(datavCmsQuestionDto);
        return getDataTable(list);
    }

    /**
     * 导出社区问答列表
     */
//    @PreAuthorize("@ss.hasPermi('datav:question:export')")
    @Log(title = "社区问答", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DatavCmsQuestion datavCmsQuestion)
    {
        List<DatavCmsQuestion> list = datavCmsQuestionService.selectDatavCmsQuestionList(datavCmsQuestion);
        ExcelUtil<DatavCmsQuestion> util = new ExcelUtil<DatavCmsQuestion>(DatavCmsQuestion.class);
        return util.exportExcel(list, "question");
    }

    /**
     * 获取社区问答详细信息
     */
//    @PreAuthorize("@ss.hasPermi('datav:question:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(datavCmsQuestionService.selectDatavCmsQuestionById(id));
    }

    /**
     * 新增社区问答
     */
//    @PreAuthorize("@ss.hasPermi('datav:question:add')")
    @Log(title = "社区问答", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DatavCmsQuestion datavCmsQuestion)
    {
        return toAjax(datavCmsQuestionService.insertDatavCmsQuestion(datavCmsQuestion));
    }

    /**
     * 修改社区问答
     */
//    @PreAuthorize("@ss.hasPermi('datav:question:edit')")
    @Log(title = "社区问答", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DatavCmsQuestion datavCmsQuestion)
    {
        return toAjax(datavCmsQuestionService.updateDatavCmsQuestion(datavCmsQuestion));
    }

    /**
     * 删除社区问答
     */
//    @PreAuthorize("@ss.hasPermi('datav:question:remove')")
    @Log(title = "社区问答", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(datavCmsQuestionService.deleteDatavCmsQuestionByIds(ids));
    }

    /**
     * 浏览数+1
     * @param datavCmsQuestion
     * @return
     */
    @PostMapping("/viewCountIncrease")
    public AjaxResult viewCountIncrease(@RequestBody DatavCmsQuestion datavCmsQuestion)
    {
        return toAjax(datavCmsQuestionService.viewCountIncrease(datavCmsQuestion.getId()));
    }

    /**
     * 最新回复列表
     * @return
     */
    @GetMapping("/reply/latest")
    public AjaxResult getLatestReply()
    {
        return AjaxResult.success(datavCmsQuestionService.getLatestReply());
    }
}