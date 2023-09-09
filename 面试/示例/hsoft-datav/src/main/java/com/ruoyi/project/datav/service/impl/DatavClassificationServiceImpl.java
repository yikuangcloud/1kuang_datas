package com.ruoyi.project.datav.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.project.datav.domain.DatavClassification;
import com.ruoyi.project.datav.mapper.DatavClassificationMapper;
import com.ruoyi.project.datav.service.IDatavClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据大屏模板分类Service业务层处理
 * 
 * @author 刘佳男【风清扬】
 * @date 2021-01-30
 */
@Service
public class DatavClassificationServiceImpl implements IDatavClassificationService 
{
    @Autowired
    private DatavClassificationMapper datavClassificationMapper;

    /**
     * 查询数据大屏模板分类
     * 
     * @param id 数据大屏模板分类ID
     * @return 数据大屏模板分类
     */
    @Override
    public DatavClassification selectDatavClassificationById(String id)
    {
        return datavClassificationMapper.selectDatavClassificationById(id);
    }

    /**
     * 查询数据大屏模板分类列表
     * 
     * @param datavClassification 数据大屏模板分类
     * @return 数据大屏模板分类
     */
    @Override
    public List<DatavClassification> selectDatavClassificationList(DatavClassification datavClassification)
    {
        return datavClassificationMapper.selectDatavClassificationList(datavClassification);
    }

    /**
     * 新增数据大屏模板分类
     * 
     * @param datavClassification 数据大屏模板分类
     * @return 结果
     */
    @Override
    public int insertDatavClassification(DatavClassification datavClassification)
    {
        datavClassification.setCreateTime(DateUtils.getNowDate());
        return datavClassificationMapper.insertDatavClassification(datavClassification);
    }

    /**
     * 修改数据大屏模板分类
     * 
     * @param datavClassification 数据大屏模板分类
     * @return 结果
     */
    @Override
    public int updateDatavClassification(DatavClassification datavClassification)
    {
        datavClassification.setUpdateTime(DateUtils.getNowDate());
        return datavClassificationMapper.updateDatavClassification(datavClassification);
    }

    /**
     * 批量删除数据大屏模板分类
     * 
     * @param ids 需要删除的数据大屏模板分类ID
     * @return 结果
     */
    @Override
    public int deleteDatavClassificationByIds(String[] ids)
    {
        return datavClassificationMapper.deleteDatavClassificationByIds(ids);
    }

    /**
     * 删除数据大屏模板分类信息
     * 
     * @param id 数据大屏模板分类ID
     * @return 结果
     */
    @Override
    public int deleteDatavClassificationById(String id)
    {
        return datavClassificationMapper.deleteDatavClassificationById(id);
    }

    @Override
    public List<DatavClassification> buildClassificationsTree(List<DatavClassification> datavClassifications) {
        List<DatavClassification> returnList = new ArrayList<DatavClassification>();
        List<Long> tempList = new ArrayList<Long>();
        for (DatavClassification dc : datavClassifications)
        {
            tempList.add(dc.getId());
        }
        for (Iterator<DatavClassification> iterator = datavClassifications.iterator(); iterator.hasNext();)
        {
            DatavClassification dept = (DatavClassification) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(dept.getParentId()))
            {
                recursionFn(datavClassifications, dept);
                returnList.add(dept);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = datavClassifications;
        }
        return returnList;
    }

    @Override
    public List<TreeSelect> buildTreeSelect(List<DatavClassification> datavClassifications) {
        List<DatavClassification> classTrees = buildClassificationsTree(datavClassifications);
        return classTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<DatavClassification> list, DatavClassification t)
    {
        // 得到子节点列表
        List<DatavClassification> childList = getChildList(list, t);
        t.setChildren(childList);
        for (DatavClassification tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<DatavClassification> it = childList.iterator();
                while (it.hasNext())
                {
                    DatavClassification n = (DatavClassification) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<DatavClassification> getChildList(List<DatavClassification> list, DatavClassification t)
    {
        List<DatavClassification> tlist = new ArrayList<DatavClassification>();
        Iterator<DatavClassification> it = list.iterator();
        while (it.hasNext())
        {
            DatavClassification n = (DatavClassification) it.next();
            if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().longValue() == t.getId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<DatavClassification> list, DatavClassification t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
