package com.ruoyi.project.datav.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.datav.domain.DatavDemandQuestion;
import com.ruoyi.project.datav.mapper.DatavDemandQuestionMapper;
import com.ruoyi.project.datav.service.IDatavDemandQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: DatavDemandQuestionServiceImpl
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 王莹
 * @Date: 2022/7/20 16:34
 */
@Service
public class DatavDemandQuestionServiceImpl implements IDatavDemandQuestionService
{
    @Autowired
    private DatavDemandQuestionMapper datavDemandQuestionMapper;

    /**
     * 查询需求问卷
     *
     * @param id 需求问卷ID
     * @return 需求问卷
     */
    @Override
    public DatavDemandQuestion selectDatavDemandQuestionById(Long id)
    {
        return datavDemandQuestionMapper.selectDatavDemandQuestionById(id);
    }

    /**
     * 查询需求问卷列表
     *
     * @param datavDemandQuestion 需求问卷
     * @return 需求问卷
     */
    @Override
    public List<DatavDemandQuestion> selectDatavDemandQuestionList(DatavDemandQuestion datavDemandQuestion)
    {
        return datavDemandQuestionMapper.selectDatavDemandQuestionList(datavDemandQuestion);
    }

    /**
     * 新增需求问卷
     *
     * @param datavDemandQuestion 需求问卷
     * @return 结果
     */
    @Override
    public int insertDatavDemandQuestion(DatavDemandQuestion datavDemandQuestion)
    {
        datavDemandQuestion.setCreateBy(SecurityUtils.getUsername());
        datavDemandQuestion.setCreateTime(DateUtils.getNowDate());
        return datavDemandQuestionMapper.insertDatavDemandQuestion(datavDemandQuestion);
    }

    /**
     * 修改需求问卷
     *
     * @param datavDemandQuestion 需求问卷
     * @return 结果
     */
    @Override
    public int updateDatavDemandQuestion(DatavDemandQuestion datavDemandQuestion)
    {
        return datavDemandQuestionMapper.updateDatavDemandQuestion(datavDemandQuestion);
    }

    /**
     * 批量删除需求问卷
     *
     * @param ids 需要删除的需求问卷ID
     * @return 结果
     */
    @Override
    public int deleteDatavDemandQuestionByIds(Long[] ids)
    {
        return datavDemandQuestionMapper.deleteDatavDemandQuestionByIds(ids);
    }

    /**
     * 删除需求问卷信息
     *
     * @param id 需求问卷ID
     * @return 结果
     */
    @Override
    public int deleteDatavDemandQuestionById(Long id)
    {
        return datavDemandQuestionMapper.deleteDatavDemandQuestionById(id);
    }

    /**
     * 获取模板类型统计数据
     *
     * @param
     * @return 结果
     */
    public List<HashMap> getTemplateTypeCount(){
        List<HashMap> resultList = new ArrayList<>();
        resultList = datavDemandQuestionMapper.getTemplateTypeCount();
//        for (Map.Entry<String, Integer> entry : res.entrySet()) {
//            HashMap<String,Object> map = new HashMap<>();
//            map.put("name",entry.getKey());
//            map.put("value",entry.getValue());
//            resultList.add(map);
//        }
        return  resultList;
    }

    /**
     * 获取模板需求统计数据
     *
     * @param
     * @return 结果
     */
    public List<HashMap> getTemplateDemandCount() {
        List<HashMap> resultList = new ArrayList<>();
//        HashMap<String,Integer> res = datavDemandQuestionMapper.getTemplateTypeCount().get(0);
        resultList =  datavDemandQuestionMapper.getTemplateDemandCount();
        return resultList;
    }

    /**
     * 获取岗位类型统计数据
     *
     * @param
     * @return 结果
     */
    public List<HashMap> getPostTypeCount() {
        List<HashMap> resultList = new ArrayList<>();
//        HashMap<String,Integer> res = datavDemandQuestionMapper.getTemplateTypeCount().get(0);
        resultList =  datavDemandQuestionMapper.getPostTypeCount();
        return resultList;
    }

    /**
     * 获取岗位类型-模板需求统计数据
     *
     * @param
     * @return 结果
     */
    public ArrayList getpostAndDemandCount(){
        ArrayList res = new ArrayList<>();
        List<HashMap> resultList = datavDemandQuestionMapper.getpostAndDemandCount();
        for(HashMap m : resultList){
            ArrayList item = new ArrayList();
            item.add(0,m.get("xAxis"));
            item.add(1,m.get("yAxis"));
            item.add(2,m.get("value"));
            res.add(item);
        }
        return res;
    }
}
