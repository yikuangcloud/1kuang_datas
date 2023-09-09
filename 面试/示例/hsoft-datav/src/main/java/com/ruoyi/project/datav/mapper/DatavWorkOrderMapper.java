package com.ruoyi.project.datav.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ruoyi.project.datav.domain.WorkOrder;

@Repository
public interface DatavWorkOrderMapper {

	/**
	 * 查询工单信息列表
	 * @param workOrder
	 * @return
	 */
	public List<WorkOrder> selectWorkOrderList(WorkOrder workOrder);
	
	/**
	 * 新增工单信息
	 * @param workOrder
	 * @return
	 */
	public int insertWorkOrder(WorkOrder workOrder);
	
	/**
	 * 修改工单信息
	 * @param workOrder
	 * @return
	 */
	public int updateWorkOrder(WorkOrder workOrder);
	
	/**
	 * （批量）删除工单信息
	 * @param ids
	 * @return
	 */
	public int deleteWorkOrderByIds(String[] ids);
	
	/**
	 * 根据编号获取详细信息
	 * @param id
	 * @return
	 */
	public WorkOrder selectWorkOrderById(String id);
	
	/**
	 * 修改“是否已读”状态
	 * @param workOrder
	 * @return
	 */
	public int updateIsRead(WorkOrder workOrder);
	
	/**
	 * 修改“是否完成”状态，并添加回复内容
	 * @param workOrder
	 * @return
	 */
	public int updateIsComplete(WorkOrder workOrder);
	
}
