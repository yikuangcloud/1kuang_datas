package com.ruoyi.project.system.mapper;
/**
 * @author zxy
 * @Description: 用户注册
 *
 */

import java.util.List;

import com.ruoyi.project.system.domain.SysUser;

public interface SysUserRegisterMapper {

	/**
	 * 按照用户账号查找
	 * @param sysUser
	 * @return
	 */
	public List<SysUser> selectUserByUserName(SysUser sysUser);
	
	/**
	 * 用户信息注册
	 * @param sysUser
	 * @return
	 */
	public int insertUser(SysUser sysUser);
	
	/**
	 * 修改用户激活标识
	 * @param sysUser
	 * @return
	 */
	public int updateUserFlag(String code);
	
}
