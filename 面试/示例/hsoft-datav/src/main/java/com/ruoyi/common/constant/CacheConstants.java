package com.ruoyi.common.constant;

/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: CacheConstants
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 王莹
 * @Date: 2022/8/23 16:17
 */
public class CacheConstants {
    /**
     * 缓存有效期，默认720（分钟）
     */
    public final static long EXPIRATION = 720;

    /**
     * 缓存刷新时间，默认120（分钟）
     */
    public final static long REFRESH_TIME = 120;

    /**
     * 密码最大错误次数
     */
    public final static int passwordMaxRetryCount = 5;

    /**
     * 密码锁定时间，默认10（分钟）
     */
    public final static long passwordLockTime = 10;

    /**
     * 权限缓存前缀
     */
    public final static String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 登录账户密码错误次数 redis key
     */
    public static final String PWD_ERR_CNT_KEY = "pwd_err_cnt:";
}
