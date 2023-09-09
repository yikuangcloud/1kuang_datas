package com.ruoyi.project.datav.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;

import java.sql.Date;

public class DatavEchartsGalleryDto {
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

    /** 排序方式 */
    @Excel(name = "排序方式")
    private String orderBy;

    /** 查询筛选 0 全部；1 我的收藏； 2 我的图表 */
    @Excel(name = "查询筛选")
    private String queryType;

    private String createBy;

    private Date createTime;

    private String keyword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
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

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
