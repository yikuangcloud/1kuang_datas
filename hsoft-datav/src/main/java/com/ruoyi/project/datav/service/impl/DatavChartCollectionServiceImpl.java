package com.ruoyi.project.datav.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.datav.domain.DatavChartCollection;
import com.ruoyi.project.datav.mapper.DatavChartCollectionMapper;
import com.ruoyi.project.datav.service.IDatavChartCollectionService;
/**
 * 收藏组件Service业务层处理
 * 
 * @author wangying
 * @date 2021-03-31
 */
@Service
public class DatavChartCollectionServiceImpl implements IDatavChartCollectionService {
	@Autowired
    private DatavChartCollectionMapper datavChartCollectionMapper;
	
	 /**
     * 查询收藏组件
     * 
     * @param id 收藏组件ID
     * @return 收藏组件
     */
	 
	@Override
	public DatavChartCollection selectDatavChartCollectionById(Integer id) {
		return datavChartCollectionMapper.selectDatavChartCollectionById(id);
	}
	/**
     * 查询收藏组件库列表
     * 
     * @param datavChartCollection 收藏组件
     * @return 收藏组件库集合
     */
	@Override
	public List<DatavChartCollection> selectDatavChartCollectionList(DatavChartCollection datavChartCollection) {
		return datavChartCollectionMapper.selectDatavChartCollectionList(datavChartCollection);
	}
	 /**
     * 新增收藏组件
     * 
     * @param datavChartCollection 收藏组件
     * @return 结果
     */
	@Override
	public int insertDatavChartCollection(DatavChartCollection datavChartCollection) {
		datavChartCollection.setCreateTime(DateUtils.getNowDate());
		return datavChartCollectionMapper.insertDatavChartCollection(datavChartCollection);
	}
	/**
	 * 删除收藏组件
	 * 
	 */
	@Override
	public int deleteDatavChartCollectionById(String collectionId) {
		return datavChartCollectionMapper.deleteDatavChartCollectionById(collectionId);
	}
	/**
	 * 根据用户名称查询收藏组件列表
	 */
	@Override
	public Integer selectDatavChartCollectionByUsername(DatavChartCollection datavChartCollection) {
		return datavChartCollectionMapper.selectDatavChartCollectionByUsername(datavChartCollection);
	}

}
