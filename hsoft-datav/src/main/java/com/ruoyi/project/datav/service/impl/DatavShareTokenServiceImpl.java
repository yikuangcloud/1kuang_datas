package com.ruoyi.project.datav.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.datav.domain.ShareTokenLog;
import com.ruoyi.project.datav.mapper.DatavShareTokenMapper;
import com.ruoyi.project.datav.service.IDatavShareTokenService;

import java.util.List;

@Service
public class DatavShareTokenServiceImpl implements IDatavShareTokenService {

	@Autowired
    private DatavShareTokenMapper SysShareTokenMapping;

	@Override
	public void addTokenLog(ShareTokenLog shareTokenLog) {
		SysShareTokenMapping.addTokenLog(shareTokenLog);
	}

	@Override
	public ShareTokenLog findById(String id) {
		return SysShareTokenMapping.findById(id);
	}

	@Override
	public List<ShareTokenLog> getTokenList(ShareTokenLog shareTokenLog) {
		return SysShareTokenMapping.getTokenList(shareTokenLog);
	}

	@Override
	public int updateTokenLog(ShareTokenLog shareTokenLog) {
		return SysShareTokenMapping.updateTokenLog(shareTokenLog);
	}


}
