package com.ruoyi.project.datav.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.datav.domain.DatavSourse;
import com.ruoyi.project.datav.service.DatavSourseService;

@RestController
@RequestMapping("/datav/datasourse")
public class DatavSourseController extends BaseController {

	@Autowired
	private DatavSourseService service;
	
	/**
	 * 查询数据源信息列表
	 * @param datavSourse
	 * @return
	 */
	@PreAuthorize("@ss.hasPermi('datav:datasourse:list')")
    @GetMapping("/list")
	public TableDataInfo selectDatavSourseList(DatavSourse datavSourse) {
		
		startPage();
		//获取用户账户
        String username = SecurityUtils.getUsername();
        //管理员用户查询全部，其他账户只查询自己的数据库链接地址
        if(!username.equals("admin")) {
        	datavSourse.setCreateBy(username);
        }
        List<DatavSourse> list = service.selectDatavSourseList(datavSourse);
        return getDataTable(list);
	}
	
	/**
	 * 新增数据源信息
	 * @param datavSourse
	 * @return
	 */
	@PreAuthorize("@ss.hasPermi('datav:datasourse:add')")
    @Log(title = "数据源管理", businessType = BusinessType.INSERT)
    @PostMapping
	public AjaxResult insertDatavSourse(@RequestBody DatavSourse datavSourse) {
		// datavSourse.setId(IdGen.uuid());
		datavSourse.setCreateBy(SecurityUtils.getUsername());
		datavSourse.setUpdateBy(SecurityUtils.getUsername());
		return toAjax(service.insertDatavSourse(datavSourse));
	}
	
	/**
	 * 修改某一条数据源信息
	 * @param datavSourse
	 * @return
	 */
	@PreAuthorize("@ss.hasPermi('datav:datasourse:edit')")
    @Log(title = "数据源管理", businessType = BusinessType.UPDATE)
    @PutMapping
	public AjaxResult updateDatavSourse(@RequestBody DatavSourse datavSourse) {
		datavSourse.setUpdateBy(SecurityUtils.getUsername());
		return toAjax(service.updateDatavSourse(datavSourse));
	}
	
	/**
	 * 删除数据源信息(包括多条删除)
	 * @param datavSourse
	 * @return
	 */
	@PreAuthorize("@ss.hasPermi('datav:datasourse:remove')")
    @Log(title = "数据源管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
	public AjaxResult deleteDatavSourseById(@PathVariable String[] ids) {
		return toAjax(service.deleteSourseByIds(ids));
	}
	
	/**
     * 根据编号获取详细信息
     */
    //@PreAuthorize("@ss.hasPermi('datav:datasourse:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable String id) {
        return AjaxResult.success(service.selectSourseById(id));
    }
	
}
