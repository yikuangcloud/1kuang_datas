package com.ruoyi.project.datav.controller;
import java.util.List;

import com.ruoyi.project.datav.domain.DatavUserEcharts;
import com.ruoyi.project.datav.service.IDatavUserEchartsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: DatavUserEchartsController
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 王莹
 * @Date: 2022/7/28 16:22
 */
@RestController
@RequestMapping("/datav/user/echarts")
public class DatavUserEchartsController extends BaseController {
    @Autowired
    private IDatavUserEchartsService datavUserEchartsService;

    /**
     * 查询用户收藏echarts素材关联列表
     */
//    @PreAuthorize("@ss.hasPermi('datav:echarts:list')")
    @GetMapping("/list")
    public TableDataInfo list(DatavUserEcharts datavUserEcharts) {
        startPage();
        List<DatavUserEcharts> list = datavUserEchartsService.selectDatavUserEchartsList(datavUserEcharts);
        return getDataTable(list);
    }

    /**
     * 导出用户收藏echarts素材关联列表
     */
//    @PreAuthorize("@ss.hasPermi('datav:echarts:export')")
    @Log(title = "用户收藏echarts素材关联", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(DatavUserEcharts datavUserEcharts) {
        List<DatavUserEcharts> list = datavUserEchartsService.selectDatavUserEchartsList(datavUserEcharts);
        ExcelUtil<DatavUserEcharts> util = new ExcelUtil<DatavUserEcharts>(DatavUserEcharts.class);
        return util.exportExcel(list, "echarts");
    }

    /**
     * 获取用户收藏echarts素材关联详细信息
     */
//    @PreAuthorize("@ss.hasPermi('datav:echarts:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(datavUserEchartsService.selectDatavUserEchartsById(id));
    }

    /**
     * 新增用户收藏echarts素材关联
     */
//    @PreAuthorize("@ss.hasPermi('datav:echarts:add')")
    @Log(title = "用户收藏echarts素材关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DatavUserEcharts datavUserEcharts) {
        return toAjax(datavUserEchartsService.insertDatavUserEcharts(datavUserEcharts));
    }

    /**
     * 修改用户收藏echarts素材关联
     */
//    @PreAuthorize("@ss.hasPermi('datav:echarts:edit')")
    @Log(title = "用户收藏echarts素材关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DatavUserEcharts datavUserEcharts) {
        return toAjax(datavUserEchartsService.updateDatavUserEcharts(datavUserEcharts));
    }

    /**
     * 删除用户收藏echarts素材关联
     */
//    @PreAuthorize("@ss.hasPermi('datav:echarts:remove')")
    @Log(title = "用户收藏echarts素材关联", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(datavUserEchartsService.deleteDatavUserEchartsByIds(ids));
    }

    /**
     * 取消收藏
     * @param datavUserEcharts
     * @return
     */
    @PostMapping(value = "/del")
    public AjaxResult del(@RequestBody DatavUserEcharts datavUserEcharts) {
        return toAjax(datavUserEchartsService.delDatavUserEcharts(datavUserEcharts));
    }
}
