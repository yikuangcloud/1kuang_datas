package com.ruoyi.project.datav.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.datav.domain.DatavTheme;
import com.ruoyi.project.datav.mapper.DatavThemeMapper;
import com.ruoyi.project.datav.service.IDatavThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据大屏自定义主题配置Service业务层处理
 * 
 * @author 刘佳男【风清扬】
 * @date 2021-01-29
 */
@Service
public class DatavThemeServiceImpl implements IDatavThemeService 
{
    @Autowired
    private DatavThemeMapper datavThemeMapper;

    /**
     * 查询数据大屏自定义主题配置
     * 
     * @param themeId 数据大屏自定义主题配置ID
     * @return 数据大屏自定义主题配置
     */
    @Override
    public DatavTheme selectDatavThemeById(String themeId)
    {
        return datavThemeMapper.selectDatavThemeById(themeId);
    }

    /**
     * 查询数据大屏自定义主题配置列表
     * 
     * @param datavTheme 数据大屏自定义主题配置
     * @return 数据大屏自定义主题配置
     */
    @Override
    public List<DatavTheme> selectDatavThemeList(DatavTheme datavTheme)
    {
        return datavThemeMapper.selectDatavThemeList(datavTheme);
    }

    /**
     * 新增数据大屏自定义主题配置
     * 
     * @param datavTheme 数据大屏自定义主题配置
     * @return 结果
     */
    @Override
    public int insertDatavTheme(DatavTheme datavTheme)
    {
        datavTheme.setCreateTime(DateUtils.getNowDate());
        return datavThemeMapper.insertDatavTheme(datavTheme);
    }

    /**
     * 修改数据大屏自定义主题配置
     * 
     * @param datavTheme 数据大屏自定义主题配置
     * @return 结果
     */
    @Override
    public int updateDatavTheme(DatavTheme datavTheme)
    {
        datavTheme.setUpdateTime(DateUtils.getNowDate());
        return datavThemeMapper.updateDatavTheme(datavTheme);
    }

    /**
     * 批量删除数据大屏自定义主题配置
     * 
     * @param themeIds 需要删除的数据大屏自定义主题配置ID
     * @return 结果
     */
    @Override
    public int deleteDatavThemeByIds(String[] themeIds)
    {
        return datavThemeMapper.deleteDatavThemeByIds(themeIds);
    }

    /**
     * 删除数据大屏自定义主题配置信息
     * 
     * @param themeId 数据大屏自定义主题配置ID
     * @return 结果
     */
    @Override
    public int deleteDatavThemeById(String themeId)
    {
        return datavThemeMapper.deleteDatavThemeById(themeId);
    }
}
