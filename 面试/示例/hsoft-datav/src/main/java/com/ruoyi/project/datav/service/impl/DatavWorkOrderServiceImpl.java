package com.ruoyi.project.datav.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.datav.domain.WorkOrder;
import com.ruoyi.project.datav.mapper.DatavWorkOrderMapper;
import com.ruoyi.project.datav.service.IDatavWorkOrderService;

@Service
public class DatavWorkOrderServiceImpl implements IDatavWorkOrderService {

	@Autowired
	private DatavWorkOrderMapper mapper;
	
	@Override
	public List<WorkOrder> selectWorkOrderList(WorkOrder workOrder) {
		return mapper.selectWorkOrderList(workOrder);
	}

	@Override
	public int insertWorkOrder(WorkOrder workOrder) {
		workOrder.setCreateTime(DateUtils.getNowDate());
		workOrder.setUpdateTime(DateUtils.getNowDate());
		return mapper.insertWorkOrder(workOrder);
	}

	@Override
	public int updateWorkOrder(WorkOrder workOrder) {
		workOrder.setUpdateTime(DateUtils.getNowDate());
		return mapper.updateWorkOrder(workOrder);
	}

	@Override
	public int deleteWorkOrderByIds(String[] ids) {
		return mapper.deleteWorkOrderByIds(ids);
	}

	@Override
	public WorkOrder selectWorkOrderById(String id) {
		return mapper.selectWorkOrderById(id);
	}

	@Override
	public int updateIsRead(WorkOrder workOrder) {
		workOrder.setUpdateTime(DateUtils.getNowDate());
		return mapper.updateIsRead(workOrder);
	}

	@Override
	public int updateIsComplete(WorkOrder workOrder) {
		workOrder.setUpdateTime(DateUtils.getNowDate());
		return mapper.updateIsComplete(workOrder);
	}

}
