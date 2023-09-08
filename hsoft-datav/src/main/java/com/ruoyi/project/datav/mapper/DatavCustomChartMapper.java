package com.ruoyi.project.datav.mapper;

import com.ruoyi.project.datav.domain.DatavCustomChart;

import java.util.List;

/**
 * 自定义组件库Mapper接口
 * 
 * @author 刘佳男【风清扬】
 * @date 2021-02-08
 */
public interface DatavCustomChartMapper 
{
    /**
     * 查询自定义组件库
     * 
     * @param id 自定义组件库ID
     * @return 自定义组件库
     */
    public DatavCustomChart selectDatavCustomChartById(Integer id);

    /**
     * 查询自定义组件库列表
     * 
     * @param datavCustomChart 自定义组件库
     * @return 自定义组件库集合
     */
    public List<DatavCustomChart> selectDatavCustomChartList(DatavCustomChart datavCustomChart);

    /**
     * 新增自定义组件库
     * 
     * @param datavCustomChart 自定义组件库
     * @return 结果
     */
    public int insertDatavCustomChart(DatavCustomChart datavCustomChart);

    /**
     * 修改自定义组件库
     * 
     * @param datavCustomChart 自定义组件库
     * @return 结果
     */
    public int updateDatavCustomChart(DatavCustomChart datavCustomChart);

    /**
     * 删除自定义组件库
     * 
     * @param id 自定义组件库ID
     * @return 结果
     */
    public int deleteDatavCustomChartById(Integer id);

    /**
     * 批量删除自定义组件库
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDatavCustomChartByIds(Integer[] ids);
    /**
     * 根据用户名查询自定义组件数
     * @param datavCustomChart
     * @return
     */
	public Integer selectDatavCustomChartByUsername(DatavCustomChart datavCustomChart);
}
