package com.ruoyi.project.datav.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据大屏模板分类对象 datav_classification
 * 
 * @author 刘佳男【风清扬】
 * @date 2021-01-30
 */
public class DatavClassification extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 类别编号 */
    @Excel(name = "类别编号")
    private String no;

    /** 父id */
    private Long parentId;

    /** 父级全路径 */
    private String parentIds;

    /** 排序 */
    @Excel(name = "排序")
    private Long sort;

    /** 大屏状态（0正常 1停用 2已发布） */
    @Excel(name = "大屏状态", readConverterExp = "0=正常,1=停用,2=已发布")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 子部门 */
    private List<DatavClassification> children = new ArrayList<DatavClassification>();

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
    public void setNo(String no) 
    {
        this.no = no;
    }

    public String getNo() 
    {
        return no;
    }
    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public Long getParentId()
    {
        return parentId;
    }
    public void setParentIds(String parentIds) 
    {
        this.parentIds = parentIds;
    }

    public String getParentIds() 
    {
        return parentIds;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
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

    public List<DatavClassification> getChildren() {
        return children;
    }

    public void setChildren(List<DatavClassification> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("no", getNo())
            .append("parentId", getParentId())
            .append("parentIds", getParentIds())
            .append("sort", getSort())
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
