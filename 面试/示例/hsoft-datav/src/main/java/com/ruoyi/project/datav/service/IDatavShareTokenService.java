package com.ruoyi.project.datav.service;

import com.ruoyi.project.datav.domain.ShareTokenLog;

import java.util.List;

public interface IDatavShareTokenService {

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
	 * 查询列表
	 * @param
	 * @return
	 */
	 public List<ShareTokenLog> getTokenList(ShareTokenLog shareTokenLog);

	/**
	 * 更新
	 * @param
	 * @return
	 */
	public int updateTokenLog(ShareTokenLog shareTokenLog);
}
