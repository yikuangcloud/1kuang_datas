package com.ruoyi.project.datav.controller;

import com.ruoyi.common.utils.FastDFSClient;
import com.ruoyi.framework.web.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @version v1.0
 * @ProjectName: springboot_fastdfs
 * @ClassName: FastDFSController
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 风清扬 [刘佳男]
 * @Date: 2021/4/2 14:31
 */

@Api(value = "fastDFS分布式文件服务器接口", tags = { "fastDFS分布式文件服务器接口" })
@RestController
@RequestMapping("fdfs")
public class FastDFSController {

    @Value("${fdfs.web-server-url}")
    private String webServerUrl;

    @Autowired
    private FastDFSClient fastDFSClient;

    @ApiOperation(value = "上传", response = AjaxResult.class)
    @PostMapping(value = "upload")
    public AjaxResult upload(MultipartFile file) throws IOException {
        String url = fastDFSClient.uploadFile(file);
        //System.out.println(url);
        //System.out.println(webServerUrl);
        return AjaxResult.success(webServerUrl + url);
    }
}
