package com.ruoyi.project.datav.service;

import java.util.List;
import com.ruoyi.project.datav.domain.DatavCmsTemplateInfo;
import com.ruoyi.project.datav.domain.DatavCmsTemplateInfoDto;

/**
 * cms模板Service接口
 * 
 * @author hanchao
 * @date 2022-07-12
 */
public interface IDatavCmsTemplateInfoService 
{
    /**
     * 查询cms模板
     * 
     * @param id cms模板ID
     * @return cms模板
     */
    public DatavCmsTemplateInfo selectDatavCmsTemplateInfoById(Long id);

    /**
     * 查询cms模板列表
     * 
     * @param datavCmsTemplateInfoDto cms模板
     * @return cms模板集合
     */
    public List<DatavCmsTemplateInfo> selectDatavCmsTemplateInfoList(DatavCmsTemplateInfoDto datavCmsTemplateInfoDto);

    /**
     * 查询用户收藏cms模板列表
     *
     * @param datavCmsTemplateInfoDto cms模板
     * @return cms模板集合
     */
    public List<DatavCmsTemplateInfo> selectDatavCmsTemplateInfoListByUser(DatavCmsTemplateInfoDto datavCmsTemplateInfoDto);

    /**
     * 获取热门cms模板列表
     *
     * @param datavCmsTemplateInfo cms模板
     * @return cms模板集合
     */
    public List<DatavCmsTemplateInfo> selectHotTemplateList(DatavCmsTemplateInfo datavCmsTemplateInfo);

    /**
     * 根据指定关键词模糊查询模板
     *
     * @param keyword 查询关键词
     * @return cms模板集合
     */
    public List<DatavCmsTemplateInfo> selectTemplateListByKeyword(String keyword);
    /**
     * 新增cms模板
     * 
     * @param datavCmsTemplateInfo cms模板
     * @return 结果
     */
    public int insertDatavCmsTemplateInfo(DatavCmsTemplateInfo datavCmsTemplateInfo);

    /**
     * 修改cms模板
     * 
     * @param datavCmsTemplateInfo cms模板
     * @return 结果
     */
    public int updateDatavCmsTemplateInfo(DatavCmsTemplateInfo datavCmsTemplateInfo);

    /**
     * 发布cms模板
     *
     * @param datavCmsTemplateInfo cms模板
     * @return 结果
     */
    public int releaseCmsTemplate(DatavCmsTemplateInfo datavCmsTemplateInfo);

    /**
     * 批量删除cms模板
     * 
     * @param ids 需要删除的cms模板ID
     * @return 结果
     */
    public int deleteDatavCmsTemplateInfoByIds(Long[] ids);

    /**
     * 删除cms模板信息
     * 
     * @param screenId cms模板ID
     * @return 结果
     */
    public int deleteDatavCmsTemplateInfoById(String screenId);

    /**
     * 收藏数+1
     *
     * @param screenId 被收藏的模板id
     * @return 结果
     */
    public int starCountIncrease(String screenId);

    /**
     * 收藏数-1
     *
     * @param screenId 被收藏的模板id
     * @return 结果
     */
    public int starCountDecrease(String screenId);

    /**
     * 浏览数+1
     *
     * @param screenId 被收藏的模板id
     * @return 结果
     */
    public int viewCountIncrease(String screenId);

    /**
     * 使用数+1
     *
     * @param screenId 被收藏的模板id
     * @return 结果
     */
    public int useCountIncrease(String screenId);
}
