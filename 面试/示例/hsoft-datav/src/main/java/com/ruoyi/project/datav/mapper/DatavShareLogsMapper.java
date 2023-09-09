package com.ruoyi.project.datav.mapper;

import com.ruoyi.project.datav.domain.DatavShareLogs;
/**
 * 数据大屏分享模板日志Mapper接口
 * @author wangying
 * @date 2021-02-08
 */
public interface DatavShareLogsMapper {
	
	/**
     * 新增分享日志
     * 
     * @param datavShareLogs 分享日志
     * @return 结果
     */
	public int insertDatavShareLogs(DatavShareLogs datavShareLogs);
	/**
	 * 通过用户名称查询分享模板列表
	 * @param datavShareLogs
	 * @return
	 */
	public int selectShareLogsByUsername(DatavShareLogs datavShareLogs);

}
