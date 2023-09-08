package com.ruoyi.project.common;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.user.UserNotExistsException;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.Sample;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 短信验证码处理
 * @author wangying
 *
 */
@RestController
public class SmsController {
    
	@Autowired
    private RedisCache redisCache;
	
    @Autowired
    private ISysUserService iSysUserService;
    
	/**
     * 生成验证码
	 * @throws Exception 
     */
	@PostMapping("/getSmsCode")
    public AjaxResult getCode(@RequestBody String phoneNum) throws Exception
    {
        
    	// 生成随机字串
//        String verifyCode = VerifySmsUtils.generateVerifyCode(6);
//
//        String result = VerifySmsUtils.sendSms(phoneNum,verifyCode);
//
//        SmsMessage message= JSONObject.parseObject(result, SmsMessage.class);
//
//        if(message != null && message.getResultCode().equals("0")){
//
//            redisCache.setCacheObject(phoneNum, verifyCode, 5, TimeUnit.MINUTES);
//
//        }

		String verifyCode = Sample.generateVerifyCode(6);

		String result = Sample.sendSms(phoneNum,verifyCode);

		if(result != null && result.equals("OK")){

			redisCache.setCacheObject(phoneNum, verifyCode, Long.parseLong("5"), TimeUnit.MINUTES);

		}

        	
        return AjaxResult.success();
    }
	/**
	 * 通过手机号验证用户是否注册
	 * @param phoneNum
	 * @return
	 * @throws Exception
	 */
	
	@PostMapping("/getBindTel")
    public AjaxResult getBindTel(@RequestBody String phoneNum) throws Exception
    {
		SysUser sysUser = iSysUserService.selectUserByTel(phoneNum);
        if(sysUser != null){
        	return AjaxResult.success();
        }else{
        	//查询无此用户信息则抛出异常错误
        	AsyncManager.me().execute(AsyncFactory.recordLogininfor(phoneNum, Constants.LOGIN_FAIL, MessageUtils.message("user.telphone.notbind")));
            throw new UserNotExistsException();
        }
    }
    
	 public static class SmsMessage {
    	private  String resultCode;

        private List<messageResult> resultLists;

		public String getResultCode() {
			return resultCode;
		}

		public void setResultCode(String resultCode) {
			this.resultCode = resultCode;
		}

		public List<messageResult> getResultLists() {
			return resultLists;
		}

		public void setResultLists(List<messageResult> resultLists) {
			this.resultLists = resultLists;
		}


    }
    
	 public static class messageResult {
    	
    	private String mobile;
    	
    	private String resultCode;
    	
    	private String resultDesc;
    	
    	private String messageId;

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getResultCode() {
			return resultCode;
		}

		public void setResultCode(String resultCode) {
			this.resultCode = resultCode;
		}

		public String getResultDesc() {
			return resultDesc;
		}

		public void setResultDesc(String resultDesc) {
			this.resultDesc = resultDesc;
		}

		public String getMessageId() {
			return messageId;
		}

		public void setMessageId(String messageId) {
			this.messageId = messageId;
		}
    	
    	
    }
}
