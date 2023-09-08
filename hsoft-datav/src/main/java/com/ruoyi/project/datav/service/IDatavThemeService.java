package com.ruoyi.project.datav.service;

import com.ruoyi.project.datav.domain.DatavTheme;

import java.util.List;

/**
 * 数据大屏自定义主题配置Service接口
 * 
 * @author 刘佳男【风清扬】
 * @date 2021-01-29
 */
public interface IDatavThemeService 
{
    /**
     * 查询数据大屏自定义主题配置
     * 
     * @param themeId 数据大屏自定义主题配置ID
     * @return 数据大屏自定义主题配置
     */
    public DatavTheme selectDatavThemeById(String themeId);

    /**
     * 查询数据大屏自定义主题配置列表
     * 
     * @param datavTheme 数据大屏自定义主题配置
     * @return 数据大屏自定义主题配置集合
     */
    public List<DatavTheme> selectDatavThemeList(DatavTheme datavTheme);

    /**
     * 新增数据大屏自定义主题配置
     * 
     * @param datavTheme 数据大屏自定义主题配置
     * @return 结果
     */
    public int insertDatavTheme(DatavTheme datavTheme);

    /**
     * 修改数据大屏自定义主题配置
     * 
     * @param datavTheme 数据大屏自定义主题配置
     * @return 结果
     */
    public int updateDatavTheme(DatavTheme datavTheme);

    /**
     * 批量删除数据大屏自定义主题配置
     * 
     * @param themeIds 需要删除的数据大屏自定义主题配置ID
     * @return 结果
     */
    public int deleteDatavThemeByIds(String[] themeIds);

    /**
     * 删除数据大屏自定义主题配置信息
     * 
     * @param themeId 数据大屏自定义主题配置ID
     * @return 结果
     */
    public int deleteDatavThemeById(String themeId);
}
