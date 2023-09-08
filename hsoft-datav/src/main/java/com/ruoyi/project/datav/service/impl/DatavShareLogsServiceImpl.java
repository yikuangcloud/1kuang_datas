package com.ruoyi.project.datav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.datav.domain.DatavShareLogs;
import com.ruoyi.project.datav.mapper.DatavShareLogsMapper;
import com.ruoyi.project.datav.service.DatavShareLogsService;
/**
 * 数据大屏分享模板日志Service业务层处理
 * @author wangying
 * @date 2021-02-08
 */
@Service
public class DatavShareLogsServiceImpl implements DatavShareLogsService {
	
	@Autowired
	private DatavShareLogsMapper datavShareLogsMapper;
	/**
     * 新增分享日志
     * 
     * @param datavShareLogs 分享日志
     * @return 结果
     */
	@Override
	public int insertShareLogs(DatavShareLogs datavShareLogs) {
		
		return datavShareLogsMapper.insertDatavShareLogs(datavShareLogs);
	}
	/**
	 * 通过用户名称查询分享模板列表
	 */
	@Override
	public int selectShareLogsByUsername(DatavShareLogs datavShareLogs) {
		return datavShareLogsMapper.selectShareLogsByUsername(datavShareLogs);
	}

}
