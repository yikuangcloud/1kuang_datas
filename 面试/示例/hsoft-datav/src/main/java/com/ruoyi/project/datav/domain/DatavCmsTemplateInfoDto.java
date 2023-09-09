package com.ruoyi.project.datav.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: DatavCmsTemplateInfoDto
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 王莹
 * @Date: 2022/7/20 9:32
 */
public class DatavCmsTemplateInfoDto {
    /** 用户Id */
    private Long userId;

    /** 大屏标题 */
    private String screenName;

    /** 大屏简介 */
    private String screenIntroduction;

    /** 大屏标签 */
    private String screenTag;

    /** 大屏分辨率 */
    private String screenResolution;

    /** 是否发布，0未发布，1已发布 */
    private String isReleased;

    /** 设备类型 pc/phone */
    private String deviceType;

    /** 分类 **/
    private String belongto;

    /** 行业 */
    private String industry;

    /** 使用场景 */
    private String scenario;

    /** 排序方式 */
    private String orderBy;

    /** 模糊查询关键字 */
    private String keyword;

    /** 查询类型 1：查询全部 2：查询用户收藏 **/
    private String queryType;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getScreenIntroduction() {
        return screenIntroduction;
    }

    public void setScreenIntroduction(String screenIntroduction) {
        this.screenIntroduction = screenIntroduction;
    }

    public String getScreenTag() {
        return screenTag;
    }

    public void setScreenTag(String screenTag) {
        this.screenTag = screenTag;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getBelongto() {
        return belongto;
    }

    public void setBelongto(String belongto) {
        this.belongto = belongto;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getIsReleased() {
        return isReleased;
    }

    public void setIsReleased(String isReleased) {
        this.isReleased = isReleased;
    }
}
