package com.ruoyi.project.datav.domain;

import java.util.List;

/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: DatavCmsQuestionDto
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 王莹
 * @Date: 2022/8/4 9:01
 */
public class DatavCmsQuestionDto {
    /** 搜索关键字 */
    private String keyword;

    /** 标签 */
    private String tags;

    /** 创建人 */
    private String createBy;

    /** 排序方式 */
    private String orderType;

    private String[] tagArr;

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getKeyword() {
        return keyword;
    }

    public String[] getTagArr() {
        return tagArr;
    }

    public void setTagArr(String[] tagArr) {
        this.tagArr = tagArr;
    }
}
