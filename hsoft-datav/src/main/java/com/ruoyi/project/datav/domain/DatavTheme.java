package com.ruoyi.project.datav.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 数据大屏自定义主题配置对象 datav_theme
 * 
 * @author 刘佳男【风清扬】
 * @date 2021-01-29
 */
public class DatavTheme extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主题id */
    private String themeId;

    /** 是否公开 */
    @Excel(name = "是否公开")
    private String isOpen;

    /** 主题名称 */
    @Excel(name = "主题名称")
    private String themeName;

    /** 颜色 */
    @Excel(name = "颜色")
    private String themeColor;

    /** 主题配置json */
    @Excel(name = "主题配置json")
    private String themeOption;

    /** 大屏状态（0正常 1停用 2已发布） */
    @Excel(name = "大屏状态", readConverterExp = "0=正常,1=停用,2=已发布")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setThemeId(String themeId) 
    {
        this.themeId = themeId;
    }

    public String getThemeId() 
    {
        return themeId;
    }
    public void setIsOpen(String isOpen) 
    {
        this.isOpen = isOpen;
    }

    public String getIsOpen() 
    {
        return isOpen;
    }
    public void setThemeName(String themeName) 
    {
        this.themeName = themeName;
    }

    public String getThemeName() 
    {
        return themeName;
    }
    public void setThemeColor(String themeColor) 
    {
        this.themeColor = themeColor;
    }

    public String getThemeColor() 
    {
        return themeColor;
    }
    public void setThemeOption(String themeOption) 
    {
        this.themeOption = themeOption;
    }

    public String getThemeOption() 
    {
        return themeOption;
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
            .append("themeId", getThemeId())
            .append("isOpen", getIsOpen())
            .append("themeName", getThemeName())
            .append("themeColor", getThemeColor())
            .append("themeOption", getThemeOption())
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
