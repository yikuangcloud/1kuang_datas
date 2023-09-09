package com.ruoyi.project.datav.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.datav.domain.DatavChartCollection;
import com.ruoyi.project.datav.service.IDatavChartCollectionService;
import com.ruoyi.project.datav.util.BASE64DecodedMultipartFile;
import com.ruoyi.project.datav.util.DatavConfig;

@RestController
@RequestMapping("/datav/chartcollection")
public class DatavChartCollectionController extends BaseController{
	@Autowired
    private IDatavChartCollectionService datavChartCollectionService;

    /**
     * 查询图表组件收藏库列表
     */
    //@PreAuthorize("@ss.hasPermi('datav:customchart:list')")
    @GetMapping("/list")
    public TableDataInfo list(DatavChartCollection datavChartCollection)
    {
    	//获取用户账户
        String username = SecurityUtils.getUsername();
        //管理员用户查询全部，其他账户只查询自己创建的模板分类
        if(!username.equals("admin")) {
        	datavChartCollection.setCreateBy(username);
        }
    	startPage();
        List<DatavChartCollection> list = datavChartCollectionService.selectDatavChartCollectionList(datavChartCollection);
        return getDataTable(list);
    }


    /**
     * 获取图表组件收藏详细信息
     */
    //@PreAuthorize("@ss.hasPermi('datav:customchart:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(datavChartCollectionService.selectDatavChartCollectionById(id));
    }

    /**
     * 新增图表组件收藏
     */
    //@PreAuthorize("@ss.hasPermi('datav:customchart:add')")
    @Log(title = "图表组件收藏", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DatavChartCollection datavChartCollection) throws IOException {
        //上传大屏截图图片
        MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipart(datavChartCollection.getThumbnail());
        String customChartPath = FileUploadUtils.upload(DatavConfig.getCustomChartPath(), file);
        datavChartCollection.setThumbnail(customChartPath);
        if(datavChartCollection.getIsOpen().equals("0")){
        	datavChartCollection.setIsOpen("Y");
        }
        datavChartCollection.setCreateBy(SecurityUtils.getUsername());
        return toAjax(datavChartCollectionService.insertDatavChartCollection(datavChartCollection));
    }
    /**
     * 删除图表组件收藏
     */
    @Log(title = "图表组件收藏", businessType = BusinessType.DELETE)
	@DeleteMapping("/{collectionId}")
    public AjaxResult remove(@PathVariable String collectionId)
    {
        return toAjax(datavChartCollectionService.deleteDatavChartCollectionById(collectionId));
    }

}
