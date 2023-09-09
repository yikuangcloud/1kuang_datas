package com.ruoyi.project.datav.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.datav.domain.DatavFormSource;
import com.ruoyi.project.datav.mapper.DatavFormSourceMapper;
import com.ruoyi.project.datav.service.IDatavFormSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 格文件数据源Service业务层处理
 * 
 * @author 风清扬【刘佳男】
 * @date 2021-06-09
 */
@Service
public class DatavFormSourceServiceImpl implements IDatavFormSourceService 
{
    @Autowired
    private DatavFormSourceMapper datavFormSourceMapper;

    /**
     * 查询格文件数据源
     * 
     * @param id 格文件数据源ID
     * @return 格文件数据源
     */
    @Override
    public DatavFormSource selectDatavFormSourceById(Long id)
    {
        return datavFormSourceMapper.selectDatavFormSourceById(id);
    }

    /**
     * 查询格文件数据源列表
     * 
     * @param datavFormSource 格文件数据源
     * @return 格文件数据源
     */
    @Override
    public List<DatavFormSource> selectDatavFormSourceList(DatavFormSource datavFormSource)
    {
        return datavFormSourceMapper.selectDatavFormSourceList(datavFormSource);
    }

    /**
     * 新增格文件数据源
     * 
     * @param datavFormSource 格文件数据源
     * @return 结果
     */
    @Override
    public int insertDatavFormSource(DatavFormSource datavFormSource)
    {
        datavFormSource.setCreateTime(DateUtils.getNowDate());
        return datavFormSourceMapper.insertDatavFormSource(datavFormSource);
    }

    /**
     * 修改格文件数据源
     * 
     * @param datavFormSource 格文件数据源
     * @return 结果
     */
    @Override
    public int updateDatavFormSource(DatavFormSource datavFormSource)
    {
        datavFormSource.setUpdateTime(DateUtils.getNowDate());
        return datavFormSourceMapper.updateDatavFormSource(datavFormSource);
    }

    /**
     * 批量删除格文件数据源
     * 
     * @param ids 需要删除的格文件数据源ID
     * @return 结果
     */
    @Override
    public int deleteDatavFormSourceByIds(Long[] ids)
    {
        return datavFormSourceMapper.deleteDatavFormSourceByIds(ids);
    }

    /**
     * 删除格文件数据源信息
     * 
     * @param id 格文件数据源ID
     * @return 结果
     */
    @Override
    public int deleteDatavFormSourceById(Long id)
    {
        return datavFormSourceMapper.deleteDatavFormSourceById(id);
    }
}
