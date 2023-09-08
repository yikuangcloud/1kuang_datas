package com.ruoyi.project.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.domain.SysUserRole;
import com.ruoyi.project.system.mapper.SysUserRegisterMapper;
import com.ruoyi.project.system.mapper.SysUserRoleMapper;
import com.ruoyi.project.system.service.ISysUserRegisterService;

/**
 * @author zxy
 * @Description: 用户注册
 *
 */
@Service
public class SysUserRegisterServiceImpl implements ISysUserRegisterService {

	@Autowired
	private SysUserRegisterMapper mapper;
	
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	@Override
	public void insertUser(SysUser sysUser) {
		
		mapper.insertUser(sysUser);
		
		// 给新注册用户赋予初始角色（测试角色）
		List<SysUserRole> list = new ArrayList<SysUserRole>();
		SysUserRole ur = new SysUserRole();
		ur.setUserId(sysUser.getUserId());
        ur.setRoleId((long)3);
        list.add(ur);
        
		sysUserRoleMapper.batchUserRole(list);
	}

	@Override
	public int updateUserFlag(String code) {
		
		return mapper.updateUserFlag(code);
	}

}
