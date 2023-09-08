package com.ruoyi.project.datav.service.impl;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.ruoyi.project.system.domain.dto.DashboardDto;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.datav.domain.DatavLineVo;
import com.ruoyi.project.datav.domain.DatavMoreVo;
import com.ruoyi.project.datav.domain.DatavScreen;
import com.ruoyi.project.datav.mapper.DatavScreenMapper;
import com.ruoyi.project.datav.service.IDatavScreenService;
import com.ruoyi.project.datav.mapper.DatavCmsTemplateInfoMapper;
import com.ruoyi.project.datav.domain.DatavCmsTemplateInfo;
/**
 * 数据大屏Service业务层处理
 * 
 * @author 刘佳男[风清扬]
 * @date 2021-01-20
 */
@Service
public class DatavScreenServiceImpl implements IDatavScreenService 
{
    @Autowired
    private DatavScreenMapper datavScreenMapper;
	@Autowired
	private DatavCmsTemplateInfoMapper datavCmsTemplateInfoMapper;

    /**
     * 查询数据大屏
     * 
     * @param screenId 数据大屏ID
     * @return 数据大屏
     */
    @Override
    public DatavScreen selectDatavScreenById(String screenId)
    {
        return datavScreenMapper.selectDatavScreenById(screenId);
    }

    /**
     * 查询数据大屏列表
     * 
     * @param datavScreen 数据大屏
     * @return 数据大屏
     */
    @Override
    public List<DatavScreen> selectDatavScreenList(DatavScreen datavScreen)
    {
        return datavScreenMapper.selectDatavScreenList(datavScreen);
    }

    /**
     * 新增数据大屏
     * 
     * @param datavScreen 数据大屏
     * @return 结果
     */
    @Override
    public int insertDatavScreen(DatavScreen datavScreen)
    {
        datavScreen.setCreateTime(DateUtils.getNowDate());
		if (datavScreen.getIsTemplate().equals("2")){
			cmsTemplateConstructor(datavScreen);
		}
        return datavScreenMapper.insertDatavScreen(datavScreen);
    }

    /**
     * 修改数据大屏
     * 
     * @param datavScreen 数据大屏
     * @return 结果
     */
    @Override
    public int updateDatavScreen(DatavScreen datavScreen)
    {
        datavScreen.setUpdateTime(DateUtils.getNowDate());
		if (datavScreen.getIsTemplate().equals("2")){
			cmsTemplateConstructor(datavScreen);
		}
		else {
			String screenId = datavScreen.getScreenId();
			datavCmsTemplateInfoMapper.deleteDatavCmsTemplateInfoById(screenId);
		}
        return datavScreenMapper.updateDatavScreen(datavScreen);
    }

	/**
	 * 构建cms模板数据信息
	 */
	public void cmsTemplateConstructor(DatavScreen datavScreen){
		datavCmsTemplateInfoMapper.deleteDatavCmsTemplateInfoById(datavScreen.getScreenId());
		DatavCmsTemplateInfo datavCmsTemplateInfo = new DatavCmsTemplateInfo();
		datavCmsTemplateInfo.setScreenId(datavScreen.getScreenId());
		datavCmsTemplateInfo.setScreenName(datavScreen.getScreenName());
		datavCmsTemplateInfo.setScreenCreateBy(datavScreen.getCreateBy());
		datavCmsTemplateInfo.setCapturePath(datavScreen.getCapturePath());
		datavCmsTemplateInfo.setBelongto(datavScreen.getBelongto());
		datavCmsTemplateInfo.setScreenCreateDate(datavScreen.getCreateTime());
		String themeOption = datavScreen.getThemeOption();
		JSONObject jsonObject = JSON.parseObject(themeOption);
		datavCmsTemplateInfo.setScreenResolution(jsonObject.getString("panelWidth")+"*"+jsonObject.getString("panelHeight"));
		if (Integer.parseInt(jsonObject.getString("panelWidth")) <= 820) {
			datavCmsTemplateInfo.setDeviceType("Phone");
		}
		else{
			datavCmsTemplateInfo.setDeviceType("PC");
		}
		datavCmsTemplateInfoMapper.insertDatavCmsTemplateInfo(datavCmsTemplateInfo);
	}

    /**
     * 批量删除数据大屏
     * 
     * @param screenIds 需要删除的数据大屏ID
     * @return 结果
     */
    @Override
    public int deleteDatavScreenByIds(String[] screenIds)
    {
        return datavScreenMapper.deleteDatavScreenByIds(screenIds);
    }

    /**
     * 删除数据大屏信息
     * 
     * @param screenId 数据大屏ID
     * @return 结果
     */
    @Override
    public int deleteDatavScreenById(String screenId)
    {
		datavCmsTemplateInfoMapper.deleteDatavCmsTemplateInfoById(screenId);
        return datavScreenMapper.deleteDatavScreenById(screenId);
    }

    @Override
    public List<DatavScreen> selectTemplateList() {
        return datavScreenMapper.selectTemplateList();
    }

    /**
     * 查询大屏模板个数
     * @return
     */
	@Override
	public int findScreenCount() {
		return datavScreenMapper.findScreenCount();
	}

	/**
     * 查询自定义组件个数
     * @return
     */
	@Override
	public int findAssemblyCount() {
		return datavScreenMapper.findScreenCount();
	}

