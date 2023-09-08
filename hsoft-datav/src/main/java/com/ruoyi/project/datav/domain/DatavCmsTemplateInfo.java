package com.ruoyi.project.datav.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * cms模板对象 datav_cms_template
 * 
 * @author hanchao
 * @date 2022-07-12
 */
public class DatavCmsTemplateInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 对应大屏id */
    @Excel(name = "对应大屏id")
    private String screenId;

    /** 大屏标题 */
    @Excel(name = "大屏标题")
    private String screenName;

    /** 大屏简介 */
    @Excel(name = "大屏简介")
    private String screenIntroduction;

    /** 大屏标签 */
    @Excel(name = "大屏标签")
    private String screenTag;

    /** 大屏分辨率 */
    @Excel(name = "大屏分辨率")
    private String screenResolution;

    /** 设备类型 pc/phone */
    @Excel(name = "设备类型 pc/phone")
    private String deviceType;

    /** 创建人 */
    @Excel(name = "创建人")
    private String screenCreateBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date screenCreateDate;

    /** 是否发布，0未发布，1已发布 */
    @Excel(name = "是否发布，0未发布，1已发布")
    private String isReleased;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date releaseDate;

    /** 缩略图路径 */
    @Excel(name = "缩略图路径")
    private String capturePath;

    /** 被查看数量*/
    @Excel(name = "被查看数量")
    private Long viewCount;

    /** 被收藏数量 */
    @Excel(name = "被收藏数量")
    private Long starCount;

    /** 被使用数量 */
    @Excel(name = "被使用数量")
    private Long useCount;

    /** 分类，null未分类，1应用模板，2设计素材 */
    @Excel(name = "分类，null未分类，1应用模板，2设计素材")
    private String belongto;

    /** 行业 */
    @Excel(name = "行业")
    private String industry;

    /** 使用场景 */
    @Excel(name = "使用场景")
    private String scenario;

    /** 排序方式 */
    @Excel(name = "排序方式")
    private String orderBy;

    /** 模糊查询关键字 */
    @Excel(name = "模糊查询关键字")
    private String keyword;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
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
    public void setScreenIntroduction(String screenIntroduction)
    {
        this.screenIntroduction = screenIntroduction;
    }

    public String getScreenIntroduction()
    {
        return screenIntroduction;
    }
    public void setScreenTag(String screenTag)
    {
        this.screenTag = screenTag;
    }

    public String getScreenTag()
    {
        return screenTag;
    }
    public void setScreenResolution(String screenResolution)
    {
        this.screenResolution = screenResolution;
    }

    public String getScreenResolution()
    {
        return screenResolution;
    }
    public void setDeviceType(String deviceType)
    {
        this.deviceType = deviceType;
    }

    public String getDeviceType()
    {
        return deviceType;
    }
    public void setScreenCreateBy(String screenCreateBy)
    {
        this.screenCreateBy = screenCreateBy;
    }

    public String getScreenCreateBy()
    {
        return screenCreateBy;
    }
    public void setScreenCreateDate(Date screenCreateDate)
    {
        this.screenCreateDate = screenCreateDate;
    }

    public Date getScreenCreateDate()
    {
        return screenCreateDate;
    }
    public void setIsReleased(String isReleased)
    {
        this.isReleased = isReleased;
    }

    public String getIsReleased()
    {
        return isReleased;
    }
    public void setReleaseDate(Date releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    public Date getReleaseDate()
    {
        return releaseDate;
    }
    public void setCapturePath(String capturePath)
    {
        this.capturePath = capturePath;
    }

    public String getCapturePath()
    {
        return capturePath;
    }
    public void setViewCount(Long viewCount)
    {
        this.viewCount = viewCount;
    }

    public Long getViewCount()
    {
        return viewCount;
    }
    public void setStarCount(Long starCount)
    {
        this.starCount = starCount;
    }

    public Long getStarCount()
    {
        return starCount;
    }
    public void setUseCount(Long useCount)
    {
        this.useCount = useCount;
    }

    public Long getUseCount()
    {
        return useCount;
    }
    public void setBelongto(String belongto)
    {
        this.belongto = belongto;
    }

    public String getBelongto()
    {
        return belongto;
    }
    public void setIndustry(String industry)
    {
        this.industry = industry;
    }

    public String getIndustry()
    {
        return industry;
    }
    public void setScenario(String scenario)
    {
        this.scenario = scenario;
    }

    public String getScenario()
    {
        return scenario;
    }

    public void setOrderBy(String orderBy)
    {
        this.orderBy = orderBy;
    }

    public String getOrderBy()
    {
        return orderBy;
    }

    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }

    public String getKeyword()
    {
        return keyword;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("screenId", getScreenId())
                .append("screenName", getScreenName())
                .append("screenIntroduction", getScreenIntroduction())
                .append("screenTag", getScreenTag())
                .append("screenResolution", getScreenResolution())
                .append("deviceType", getDeviceType())
                .append("screenCreateBy", getScreenCreateBy())
                .append("screenCreateDate", getScreenCreateDate())
                .append("isReleased", getIsReleased())
                .append("releaseDate", getReleaseDate())
                .append("capturePath", getCapturePath())
                .append("viewCount", getViewCount())
                .append("starCount", getStarCount())
                .append("useCount", getUseCount())
                .append("belongto", getBelongto())
                .append("industry", getIndustry())
                .append("scenario", getScenario())
                .append("orderBy", getOrderBy())
                .append("keyword", getKeyword())
                .toString();
    }
}
