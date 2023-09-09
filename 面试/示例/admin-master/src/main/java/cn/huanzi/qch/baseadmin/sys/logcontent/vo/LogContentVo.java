package cn.huanzi.qch.baseadmin.sys.logcontent.vo;

import cn.huanzi.qch.baseadmin.common.pojo.PageCondition;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author laker
 * @since 2021-08-11
 */
@Data
public class LogContentVo extends PageCondition implements Serializable {

    private Long id;

    private String saleName;

    private Date createTime;

    private Integer isPassed;

    private Integer delFlag;
    private String endDate;
    private String startDate;




}
