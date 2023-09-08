package com.ruoyi.project.system.service.impl;

import java.util.List;
import java.util.UUID;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.datav.service.DatavAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.SysWebsocketMapper;
import com.ruoyi.project.system.domain.SysWebsocket;
import com.ruoyi.project.system.service.ISysWebsocketService;

/**
 * websocket通讯接口管理Service业务层处理
 * 
 * @author sunyan
 * @date 2022-02-08
 */
@Service
public class SysWebsocketServiceImpl implements ISysWebsocketService 
{
    @Autowired
    private SysWebsocketMapper sysWebsocketMapper;

    @Autowired
    private DatavAuthorityService datavAuthorityService;
    /**
     * 查询websocket通讯接口管理
     * 
     * @param id websocket通讯接口管理ID
     * @return websocket通讯接口管理
     */
    @Override
    public SysWebsocket selectSysWebsocketById(Long id)
    {
        return sysWebsocketMapper.selectSysWebsocketById(id);
    }

    /**
     * 查询websocket通讯接口管理列表
     * 
     * @param sysWebsocket websocket通讯接口管理
     * @return websocket通讯接口管理
     */
    @Override
    public List<SysWebsocket> selectSysWebsocketList(SysWebsocket sysWebsocket)
    {
        String createBy = datavAuthorityService.getAuthorities(SecurityUtils.getLoginUser().getUser());
        sysWebsocket.setCreateBy(createBy);
        return sysWebsocketMapper.selectSysWebsocketList(sysWebsocket);
    }

    /**
     * 新增websocket通讯接口管理
     * 
     * @param sysWebsocket websocket通讯接口管理
     * @return 结果
     */
    @Override
    public int insertSysWebsocket(SysWebsocket sysWebsocket)
    {
        sysWebsocket.setCreateBy(SecurityUtils.getUsername());
        sysWebsocket.setCreateTime(DateUtils.getNowDate());
        sysWebsocket.setInterfaceKey(UUID.randomUUID().toString().replaceAll("-",""));
        return sysWebsocketMapper.insertSysWebsocket(sysWebsocket);
    }

    /**
     * 修改websocket通讯接口管理
     * 
     * @param sysWebsocket websocket通讯接口管理
     * @return 结果
     */
    @Override
    public int updateSysWebsocket(SysWebsocket sysWebsocket)
    {
        sysWebsocket.setUpdateBy(SecurityUtils.getUsername());
        sysWebsocket.setUpdateTime(DateUtils.getNowDate());
        return sysWebsocketMapper.updateSysWebsocket(sysWebsocket);
    }

    /**
     * 批量删除websocket通讯接口管理
     * 
     * @param ids 需要删除的websocket通讯接口管理ID
     * @return 结果
     */
    @Override
    public int deleteSysWebsocketByIds(Long[] ids)
    {
        return sysWebsocketMapper.deleteSysWebsocketByIds(ids);
    }

    /**
     * 删除websocket通讯接口管理信息
     * 
     * @param id websocket通讯接口管理ID
     * @return 结果
     */
    @Override
    public int deleteSysWebsocketById(Long id)
    {
        return sysWebsocketMapper.deleteSysWebsocketById(id);
    }
}
