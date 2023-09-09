package com.ruoyi.project.datav.service;

import com.ruoyi.project.datav.domain.DatavShareLogs;
/**
 * 数据大屏分享模板日志Service接口
 * @author wangying
 * @date 2021-02-08
 */
public interface DatavShareLogsService {
	 /**
     * 新增分享日志
     * 
     * @param datavShareLogs 分享日志
     * @return 结果
     */
	public int insertShareLogs(DatavShareLogs datavShareLogs);
	/**
	 * 根据用户名称查询分享模板列表
	 * @return
	 */
	public int selectShareLogsByUsername(DatavShareLogs datavShareLogs);

}
