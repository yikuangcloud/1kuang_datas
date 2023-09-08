package com.ruoyi.project.datav.mapper;

import com.ruoyi.project.datav.domain.DatavClassification;

import java.util.List;

/**
 * 数据大屏模板分类Mapper接口
 * 
 * @author 刘佳男【风清扬】
 * @date 2021-01-30
 */
public interface DatavClassificationMapper 
{
    /**
     * 查询数据大屏模板分类
     * 
     * @param id 数据大屏模板分类ID
     * @return 数据大屏模板分类
     */
    public DatavClassification selectDatavClassificationById(String id);

    /**
     * 查询数据大屏模板分类列表
     * 
     * @param datavClassification 数据大屏模板分类
     * @return 数据大屏模板分类集合
     */
    public List<DatavClassification> selectDatavClassificationList(DatavClassification datavClassification);

    /**
     * 新增数据大屏模板分类
     * 
     * @param datavClassification 数据大屏模板分类
     * @return 结果
     */
    public int insertDatavClassification(DatavClassification datavClassification);

    /**
     * 修改数据大屏模板分类
     * 
     * @param datavClassification 数据大屏模板分类
     * @return 结果
     */
    public int updateDatavClassification(DatavClassification datavClassification);

    /**
     * 删除数据大屏模板分类
     * 
     * @param id 数据大屏模板分类ID
     * @return 结果
     */
    public int deleteDatavClassificationById(String id);

    /**
     * 批量删除数据大屏模板分类
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDatavClassificationByIds(String[] ids);
}