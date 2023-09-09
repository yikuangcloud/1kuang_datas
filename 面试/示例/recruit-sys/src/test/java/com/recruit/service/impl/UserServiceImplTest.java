package com.recruit.service.impl;

import com.recruit.common.LocalUser;
import com.recruit.dto.user.ChangePasswordDTO;
import com.recruit.dto.user.RegisterDTO;
import com.recruit.dto.user.UpdateInfoDTO;
import com.recruit.mapper.GroupMapper;
import com.recruit.mapper.GroupPermissionMapper;
import com.recruit.mapper.PermissionMapper;
import com.recruit.mapper.UserGroupMapper;
import com.recruit.mapper.UserMapper;
import com.recruit.model.GroupDO;
import com.recruit.model.GroupPermissionDO;
import com.recruit.model.PermissionDO;
import com.recruit.model.UserDO;
import com.recruit.model.UserGroupDO;
import com.recruit.service.impl.AdminServiceImpl;
import com.recruit.service.impl.UserIdentityServiceImpl;
import com.recruit.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
@Slf4j
@ActiveProfiles("test")
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private UserIdentityServiceImpl userIdentityService;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private UserGroupMapper userGroupMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private GroupPermissionMapper groupPermissionMapper;

    @Autowired
    private UserMapper userMapper;

    public Integer mockData() {
        UserDO user = UserDO.builder().username("pedro大大咧咧").nickname("pedro大大咧咧").build();
        GroupDO group = GroupDO.builder().name("测试分组1").info("just for test").build();
        PermissionDO permission1 = PermissionDO.builder().name("权限1").module("炉石传说").build();
        PermissionDO permission2 = PermissionDO.builder().name("权限2").module("炉石传说").build();
        userMapper.insert(user);
        groupMapper.insert(group);
        permissionMapper.insert(permission1);
        permissionMapper.insert(permission2);
        List<GroupPermissionDO> relations = new ArrayList<>();
        GroupPermissionDO relation1 = new GroupPermissionDO(group.getId(), permission1.getId());
        GroupPermissionDO relation2 = new GroupPermissionDO(group.getId(), permission2.getId());
        relations.add(relation1);
        relations.add(relation2);
        groupPermissionMapper.insertBatch(relations);
        UserGroupDO userGroup = new UserGroupDO(user.getId(), group.getId());
        userGroupMapper.insert(userGroup);
        return user.getId();
    }

    @Before
    public void setUp() {
        RegisterDTO dto = new RegisterDTO();
        dto.setUsername("pedro");
        dto.setPassword("123456");
        dto.setConfirmPassword("123456");
        log.info("dto: {}", dto);
        UserDO user = userService.createUser(dto);
        log.info("user: {}", user);
        // Mock logined user
        LocalUser.setLocalUser(user);
    }

    @Test
    public void createUser() {
        RegisterDTO dto = new RegisterDTO();
        dto.setUsername("pedro111");
        dto.setPassword("123456");
        dto.setConfirmPassword("123456");
        log.info("dto: {}", dto);
        UserDO user = userService.createUser(dto);
        log.info("user: {}", user);
        assertEquals(user.getUsername(), "pedro111");
        assertNull(user.getEmail());
    }

    @Test
    public void updateUserInfo() {
        UpdateInfoDTO dto = new UpdateInfoDTO();
        dto.setNickname("pedro-gao");
        dto.setUsername("pedro-gao");
        dto.setEmail("1312342604@qq.com");
        userService.updateUserInfo(dto);

        UserDO userDO = userService.getUserByUsername("pedro-gao");
        assertEquals(userDO.getEmail(), "1312342604@qq.com");

        boolean b = userIdentityService.verifyUsernamePassword(userDO.getId(), "pedro-gao", "123456");
        assertTrue(b);
    }

    @Test
    public void updateUserInfoNoUsername() {
        UpdateInfoDTO dto = new UpdateInfoDTO();
        dto.setNickname("pedro-gao");
        dto.setEmail("1312342604@qq.com");
        userService.updateUserInfo(dto);

        UserDO userDO = userService.getUserByUsername("pedro");
        assertEquals(userDO.getEmail(), "1312342604@qq.com");
        assertEquals(userDO.getNickname(), "pedro-gao");

        boolean b = userIdentityService.verifyUsernamePassword(userDO.getId(), "pedro", "123456");
        assertTrue(b);
    }

    @Test
    public void changeUserPassword() {
        ChangePasswordDTO dto = new ChangePasswordDTO();
        dto.setNewPassword("147258");
        dto.setConfirmPassword("147258");
        dto.setOldPassword("123456");
        UserDO user = userService.changeUserPassword(dto);

        boolean b = userIdentityService.verifyUsernamePassword(user.getId(), "pedro", "147258");
        assertTrue(b);
    }

    @Test
    public void getUserPermissions() {
        Integer id = mockData();
        List<Map<String, List<Map<String, String>>>> structuringPermissions = userService.getStructuralUserPermissions(id);
        assertTrue(structuringPermissions.size() > 0);
        log.info("structuringPermissions: {}", structuringPermissions);
        boolean anyMatch = structuringPermissions.stream().anyMatch(it -> it.containsKey("炉石传说"));
        assertTrue(anyMatch);
    }

    @Test
    public void findByUsername() {
        UserDO user = userService.getUserByUsername("pedro");
        log.info("user:{}", user);
        assertEquals(user.getUsername(), "pedro");
    }

    @Test
    public void checkUserExistByUsername() {
        boolean b = userService.checkUserExistByUsername("pedro");
        assertTrue(b);
    }


    @Test
    public void checkUserExistById() {
        boolean b = userService.checkUserExistById(100);
        assertFalse(b);
    }

    @Test
    public void checkUserExistById1() {
        UserDO user = LocalUser.getLocalUser();
        boolean b = userService.checkUserExistById(user.getId());
        assertTrue(b);
    }

    @Test
    public void checkCreateAndDeleteUser() {
        // 新建某个用户
        RegisterDTO dto = new RegisterDTO();
        dto.setUsername("pedro111");
        dto.setPassword("123456");
        dto.setConfirmPassword("123456");
        log.info("dto: {}", dto);
        UserDO user = userService.createUser(dto);
        log.info("user: {}", user);
        assertEquals(user.getUsername(), "pedro111");
        assertNull(user.getEmail());

        boolean b = adminService.deleteUser(user.getId());
        assertTrue(b);

        UserDO newUser = userService.createUser(dto);
        assertEquals(newUser.getUsername(), "pedro111");
    }
}