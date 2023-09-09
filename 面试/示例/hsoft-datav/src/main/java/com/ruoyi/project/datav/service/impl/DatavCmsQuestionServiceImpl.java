package com.ruoyi.project.datav.service.impl;
import java.util.HashSet;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.datav.domain.DatavCmsQuestion;
import com.ruoyi.project.datav.domain.DatavCmsQuestionDto;
import com.ruoyi.project.datav.domain.DatavCmsQuestionVo;
import com.ruoyi.project.datav.mapper.DatavCmsQuestionMapper;
import com.ruoyi.project.datav.service.IDatavCmsQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version v1.0
 * @ProjectName: hsoft-datav
 * @ClassName: DatavCmsQuestionServiceImpl
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 王莹
 * @Date: 2022/8/3 11:24
 */
@Service
public class DatavCmsQuestionServiceImpl implements IDatavCmsQuestionService
{
    @Autowired
    private DatavCmsQuestionMapper datavCmsQuestionMapper;

    /**
     * 查询社区问答
     *
     * @param id 社区问答ID
     * @return 社区问答
     */
    @Override
    public DatavCmsQuestionVo selectDatavCmsQuestionById(Long id)
    {
        DatavCmsQuestionVo datavCmsQuestionVO = new DatavCmsQuestionVo();
        datavCmsQuestionVO.setQuestionInfo(datavCmsQuestionMapper.selectDatavCmsQuestionById(id));

        List<DatavCmsQuestionVo> firstCommentList = selectFirstCommentById(id);
        List<DatavCmsQuestionVo> secondCommentList = selectSecondCommentById(id);
        //将二级评论用链表的方式添加到一级评论
        List<DatavCmsQuestionVo> list = addComments(firstCommentList, secondCommentList);
        datavCmsQuestionVO.setCommentNodes(list);
        return datavCmsQuestionVO;
    }

    /**
     * 将评论和对应评论下的回复组合
     */
    private List<DatavCmsQuestionVo> addComments(List<DatavCmsQuestionVo> firstList, List<DatavCmsQuestionVo> thenList){
        for (DatavCmsQuestionVo node : firstList) {
            HashSet<Long> parentIds = new HashSet<>();
            parentIds.add(node.getId());
            int size = thenList.size();
            for (int i = 0; i < size; i++) {
                if(parentIds.contains(thenList.get(i).getParentId())){
                    parentIds.add(thenList.get(i).getId());
                    node.getCommentNodes().add(thenList.get(i));
                    thenList.remove(i);
                    i--;
                    size--;
                }
            }
        }
        return firstList;
    }

    /**
     * 查询问题id下所有的一级评论
     *
     * @param id 社区问答ID
     * @return 社区问答
     */
    @Override
    public List<DatavCmsQuestionVo> selectFirstCommentById(Long id) {
        return datavCmsQuestionMapper.selectFirstCommentById(id);
    }
    /**
     * 查询问题id下所有的二级评论
     *
     * @param id 社区问答ID
     * @return 社区问答
     */
    @Override
    public List<DatavCmsQuestionVo> selectSecondCommentById(Long id) {
        return datavCmsQuestionMapper.selectSecondCommentById(id);
    }

    /**
     * 查询社区问答列表
     *
     * @param datavCmsQuestion 社区问答
     * @return 社区问答
     */
    @Override
    public List<DatavCmsQuestion> selectDatavCmsQuestionList(DatavCmsQuestion datavCmsQuestion)
    {
        return datavCmsQuestionMapper.selectDatavCmsQuestionList(datavCmsQuestion);
    }

    /**
     * 新增社区问答
     *
     * @param datavCmsQuestion 社区问答
     * @return 结果
     */
    @Override
    public int insertDatavCmsQuestion(DatavCmsQuestion datavCmsQuestion)
    {
        String contentText = datavCmsQuestion.getContentText();
        if(contentText != null && contentText.length()>200){
            datavCmsQuestion.setContentText(contentText.substring(0,200));
        }
        datavCmsQuestion.setCreateBy(SecurityUtils.getUsername());
        datavCmsQuestion.setCreateTime(DateUtils.getNowDate());
        return datavCmsQuestionMapper.insertDatavCmsQuestion(datavCmsQuestion);
    }

    /**
     * 修改社区问答
     *
     * @param datavCmsQuestion 社区问答
     * @return 结果
     */
    @Override
    public int updateDatavCmsQuestion(DatavCmsQuestion datavCmsQuestion)
    {
        return datavCmsQuestionMapper.updateDatavCmsQuestion(datavCmsQuestion);
    }

    /**
     * 批量删除社区问答
     *
     * @param ids 需要删除的社区问答ID
     * @return 结果
     */
    @Override
    public int deleteDatavCmsQuestionByIds(Long[] ids)
    {
        return datavCmsQuestionMapper.deleteDatavCmsQuestionByIds(ids);
    }

    /**
     * 删除社区问答信息
     *
     * @param id 社区问答ID
     * @return 结果
     */
    @Override
    public int deleteDatavCmsQuestionById(Long id)
    {
        return datavCmsQuestionMapper.deleteDatavCmsQuestionById(id);
    }
    /**
     * 查询社区问答列表
     *
     * @param datavCmsQuestionDto 社区问答
     * @return 社区问答集合
     */
    @Override
    public List<DatavCmsQuestion> selectQuestionList(DatavCmsQuestionDto datavCmsQuestionDto) {
        return datavCmsQuestionMapper.selectQuestionList(datavCmsQuestionDto);
    }
    /**
     * 浏览数+1
     * @param id
     * @return
     */
    @Override
    public int viewCountIncrease(Long id) {
        return datavCmsQuestionMapper.viewCountIncrease(id);
    }

    /**
     * 最新回复列表
     * @return
     */
    @Override
    public List<DatavCmsQuestion> getLatestReply() {
        return datavCmsQuestionMapper.getLatestReply();
    }
}
