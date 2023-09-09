package com.ruoyi.project.datav.mapper;

import java.util.List;
import com.ruoyi.project.datav.domain.DatavCmsQuestion;
import com.ruoyi.project.datav.domain.DatavCmsQuestionDto;
import com.ruoyi.project.datav.domain.DatavCmsQuestionVo;

public interface DatavCmsQuestionMapper {
    /**
     * 查询社区问答
     *
     * @param id 社区问答ID
     * @return 社区问答
     */
    public DatavCmsQuestionVo selectDatavCmsQuestionById(Long id);

    /**
     * 查询社区问答列表
     *
     * @param datavCmsQuestion 社区问答
     * @return 社区问答集合
     */
    public List<DatavCmsQuestion> selectDatavCmsQuestionList(DatavCmsQuestion datavCmsQuestion);

    /**
     * 新增社区问答
     *
     * @param datavCmsQuestion 社区问答
     * @return 结果
     */
    public int insertDatavCmsQuestion(DatavCmsQuestion datavCmsQuestion);

    /**
     * 修改社区问答
     *
     * @param datavCmsQuestion 社区问答
     * @return 结果
     */
    public int updateDatavCmsQuestion(DatavCmsQuestion datavCmsQuestion);

    /**
     * 删除社区问答
     *
     * @param id 社区问答ID
     * @return 结果
     */
    public int deleteDatavCmsQuestionById(Long id);

    /**
     * 批量删除社区问答
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDatavCmsQuestionByIds(Long[] ids);
    /**
     * 查询社区问答列表
     *
     * @param datavCmsQuestionDto 社区问答
     * @return 社区问答集合
     */
    List<DatavCmsQuestion> selectQuestionList(DatavCmsQuestionDto datavCmsQuestionDto);
    /**
     * 浏览数+1
     * @param id
     * @return
     */
    int viewCountIncrease(Long id);

    /**
     * 查询问题id下所有的一级评论
     *
     * @param id 社区问答ID
     * @return 社区问答
     */
    List<DatavCmsQuestionVo> selectFirstCommentById(Long id);
    /**
     * 查询问题id下所有的二级评论
     *
     * @param id 社区问答ID
     * @return 社区问答
     */
    List<DatavCmsQuestionVo> selectSecondCommentById(Long id);

    /**
     * 最新回复列表
     * @return
     */
    List<DatavCmsQuestion> getLatestReply();
}
