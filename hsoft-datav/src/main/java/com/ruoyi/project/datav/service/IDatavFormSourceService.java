package com.ruoyi.project.datav.service;

import com.ruoyi.project.datav.domain.DatavFormSource;

import java.util.List;

/**
 * 格文件数据源Service接口
 * 
 * @author 风清扬【刘佳男】
 * @date 2021-06-09
 */
public interface IDatavFormSourceService 
{
    /**
     * 查询格文件数据源
     * 
     * @param id 格文件数据源ID
     * @return 格文件数据源
     */
    public DatavFormSource selectDatavFormSourceById(Long id);

    /**
     * 查询格文件数据源列表
     * 
     * @param datavFormSource 格文件数据源
     * @return 格文件数据源集合
     */
    public List<DatavFormSource> selectDatavFormSourceList(DatavFormSource datavFormSource);

    /**
     * 新增格文件数据源
     * 
     * @param datavFormSource 格文件数据源
     * @return 结果
     */
    public int insertDatavFormSource(DatavFormSource datavFormSource);

    /**
     * 修改格文件数据源
     * 
     * @param datavFormSource 格文件数据源
     * @return 结果
     */
    public int updateDatavFormSource(DatavFormSource datavFormSource);

    /**
     * 批量删除格文件数据源
     * 
     * @param ids 需要删除的格文件数据源ID
     * @return 结果
     */
    public int deleteDatavFormSourceByIds(Long[] ids);

    /**
     * 删除格文件数据源信息
     * 
     * @param id 格文件数据源ID
     * @return 结果
     */
    public int deleteDatavFormSourceById(Long id);
}
