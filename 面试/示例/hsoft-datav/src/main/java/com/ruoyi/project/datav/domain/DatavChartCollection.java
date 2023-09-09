package com.ruoyi.project.datav.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

public class DatavChartCollection extends BaseEntity{
	private static final long serialVersionUID = 1L;

    /** 自定义图表id */
    private Integer id;

    /** 自定义图表名称 */
    @Excel(name = "自定义图表名称")
    private String name;

    /** 自定义图表option配置 */
    @Excel(name = "自定义图表option配置")
    private String chartOption;

    /** 所属图表 */
    @Excel(name = "所属图表")
    private String graph;

    /** 所属组件 */
    @Excel(name = "所属组件")
    private String component;

    /** 描述 */
    @Excel(name = "描述")
    private String describe;

    /** 图表缩略图 */
    @Excel(name = "图表缩略图")
    private String thumbnail;

    /** 是否公开 */
    @Excel(name = "是否公开")
    private String isOpen;

    /** 大屏状态（0正常 1停用 2已发布） */
    @Excel(name = "大屏状态", readConverterExp = "0=正常,1=停用,2=已发布")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChartOption() {
		return chartOption;
	}

	public void setChartOption(String chartOption) {
		this.chartOption = chartOption;
	}

	public String getGraph() {
		return graph;
	}

	public void setGraph(String graph) {
		this.graph = graph;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
    
}
