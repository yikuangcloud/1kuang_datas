package com.ruoyi.project.datav.domain;

/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: FileTemplateDto
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 王莹
 * @Date: 2022/7/21 13:34
 */
public class FileTemplateDto {

    /** 图片上传id */
    private String uploadId;

    /** 更新类型 1：查看 2：收藏 3：取消收藏 4：创建*/
    private String type;

    public String getUploadId() {
        return uploadId;
    }

    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
