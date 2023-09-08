package com.ruoyi.project.datav.util;

import java.util.UUID;

/**
 * 封装ID算法工具类
 * @author 刘佳男
 * @date 2019-02-27
 *
 */

public class IdGen {

	/**
	 * 封装JDK自带的UUID，通过random数字生成，中间无-字符
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static void main(String[] args) {
		System.out.println(uuid());
	}
}
