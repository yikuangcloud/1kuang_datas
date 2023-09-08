package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.SysWebsocket;

/**
 * websocket通讯接口管理Service接口
 * 
 * @author sunyan
 * @date 2022-02-08
 */
public interface ISysWebsocketService 
{
    /**
     * 查询websocket通讯接口管理
     * 
     * @param id websocket通讯接口管理ID
     * @return websocket通讯接口管理
     */
    public SysWebsocket selectSysWebsocketById(Long id);

    /**
     * 查询websocket通讯接口管理列表
     * 
     * @param sysWebsocket websocket通讯接口管理
     * @return websocket通讯接口管理集合
     */
    public List<SysWebsocket> selectSysWebsocketList(SysWebsocket sysWebsocket);

    /**
     * 新增websocket通讯接口管理
     * 
     * @param sysWebsocket websocket通讯接口管理
     * @return 结果
     */
    public int insertSysWebsocket(SysWebsocket sysWebsocket);

    /**
     * 修改websocket通讯接口管理
     * 
     * @param sysWebsocket websocket通讯接口管理
     * @return 结果
     */
    public int updateSysWebsocket(SysWebsocket sysWebsocket);

    /**
     * 批量删除websocket通讯接口管理
     * 
     * @param ids 需要删除的websocket通讯接口管理ID
     * @return 结果
     */
    public int deleteSysWebsocketByIds(Long[] ids);

    /**
     * 删除websocket通讯接口管理信息
     * 
     * @param id websocket通讯接口管理ID
     * @return 结果
     */
    public int deleteSysWebsocketById(Long id);
}
