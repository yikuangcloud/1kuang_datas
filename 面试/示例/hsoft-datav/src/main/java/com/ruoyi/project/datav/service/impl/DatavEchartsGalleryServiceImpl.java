package com.ruoyi.project.datav.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.datav.domain.DatavEchartsGallery;
import com.ruoyi.project.datav.domain.DatavEchartsGalleryDto;
import com.ruoyi.project.datav.domain.DatavUserEcharts;
import com.ruoyi.project.datav.mapper.DatavEchartsGalleryMapper;
import com.ruoyi.project.datav.service.IDatavEchartsGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: DatavEchartsGalleryServiceImpl
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 王莹
 * @Date: 2022/7/27 15:31
 */
@Service
public class DatavEchartsGalleryServiceImpl implements IDatavEchartsGalleryService
{
    @Autowired
    private DatavEchartsGalleryMapper datavEchartsGalleryMapper;

    /**
     * 查询echarts素材
     *
     * @param id echarts素材ID
     * @return echarts素材
     */
    @Override
    public DatavEchartsGallery selectDatavEchartsGalleryById(Long id)
    {
        return datavEchartsGalleryMapper.selectDatavEchartsGalleryById(id);
    }

    /**
     * 查询echarts素材列表
     *
     * @param datavEchartsGalleryDto echarts素材
     * @return echarts素材
     */
    @Override
    public List<DatavEchartsGallery> selectDatavEchartsGalleryList(DatavEchartsGalleryDto datavEchartsGalleryDto)
    {
        return datavEchartsGalleryMapper.selectDatavEchartsGalleryList(datavEchartsGalleryDto);
    }

    /**
     * 查询echarts素材收藏列表
     *
     * @param datavEchartsGalleryDto echarts素材
     * @return echarts素材收藏集合
     */
    public List<DatavEchartsGallery> selectDatavEchartsGalleryListByStar(DatavEchartsGalleryDto datavEchartsGalleryDto){
        return datavEchartsGalleryMapper.selectDatavEchartsGalleryListByStar(datavEchartsGalleryDto);
    }

    /**
     * 新增echarts素材
     *
     * @param datavEchartsGallery echarts素材
     * @return 结果
     */
    @Override
    public int insertDatavEchartsGallery(DatavEchartsGallery datavEchartsGallery)
    {
        datavEchartsGallery.setCreateTime(DateUtils.getNowDate());
        return datavEchartsGalleryMapper.insertDatavEchartsGallery(datavEchartsGallery);
    }

    /**
     * 修改echarts素材
     *
     * @param datavEchartsGallery echarts素材
     * @return 结果
     */
    @Override
    public int updateDatavEchartsGallery(DatavEchartsGallery datavEchartsGallery)
    {
        return datavEchartsGalleryMapper.updateDatavEchartsGallery(datavEchartsGallery);
    }

    /**
     * 批量删除echarts素材
     *
     * @param ids 需要删除的echarts素材ID
     * @return 结果
     */
    @Override
    public int deleteDatavEchartsGalleryByIds(Long[] ids)
    {
        return datavEchartsGalleryMapper.deleteDatavEchartsGalleryByIds(ids);
    }

    /**
     * 删除echarts素材信息
     *
     * @param datavEchartsGallery echarts素材ID
     * @return 结果
     */
    @Override
    public int deleteDatavEchartsGalleryById(DatavEchartsGallery datavEchartsGallery)
    {
        return datavEchartsGalleryMapper.deleteDatavEchartsGalleryById(datavEchartsGallery);
    }

    /**
     * 收藏+1
     * @param datavEchartsGallery
     * @return
     */
    @Override
    public int starIncrease(DatavEchartsGallery datavEchartsGallery) {

        return datavEchartsGalleryMapper.starIncrease(datavEchartsGallery);
    }

    /**
     * 收藏-1
     * @param datavEchartsGallery
     * @return
     */
    @Override
    public int starDecrease(DatavEchartsGallery datavEchartsGallery) {
        return datavEchartsGalleryMapper.starDecrease(datavEchartsGallery);
    }

    /**
     * 查看+1
     * @param datavEchartsGallery
     * @return
     */
    @Override
    public int viewIncrease(DatavEchartsGallery datavEchartsGallery) {
        return datavEchartsGalleryMapper.viewIncrease(datavEchartsGallery);
    }
}
