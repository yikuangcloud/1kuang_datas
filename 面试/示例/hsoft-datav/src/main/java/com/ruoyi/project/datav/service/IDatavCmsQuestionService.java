package com.ruoyi.project.datav.service;
import com.ruoyi.project.datav.domain.DatavCmsQuestion;
import com.ruoyi.project.datav.domain.DatavCmsQuestionDto;
import com.ruoyi.project.datav.domain.DatavCmsQuestionVo;

import java.util.List;
public interface IDatavCmsQuestionService
{
    /**
     * 查询社区问答
     *
     * @param id 社区问答ID
     * @return 社区问答
     */
    public DatavCmsQuestionVo selectDatavCmsQuestionById(Long id);
    /**
     * 查询问题id下所有的一级评论
     *
     * @param id 社区问答ID
     * @return 社区问答
     */
    public List<DatavCmsQuestionVo> selectFirstCommentById(Long id);
    /**
     * 查询问题id下所有的二级评论
     *
     * @param id 社区问答ID
     * @return 社区问答
     */
    public List<DatavCmsQuestionVo> selectSecondCommentById(Long id);

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
     * 批量删除社区问答
     *
     * @param ids 需要删除的社区问答ID
     * @return 结果
     */
    public int deleteDatavCmsQuestionByIds(Long[] ids);

    /**
     * 删除社区问答信息
     *
     * @param id 社区问答ID
     * @return 结果
     */
    public int deleteDatavCmsQuestionById(Long id);

    /**
     * 查询社区问答列表
     *
     * @param datavCmsQuestionDto 社区问答
     * @return 社区问答集合
     */
    public List<DatavCmsQuestion> selectQuestionList(DatavCmsQuestionDto datavCmsQuestionDto);
    /**
     * 浏览数+1
     * @param id
     * @return
     */
    int viewCountIncrease(Long id);

    /**
     * 最新回复列表
     * @return
     */
    public List<DatavCmsQuestion> getLatestReply();
}
