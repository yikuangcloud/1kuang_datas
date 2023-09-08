package com.ruoyi.project.datav.controller;

import com.ruoyi.common.utils.FastDFSClient;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.datav.domain.DatavFileUpload;
import com.ruoyi.project.datav.domain.DatavFileUploadDto;
import com.ruoyi.project.datav.domain.FileTemplateDto;
import com.ruoyi.project.datav.service.IDatavFileUploadService;
import com.ruoyi.project.datav.util.DatavConfig;
import com.ruoyi.project.datav.util.IdGen;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据大屏文件上传映射Controller
 * 
 * @author 刘佳男【风清扬】
 * @date 2021-01-26
 */
@RestController
@RequestMapping("/datav/upload")
public class DatavFileUploadController extends BaseController
{
    @Value("${fdfs.web-server-url}")
    private String webServerUrl;

    @Autowired
    private FastDFSClient fastDFSClient;
	
	@Autowired
    private IDatavFileUploadService datavFileUploadService;

    /**
     * 查询数据大屏文件上传映射列表
     */
    @PreAuthorize("@ss.hasPermi('datav:upload:list')")
    @GetMapping("/list")
    public TableDataInfo list(DatavFileUpload datavFileUpload)
    {
    	//获取用户账户
        String username = SecurityUtils.getUsername();
        //管理员用户查询全部，其他账户只查询自己创建的模板分类
        if(!username.equals("admin")) {
        	datavFileUpload.setCreateBy(username);
        }
    	startPage();
        List<DatavFileUpload> list = datavFileUploadService.selectDatavFileUploadList(datavFileUpload);
        return getDataTable(list);
    }

    /**
     * 导出数据大屏文件上传映射列表
     */
    @PreAuthorize("@ss.hasPermi('datav:upload:export')")
    @Log(title = "数据大屏文件上传映射", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DatavFileUpload datavFileUpload)
    {
        List<DatavFileUpload> list = datavFileUploadService.selectDatavFileUploadList(datavFileUpload);
        ExcelUtil<DatavFileUpload> util = new ExcelUtil<DatavFileUpload>(DatavFileUpload.class);
        return util.exportExcel(list, "upload");
    }

    /**
     * 获取数据大屏文件上传映射详细信息
     */
    @PreAuthorize("@ss.hasPermi('datav:upload:query')")
    @GetMapping(value = "/{uploadId}")
    public AjaxResult getInfo(@PathVariable("uploadId") String uploadId)
    {
        return AjaxResult.success(datavFileUploadService.selectDatavFileUploadById(uploadId));
    }

    /**
     * 新增数据大屏文件上传映射
     */
//    @PreAuthorize("@ss.hasPermi('datav:upload:add')")
    @Log(title = "数据大屏文件上传映射", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DatavFileUpload datavFileUpload)
    {
        datavFileUpload.setUploadId(IdGen.uuid());
        //非公开
        datavFileUpload.setIsOpen("0");
        //非模板
        datavFileUpload.setIsTemplate("0");
        return toAjax(datavFileUploadService.insertDatavFileUpload(datavFileUpload));
    }

    /**
     * 修改数据大屏文件上传映射
     */
    @PreAuthorize("@ss.hasPermi('datav:upload:edit')")
    @Log(title = "数据大屏文件上传映射", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DatavFileUpload datavFileUpload)
    {
        return toAjax(datavFileUploadService.updateDatavFileUpload(datavFileUpload));
    }

    /**
     * 删除数据大屏文件上传映射
     */
//    @PreAuthorize("@ss.hasPermi('datav:upload:remove')")
//    @Log(title = "数据大屏文件上传映射", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{uploadIds}")
//    public AjaxResult remove(@PathVariable String[] uploadIds)
//    {
//        return toAjax(datavFileUploadService.deleteDatavFileUploadByIds(uploadIds));
//    }
    @PreAuthorize("@ss.hasPermi('datav:upload:remove')")
    @Log(title = "数据大屏文件上传映射", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uploadId}")
    public AjaxResult remove(@PathVariable String uploadId)
    {
        return toAjax(datavFileUploadService.deleteDatavFileUploadById(uploadId));
    }

