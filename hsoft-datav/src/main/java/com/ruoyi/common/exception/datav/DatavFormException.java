package com.ruoyi.common.exception.datav;

/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: DatavFormException
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 风清扬 [刘佳男]
 * @Date: 2021/6/16 11:06
 */

public class DatavFormException extends DatavException {

    private static final long serialVersionUID = 1L;

    public DatavFormException() {
        super("datav.form.error", null);
    }
}
