package com.ruoyi.project.datav.service;

import java.util.List;

import com.ruoyi.project.datav.domain.DatavSourse;

/**
 * 
 * @author 张馨月
 * @Description: 数据源管理
 *
 */

public interface DatavSourseService {

	/**
	 * 查询数据源信息列表
	 * @param datavSourse
	 * @return
	 */
	public List<DatavSourse> selectDatavSourseList(DatavSourse datavSourse);
	
	/**
	 * 添加数据源信息
	 * @param datavSourse
	 * @return
	 */
	public int insertDatavSourse(DatavSourse datavSourse);
	
	/**
	 * 修改某一条数据源信息
	 * @param datavSourse
	 * @return
	 */
	public int updateDatavSourse(DatavSourse datavSourse);
	
	/**
	 * 删除某一条数据源信息
	 * @param datavSourse
	 * @return
	 */
	public int deleteDatavSourseById(String[] id);
	
	/**
	 * 根据编号获取详细信息
	 * @param id
	 * @return
	 */
	public DatavSourse selectSourseById(String id);
	
	/**
	 * 批量删除数据源信息
	 * @param ids
	 * @return
	 */
	public int deleteSourseByIds(String[] ids);

	
}
