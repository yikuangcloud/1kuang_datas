package com.ruoyi.project.datav.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.datav.domain.DatavCmsTemplateInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.datav.mapper.DatavCmsTemplateInfoMapper;
import com.ruoyi.project.datav.domain.DatavCmsTemplateInfo;
import com.ruoyi.project.datav.service.IDatavCmsTemplateInfoService;

/**
 * cms模板Service业务层处理
 * 
 * @author hanchao
 * @date 2022-07-12
 */
@Service
public class DatavCmsTemplateInfoServiceImpl implements IDatavCmsTemplateInfoService 
{
    @Autowired
    private DatavCmsTemplateInfoMapper datavCmsTemplateInfoMapper;

    /**
     * 查询cms模板
     * 
     * @param id cms模板ID
     * @return cms模板
     */
    @Override
    public DatavCmsTemplateInfo selectDatavCmsTemplateInfoById(Long id)
    {
        return datavCmsTemplateInfoMapper.selectDatavCmsTemplateInfoById(id);
    }

    /**
     * 查询cms模板列表
     * 
     * @param
     * @return cms模板
     */
    @Override
    public List<DatavCmsTemplateInfo> selectDatavCmsTemplateInfoList(DatavCmsTemplateInfoDto datavCmsTemplateInfoDto)
    {
        return datavCmsTemplateInfoMapper.selectDatavCmsTemplateInfoList(datavCmsTemplateInfoDto);
    }
    /**
     * 查询用户收藏cms模板列表
     *
     * @param datavCmsTemplateInfoDto cms模板
     * @return cms模板集合
     */
    @Override
    public List<DatavCmsTemplateInfo> selectDatavCmsTemplateInfoListByUser(DatavCmsTemplateInfoDto datavCmsTemplateInfoDto) {
        return datavCmsTemplateInfoMapper.selectDatavCmsTemplateInfoListByUser(datavCmsTemplateInfoDto);
    }

    /**
     * 获取热门cms模板列表
     *
     * @param datavCmsTemplateInfo cms模板
     * @return cms模板集合
     */
    public List<DatavCmsTemplateInfo> selectHotTemplateList(DatavCmsTemplateInfo datavCmsTemplateInfo)
    {
        return datavCmsTemplateInfoMapper.selectHotTemplateList(datavCmsTemplateInfo);
    }
    /**
     * 根据指定关键词模糊查询模板
     *
     * @param keyword 查询关键词
     * @return cms模板集合
     */
    public List<DatavCmsTemplateInfo> selectTemplateListByKeyword(String keyword){
        return datavCmsTemplateInfoMapper.selectTemplateListByKeyword(keyword);
    }

    /**
     * 新增cms模板
     * 
     * @param datavCmsTemplateInfo cms模板
     * @return 结果
     */
    @Override
    public int insertDatavCmsTemplateInfo(DatavCmsTemplateInfo datavCmsTemplateInfo)
    {
        return datavCmsTemplateInfoMapper.insertDatavCmsTemplateInfo(datavCmsTemplateInfo);
    }

    /**
     * 修改cms模板
     * 
     * @param datavCmsTemplateInfo cms模板
     * @return 结果
     */
    @Override
    public int updateDatavCmsTemplateInfo(DatavCmsTemplateInfo datavCmsTemplateInfo)
    {
        return datavCmsTemplateInfoMapper.updateDatavCmsTemplateInfo(datavCmsTemplateInfo);
    }

    /**
     * 修改cms模板
     *
     * @param datavCmsTemplateInfo cms模板
     * @return 结果
     */
    @Override
    public int releaseCmsTemplate(DatavCmsTemplateInfo datavCmsTemplateInfo)
    {
        return datavCmsTemplateInfoMapper.releaseCmsTemplate(datavCmsTemplateInfo);
    }

    /**
     * 批量删除cms模板
     * 
     * @param ids 需要删除的cms模板ID
     * @return 结果
     */
    @Override
    public int deleteDatavCmsTemplateInfoByIds(Long[] ids)
    {
        return datavCmsTemplateInfoMapper.deleteDatavCmsTemplateInfoByIds(ids);
    }

    /**
     * 删除cms模板信息
     * 
     * @param
     * @return 结果
     */
    @Override
    public int deleteDatavCmsTemplateInfoById(String screenId)
    {
        return datavCmsTemplateInfoMapper.deleteDatavCmsTemplateInfoById(screenId);
    }

    /**
     * 收藏数+1
     *
     * @param screenId 被收藏的模板id
     * @return 结果
     */
    public int starCountIncrease(String screenId){
        return datavCmsTemplateInfoMapper.starCountIncrease(screenId);
    }

    /**
     * 收藏数-1
     *
     * @param screenId 被收藏的模板id
     * @return 结果
     */
    public int starCountDecrease(String screenId){
        return datavCmsTemplateInfoMapper.starCountDecrease(screenId);
    }

    /**
     * 浏览数+1
     *
     * @param screenId 被收藏的模板id
     * @return 结果
     */
    public int viewCountIncrease(String screenId){
        return datavCmsTemplateInfoMapper.viewCountIncrease(screenId);
    }

    /**
     * 使用数+1
     *
     * @param screenId 被收藏的模板id
     * @return 结果
     */
    public int useCountIncrease(String screenId){
        return datavCmsTemplateInfoMapper.useCountIncrease(screenId);
    }
}
