package com.ruoyi.project.datav.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.common.utils.FastDFSClient;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.netty.ChatHandler;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.datav.domain.WorkOrder;
import com.ruoyi.project.datav.service.IDatavWorkOrderService;

@RestController
@RequestMapping("/datav/workorder")
public class DatavWorkOrderController extends BaseController {

	@Autowired
	private IDatavWorkOrderService service;
	@Autowired
	private ChatHandler chatHandler;
	
	@Value("${fdfs.web-server-url}")
    private String webServerUrl;

    @Autowired
    private FastDFSClient fastDFSClient;
	
	/**
	 *  查询工单信息列表
	 * @param workOrder
	 * @return
	 */
	@PreAuthorize("@ss.hasPermi('datav:workorder:list')")
    @GetMapping("/list")
	public TableDataInfo selectWorkOrderList(WorkOrder workOrder) {
		
		startPage();
		//获取用户账户
        String username = SecurityUtils.getUsername();
        //管理员用户查询全部，其他账户只查询自己的工单
        if(!username.equals("admin")) {
        	workOrder.setCreateBy(username);
        }
        List<WorkOrder> list = service.selectWorkOrderList(workOrder);
        return getDataTable(list);
	}
	
	/**
	 * 新增工单信息
	 * @param workOrder
	 * @return
	 * @throws IOException 
	 */
	@PreAuthorize("@ss.hasPermi('datav:workorder:add')")
    //@Log(title = "工单管理", businessType = BusinessType.INSERT)
    @PostMapping
	public AjaxResult insertWorkOrder(@RequestParam("file") MultipartFile[] files, WorkOrder workOrder) throws IOException {
		//将文件上传到文件服务器中，保存文件地址的集合
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < files.length; i++) {
			builder.append(webServerUrl + fastDFSClient.uploadFile(files[i]) + ",");
		}
		workOrder.setEnclosure(builder.toString());
		workOrder.setIsRead("1");
		workOrder.setIsComplete("1");
		workOrder.setCreateBy(SecurityUtils.getUsername());
		workOrder.setUpdateBy(SecurityUtils.getUsername());
		
		List<String> list = Arrays.asList(workOrder.getCreateBy());
		String result = list.stream().map(String::valueOf).collect(Collectors.joining(","));
		
		String recall = "{\"type\":2, \"receiverIds\":\"" + "admin" + "\",\"sender\": \""+ result +"\",\"message\": \"" + "您有新的工单推送！" + "\"}"; 
		System.err.println(recall);
		chatHandler.toOpera(recall);
		
		return toAjax(service.insertWorkOrder(workOrder));
	}
	
	/**
	 * 修改某一条工单信息
	 * @param workOrder
	 * @return
	 * @throws IOException 
	 */
	@PreAuthorize("@ss.hasPermi('datav:workorder:edit')")
    //@Log(title = "工单管理", businessType = BusinessType.UPDATE)
    @PutMapping
	public AjaxResult updateDatavSourse(@RequestParam("file") MultipartFile[] files,WorkOrder workOrder) throws IOException {
		String url = workOrder.getEnclosure();
		if(url == null || url.equals("null")){
			url = "";
		}
		for (int i = 0; i < files.length; i++) {
			
			url += webServerUrl + fastDFSClient.uploadFile(files[i]) + ",";
		}
		workOrder.setEnclosure(url);
		workOrder.setUpdateBy(SecurityUtils.getUsername());
		return toAjax(service.updateWorkOrder(workOrder));
	}
	
	/**
	 * 删除工单信息(包括多条删除)
	 * @param ids
	 * @return
	 */
	@PreAuthorize("@ss.hasPermi('datav:workorder:remove')")
    @Log(title = "工单管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
	public AjaxResult deleteWorkOrderByIds(@PathVariable String[] ids) {
		return toAjax(service.deleteWorkOrderByIds(ids));
	}
	
	/**
     * 根据编号获取详细信息
     * @param id
	 * @return
	 */
    @GetMapping(value = "/{id}")
    public AjaxResult selectWorkOrderById(@PathVariable String id) {
        return AjaxResult.success(service.selectWorkOrderById(id));
    }
    
    /**
	 * 修改“是否已读”状态
	 * @param workOrder
	 * @return
	 */
    @PreAuthorize("@ss.hasPermi('datav:workorder:accept')")
    @Log(title = "工单管理", businessType = BusinessType.UPDATE)
    @PostMapping("/accept")
	public AjaxResult updateIsRead(@RequestBody WorkOrder workOrder) {
		workOrder.setUpdateBy(SecurityUtils.getUsername());
		return toAjax(service.updateIsRead(workOrder));
	}
	
    /**
	 * 修改“是否完成”状态，并添加回复内容
	 * @param workOrder
	 * @return
	 */
    @Log(title = "工单管理", businessType = BusinessType.UPDATE)
    @PostMapping("/complete")
	public AjaxResult updateIsComplete(@RequestBody WorkOrder workOrder) {
    	List<String> list = Arrays.asList(workOrder.getCreateBy());
		String result = list.stream().map(String::valueOf).collect(Collectors.joining(","));
    	workOrder.setUpdateBy(SecurityUtils.getUsername());
		String recall = "{\"type\":2, \"receiverIds\":\"" + result + "\",\"sender\": \""+ "admin" +"\",\"message\": \"" + "您的工单已解决！" + "\"}"; 
		System.err.println(recall);
		chatHandler.toOpera(recall);
		return toAjax(service.updateIsComplete(workOrder));
	}
    /**
     * 删除附件
     * @param url
     * @return
     */
    @Log(title = "工单管理", businessType = BusinessType.DELETE)
	@PostMapping("/delFile")
    public AjaxResult delFile(@RequestBody String url)
    {
    	if(!StringUtils.isEmpty(url)){
    		fastDFSClient.deleteFile(url.replace(webServerUrl, ""));
    	}
    	return toAjax(1);
    }
}
