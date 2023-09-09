package com.ruoyi.project.datav.service;

import java.util.List;

import com.ruoyi.project.datav.domain.Carbon;
import com.ruoyi.project.datav.domain.TreeDto;

public interface TreeService {
	
	public List<Carbon> findList (TreeDto treeDto);

}
