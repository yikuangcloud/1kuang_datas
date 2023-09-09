package com.ruoyi.common.exception.datav;

import com.ruoyi.common.exception.BaseException;

/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: DatavException
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 风清扬 [刘佳男]
 * @Date: 2021/6/16 11:02
 */

public class DatavException extends BaseException {

    private static final long serialVersionUID = 1L;

    public DatavException(String code, Object[] args) {
        super("datav", code, args, null);
    }
}
