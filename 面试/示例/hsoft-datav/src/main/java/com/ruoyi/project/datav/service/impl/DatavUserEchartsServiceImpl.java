package com.ruoyi.project.datav.service.impl;

import java.util.List;

import com.ruoyi.project.datav.domain.DatavUserEcharts;
import com.ruoyi.project.datav.mapper.DatavUserEchartsMapper;
import com.ruoyi.project.datav.service.IDatavUserEchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: DatavUserEchartsServiceImpl
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 王莹
 * @Date: 2022/7/28 16:18
 */
@Service
public class DatavUserEchartsServiceImpl implements IDatavUserEchartsService
{
    @Autowired
    private DatavUserEchartsMapper datavUserEchartsMapper;

    /**
     * 查询用户收藏echarts素材关联
     *
     * @param id 用户收藏echarts素材关联ID
     * @return 用户收藏echarts素材关联
     */
    @Override
    public DatavUserEcharts selectDatavUserEchartsById(Long id)
    {
        return datavUserEchartsMapper.selectDatavUserEchartsById(id);
    }

    /**
     * 查询用户收藏echarts素材关联列表
     *
     * @param datavUserEcharts 用户收藏echarts素材关联
     * @return 用户收藏echarts素材关联
     */
    @Override
    public List<DatavUserEcharts> selectDatavUserEchartsList(DatavUserEcharts datavUserEcharts)
    {
        return datavUserEchartsMapper.selectDatavUserEchartsList(datavUserEcharts);
    }

    /**
     * 新增用户收藏echarts素材关联
     *
     * @param datavUserEcharts 用户收藏echarts素材关联
     * @return 结果
     */
    @Override
    public int insertDatavUserEcharts(DatavUserEcharts datavUserEcharts)
    {
        return datavUserEchartsMapper.insertDatavUserEcharts(datavUserEcharts);
    }

    /**
     * 修改用户收藏echarts素材关联
     *
     * @param datavUserEcharts 用户收藏echarts素材关联
     * @return 结果
     */
    @Override
    public int updateDatavUserEcharts(DatavUserEcharts datavUserEcharts)
    {
        return datavUserEchartsMapper.updateDatavUserEcharts(datavUserEcharts);
    }

    /**
     * 批量删除用户收藏echarts素材关联
     *
     * @param ids 需要删除的用户收藏echarts素材关联ID
     * @return 结果
     */
    @Override
    public int deleteDatavUserEchartsByIds(Long[] ids)
    {
        return datavUserEchartsMapper.deleteDatavUserEchartsByIds(ids);
    }

    /**
     * 删除用户收藏echarts素材关联信息
     *
     * @param id 用户收藏echarts素材关联ID
     * @return 结果
     */
    @Override
    public int deleteDatavUserEchartsById(Long id)
    {
        return datavUserEchartsMapper.deleteDatavUserEchartsById(id);
    }

    /**
     * 删除用户收藏表
     * @param datavUserEcharts
     * @return
     */
    @Override
    public int delDatavUserEcharts(DatavUserEcharts datavUserEcharts) {
        return datavUserEchartsMapper.delDatavUserEcharts(datavUserEcharts);
    }

}

