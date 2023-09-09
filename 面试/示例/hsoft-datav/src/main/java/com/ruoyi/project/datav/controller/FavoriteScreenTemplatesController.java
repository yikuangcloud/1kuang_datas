package com.ruoyi.project.datav.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.datav.domain.FavoriteScreenTemplatesVo;
import org.aspectj.weaver.loadtime.Aj;
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
import com.ruoyi.project.datav.domain.FavoriteScreenTemplates;
import com.ruoyi.project.datav.service.IFavoriteScreenTemplatesService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 素材收藏Controller
 * 
 * @author hanchao
 * @date 2022-07-18
 */
@RestController
@RequestMapping("/templates/favorite")
public class FavoriteScreenTemplatesController extends BaseController
{
    @Autowired
    private IFavoriteScreenTemplatesService favoriteScreenTemplatesService;

    /**
     * 查询素材收藏列表
     */
    @PreAuthorize("@ss.hasPermi('templates:favorite:list')")
    @GetMapping("/list")
    public TableDataInfo list(FavoriteScreenTemplates favoriteScreenTemplates)
    {
        startPage();
        List<FavoriteScreenTemplates> list = favoriteScreenTemplatesService.selectFavoriteScreenTemplatesList(favoriteScreenTemplates);
        return getDataTable(list);
    }

    /**
     * 根据用户查询收藏列表
     * @param favoriteScreenTemplates
     * @return
     */
    @GetMapping("/user/list")
    public AjaxResult getListByUser(FavoriteScreenTemplates favoriteScreenTemplates)
    {
        favoriteScreenTemplates.setUid(SecurityUtils.getLoginUser().getUser().getUserId());
        List<FavoriteScreenTemplatesVo> list = favoriteScreenTemplatesService.selectFavoriteListByUser(favoriteScreenTemplates);
        //分为 模板列表和图片素材列表
        Map<String, List<FavoriteScreenTemplatesVo>> map = list.stream().collect(Collectors.groupingBy(item -> {
            if (item.getScreenId() == null || item.getScreenId().equals("")) {
              return "file";
            }
            return "screen";
        }));

        return AjaxResult.success((map));
    }

    /**
     * 导出素材收藏列表
     */
    @PreAuthorize("@ss.hasPermi('templates:favorite:export')")
    @Log(title = "素材收藏", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(FavoriteScreenTemplates favoriteScreenTemplates)
    {
        List<FavoriteScreenTemplates> list = favoriteScreenTemplatesService.selectFavoriteScreenTemplatesList(favoriteScreenTemplates);
        ExcelUtil<FavoriteScreenTemplates> util = new ExcelUtil<FavoriteScreenTemplates>(FavoriteScreenTemplates.class);
        return util.exportExcel(list, "favorite");
    }

    /**
     * 获取素材收藏详细信息
     */
    @PreAuthorize("@ss.hasPermi('templates:favorite:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(favoriteScreenTemplatesService.selectFavoriteScreenTemplatesById(id));
    }

    /**
     * 素材收藏
     */
//    @PreAuthorize("@ss.hasPermi('templates:favorite:add')")
    @Log(title = "素材收藏", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult templateStar(@RequestBody FavoriteScreenTemplates favoriteScreenTemplates)
    {
        String msg;
        Boolean result = favoriteScreenTemplatesService.templateStar(favoriteScreenTemplates);
        if (result) {
            msg = "收藏成功";
        }
        else {
            msg = "取消收藏";
        }
        return AjaxResult.success(msg,result);
    }

    /**
     * 修改素材收藏
     */
    @PreAuthorize("@ss.hasPermi('templates:favorite:edit')")
    @Log(title = "素材收藏", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FavoriteScreenTemplates favoriteScreenTemplates)
    {
        return toAjax(favoriteScreenTemplatesService.updateFavoriteScreenTemplates(favoriteScreenTemplates));
    }

    /**
     * 删除素材收藏
     */
    @PreAuthorize("@ss.hasPermi('templates:favorite:remove')")
    @Log(title = "素材收藏", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(favoriteScreenTemplatesService.deleteFavoriteScreenTemplatesByIds(ids));
    }

    /**
     * 验证模板是否被收藏
     */
    @PostMapping("/isExisted")
    public AjaxResult isExisted(@RequestBody FavoriteScreenTemplates favoriteScreenTemplates)
    {
        FavoriteScreenTemplates result = favoriteScreenTemplatesService.isExisted(favoriteScreenTemplates);
        if(result == null){
            return AjaxResult.success("未收藏",false);
        }
        else {
            return AjaxResult.success("已收藏",true);
        }
    }
}
