package com.ruoyi.project.system.service;

import com.ruoyi.project.system.domain.SysUser;

/**
 * @author zxy
 * @Description: 用户注册
 *
 */
public interface ISysUserRegisterService {

	/**
	 * 用户信息注册
	 * @param sysUser
	 * @return
	 */
	public void insertUser(SysUser sysUser);
	
	/**
	 * 修改用户激活标识
	 * @param sysUser
	 * @return
	 */
	public int updateUserFlag(String code);
	
}
