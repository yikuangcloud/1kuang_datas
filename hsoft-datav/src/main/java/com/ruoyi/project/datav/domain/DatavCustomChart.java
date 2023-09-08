package com.ruoyi.project.datav.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 自定义组件库对象 datav_custom_chart
 * 
 * @author 刘佳男【风清扬】
 * @date 2021-02-08
 */
public class DatavCustomChart extends BaseEntity
{
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

    /** 标签 */
    @Excel(name = "标签")
    private String tag;

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

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setChartOption(String chartOption) 
    {
        this.chartOption = chartOption;
    }

    public String getChartOption() 
    {
        return chartOption;
    }
    public void setGraph(String graph) 
    {
        this.graph = graph;
    }

    public String getGraph() 
    {
        return graph;
    }
    public void setComponent(String component) 
    {
        this.component = component;
    }

    public String getComponent() 
    {
        return component;
    }
    public void setTag(String tag) 
    {
        this.tag = tag;
    }

    public String getTag() 
    {
        return tag;
    }
    public void setThumbnail(String thumbnail) 
    {
        this.thumbnail = thumbnail;
    }

    public String getThumbnail() 
    {
        return thumbnail;
    }
    public void setIsOpen(String isOpen) 
    {
        this.isOpen = isOpen;
    }

    public String getIsOpen() 
    {
        return isOpen;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("chartOption", getChartOption())
            .append("graph", getGraph())
            .append("component", getComponent())
            .append("tag", getTag())
            .append("thumbnail", getThumbnail())
            .append("isOpen", getIsOpen())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
