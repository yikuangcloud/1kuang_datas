package com.ruoyi.project.datav.service.impl;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.ruoyi.project.datav.service.DatavAuthorityService;
import com.ruoyi.project.system.domain.SysRole;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.ISysDeptService;
import com.ruoyi.project.system.service.ISysUserService;

@Service
public class DatavAuthorityServiceImpl implements DatavAuthorityService {
	
	@Autowired
	private ISysUserService iSysUserService;
	
	@Autowired
	private ISysDeptService iSysDeptService;

	@Override
	public String getAuthorities(SysUser user) {
		
		String createBy = "";
		
		//满足条件用户集合
		List<SysUser> userList = Lists.newArrayList();
		
		//获取用户角色组
		
		List<SysRole> roleList = iSysUserService.selectUserRoleIdGroup(user.getUserName());
		
		if(!roleList.isEmpty()){
			
			String scopes = "";
			//获取角色权限范围集合
			for (int i=0;i<roleList.size();i++) {
				
				scopes += roleList.get(i).getDataScope()+",";
				
			}
			//如果为全部权限则其余角色不需要
			if(scopes.indexOf("1") > -1){
				scopes = "1";
			}
			//如果包含本部门及以下权限则删除本部门权限
			else if(scopes.indexOf("3") > -1 && scopes.indexOf("4") > -1){
				scopes = scopes.replace("3,", "");
			}
			
			String[] scopesList = scopes.split(",");
			
			//遍历用户权限列表，分别获取权限对应用户
			for (String scope : scopesList) {
				if(scope.equals("1")){
					
				}
				//自定义数据权限，获取自定义权限对应部门列表
				else if(scope.equals("2")){
					List<Integer> deptList = iSysDeptService.selectDeptListByRoleId(Long.parseLong("2"));
					
					for(int j = 0;j < deptList.size() ;j++){
						//根据部门id获取该部门用户集合
						SysUser deptUser = new SysUser();
						
						deptUser.setDeptId(Long.parseLong(deptList.get(j).toString()));
						
						List<SysUser> deptUserList =  iSysUserService.selectUserList(deptUser);
						
						//取各部门用户的并集
//						userList.removeAll(deptUserList);
						userList.addAll(deptUserList);
						
					}
					
				}
				//本部门权限
				else if(scope.equals("3")){
					//根据部门id获取该部门用户集合
					
					List<SysUser> deptUserList =  iSysUserService.selectUserListByDeptId(user.getDeptId());
					
					//取用户的并集
//					userList.removeAll(deptUserList);
					userList.addAll(deptUserList);
				}
				//本部门及以下权限
				else if(scope.equals("4")){
					
					//根据部门id获取该部门以及子部门用户集合
					SysUser deptUser = new SysUser();
					
					deptUser.setDeptId(user.getDeptId());
					
					List<SysUser> deptUserList =  iSysUserService.selectUserList(deptUser);
					
					//取用户的并集
//					userList.removeAll(deptUserList);
					userList.addAll(deptUserList);
					
				}
				//仅为自己权限
				else if(scope.equals("5")){
//					userList.remove(user);
					userList.add(user);
				}
			}
			
			
			HashSet h = new HashSet(userList);   
			userList.clear();   
			userList.addAll(h); 
			
			//如果用户集合不为空则构建用户列表
			if(!userList.isEmpty()){
				
				
				StringBuilder builder = new StringBuilder();
				
				for (SysUser sysuser : userList) {
					builder.append("'"+sysuser.getUserName()+"',");
				}
				
				String users = builder.toString();
				users = users.substring(0,users.length()-1);
				
				createBy = "("+users+")";
				
			}
			
			
		}
		
		return createBy;
	}

}
