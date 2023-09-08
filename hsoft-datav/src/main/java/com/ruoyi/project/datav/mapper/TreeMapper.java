package com.ruoyi.project.datav.mapper;

import java.util.List;

import com.ruoyi.project.datav.domain.Carbon;
import com.ruoyi.project.datav.domain.TreeDto;

public interface TreeMapper {
	public List<Carbon> findList(TreeDto treeDto);
}
