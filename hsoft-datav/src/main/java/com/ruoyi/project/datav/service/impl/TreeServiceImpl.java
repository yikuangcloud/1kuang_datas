package com.ruoyi.project.datav.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.datav.domain.Carbon;
import com.ruoyi.project.datav.domain.TreeDto;
import com.ruoyi.project.datav.mapper.TreeMapper;
import com.ruoyi.project.datav.service.TreeService;
@Service
public class TreeServiceImpl implements TreeService {
	@Autowired
	private TreeMapper treeMapper;

	@Override
	public List<Carbon> findList(TreeDto treeDto) {
		return treeMapper.findList(treeDto);
	}

}
