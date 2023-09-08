package com.ruoyi.project.datav.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * echarts素材对象 datav_echarts_gallary
 * 
 * @author ruoyi
 * @date 2022-07-27
 */
public class DatavEchartsGallery extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 组件图表名称 */
    @Excel(name = "组件图表名称")
    private String name;

    /** 组件图表option配置 */
    @Excel(name = "组件图表option配置")
    private String chartOption;

    /** 所属图表 */
    @Excel(name = "所属图表")
    private String graph;

    /** 所属组件 */
    @Excel(name = "所属组件")
    private String component;

    /** 图表缩略图 */
    @Excel(name = "图表缩略图")
    private String thumbnail;

    /** 图表描述 */
    @Excel(name = "图表描述")
    private String description;

    /** 图表外部链接 */
    @Excel(name = "图表外部链接")
    private String links;

    /** 被查看数量*/
    @Excel(name = "被查看数量")
    private Long viewCount;

    /** 被收藏数量 */
    @Excel(name = "被收藏数量")
    private Long starCount;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
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
    public void setThumbnail(String thumbnail) 
    {
        this.thumbnail = thumbnail;
    }

    public String getThumbnail() 
    {
        return thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Long getStarCount() {
        return starCount;
    }

    public void setStarCount(Long starCount) {
        this.starCount = starCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("chartOption", getChartOption())
            .append("graph", getGraph())
            .append("component", getComponent())
            .append("thumbnail", getThumbnail())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
