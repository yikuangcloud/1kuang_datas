package com.ruoyi.project.datav.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.datav.domain.DatavCustomChart;
import com.ruoyi.project.datav.mapper.DatavCustomChartMapper;
import com.ruoyi.project.datav.service.IDatavCustomChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 自定义组件库Service业务层处理
 * 
 * @author 刘佳男【风清扬】
 * @date 2021-02-08
 */
@Service
public class DatavCustomChartServiceImpl implements IDatavCustomChartService 
{
    @Autowired
    private DatavCustomChartMapper datavCustomChartMapper;

    /**
     * 查询自定义组件库
     * 
     * @param id 自定义组件库ID
     * @return 自定义组件库
     */
    @Override
    public DatavCustomChart selectDatavCustomChartById(Integer id)
    {
        return datavCustomChartMapper.selectDatavCustomChartById(id);
    }

    /**
     * 查询自定义组件库列表
     * 
     * @param datavCustomChart 自定义组件库
     * @return 自定义组件库
     */
    @Override
    public List<DatavCustomChart> selectDatavCustomChartList(DatavCustomChart datavCustomChart)
    {
        return datavCustomChartMapper.selectDatavCustomChartList(datavCustomChart);
    }

    /**
     * 新增自定义组件库
     * 
     * @param datavCustomChart 自定义组件库
     * @return 结果
     */
    @Override
    public int insertDatavCustomChart(DatavCustomChart datavCustomChart)
    {
        datavCustomChart.setCreateTime(DateUtils.getNowDate());
        return datavCustomChartMapper.insertDatavCustomChart(datavCustomChart);
    }

    /**
     * 修改自定义组件库
     * 
     * @param datavCustomChart 自定义组件库
     * @return 结果
     */
    @Override
    public int updateDatavCustomChart(DatavCustomChart datavCustomChart)
    {
        datavCustomChart.setUpdateTime(DateUtils.getNowDate());
        return datavCustomChartMapper.updateDatavCustomChart(datavCustomChart);
    }

    /**
     * 批量删除自定义组件库
     * 
     * @param ids 需要删除的自定义组件库ID
     * @return 结果
     */
    @Override
    public int deleteDatavCustomChartByIds(Integer[] ids)
    {
        return datavCustomChartMapper.deleteDatavCustomChartByIds(ids);
    }

    /**
     * 删除自定义组件库信息
     * 
     * @param id 自定义组件库ID
     * @return 结果
     */
    @Override
    public int deleteDatavCustomChartById(Integer id)
    {
        return datavCustomChartMapper.deleteDatavCustomChartById(id);
    }
    /**
     * 根据用户名称查询自定义组件列表
     */
	@Override
	public Integer selectDatavCustomChartByUsername(DatavCustomChart datavCustomChart) {
		return datavCustomChartMapper.selectDatavCustomChartByUsername(datavCustomChart);
	}
}
