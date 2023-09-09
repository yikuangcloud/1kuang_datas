package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.SysWebsocket;

/**
 * websocket通讯接口管理Mapper接口
 * 
 * @author sunyan
 * @date 2022-02-08
 */
public interface SysWebsocketMapper 
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
     * 删除websocket通讯接口管理
     * 
     * @param id websocket通讯接口管理ID
     * @return 结果
     */
    public int deleteSysWebsocketById(Long id);

    /**
     * 批量删除websocket通讯接口管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysWebsocketByIds(Long[] ids);
}
