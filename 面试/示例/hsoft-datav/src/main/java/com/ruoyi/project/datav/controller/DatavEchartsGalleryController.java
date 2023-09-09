package com.ruoyi.project.datav.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.utils.FastDFSClient;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.project.datav.domain.DatavEchartsGallery;
import com.ruoyi.project.datav.domain.DatavEchartsGalleryDto;
import com.ruoyi.project.datav.domain.DatavUserEcharts;
import com.ruoyi.project.datav.service.IDatavEchartsGalleryService;
import com.ruoyi.project.datav.service.IDatavUserEchartsService;
import com.ruoyi.project.datav.util.BASE64DecodedMultipartFile;
import com.ruoyi.project.datav.util.DatavConfig;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.loadtime.Aj;
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
import org.springframework.web.multipart.MultipartFile;

/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: DatavEchartsGalleryController
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 王莹
 * @Date: 2022/7/27 15:28
 */
@RestController
@RequestMapping("/datav/echartsGallery")
public class DatavEchartsGalleryController extends BaseController
{
    @Autowired
    private IDatavEchartsGalleryService datavEchartsGalleryService;
    @Autowired
    private IDatavUserEchartsService datavUserEchartsService;
    @Autowired
    private FastDFSClient fastDFSClient;
    /**
     * 查询echarts素材列表
     */
//    @PreAuthorize("@ss.hasPermi('datav:echartsGallery:list')")
    @GetMapping("/list")
    public TableDataInfo list(DatavEchartsGalleryDto datavEchartsGalleryDto)
    {
        startPage();
        List<DatavEchartsGallery> list = new ArrayList<>();
        if(datavEchartsGalleryDto.getQueryType().equals("1")){//全部
             list = datavEchartsGalleryService.selectDatavEchartsGalleryList(datavEchartsGalleryDto);
        }
        else if(datavEchartsGalleryDto.getQueryType().equals("2")){
            datavEchartsGalleryDto.setCreateBy(SecurityUtils.getUsername());
            list = datavEchartsGalleryService.selectDatavEchartsGalleryListByStar(datavEchartsGalleryDto);
        }
        else if (datavEchartsGalleryDto.getQueryType().equals("3")){//我的图表
            datavEchartsGalleryDto.setCreateBy(SecurityUtils.getUsername());
            list = datavEchartsGalleryService.selectDatavEchartsGalleryList(datavEchartsGalleryDto);
        }
        return getDataTable(list);
    }

    /**
     * 导出echarts素材列表
     */
//    @PreAuthorize("@ss.hasPermi('datav:echartsGallery:export')")
    @Log(title = "echarts素材", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DatavEchartsGalleryDto datavEchartsGalleryDto)
    {
        List<DatavEchartsGallery> list = datavEchartsGalleryService.selectDatavEchartsGalleryList(datavEchartsGalleryDto);
        ExcelUtil<DatavEchartsGallery> util = new ExcelUtil<DatavEchartsGallery>(DatavEchartsGallery.class);
        return util.exportExcel(list, "gallery");
    }

    /**
     * 获取echarts素材详细信息
     */
//    @PreAuthorize("@ss.hasPermi('datav:echartsGallery:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(datavEchartsGalleryService.selectDatavEchartsGalleryById(id));
    }

    /**
     * 新增echarts素材
     */
//    @PreAuthorize("@ss.hasPermi('datav:echartsGallery:add')")
    @Log(title = "echarts素材", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DatavEchartsGallery datavEchartsGallery) throws IOException
    {
        //上传大屏截图图片
        MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipart(datavEchartsGallery.getThumbnail());
        String customChartPath = FileUploadUtils.upload(DatavConfig.getEchartsCapturePath(), file);
        datavEchartsGallery.setThumbnail(customChartPath);
        datavEchartsGallery.setCreateBy(SecurityUtils.getUsername());
        return toAjax(datavEchartsGalleryService.insertDatavEchartsGallery(datavEchartsGallery));
    }

    /**
     * 修改echarts素材
     */
//    @PreAuthorize("@ss.hasPermi('datav:echartsGallery:edit')")
    @Log(title = "echarts素材", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DatavEchartsGallery datavEchartsGallery) throws IOException
    {
        //上传大屏截图图片
        if(!StringUtils.isEmpty(datavEchartsGallery.getThumbnail())){
            fastDFSClient.deleteFile(datavEchartsGallery.getThumbnail());
        }
        MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipart(datavEchartsGallery.getThumbnail());
        String customChartPath = FileUploadUtils.upload(DatavConfig.getEchartsCapturePath(), file);
        datavEchartsGallery.setThumbnail(customChartPath);
        return toAjax(datavEchartsGalleryService.updateDatavEchartsGallery(datavEchartsGallery));
    }

    /**
     * 删除echarts素材
     */
//    @PreAuthorize("@ss.hasPermi('datav:echartsGallery:remove')")
//    @Log(title = "echarts素材", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{ids}")
//    public AjaxResult remove(@PathVariable Long[] ids)
//    {
//        return toAjax(datavEchartsGalleryService.deleteDatavEchartsGalleryByIds(ids));
//    }
    /**
     * 删除echarts素材
     */
    @Log(title = "echarts素材", businessType = BusinessType.DELETE)
    @DeleteMapping
    public AjaxResult deleteDatavEchartsGalleryById(@RequestBody DatavEchartsGallery datavEchartsGallery)
    {
        //管理员用户可删除全部，其他账户只删除自己创建的模板
        String createBy = SecurityUtils.getUsername();
        if(!createBy.equals("admin")) {
            datavEchartsGallery.setCreateBy(createBy);
        }

        int flag = -1;
        int result = datavEchartsGalleryService.deleteDatavEchartsGalleryById(datavEchartsGallery);
        if(result > 0) {
            flag = datavUserEchartsService.deleteDatavUserEchartsById(datavEchartsGallery.getId());
        }
        if(flag > -1) {
            return  AjaxResult.success();
        }
        else {
            return AjaxResult.error();
        }
    }
    /**
     * 收藏+1
     * @param datavEchartsGallery
     * @return
     */
    @PostMapping(value = "/starIncrease")
    public AjaxResult starIncrease(@RequestBody DatavEchartsGallery datavEchartsGallery)
    {
        return toAjax(datavEchartsGalleryService.starIncrease(datavEchartsGallery));
    }
    /**
     * 收藏-1
     * @param datavEchartsGallery
     * @return
     */
    @PostMapping(value = "/starDecrease")
    public AjaxResult starDecrease(@RequestBody DatavEchartsGallery datavEchartsGallery)
    {
        return toAjax(datavEchartsGalleryService.starDecrease(datavEchartsGallery));
    }
    /**
     * 查看+1
     * @param datavEchartsGallery
     * @return
     */
    @PostMapping(value = "/viewIncrease")
    public AjaxResult viewIncrease(@RequestBody DatavEchartsGallery datavEchartsGallery)
    {
        return toAjax(datavEchartsGalleryService.viewIncrease(datavEchartsGallery));
    }
}

