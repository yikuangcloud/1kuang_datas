package com.ruoyi.project.datav.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: DatavDemandQuestion
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 王莹
 * @Date: 2022/7/20 16:31
 */
public class DatavDemandQuestion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 模板类型 */
    @Excel(name = "模板类型")
    private String templateType;

    /** 模板需求 */
    @Excel(name = "模板需求")
    private String templateDemand;

    /** 岗位类型 */
    @Excel(name = "岗位类型")
    private String postType;

    /** 需求详情 */
    @Excel(name = "需求详情")
    private String demandDesc;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setTemplateType(String templateType)
    {
        this.templateType = templateType;
    }

    public String getTemplateType()
    {
        return templateType;
    }
    public void setTemplateDemand(String templateDemand)
    {
        this.templateDemand = templateDemand;
    }

    public String getTemplateDemand()
    {
        return templateDemand;
    }
    public void setPostType(String postType)
    {
        this.postType = postType;
    }

    public String getPostType()
    {
        return postType;
    }
    public void setDemandDesc(String demandDesc)
    {
        this.demandDesc = demandDesc;
    }

    public String getDemandDesc()
    {
        return demandDesc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("templateType", getTemplateType())
                .append("templateDemand", getTemplateDemand())
                .append("postType", getPostType())
                .append("demandDesc", getDemandDesc())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .toString();
    }
}
