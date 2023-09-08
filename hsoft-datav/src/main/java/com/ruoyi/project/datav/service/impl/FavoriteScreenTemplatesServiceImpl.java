package com.ruoyi.project.datav.service.impl;

import java.util.List;

import com.ruoyi.project.datav.domain.FavoriteScreenTemplatesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.datav.mapper.DatavCmsTemplateInfoMapper;
import com.ruoyi.project.datav.mapper.FavoriteScreenTemplatesMapper;
import com.ruoyi.project.datav.domain.FavoriteScreenTemplates;
import com.ruoyi.project.datav.service.IFavoriteScreenTemplatesService;
import com.ruoyi.common.utils.SecurityUtils;
/**
 * 素材收藏Service业务层处理
 * 
 * @author hanchao
 * @date 2022-07-18
 */
@Service
public class FavoriteScreenTemplatesServiceImpl implements IFavoriteScreenTemplatesService 
{
    @Autowired
    private FavoriteScreenTemplatesMapper favoriteScreenTemplatesMapper;
    @Autowired
    private DatavCmsTemplateInfoMapper datavCmsTemplateInfoMapper;

    /**
     * 查询素材收藏
     * 
     * @param id 素材收藏ID
     * @return 素材收藏
     */
    @Override
    public FavoriteScreenTemplates selectFavoriteScreenTemplatesById(Long id)
    {
        return favoriteScreenTemplatesMapper.selectFavoriteScreenTemplatesById(id);
    }

    /**
     * 查询素材收藏列表
     * 
     * @param favoriteScreenTemplates 素材收藏
     * @return 素材收藏
     */
    @Override
    public List<FavoriteScreenTemplates> selectFavoriteScreenTemplatesList(FavoriteScreenTemplates favoriteScreenTemplates)
    {
        return favoriteScreenTemplatesMapper.selectFavoriteScreenTemplatesList(favoriteScreenTemplates);
    }

    /**
     * 素材收藏
     * 
     * @param favoriteScreenTemplates 素材收藏
     * @return 结果
     */
    @Override
    public Boolean templateStar(FavoriteScreenTemplates favoriteScreenTemplates)
    {
        FavoriteScreenTemplates result = isExisted(favoriteScreenTemplates);
        if(result == null){//说明素材没有被收藏，下一步插入数据，收藏数加一
            favoriteScreenTemplatesMapper.insertFavoriteScreenTemplates(favoriteScreenTemplates);
            return true;
        }
        else{  //说明素材已经被收藏，下一步取消收藏，收藏数减一
            favoriteScreenTemplatesMapper.deleteFavoriteScreenTemplatesById(result.getId());
            return false;
        }

    }
    /**
     * 验证是否已经被收藏
     */
    @Override
    public FavoriteScreenTemplates isExisted(FavoriteScreenTemplates favoriteScreenTemplates){
        favoriteScreenTemplates.setUid(SecurityUtils.getLoginUser().getUser().getUserId());
        return favoriteScreenTemplatesMapper.isExisted(favoriteScreenTemplates);
    }
    /**
     * 查询用户素材收藏列表
     *
     * @param favoriteScreenTemplates 素材收藏
     * @return 素材收藏集合
     */
    @Override
    public List<FavoriteScreenTemplatesVo> selectFavoriteListByUser(FavoriteScreenTemplates favoriteScreenTemplates) {
        return favoriteScreenTemplatesMapper.selectFavoriteListByUser(favoriteScreenTemplates);
    }

    /**
     * 修改素材收藏
     * 
     * @param favoriteScreenTemplates 素材收藏
     * @return 结果
     */
    @Override
    public int updateFavoriteScreenTemplates(FavoriteScreenTemplates favoriteScreenTemplates)
    {
        return favoriteScreenTemplatesMapper.updateFavoriteScreenTemplates(favoriteScreenTemplates);
    }

    /**
     * 批量删除素材收藏
     * 
     * @param ids 需要删除的素材收藏ID
     * @return 结果
     */
    @Override
    public int deleteFavoriteScreenTemplatesByIds(Long[] ids)
    {
        return favoriteScreenTemplatesMapper.deleteFavoriteScreenTemplatesByIds(ids);
    }

    /**
     * 删除素材收藏信息
     * 
     * @param id 素材收藏ID
     * @return 结果
     */
    @Override
    public int deleteFavoriteScreenTemplatesById(Long id)
    {
        return favoriteScreenTemplatesMapper.deleteFavoriteScreenTemplatesById(id);
    }
}
