package com.ruoyi.project.datav.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 数据大屏文件上传映射对象 datav_file_upload
 * 
 * @author 刘佳男【风清扬】
 * @date 2021-01-26
 */
public class DatavFileUpload extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 图片上传id */
    private String uploadId;

    /** 是否公开 */
    @Excel(name = "是否公开")
    private String isOpen;

    /** 0：否 1：是（管理员操作模板超市素材） */
    @Excel(name = "是否模板")
    private String isTemplate;

    /** 图片上传路径 */
    @Excel(name = "图片上传路径")
    private String imagePath;

    /** 图片类型：0：背景图片；1：背景框；2：切图； */
    @Excel(name = "图片类型：0：背景图片；1：背景框；2：切图；")
    private String type;

    /** 图片名称 */
    @Excel(name = "图片名称")
    private String name;

    /** 所属模板 */
    @Excel(name = "所属模板")
    private String viewTemplate;

    /** 图片分类:例如 1：图标；2：边框；3：按钮 */
    @Excel(name = "图片分类:例如 1：图标；2：边框；3：按钮")
    private String sort;

    /** 大屏状态（0正常 1停用 2已发布） */
    @Excel(name = "大屏状态", readConverterExp = "0=正常,1=停用,2=已发布")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 被查看数量*/
    @Excel(name = "被查看数量")
    private Long viewCount;

    /** 被收藏数量 */
    @Excel(name = "被收藏数量")
    private Long starCount;

    /** 被使用数量 */
    @Excel(name = "被使用数量")
    private Long useCount;

    public void setUploadId(String uploadId) 
    {
        this.uploadId = uploadId;
    }

    public String getUploadId() 
    {
        return uploadId;
    }
    public void setIsOpen(String isOpen) 
    {
        this.isOpen = isOpen;
    }

    public String getIsOpen() 
    {
        return isOpen;
    }
    public void setImagePath(String imagePath) 
    {
        this.imagePath = imagePath;
    }

    public String getIsTemplate() {
        return isTemplate;
    }

    public void setIsTemplate(String isTemplate) {
        this.isTemplate = isTemplate;
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

    public Long getUseCount() {
        return useCount;
    }

    public void setUseCount(Long useCount) {
        this.useCount = useCount;
    }

    public String getImagePath()
    {
        return imagePath;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setViewTemplate(String viewTemplate) 
    {
        this.viewTemplate = viewTemplate;
    }

    public String getViewTemplate() 
    {
        return viewTemplate;
    }
    public void setSort(String sort) 
    {
        this.sort = sort;
    }

    public String getSort() 
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("uploadId", getUploadId())
            .append("isOpen", getIsOpen())
            .append("imagePath", getImagePath())
            .append("type", getType())
            .append("name", getName())
            .append("viewTemplate", getViewTemplate())
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
