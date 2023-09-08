package com.ruoyi.project.datav.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.datav.domain.DatavFileUpload;
import com.ruoyi.project.datav.domain.DatavFileUploadDto;
import com.ruoyi.project.datav.domain.FileTemplateDto;
import com.ruoyi.project.datav.mapper.DatavFileUploadMapper;
import com.ruoyi.project.datav.service.IDatavFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * 数据大屏文件上传映射Service业务层处理
 * 
 * @author 刘佳男【风清扬】
 * @date 2021-01-26
 */
@Service
public class DatavFileUploadServiceImpl implements IDatavFileUploadService 
{
    @Autowired
    private DatavFileUploadMapper datavFileUploadMapper;

    /**
     * 查询数据大屏文件上传映射
     * 
     * @param uploadId 数据大屏文件上传映射ID
     * @return 数据大屏文件上传映射
     */
    @Override
    public DatavFileUpload selectDatavFileUploadById(String uploadId)
    {
        return datavFileUploadMapper.selectDatavFileUploadById(uploadId);
    }

    /**
     * 查询数据大屏文件上传映射列表
     * 
     * @param datavFileUpload 数据大屏文件上传映射
     * @return 数据大屏文件上传映射
     */
    @Override
    public List<DatavFileUpload> selectDatavFileUploadList(DatavFileUpload datavFileUpload)
    {
        return datavFileUploadMapper.selectDatavFileUploadList(datavFileUpload);
    }

    /**
     * 新增数据大屏文件上传映射
     * 
     * @param datavFileUpload 数据大屏文件上传映射
     * @return 结果
     */
    @Override
    public int insertDatavFileUpload(DatavFileUpload datavFileUpload)
    {
        datavFileUpload.setCreateTime(DateUtils.getNowDate());
        return datavFileUploadMapper.insertDatavFileUpload(datavFileUpload);
    }

    /**
     * 修改数据大屏文件上传映射
     * 
     * @param datavFileUpload 数据大屏文件上传映射
     * @return 结果
     */
    @Override
    public int updateDatavFileUpload(DatavFileUpload datavFileUpload)
    {
        datavFileUpload.setUpdateTime(DateUtils.getNowDate());
        return datavFileUploadMapper.updateDatavFileUpload(datavFileUpload);
    }

    /**
     * 批量删除数据大屏文件上传映射
     * 
     * @param uploadIds 需要删除的数据大屏文件上传映射ID
     * @return 结果
     */
    @Override
    public int deleteDatavFileUploadByIds(String[] uploadIds)
    {
        return datavFileUploadMapper.deleteDatavFileUploadByIds(uploadIds);
    }

    /**
     * 删除数据大屏文件上传映射信息
     * 
     * @param uploadId 数据大屏文件上传映射ID
     * @return 结果
     */
    @Override
    public int deleteDatavFileUploadById(String uploadId)
    {
        return datavFileUploadMapper.deleteDatavFileUploadById(uploadId);
    }

    /**
     * 查询模板素材列表
     * @param datavFileUploadDto
     * @return
     */
    @Override
    public List<DatavFileUpload> selectFileTemplateList(DatavFileUploadDto datavFileUploadDto) {
        return datavFileUploadMapper.selectFileTemplateList(datavFileUploadDto);
    }
    /**
     * 更新图片素材查看、收藏、创建数
     * @param fileTemplateDto
     * @return
     * @throws IOException
     */
    @Override
    public int updateFileTemplate(FileTemplateDto fileTemplateDto) {
        return datavFileUploadMapper.updateFileTemplate(fileTemplateDto);
    }
    /**
     * 查询用户收藏列表
     * @param datavFileUploadDto
     * @return
     */
    @Override
    public List<DatavFileUpload> selectFileTemplateListByUser(DatavFileUploadDto datavFileUploadDto) {
        return datavFileUploadMapper.selectFileTemplateListByUser(datavFileUploadDto);
    }

    /**
     * 根据用户名称查询上传文件数量
     */
	@Override
	public Integer selectDatavFileUploadByUsername(DatavFileUpload datavFileUpload) {
		return datavFileUploadMapper.selectDatavFileUploadByUsername(datavFileUpload);
	}
}
