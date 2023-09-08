package com.ruoyi.project.system.domain.dto;

/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: DashbordDto
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 王莹
 * @Date: 2022/8/16 10:59
 */
public class DashboardDto {
    /*时间类型 (day:日，week:周，month:月，year:年)*/
    private String type;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
