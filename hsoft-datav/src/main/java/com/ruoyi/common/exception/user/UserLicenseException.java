package com.ruoyi.common.exception.user;

/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: UserLicenseException
 * @Description: 用户授权规范异常类
 * @Author: 风清扬 [刘佳男]
 * @Date: 2021/6/11 11:13
 */

public class UserLicenseException extends UserException {

    private static final long serialVersionUID = 1L;

    public UserLicenseException() {
        super("user.license.error", null);
    }

}
