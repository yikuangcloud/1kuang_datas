package com.ruoyi.project.datav.service;
import com.ruoyi.project.datav.domain.DatavEchartsGallery;
import com.ruoyi.project.datav.domain.DatavEchartsGalleryDto;
import com.ruoyi.project.datav.domain.DatavUserEcharts;

import java.util.List;

/**
 * echarts素材Service接口
 *
 * @author ruoyi
 * @date 2022-07-27
 */
public interface IDatavEchartsGalleryService
{
    /**
     * 查询echarts素材
     *
     * @param id echarts素材ID
     * @return echarts素材
     */
    public DatavEchartsGallery selectDatavEchartsGalleryById(Long id);

    /**
     * 查询echarts素材列表
     *
     * @param datavEchartsGalleryDto echarts素材
     * @return echarts素材集合
     */
    public List<DatavEchartsGallery> selectDatavEchartsGalleryList(DatavEchartsGalleryDto datavEchartsGalleryDto);

    /**
     * 查询echarts素材收藏列表
     *
     * @param datavEchartsGalleryDto echarts素材
     * @return echarts素材收藏集合
     */
    public List<DatavEchartsGallery> selectDatavEchartsGalleryListByStar(DatavEchartsGalleryDto datavEchartsGalleryDto);

    /**
     * 新增echarts素材
     *
     * @param datavEchartsGallery echarts素材
     * @return 结果
     */
    public int insertDatavEchartsGallery(DatavEchartsGallery datavEchartsGallery);

    /**
     * 修改echarts素材
     *
     * @param datavEchartsGallery echarts素材
     * @return 结果
     */
    public int updateDatavEchartsGallery(DatavEchartsGallery datavEchartsGallery);

    /**
     * 批量删除echarts素材
     *
     * @param ids 需要删除的echarts素材ID
     * @return 结果
     */
    public int deleteDatavEchartsGalleryByIds(Long[] ids);

    /**
     * 删除echarts素材信息
     *
     * @param id echarts素材ID
     * @return 结果
     */
    public int deleteDatavEchartsGalleryById(DatavEchartsGallery datavEchartsGallery);
    /**
     * 收藏+1
     * @param datavEchartsGallery
     * @return
     */
    int starIncrease(DatavEchartsGallery datavEchartsGallery);

    /**
     * 收藏-1
     * @param datavEchartsGallery
     * @return
     */
    int starDecrease(DatavEchartsGallery datavEchartsGallery);

    /**
     * 查看+1
     * @param datavEchartsGallery
     * @return
     */
    int viewIncrease(DatavEchartsGallery datavEchartsGallery);
}
