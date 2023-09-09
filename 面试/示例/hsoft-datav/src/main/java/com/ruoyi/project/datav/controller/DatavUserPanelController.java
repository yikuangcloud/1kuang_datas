package com.ruoyi.project.datav.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.datav.domain.DatavChartCollection;
import com.ruoyi.project.datav.domain.DatavCustomChart;
import com.ruoyi.project.datav.domain.DatavFileUpload;
import com.ruoyi.project.datav.domain.DatavMoreVo;
import com.ruoyi.project.datav.domain.DatavScreen;
import com.ruoyi.project.datav.domain.DatavShareLogs;
import com.ruoyi.project.datav.domain.DatavSourse;
import com.ruoyi.project.datav.service.DatavShareLogsService;
import com.ruoyi.project.datav.service.DatavSourseService;
import com.ruoyi.project.datav.service.IDatavChartCollectionService;
import com.ruoyi.project.datav.service.IDatavClassificationService;
import com.ruoyi.project.datav.service.IDatavCustomChartService;
import com.ruoyi.project.datav.service.IDatavFileUploadService;
import com.ruoyi.project.datav.service.IDatavScreenService;

@RestController
@RequestMapping("/datav/user/panel")
public class DatavUserPanelController {
	
	@Autowired
    private IDatavChartCollectionService datavChartCollectionService;
	
	@Autowired
    private IDatavScreenService datavScreenService;
	
	@Autowired
    private DatavShareLogsService datavShareLogsService;
	
	@Autowired
	private DatavSourseService datavSourseService;
	
    @Autowired
    private IDatavCustomChartService datavCustomChartService;
    
	@Autowired
    private IDatavFileUploadService datavFileUploadService;
	
    @Autowired
    private IDatavClassificationService datavClassificationService;
	
	
    @GetMapping(value = "/group/{username}")
    public AjaxResult getPanelGroup(@PathVariable("username") String username)
    {
    	AjaxResult ajax = AjaxResult.success();
        
    	//模板数
    	DatavScreen datavScreen = new DatavScreen();
        datavScreen.setCreateBy(username);
        
        Integer templateNum = datavScreenService.selectDatavScreenByUsername(datavScreen);
        
        ajax.put("templateNum", templateNum);
        //自定义组件数
        DatavCustomChart datavCustomChart = new DatavCustomChart();
        datavCustomChart.setCreateBy(username);
        
        Integer customNum = datavCustomChartService.selectDatavCustomChartByUsername(datavCustomChart);
        ajax.put("customNum", customNum);
        
        //收藏组件数
        DatavChartCollection datavChartCollection = new DatavChartCollection();
        datavChartCollection.setCreateBy(username);
        Integer collectNum = datavChartCollectionService.selectDatavChartCollectionByUsername(datavChartCollection);
        ajax.put("collectNum", collectNum);
        
    	//上传素材数
        DatavFileUpload datavFileUpload = new DatavFileUpload();
        datavFileUpload.setCreateBy(username);
        Integer uploadNum = datavFileUploadService.selectDatavFileUploadByUsername(datavFileUpload);
        ajax.put("uploadNum", uploadNum);
        
        //数据源配置数
        DatavSourse datavSourse = new DatavSourse();
        datavSourse.setCreateBy(username);
        List<DatavSourse> sourseList = datavSourseService.selectDatavSourseList(datavSourse);
        ajax.put("sourseNum", sourseList.size());
        
        //分享模板数
        DatavShareLogs datavShareLogs = new DatavShareLogs();
        datavShareLogs.setCreateBy(username);
        Integer shareNum =  datavShareLogsService.selectShareLogsByUsername(datavShareLogs);
        ajax.put("shareNum", shareNum);
        
        return AjaxResult.success(ajax);
    }
    /**
     * 查询用户创建模板数饼图
     * @param username
     * @return
     */
    @GetMapping(value = "/template/pie/{username}")
    public AjaxResult getTemplatePie(@PathVariable("username") String username){
	
    	AjaxResult ajax = AjaxResult.success();
    	//非模板数
    	DatavScreen datavScreen = new DatavScreen();
        datavScreen.setCreateBy(username);
        datavScreen.setIsTemplate("0");
        Integer notTempNum = datavScreenService.selectDatavScreenByUsername(datavScreen);
       
        ajax.put("notTempNum", notTempNum);
        //模板数
        datavScreen.setCreateBy(username);
        datavScreen.setIsTemplate("1");
        Integer isTempNum = datavScreenService.selectDatavScreenByUsername(datavScreen);
        ajax.put("isTempNum", isTempNum);
    	return AjaxResult.success(ajax);
	}
    /**
     * 查询用户创建模板公开数饼图
     * @param username
     * @return
     */
    @GetMapping(value = "/template/open/pie/{username}")
    public AjaxResult getTemplateOpenPie(@PathVariable("username") String username){
	
    	AjaxResult ajax = AjaxResult.success();
    	//非公开模板数
    	DatavScreen datavScreen = new DatavScreen();
        datavScreen.setCreateBy(username);
        datavScreen.setIsPublic("0");
        Integer notPublicNum = datavScreenService.selectDatavScreenByUsername(datavScreen);
        ajax.put("notPublicNum", notPublicNum);
        //公开模板数
        datavScreen.setCreateBy(username);
        datavScreen.setIsPublic("1");
        Integer isPublicNum = datavScreenService.selectDatavScreenByUsername(datavScreen);
        ajax.put("isPublicNum", isPublicNum);
        
    	return AjaxResult.success(ajax);
	}
    /**
     * 查询用户模板分类柱状图图
     * @param username
     * @return
     */
    @GetMapping(value = "/template/bar/{username}")
    public AjaxResult getTemplateBar(@PathVariable("username") String username){
	
    	//非模板数
    	DatavScreen datavScreen = new DatavScreen();
        datavScreen.setCreateBy(username);
        
        List<DatavMoreVo> temList = datavScreenService.selectDatavScreenByUsernameAndBelongto(datavScreen);
        
        List<String> xsis = new ArrayList<>();
        List<Integer> data = new ArrayList<>();
        
        for (DatavMoreVo datavMoreVo : temList) {
			if(StringUtils.isEmpty(datavMoreVo.getMoreName())){
				xsis.add("无分类");
			}else{
				//翻译模板分类名称
				String belongto = datavClassificationService.selectDatavClassificationById(datavMoreVo.getMoreName()).getName() ;
	            xsis.add(belongto);
			}
			
			data.add(datavMoreVo.getCount());
		}

        
        JSONObject bar =new JSONObject();
        bar.put("xsis", xsis);
        bar.put("data", data);
        
    	return AjaxResult.success(bar);
	}
}
