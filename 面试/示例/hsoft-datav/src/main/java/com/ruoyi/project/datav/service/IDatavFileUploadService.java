package com.ruoyi.project.datav.service;

import com.ruoyi.project.datav.domain.DatavFileUpload;
import com.ruoyi.project.datav.domain.DatavFileUploadDto;
import com.ruoyi.project.datav.domain.FileTemplateDto;

import java.io.IOException;
import java.util.List;

/**
 * 数据大屏文件上传映射Service接口
 * 
 * @author 刘佳男【风清扬】
 * @date 2021-01-26
 */
public interface IDatavFileUploadService 
{
    /**
     * 查询数据大屏文件上传映射
     * 
     * @param uploadId 数据大屏文件上传映射ID
     * @return 数据大屏文件上传映射
     */
    public DatavFileUpload selectDatavFileUploadById(String uploadId);

    /**
     * 查询数据大屏文件上传映射列表
     * 
     * @param datavFileUpload 数据大屏文件上传映射
     * @return 数据大屏文件上传映射集合
     */
    public List<DatavFileUpload> selectDatavFileUploadList(DatavFileUpload datavFileUpload);
    
    /**
     * 根据用户名称查询数据大屏文件上传映射列表数量
     * 
     * @param datavFileUpload 数据大屏文件上传映射
     * @return 数据大屏文件上传映射集合
     */
    public Integer selectDatavFileUploadByUsername(DatavFileUpload datavFileUpload);

    /**
     * 新增数据大屏文件上传映射
     * 
     * @param datavFileUpload 数据大屏文件上传映射
     * @return 结果
     */
    public int insertDatavFileUpload(DatavFileUpload datavFileUpload);

    /**
     * 修改数据大屏文件上传映射
     * 
     * @param datavFileUpload 数据大屏文件上传映射
     * @return 结果
     */
    public int updateDatavFileUpload(DatavFileUpload datavFileUpload);

    /**
     * 批量删除数据大屏文件上传映射
     * 
     * @param uploadIds 需要删除的数据大屏文件上传映射ID
     * @return 结果
     */
    public int deleteDatavFileUploadByIds(String[] uploadIds);

    /**
     * 删除数据大屏文件上传映射信息
     * 
     * @param uploadId 数据大屏文件上传映射ID
     * @return 结果
     */
    public int deleteDatavFileUploadById(String uploadId);

    /**
     * 查询模板素材列表
     * @param datavFileUploadDto
     * @return
     */
    List<DatavFileUpload> selectFileTemplateList(DatavFileUploadDto datavFileUploadDto);
    /**
     * 更新图片素材查看、收藏、创建数
     * @param fileTemplateDto
     * @return
     * @throws IOException
     */
    public int  updateFileTemplate(FileTemplateDto fileTemplateDto);

    /**
     * 查询用户收藏列表
     * @param datavFileUploadDto
     * @return
     */
    List<DatavFileUpload> selectFileTemplateListByUser(DatavFileUploadDto datavFileUploadDto);
}
