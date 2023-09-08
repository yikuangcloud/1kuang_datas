package com.ruoyi.project.datav.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: DatavFileUploadDto
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 王莹
 * @Date: 2022/7/20 13:11
 */
public class DatavFileUploadDto {
    /** 图片名称 */
    private String name;

    /** 图片类型：0：背景图片；1：背景框；2：切图； */
    private String type;

    /** 排序方式 */
    private String orderBy;

    /** 图片分类:例如 1：图标；2：边框；3：按钮 */
    private String sort;

    /** 用户Id */
    private Long userId;

    /** 查询类型 1：查询全部 2：查询用户收藏 **/
    private String queryType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }
}
