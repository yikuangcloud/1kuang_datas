package com.ruoyi.project.datav.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 数据大屏对象 datav_screen
 * 
 * @author 刘佳男[风清扬]
 * @date 2021-01-20
 */
public class DatavScreen extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 大屏id */
    private String screenId;

    /** 大屏名称 */
    @Excel(name = "大屏名称")
    private String screenName;

    /** 数据大屏再画数据结构 */
    @Excel(name = "数据大屏再画数据结构")
    private String drawOption;

    /** 主题再画数据结构 */
    @Excel(name = "主题再画数据结构")
    private String themeOption;

    /** 全局组件id */
    @Excel(name = "全局组件id")
    private Long idGlobal;

    /** 全局zindex图层索引 */
    @Excel(name = "全局zindex图层索引")
    private Long zindex;

    /** 数据大屏截屏缩略图路径 */
    @Excel(name = "数据大屏截屏缩略图路径")
    private String capturePath;

    /** 大屏密码 */
    @Excel(name = "大屏密码")
    private String password;

    /** 是否公开：0不公开，1公开 */
    @Excel(name = "是否公开：0不公开，1公开")
    private String isPublic;

    /** 是否作为模板：0不作为模板，1作为模板 */
    @Excel(name = "是否作为模板：0不作为模板，1作为模板")
    private String isTemplate;

    /** 模板分类：null为未分类 */
    @Excel(name = "模板分类：null为未分类")
    private String belongto;

    /** echart图option集合 */
    @Excel(name = "echart图option集合")
    private String optionMap;

    /** 大屏状态（0正常 1停用 2已发布） */
    @Excel(name = "大屏状态", readConverterExp = "0=正常,1=停用,2=已发布")
    private String status;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    public void setScreenId(String screenId) 
    {
        this.screenId = screenId;
    }

    public String getScreenId() 
    {
        return screenId;
    }
    public void setScreenName(String screenName) 
    {
        this.screenName = screenName;
    }

    public String getScreenName() 
    {
        return screenName;
    }
    public void setDrawOption(String drawOption) 
    {
        this.drawOption = drawOption;
    }

    public String getDrawOption() 
    {
        return drawOption;
    }
    public void setThemeOption(String themeOption) 
    {
        this.themeOption = themeOption;
    }

    public String getThemeOption() 
    {
        return themeOption;
    }
    public void setIdGlobal(Long idGlobal) 
    {
        this.idGlobal = idGlobal;
    }

    public Long getIdGlobal() 
    {
        return idGlobal;
    }
    public void setZindex(Long zindex) 
    {
        this.zindex = zindex;
    }

    public Long getZindex() 
    {
        return zindex;
    }
    public void setCapturePath(String capturePath) 
    {
        this.capturePath = capturePath;
    }

    public String getCapturePath() 
    {
        return capturePath;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setIsPublic(String isPublic) 
    {
        this.isPublic = isPublic;
    }

    public String getIsPublic() 
    {
        return isPublic;
    }
    public void setIsTemplate(String isTemplate) 
    {
        this.isTemplate = isTemplate;
    }

    public String getIsTemplate() 
    {
        return isTemplate;
    }
    public void setBelongto(String belongto) 
    {
        this.belongto = belongto;
    }

    public String getBelongto() 
    {
        return belongto;
    }
    public void setOptionMap(String optionMap) 
    {
        this.optionMap = optionMap;
    }

    public String getOptionMap() 
    {
        return optionMap;
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
            .append("screenId", getScreenId())
            .append("screenName", getScreenName())
            .append("drawOption", getDrawOption())
            .append("themeOption", getThemeOption())
            .append("idGlobal", getIdGlobal())
            .append("zindex", getZindex())
            .append("capturePath", getCapturePath())
            .append("password", getPassword())
            .append("isPublic", getIsPublic())
            .append("isTemplate", getIsTemplate())
            .append("belongto", getBelongto())
            .append("optionMap", getOptionMap())
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
