package com.ruoyi.project.datav.domain;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: DatavUserEcharts
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 王莹
 * @Date: 2022/7/28 16:12
 */
public class DatavUserEcharts extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 用户姓名 */
    @Excel(name = "用户姓名")
    private String username;

    /** 图表id */
    @Excel(name = "图表id")
    private Long chartId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }
    public void setChartId(Long chartId)
    {
        this.chartId = chartId;
    }

    public Long getChartId()
    {
        return chartId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("username", getUsername())
                .append("chartId", getChartId())
                .toString();
    }
}

