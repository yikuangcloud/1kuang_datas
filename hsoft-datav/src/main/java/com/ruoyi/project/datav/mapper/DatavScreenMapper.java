package com.ruoyi.project.datav.mapper;

import java.util.List;

import com.ruoyi.project.datav.domain.DatavLineVo;
import com.ruoyi.project.datav.domain.DatavMoreVo;
import com.ruoyi.project.datav.domain.DatavScreen;
import com.ruoyi.project.system.domain.dto.DashboardDto;

/**
 * 数据大屏Mapper接口
 * 
 * @author 刘佳男[风清扬]
 * @date 2021-01-20
 */
public interface DatavScreenMapper 
{
    /**
     * 查询数据大屏
     * 
     * @param screenId 数据大屏ID
     * @return 数据大屏
     */
    public DatavScreen selectDatavScreenById(String screenId);

    /**
     * 查询数据大屏列表
     * 
     * @param datavScreen 数据大屏
     * @return 数据大屏集合
     */
    public List<DatavScreen> selectDatavScreenList(DatavScreen datavScreen);

    /**
     * 新增数据大屏
     * 
     * @param datavScreen 数据大屏
     * @return 结果
     */
    public int insertDatavScreen(DatavScreen datavScreen);

    /**
     * 修改数据大屏
     * 
     * @param datavScreen 数据大屏
     * @return 结果
     */
    public int updateDatavScreen(DatavScreen datavScreen);

    /**
     * 删除数据大屏
     * 
     * @param screenId 数据大屏ID
     * @return 结果
     */
    public int deleteDatavScreenById(String screenId);

    /**
     * 批量删除数据大屏
     * 
     * @param screenIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteDatavScreenByIds(String[] screenIds);

    /**
     * 查询大屏模板
     * @return
     */
    public List<DatavScreen> selectTemplateList();

    /**
     * CMS页面 查询公开模板
     * @return
     */
    public List<DatavScreen> selectCMSPublicTemplateList();

    /**
     * 查询大屏模板个数
     * @return
     */
    public int findScreenCount();
    
    /**
     * 查询自定义组件个数
     * @return
     */
    public int findAssemblyCount();
    
    /**
     * 查询近一周每日新增用户数变化趋势
     * @return
     */
    public List<DatavLineVo> findFirstList(DashboardDto dashboardDto);
    
    /**
     * 查询近一周大屏模板个数变化趋势
     * @return
     */
    public List<DatavLineVo> findThirdList(DashboardDto dashboardDto);
    
    /**
     * 查询近一周组件个数变化趋势
     * @return
     */
    public List<DatavLineVo> findForthList(DashboardDto dashboardDto);
    
    /**
     * 查询是否公开的集合信息
     * @return
     */
    public List<DatavMoreVo> findIsPublic();
    
    /**
     * 查询是否是否是模板的集合信息
     * @return
     */
    public List<DatavMoreVo> findIsTemplate();
    /**
     * 查询自定义组件分类
     * @return
     */
    public List<DatavMoreVo> findCustomPie();
    
    /**
     * 查询自定义组件数量变化
     * @return
     */
    public List<DatavLineVo> findCustom();
    
    /**
     * 根据用户名称查询模板
     * @param datavScreen
     * @return
     */
	public Integer selectDatavScreenByUsername(DatavScreen datavScreen);
	
	/**
	 * 根据用户姓名按模板分类查询
	 * @param datavScreen
	 * @return
	 */
	public List<DatavMoreVo> selectDatavScreenByUsernameAndBelongto(DatavScreen datavScreen);
}
