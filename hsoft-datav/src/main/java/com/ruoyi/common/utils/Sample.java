package com.ruoyi.common.utils;

import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;

import java.util.Random;

/**
 * @version v1.0
 * @ProjectName: cube
 * @ClassName: Sample
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 风清扬 [刘佳男]
 * @Date: 2021/11/30 11:09
 */

public class Sample {


    // 随机验证码字符
    public static final String VERIFY_CODES = "0123456789";

    public static final String ACCESS_KEY_ID = "LTAI5tQR3f3EHV8rdxciDgx6";

    public static final String ACCESS_KEY_SECRET = "ocMxE0uCPiFcygcFO1Ln9nzrDdVFCx";

    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    /**
     * 使用指定源生成验证码
     *
     * @param verifySize 验证码长度
     * @return
     */
    public static String generateVerifyCode(int verifySize)
    {
        String sources = VERIFY_CODES;

        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for (int i = 0; i < verifySize; i++)
        {
            verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
        }
        return verifyCode.toString();
    }

    public static String sendSms(String phone,String code) throws Exception {

        com.aliyun.dysmsapi20170525.Client client = Sample.createClient(ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName("工大软件")
                .setTemplateCode("SMS_229015089")
                .setTemplateParam("{\"code\":" + code + "}");
        // 复制代码运行请自行打印 API 的返回值
        SendSmsResponse ssr = client.sendSms(sendSmsRequest);
        return ssr.getBody().getCode();
    }

    public static void main(String[] args_) throws Exception {

//        java.util.List<String> args = java.util.Arrays.asList(args_);
//        com.aliyun.dysmsapi20170525.Client client = Sample.createClient(ACCESS_KEY_ID, ACCESS_KEY_SECRET);
//        SendSmsRequest sendSmsRequest = new SendSmsRequest()
//                .setPhoneNumbers("15776481145")
//                .setSignName("工大软件")
//                .setTemplateCode("SMS_229015089")
//                .setTemplateParam("{\"code\":\"123456\"}");
//        // 复制代码运行请自行打印 API 的返回值
//        SendSmsResponse ssr = client.sendSms(sendSmsRequest);
//        System.out.println(ssr.toString());

        String code = generateVerifyCode(6);
        String result = sendSms("15776481145", code);
        System.out.println(result);
    }
}
