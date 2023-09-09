package com.ruoyi.project.datav.controller;

import com.ruoyi.common.exception.datav.DatavFormException;
import com.ruoyi.common.utils.FastDFSClient;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.datav.domain.DatavFormSource;
import com.ruoyi.project.datav.service.IDatavFormSourceService;
import com.ruoyi.project.datav.util.FormAnalysisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 格文件数据源Controller
 * 
 * @author 风清扬【刘佳男】
 * @date 2021-06-09
 */
@RestController
@RequestMapping("/datav/formsource")
public class DatavFormSourceController extends BaseController
{
    @Autowired
    private IDatavFormSourceService datavFormSourceService;

    @Autowired
    private FastDFSClient fastDFSClient;

    @Value("${fdfs.web-server-url}")
    private String webServerUrl;

    /**
     * 查询表格文件数据源列表
     */
    @PreAuthorize("@ss.hasPermi('datav:formsource:list')")
    @GetMapping("/list")
    public TableDataInfo list(DatavFormSource datavFormSource)
    {
        startPage();
        //获取用户账户
        String username = SecurityUtils.getUsername();
        if(!username.equals("admin")) {
            datavFormSource.setCreateBy(username);
        }
        List<DatavFormSource> list = datavFormSourceService.selectDatavFormSourceList(datavFormSource);
        return getDataTable(list);
    }

    /**
     * 导出表格文件数据源列表
     */
    @PreAuthorize("@ss.hasPermi('datav:formsource:export')")
    @Log(title = "格文件数据源", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DatavFormSource datavFormSource)
    {
        List<DatavFormSource> list = datavFormSourceService.selectDatavFormSourceList(datavFormSource);
        ExcelUtil<DatavFormSource> util = new ExcelUtil<DatavFormSource>(DatavFormSource.class);
        return util.exportExcel(list, "formsource");
    }

    /**
     * 获取表格文件数据源详细信息
     */
    @PreAuthorize("@ss.hasPermi('datav:formsource:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(datavFormSourceService.selectDatavFormSourceById(id));
    }

    /**
     * 新增表格文件数据源
     */
    @PreAuthorize("@ss.hasPermi('datav:formsource:add')")
    //@Log(title = "格文件数据源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestParam("file") MultipartFile[] files, DatavFormSource datavFormSource) throws IOException {
        //1.先上传文件到fastDFS文件服务器上，并返回文件连接赋值
        datavFormSource.setFileUrl(webServerUrl + fastDFSClient.uploadFile(files[0]));
        //2.判断文件是否传了文件名称，如果没有传则按照文件名赋值
        if (StringUtils.isBlank(datavFormSource.getFileName())) {
            String originalFilename = files[0].getOriginalFilename();
            originalFilename = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            datavFormSource.setFileName(originalFilename);
        }
        //3.按照图表类型解析文件格式返回标准json数据格式
        try {
//            String formData = FormAnalysisUtil.readFile(datavFormSource.getChartType(), files[0]);
            String formData = FormAnalysisUtil.readFile("chart", files[0]);
            datavFormSource.setFormData(formData);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatavFormException();
        }
        datavFormSource.setCreateBy(SecurityUtils.getUsername());
        datavFormSourceService.insertDatavFormSource(datavFormSource);

        return AjaxResult.success(datavFormSourceService.selectDatavFormSourceById(datavFormSource.getId()));
    }

    /**
     * 修改表格文件数据源
     */
    @PreAuthorize("@ss.hasPermi('datav:formsource:edit')")
    //@Log(title = "格文件数据源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestParam("file") MultipartFile[] files, DatavFormSource datavFormSource) throws IOException {
        //1.先上传文件到fastDFS文件服务器上，并返回文件连接赋值
        fastDFSClient.deleteFile(datavFormSource.getFileUrl().replace(webServerUrl, ""));
        datavFormSource.setFileUrl(webServerUrl + fastDFSClient.uploadFile(files[0]));
        //2.判断文件是否传了文件名称，如果没有传则按照文件名赋值
        if (StringUtils.isBlank(datavFormSource.getFileName())) {
            String originalFilename = files[0].getOriginalFilename();
            originalFilename = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            datavFormSource.setFileName(originalFilename);
        }
        //3.按照图表类型解析文件格式返回标准json数据格式
        try {
//            String formData = FormAnalysisUtil.readFile(datavFormSource.getChartType(), files[0]);
            String formData = FormAnalysisUtil.readFile("chart", files[0]);
            datavFormSource.setFormData(formData);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatavFormException();
        }
        datavFormSource.setUpdateBy(SecurityUtils.getUsername());
        datavFormSourceService.updateDatavFormSource(datavFormSource);

        return AjaxResult.success(datavFormSource);
    }

    /**
     * 删除表格文件数据源
     */
    @PreAuthorize("@ss.hasPermi('datav:formsource:remove')")
    @Log(title = "格文件数据源", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(datavFormSourceService.deleteDatavFormSourceByIds(ids));
    }
}
