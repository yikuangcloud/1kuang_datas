package com.ruoyi.project.datav.domain;

/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: DatavScreenTemplateVO
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 风清扬 [刘佳男]
 * @Date: 2021/3/16 14:40
 */

public class DatavScreenTemplateVO {

    /** 大屏id */
    private String screenId;

    /** 大屏名称 */
    private String screenName;

    /** 数据大屏截屏缩略图路径 */
    private String capturePath;

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getCapturePath() {
        return capturePath;
    }

    public void setCapturePath(String capturePath) {
        this.capturePath = capturePath;
    }
}
