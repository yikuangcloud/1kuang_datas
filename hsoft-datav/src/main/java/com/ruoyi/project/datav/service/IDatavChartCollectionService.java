package com.ruoyi.project.datav.service;

import java.util.List;

import com.ruoyi.project.datav.domain.DatavChartCollection;
/**
 * 收藏组件Service接口
 * 
 * @author wangying
 * @date 2021-03-31
 */
public interface IDatavChartCollectionService {
	/**
     * 查询收藏组件
     * 
     * @param id 收藏组件ID
     * @return 收藏组件
     */
    public DatavChartCollection selectDatavChartCollectionById(Integer id);

    /**
     * 查询收藏组件列表
     * 
     * @param datavChartCollection 收藏组件
     * @return 收藏组件库集合
     */
    public List<DatavChartCollection> selectDatavChartCollectionList(DatavChartCollection datavChartCollection);

    /**
     * 新增收藏组件
     * 
     * @param datavChartCollection 收藏组件
     * @return 结果
     */
    public int insertDatavChartCollection(DatavChartCollection datavChartCollection);
    /**
     * 删除收藏组件
     * @param collectionId
     * @return
     */
	public int deleteDatavChartCollectionById(String collectionId);
	/**
	 * 根据用户名称查询收藏组件列表
	 * @param datavChartCollection
	 * @return
	 */
	public Integer selectDatavChartCollectionByUsername(DatavChartCollection datavChartCollection);
}
