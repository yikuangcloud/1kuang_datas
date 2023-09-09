package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2021-03-06 08:32:26
 */
@Data
@TableName("baidu_get")
public class BaiduGetEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Integer id;
	/**
	 * 搜索关键词
	 */
	private String searchKey;



	private String fileName;


	/**
	 * 搜索时间
	 */
	private Date searchTime;

//	任务状态  0：正常  1：暂停
	private Integer getStatus;

	private String province;

	private String city;

	private String type;
}
