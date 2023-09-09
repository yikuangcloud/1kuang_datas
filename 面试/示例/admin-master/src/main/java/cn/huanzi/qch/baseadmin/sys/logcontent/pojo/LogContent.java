package cn.huanzi.qch.baseadmin.sys.logcontent.pojo;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Entity
@Table(name = "log_content")
@Data
public class LogContent implements Serializable {

    @Id
    private Long id;

    private String saleName;

    private String content;

    private Date createTime;

    private Integer isPassed;

    private Integer delFlag;

    private Date totime;


}
