package com.ruoyi.project.datav.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 素材收藏对象 favorite_screen_templates
 * 
 * @author hanchao
 * @date 2022-07-18
 */
public class FavoriteScreenTemplates extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long uid;

    /** 收藏的模板id */
    @Excel(name = "收藏的模板id")
    private String screenId;

    /** 收藏图片素材id */
    @Excel(name = "收藏图片素材id")
    private String imageUploadId;

    /** 收藏素材类型 1：模板 2：图片 */
    @Excel(name = "收藏素材类型")
    private String type;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUid(Long uid) 
    {
        this.uid = uid;
    }

    public Long getUid() 
    {
        return uid;
    }
    public void setScreenId(String screenId) 
    {
        this.screenId = screenId;
    }

    public String getScreenId() 
    {
        return screenId;
    }
    public void setImageUploadId(String imageUploadId) 
    {
        this.imageUploadId = imageUploadId;
    }

    public String getImageUploadId() 
    {
        return imageUploadId;
    }

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("uid", getUid())
            .append("screenId", getScreenId())
            .append("imageUploadId", getImageUploadId())
            .append("type", getType())
            .toString();
    }
}