    @PostMapping("/fileUpload")
    public AjaxResult fileUpload(@RequestParam("file") MultipartFile[] files, DatavFileUpload datavFileUpload) throws IOException {

//        System.out.println(files.length);
        for (int i = 0; i < files.length; i++) {

            String imagePath = FileUploadUtils.upload(DatavConfig.getBackgroundBoxPath(), files[i]);
            String imageName = files[i].getOriginalFilename();

            DatavFileUpload fileUpload = new DatavFileUpload();
            fileUpload.setUploadId(IdGen.uuid());
            fileUpload.setIsOpen(datavFileUpload.getIsOpen());
            fileUpload.setIsTemplate(datavFileUpload.getIsTemplate());
            fileUpload.setImagePath(imagePath);
            fileUpload.setType(datavFileUpload.getType());
            fileUpload.setName(imageName);
            fileUpload.setViewTemplate(datavFileUpload.getViewTemplate());
            fileUpload.setSort(datavFileUpload.getSort());
            fileUpload.setCreateBy(SecurityUtils.getUsername());
            datavFileUploadService.insertDatavFileUpload(fileUpload);
        }


        return AjaxResult.success();
    }
    
    @PostMapping("/videoUpload")
    public AjaxResult videoUpload(@RequestParam("file") MultipartFile[] files, DatavFileUpload datavFileUpload) throws IOException {

        for (int i = 0; i < files.length; i++) {
        	
        	String url = fastDFSClient.uploadFile(files[i]);
        	if(!StringUtils.isEmpty(url)){
        		String imageName = files[i].getOriginalFilename();

                DatavFileUpload fileUpload = new DatavFileUpload();
                fileUpload.setUploadId(IdGen.uuid());
                fileUpload.setIsOpen(datavFileUpload.getIsOpen());
                fileUpload.setImagePath(webServerUrl + url);
                fileUpload.setType(datavFileUpload.getType());
                fileUpload.setName(imageName);
                fileUpload.setViewTemplate(datavFileUpload.getViewTemplate());
                fileUpload.setCreateBy(SecurityUtils.getUsername());
                datavFileUploadService.insertDatavFileUpload(fileUpload);
        	}

        }


        return AjaxResult.success();
    }
    
    @PreAuthorize("@ss.hasPermi('datav:upload:remove')")
    @Log(title = "数据大屏文件上传映射", businessType = BusinessType.DELETE)
	@DeleteMapping("/videoDel/{uploadId}")
    public AjaxResult videoDel(@PathVariable String uploadId)
    {
    	DatavFileUpload file = datavFileUploadService.selectDatavFileUploadById(uploadId);
    	if(file != null && !StringUtils.isEmpty(file.getImagePath())){
    		fastDFSClient.deleteFile(file.getImagePath());
    	}
    	return toAjax(datavFileUploadService.deleteDatavFileUploadById(uploadId));
    }

    /**
     * 查询模板超市素材列表
     */
    @GetMapping("/template/list")
    public TableDataInfo templateList(DatavFileUploadDto datavFileUploadDto)
    {
        datavFileUploadDto.setUserId(SecurityUtils.getLoginUser().getUser().getUserId());
        List<DatavFileUpload> list = new ArrayList<>();
        startPage();
        if(datavFileUploadDto.getQueryType() == null || datavFileUploadDto.getQueryType().equals("1")){
            //查询全部
            list = datavFileUploadService.selectFileTemplateList(datavFileUploadDto);
        }else{
            //查询用户收藏素材列表
            list = datavFileUploadService.selectFileTemplateListByUser(datavFileUploadDto);
        }
        return getDataTable(list);
    }

    /**
     * 更新图片素材查看、收藏、创建数
     * @param fileTemplateDto
     * @return
     * @throws IOException
     */
    @PostMapping("/update/template")
    public AjaxResult updateTemplate(@RequestBody FileTemplateDto fileTemplateDto) throws IOException {

        return AjaxResult.success(datavFileUploadService.updateFileTemplate(fileTemplateDto));
    }
}
