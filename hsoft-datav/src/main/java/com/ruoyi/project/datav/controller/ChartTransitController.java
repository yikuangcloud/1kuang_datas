package com.ruoyi.project.datav.controller;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.framework.netty.ChatHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.framework.web.domain.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "图表组件API透传接口", tags = { "图表组件API透传接口" })
@RestController
@RequestMapping("/chart/transit")
public class ChartTransitController {
	
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ChatHandler chatHandler;
	
	@ApiOperation(value = "接口透传", response = AjaxResult.class)
	@PostMapping(value = "do")
	public AjaxResult transit(@RequestBody JSONObject object) {
		//方法类型
		String method = object.getString("method");
		//请求地址
		String url = object.getString("url");

		if("".equals(url)){
		    return AjaxResult.error("接口地址不能为空");
        }
        //参数
        JSONObject query  = object.getJSONObject("query");

        String token = object.getString("token");
		
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //如果输入token则请求头中加安全认证
        if(token!= null && !token.isEmpty()){

            headers.setBearerAuth(token);
        }

        //请求方法体
        HttpMethod httpMethod = HttpMethod.GET;
        HttpEntity<JSONObject> entity = new HttpEntity<>(query, headers);

        if("POST".equals(method)){
        	httpMethod = HttpMethod.POST;
        }
        try {
            ResponseEntity<JSONObject> responseData = restTemplate.exchange(url, httpMethod, entity, JSONObject.class);

            if(responseData.getBody() != null){

                JSONObject jsonObject = responseData.getBody();
                if(jsonObject.get("data") != null){

                    return AjaxResult.success(jsonObject.get("data"));
                }else{
                    return AjaxResult.error("接口格式返回错误");
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return AjaxResult.success();

	}

    @ApiOperation(value = "远程控制接口", response = AjaxResult.class)
    @PostMapping(value = "remote")
    public AjaxResult remote(@RequestBody JSONObject object) {

        //参数
        JSONObject query  = object.getJSONObject("query");

        JSONArray interfaceArry = object.getJSONArray("interface");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);



        for (Object obj:interfaceArry
             ) {

            JSONObject interObj = JSONObject.parseObject(JSONObject.toJSONString(obj));

            if(interObj != null && interObj.size() != 0){

                //请求地址
                String url = interObj.getString("interfaceUrl");
                //key
                String key = interObj.getString("interfaceKey");

                String token = interObj.getString("token");

                //如果输入token则请求头中加安全认证
                if(token!= null && !token.isEmpty()){

                    headers.setBearerAuth(token);
                }
                HttpEntity<JSONObject> entity = new HttpEntity<>(query, headers);

                try{
                    ResponseEntity<JSONObject> responseData = restTemplate.exchange(url, HttpMethod.POST, entity, JSONObject.class);

                    //获取返回结果
                    if(!responseData.getBody().isEmpty()){

                        try{
                            JSONObject jsonObject = responseData.getBody();
                            if(jsonObject.get("data") != null){

                                JSONObject arrObj = new JSONObject();

                                arrObj.put("result",jsonObject.get("data"));
                                arrObj.put("key", key);
                                //对绑定该key的组件发送消息
                                chatHandler.toComponent(JSONObject.toJSONString(arrObj));
                            }else{
                                return AjaxResult.error("接口格式返回错误");
                            }

                        }catch (Exception e){
                            return AjaxResult.error("接口格式返回错误");
                        }

                    }
                }catch (Exception e){
                    return AjaxResult.error("远程接口调用失败");
                }

            }

        }

        return AjaxResult.success();
    }

    @ApiOperation(value = "选项卡远程控制接口", response = AjaxResult.class)
    @PostMapping(value = "/tab/remote")
    public AjaxResult tabRemote(@RequestBody JSONObject object) {

        //对绑定该key的组件发送消息
        chatHandler.toComponent(JSONObject.toJSONString(object));

        return AjaxResult.success();
    }

}
