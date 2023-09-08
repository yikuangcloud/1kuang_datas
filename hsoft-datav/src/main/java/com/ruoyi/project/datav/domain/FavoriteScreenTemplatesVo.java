package com.ruoyi.project.datav.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: FavoriteScreenTemplatesVo
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 王莹
 * @Date: 2022/7/20 11:10
 */
public class FavoriteScreenTemplatesVo {

    /** 收藏的模板id */
    private String screenId;

    /** 收藏图片素材id */
    private String imageUploadId;

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public String getImageUploadId() {
        return imageUploadId;
    }

    public void setImageUploadId(String imageUploadId) {
        this.imageUploadId = imageUploadId;
    }
}
