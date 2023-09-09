package com.ruoyi.project.datav.mapper;

import java.util.List;
import com.ruoyi.project.datav.domain.FavoriteScreenTemplates;
import com.ruoyi.project.datav.domain.FavoriteScreenTemplatesVo;

/**
 * 素材收藏Mapper接口
 * 
 * @author hanchao
 * @date 2022-07-18
 */
public interface FavoriteScreenTemplatesMapper 
{
    /**
     * 查询素材收藏
     * 
     * @param id 素材收藏ID
     * @return 素材收藏
     */
    public FavoriteScreenTemplates selectFavoriteScreenTemplatesById(Long id);

    /**
     * 查询素材收藏列表
     * 
     * @param favoriteScreenTemplates 素材收藏
     * @return 素材收藏集合
     */
    public List<FavoriteScreenTemplates> selectFavoriteScreenTemplatesList(FavoriteScreenTemplates favoriteScreenTemplates);

    /**
     * 新增素材收藏
     * 
     * @param favoriteScreenTemplates 素材收藏
     * @return 结果
     */
    public int insertFavoriteScreenTemplates(FavoriteScreenTemplates favoriteScreenTemplates);

    /**
     * 验证素材是否收藏
     *
     * @param favoriteScreenTemplates 素材收藏
     * @return 结果
     */
    public FavoriteScreenTemplates isExisted(FavoriteScreenTemplates favoriteScreenTemplates);

    /**
     * 修改素材收藏
     * 
     * @param favoriteScreenTemplates 素材收藏
     * @return 结果
     */
    public int updateFavoriteScreenTemplates(FavoriteScreenTemplates favoriteScreenTemplates);

    /**
     * 删除素材收藏
     * 
     * @param id 素材收藏ID
     * @return 结果
     */
    public int deleteFavoriteScreenTemplatesById(Long id);

    /**
     * 批量删除素材收藏
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFavoriteScreenTemplatesByIds(Long[] ids);
    /**
     * 查询用户素材收藏列表
     *
     * @param favoriteScreenTemplates 素材收藏
     * @return 素材收藏集合
     */
    List<FavoriteScreenTemplatesVo> selectFavoriteListByUser(FavoriteScreenTemplates favoriteScreenTemplates);
}
