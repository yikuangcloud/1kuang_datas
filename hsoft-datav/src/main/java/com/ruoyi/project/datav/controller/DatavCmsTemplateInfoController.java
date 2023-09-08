package com.ruoyi.project.datav.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.datav.domain.DatavCmsTemplateInfoDto;
import com.ruoyi.project.datav.domain.DatavScreen;
import com.ruoyi.project.datav.util.IdGen;
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
import com.ruoyi.project.datav.domain.DatavCmsTemplateInfo;
import com.ruoyi.project.datav.service.IDatavCmsTemplateInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * cms模板Controller
 * 
 * @author hanchao
 * @date 2022-07-12
 */
@RestController
@RequestMapping("/datav/cmsTemplate")
public class DatavCmsTemplateInfoController extends BaseController
{
    @Autowired
    private IDatavCmsTemplateInfoService datavCmsTemplateInfoService;

    /**
     * 查询cms模板列表
     */
    @GetMapping("/list")
    public TableDataInfo getCMSPublicTemplateList(DatavCmsTemplateInfoDto datavCmsTemplateInfoDto)
    {
        startPage();
        List<DatavCmsTemplateInfo> list = datavCmsTemplateInfoService.selectDatavCmsTemplateInfoList(datavCmsTemplateInfoDto);
        return getDataTable(list);
    }
    /**
     * 查询cms模板列表
     */
    @GetMapping("/user/list")
    public TableDataInfo getTemplateList(DatavCmsTemplateInfoDto datavCmsTemplateInfoDto)
    {

        datavCmsTemplateInfoDto.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        List<DatavCmsTemplateInfo> list = new ArrayList<>();
        startPage();
        if(datavCmsTemplateInfoDto.getQueryType().equals("1")){
            list = datavCmsTemplateInfoService.selectDatavCmsTemplateInfoList(datavCmsTemplateInfoDto);
        }else{
            list = datavCmsTemplateInfoService.selectDatavCmsTemplateInfoListByUser(datavCmsTemplateInfoDto);
        }
        return getDataTable(list);
    }

    /**
     * 获取热门cms模板列表
     */
    @GetMapping("/hot")
    public TableDataInfo getHotTemplateList(DatavCmsTemplateInfo datavCmsTemplateInfo)
    {
        List<DatavCmsTemplateInfo> list = datavCmsTemplateInfoService.selectHotTemplateList(datavCmsTemplateInfo);
        return getDataTable(list);
    }

    /**
     * 获取cms模板详细信息
     */
    @GetMapping(value="/keyword")
    public AjaxResult selectTemplateListByKeyword(String keyword)
    {
        return AjaxResult.success(datavCmsTemplateInfoService.selectTemplateListByKeyword(keyword));
    }

    /**
     * 获取cms模板详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(datavCmsTemplateInfoService.selectDatavCmsTemplateInfoById(id));
    }

    /**
     * 新增cms模板
     */
    @Log(title = "cms模板", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DatavCmsTemplateInfo datavCmsTemplateInfo)
    {
        datavCmsTemplateInfo.setReleaseDate(DateUtils.getNowDate());
        return toAjax(datavCmsTemplateInfoService.insertDatavCmsTemplateInfo(datavCmsTemplateInfo));
    }

    /**
     * 修改cms模板
     */
    @Log(title = "cms模板", businessType = BusinessType.UPDATE)
    @PutMapping("/update")
    public AjaxResult edit(@RequestBody DatavCmsTemplateInfo datavCmsTemplateInfo)
    {
        datavCmsTemplateInfo.setReleaseDate(DateUtils.getNowDate());
        return toAjax(datavCmsTemplateInfoService.updateDatavCmsTemplateInfo(datavCmsTemplateInfo));
    }

    /**
     * 删除cms模板
     */
    @Log(title = "cms模板", businessType = BusinessType.UPDATE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(datavCmsTemplateInfoService.deleteDatavCmsTemplateInfoByIds(ids));
    }
    /**
     * 发布cms模板
     */
    @Log(title = "cms模板", businessType = BusinessType.UPDATE)
    @PutMapping("/release")
    public AjaxResult releaseCmsTemplate(@RequestBody DatavCmsTemplateInfo datavCmsTemplateInfo)
    {
        return AjaxResult.success(datavCmsTemplateInfoService.releaseCmsTemplate(datavCmsTemplateInfo));
    }

    /**
     * 复制cms模板
     */
    @Log(title = "cms模板", businessType = BusinessType.INSERT)
    @PostMapping("/copyTemplate/{id}")
    public AjaxResult copyTemplate(@PathVariable Long id)
    {
        DatavCmsTemplateInfo datavCmsTemplateInfo = datavCmsTemplateInfoService.selectDatavCmsTemplateInfoById(id);
        String newScreenName = datavCmsTemplateInfo.getScreenName() + "-副本";
        datavCmsTemplateInfo.setScreenName(newScreenName);
        datavCmsTemplateInfo.setCreateBy(SecurityUtils.getUsername());
        datavCmsTemplateInfo.setScreenCreateDate(new Date());
        datavCmsTemplateInfo.setIsReleased("0");
        datavCmsTemplateInfo.setReleaseDate(null);
        datavCmsTemplateInfo.setViewCount(0L);
        datavCmsTemplateInfo.setStarCount(0L);
        datavCmsTemplateInfo.setUseCount(0L);
        return toAjax(datavCmsTemplateInfoService.insertDatavCmsTemplateInfo(datavCmsTemplateInfo));
    }
    /**
     * 收藏数+1
     */
    @Log(title = "cms模板", businessType = BusinessType.UPDATE)
    @PutMapping("/starCountIncrease")
    public AjaxResult starCountIncrease(@RequestBody DatavCmsTemplateInfo datavCmsTemplateInfo)
    {
        return toAjax(datavCmsTemplateInfoService.starCountIncrease(datavCmsTemplateInfo.getScreenId()));
    }
    /**
     * 收藏数-1
     */
    @Log(title = "cms模板", businessType = BusinessType.UPDATE)
    @PutMapping("/starCountDecrease")
    public AjaxResult starCountDecrease(@RequestBody DatavCmsTemplateInfo datavCmsTemplateInfo)
    {
        return toAjax(datavCmsTemplateInfoService.starCountDecrease(datavCmsTemplateInfo.getScreenId()));
    }

    /**
     * 浏览数+1
     */
    @Log(title = "cms模板", businessType = BusinessType.UPDATE)
    @PutMapping("/viewCountIncrease")
    public AjaxResult viewCountIncrease(@RequestBody DatavCmsTemplateInfo datavCmsTemplateInfo)
    {
        return toAjax(datavCmsTemplateInfoService.viewCountIncrease(datavCmsTemplateInfo.getScreenId()));
    }
    /**
     * 使用数+1
     */
    @Log(title = "cms模板", businessType = BusinessType.UPDATE)
    @PutMapping("/useCountIncrease")
    public AjaxResult useCountIncrease(@RequestBody DatavCmsTemplateInfo datavCmsTemplateInfo)
    {
        return toAjax(datavCmsTemplateInfoService.useCountIncrease(datavCmsTemplateInfo.getScreenId()));
    }
}
