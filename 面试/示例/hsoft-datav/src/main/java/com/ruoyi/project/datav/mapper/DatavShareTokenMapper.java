package com.ruoyi.project.datav.mapper;

import com.ruoyi.project.datav.domain.ShareTokenLog;

import java.util.List;

public interface DatavShareTokenMapper {

	/**
	 * 新增记录
	 * @param shareTokenLog
	 * @return
	 */
	public void addTokenLog(ShareTokenLog shareTokenLog);
	
	/**
	 * 根据id查询信息
	 * @param id
	 * @return
	 */
	public ShareTokenLog findById(String id);

	/**
	 * 获取列表
	 * @param shareTokenLog
	 * @return List<ShareTokenLog>
	 */
	public List<ShareTokenLog> getTokenList(ShareTokenLog shareTokenLog);

	/**
	 * 更新记录
	 * @param id
	 * @return void
	 */
	public int updateTokenLog(ShareTokenLog shareTokenLog);
}
