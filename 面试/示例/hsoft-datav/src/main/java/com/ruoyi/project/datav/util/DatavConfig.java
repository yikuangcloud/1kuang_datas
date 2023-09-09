package com.ruoyi.project.datav.util;

import com.ruoyi.framework.config.RuoYiConfig;

/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: DatavConfig
 * @Description: 数据大屏基本配置类
 * @Author: 风清扬 [刘佳男]
 * @Date: 2021/1/19 14:50
 */

public class DatavConfig {

    /**
     * 获取背景图片上传路径（大屏主题背景图片）
     * @return
     */
    public static String getBackgroundImagePath() {
        return RuoYiConfig.getProfile() + "/backgroundImage";
    }

    /**
     * 获取背景框上传路径（大屏背景框图片，包括图表，边框，按钮）
     * @return
     */
    public static String getBackgroundBoxPath() {
        return RuoYiConfig.getProfile() + "/backgroundBox";
    }

    /**
     * 获取UI原型切图上传路径
     * @return
     */
    public static String getPrototypeImagePath() {
        return RuoYiConfig.getProfile() + "/prototypeImage";
    }

    /**
     * 获取大屏拍照截图上传路径
     * @return
     */
    public static String getScreenCapturePath() {
        return RuoYiConfig.getProfile() + "/screenCapture";
    }

    /**
     * 获取自定义组件切图图片路径
     * @return
     */
    public static String getCustomChartPath() {
        return RuoYiConfig.getProfile() + "/customChart";
    }

    /**
     * 获取自定义组件切图图片路径
     * @return
     */
    public static String getEchartsCapturePath() {
        return RuoYiConfig.getProfile() + "/echartsGallery";
    }

    /**
     * 获取视频路径
     * @return
     */
    public static String getVideoPath() {
        return RuoYiConfig.getProfile() + "/video";
    }
}
