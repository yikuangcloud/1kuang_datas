package com.ruoyi.project.datav.service;

import com.ruoyi.project.datav.domain.DatavDemandQuestion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 需求问卷Service接口
 *
 * @author ruoyi
 * @date 2022-07-20
 */
public interface IDatavDemandQuestionService
{
    /**
     * 查询需求问卷
     *
     * @param id 需求问卷ID
     * @return 需求问卷
     */
    public DatavDemandQuestion selectDatavDemandQuestionById(Long id);

    /**
     * 查询需求问卷列表
     *
     * @param datavDemandQuestion 需求问卷
     * @return 需求问卷集合
     */
    public List<DatavDemandQuestion> selectDatavDemandQuestionList(DatavDemandQuestion datavDemandQuestion);

    /**
     * 新增需求问卷
     *
     * @param datavDemandQuestion 需求问卷
     * @return 结果
     */
    public int insertDatavDemandQuestion(DatavDemandQuestion datavDemandQuestion);

    /**
     * 修改需求问卷
     *
     * @param datavDemandQuestion 需求问卷
     * @return 结果
     */
    public int updateDatavDemandQuestion(DatavDemandQuestion datavDemandQuestion);

    /**
     * 批量删除需求问卷
     *
     * @param ids 需要删除的需求问卷ID
     * @return 结果
     */
    public int deleteDatavDemandQuestionByIds(Long[] ids);

    /**
     * 删除需求问卷信息
     *
     * @param id 需求问卷ID
     * @return 结果
     */
    public int deleteDatavDemandQuestionById(Long id);

    /**
     * 获取模板类型统计数据
     *
     * @param
     * @return 结果
     */
    public List<HashMap> getTemplateTypeCount();

    /**
     * 获取模板需求统计数据
     *
     * @param
     * @return 结果
     */
    public List<HashMap> getTemplateDemandCount();

    /**
     * 获取岗位类型统计数据
     *
     * @param
     * @return 结果
     */
    public List<HashMap> getPostTypeCount();

    /**
     * 获取岗位类型-模板需求统计数据
     *
     * @param
     * @return 结果
     */
    public ArrayList getpostAndDemandCount();
}
