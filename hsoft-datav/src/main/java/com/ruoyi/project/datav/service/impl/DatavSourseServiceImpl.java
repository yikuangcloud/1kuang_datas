package com.ruoyi.project.datav.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.datav.domain.DatavSourse;
import com.ruoyi.project.datav.mapper.DatavSourseMapper;
import com.ruoyi.project.datav.service.DatavSourseService;

/**
 * 
 * @author 张馨月
 * @Description: 数据源管理
 *
 */
@Service
public class DatavSourseServiceImpl implements DatavSourseService {

	@Autowired
	private DatavSourseMapper mapper;
	
	@Override
	public List<DatavSourse> selectDatavSourseList(DatavSourse datavSourse) {
		return mapper.selectDatavSourseList(datavSourse);
	}

	@Override
	public int insertDatavSourse(DatavSourse datavSourse) {
		datavSourse.setCreateTime(DateUtils.getNowDate());
		datavSourse.setUpdateTime(DateUtils.getNowDate());
		return mapper.insertDatavSourse(datavSourse);
	}

	@Override
	public int updateDatavSourse(DatavSourse datavSourse) {
		datavSourse.setUpdateTime(DateUtils.getNowDate());
		return mapper.updateDatavSourse(datavSourse);
	}

	@Override
	public int deleteDatavSourseById(String[] id) {
		return mapper.deleteDatavSourseById(id);
	}

	@Override
	public DatavSourse selectSourseById(String id) {
		return mapper.selectSourseById(id);
	}

	@Override
	public int deleteSourseByIds(String[] ids) {
		return mapper.deleteSourseByIds(ids);
	}

}
