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
@TableName("baidu_getinfo")
public class BaiduGetinfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 链接
	 */
	private String infoUrl;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 公司名称
	 */
	private String company;
	/**
	 * 联系信息（杂）
	 */
	private String contact;
	/**
	 * 链接名
	 */
	private String title;
	/**
	 * 
	 */
	private Date addTime;
	/**
	 * 包含电话的信息（可能包含其他信息）
	 */
	private String telInfo;
	/**
	 * 纯电话信息，多个以逗号分开
	 */
	private String tel;
	/**
	 * 搜索记录的id
	 */
	private Integer getId;


	/**
	 * 搜索关键词
	 */
	private String searchKey;

}