	/**
     * 查询近一周每日新增用户数变化趋势
     * @return
     */
	@Override
	public AjaxResult findFirstList(DashboardDto dashboardDto) {
		List<DatavLineVo> list = datavScreenMapper.findFirstList(dashboardDto);
		List<String> createTime = Lists.newArrayList();
		List<Integer> count = Lists.newArrayList();
		Map<String, Object> map = new HashedMap<>();
		
		for(int i = 0; i < list.size(); i++) {
			createTime.add(list.get(i).getCreateTime());
			count.add(list.get(i).getCount());
		}
		
		map.put("createTime", createTime);
		map.put("count", count);
		
		return AjaxResult.success(map);
	}

	/**
     * 查询近一周大屏模板个数变化趋势
     * @return
     */
	@Override
	public AjaxResult findThirdList(DashboardDto dashboardDto) {
		List<DatavLineVo> list = datavScreenMapper.findThirdList(dashboardDto);
		List<String> createTime = Lists.newArrayList();
		List<Integer> count = Lists.newArrayList();
		Map<String, Object> map = new HashedMap<>();
		
		for(int i = 0; i < list.size(); i++) {
			createTime.add(list.get(i).getCreateTime());
			count.add(list.get(i).getCount());
		}
		
		map.put("createTime", createTime);
		map.put("count", count);
		
		return AjaxResult.success(map); 
	}

	/**
     * 查询近一周组件个数变化趋势
     * @return
     */
	@Override
	public AjaxResult findForthList(DashboardDto dashboardDto) {
		List<DatavLineVo> list = datavScreenMapper.findForthList(dashboardDto);
		List<String> createTime = Lists.newArrayList();
		List<Integer> count = Lists.newArrayList();
		Map<String, Object> map = new HashedMap<>();
		
		for(int i = 0; i < list.size(); i++) {
			createTime.add(list.get(i).getCreateTime());
			count.add(list.get(i).getCount());
		}
		
		map.put("createTime", createTime);
		map.put("count", count);
		
		return AjaxResult.success(map); 
	}
	
	/**
     * 查询是否公开的集合信息
     * @return
     */
	@Override
	public AjaxResult findIsPublic() {
		List<DatavMoreVo> list = datavScreenMapper.findIsPublic();
		List<JSONObject> jsonObjects = Lists.newArrayList();
		for(int i = 0; i < list.size(); i++) {
			//封装成echarts图代码格式
			String jsonStr = "{value:" + list.get(i).getCount() + ", name:'" + list.get(i).getMoreName() + "'}";
			JSONObject jsonObject = JSONObject.parseObject(jsonStr); // json字符串变成json对象
			jsonObjects.add(jsonObject);
		}
		return AjaxResult.success(jsonObjects);
	}

	/**
     * 查询是否是否是模板的集合信息
     * @return
     */
	@Override
	public AjaxResult findIsTemplate() {
		List<DatavMoreVo> list = datavScreenMapper.findIsTemplate();
		List<JSONObject> jsonObjects = Lists.newArrayList();
		for(int i = 0; i < list.size(); i++) {
			//封装成echarts图代码格式
			String jsonStr = "{value:" + list.get(i).getCount() + ", name:'" + list.get(i).getMoreName() + "'}";
			JSONObject jsonObject = JSONObject.parseObject(jsonStr); // json字符串变成json对象
			jsonObjects.add(jsonObject);
		}
		return AjaxResult.success(jsonObjects);
	}
	/**
	 * 查询是否是否是模板的集合信息
	 * @return
	 */
	@Override
	public AjaxResult findCustomPie() {
		List<DatavMoreVo> list = datavScreenMapper.findCustomPie();
		List<JSONObject> jsonObjects = Lists.newArrayList();
		for(int i = 0; i < list.size(); i++) {
			//封装成echarts图代码格式
			String jsonStr = "{value:" + list.get(i).getCount() + ", name:'" + list.get(i).getMoreName() + "'}";
			JSONObject jsonObject = JSONObject.parseObject(jsonStr); // json字符串变成json对象
			jsonObjects.add(jsonObject);
		}
		return AjaxResult.success(jsonObjects);
	}
	/**
     * 查询自定义组件数量变化
     * @return
     */
	@Override
	public AjaxResult findCustom() {
		List<DatavLineVo> list = datavScreenMapper.findCustom();
		List<String> createTime = Lists.newArrayList();
		List<Integer> count = Lists.newArrayList();
		Map<String, Object> map = new HashedMap<>();
		
		for(int i = 0; i < list.size(); i++) {
			createTime.add(list.get(i).getCreateTime());
			count.add(list.get(i).getCount());
		}
		
		map.put("createTime", createTime);
		map.put("count", count);
		
		return AjaxResult.success(map);
	}
	
    /**
     * 根据用户名称查询模板数量
     */
	@Override
	public Integer selectDatavScreenByUsername(DatavScreen datavScreen) {
		return datavScreenMapper.selectDatavScreenByUsername(datavScreen);
	}
	
	/**
	 * 根据用户姓名按模板分类查询
	 */
	@Override
	public List<DatavMoreVo> selectDatavScreenByUsernameAndBelongto(DatavScreen datavScreen) {
		return datavScreenMapper.selectDatavScreenByUsernameAndBelongto(datavScreen);
	}

	/**
	 * CMS页面 查询公开模板
	 * @param
	 * @return
	 */
	public List<DatavScreen> selectCMSPublicTemplateList(){
		return datavScreenMapper.selectCMSPublicTemplateList();
	}
}
