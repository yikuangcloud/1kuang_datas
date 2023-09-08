package com.ruoyi.project.datav.domain;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: DatavCmsQuestion
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 王莹
 * @Date: 2022/8/3 11:20
 */
public class DatavCmsQuestion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 问题id */
    @Excel(name = "问题id")
    private Long questionId;

    /** 回复id */
    @Excel(name = "回复id")
    private Long parentId;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 内容 */
    @Excel(name = "内容文字")
    private String contentText;

    /** 查看数 */
    @Excel(name = "查看数")
    private Long viewCount;

    /** 标签 */
    @Excel(name = "标签")
    private String tags;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setQuestionId(Long questionId)
    {
        this.questionId = questionId;
    }

    public Long getQuestionId()
    {
        return questionId;
    }
    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public Long getParentId()
    {
        return parentId;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setViewCount(Long viewCount)
    {
        this.viewCount = viewCount;
    }

    public Long getViewCount()
    {
        return viewCount;
    }
    public void setTags(String tags)
    {
        this.tags = tags;
    }

    public String getTags()
    {
        return tags;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("questionId", getQuestionId())
                .append("parentId", getParentId())
                .append("title", getTitle())
                .append("content", getContent())
                .append("viewCount", getViewCount())
                .append("tags", getTags())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .toString();
    }
}
