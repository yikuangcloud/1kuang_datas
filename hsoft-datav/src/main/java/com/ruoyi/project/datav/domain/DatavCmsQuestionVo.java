package com.ruoyi.project.datav.domain;

import java.util.ArrayList;
import java.util.List;

public class DatavCmsQuestionVo extends DatavCmsQuestion {

    /** 问题描述*/
    private DatavCmsQuestionVo questionInfo;

    /** 评论父节点用户*/
    private String parentUsername;

    /** 评论*/
    private List<DatavCmsQuestionVo> commentNodes  = new ArrayList<>();

    public DatavCmsQuestionVo getQuestionInfo() {
        return questionInfo;
    }

    public void setQuestionInfo(DatavCmsQuestionVo questionInfo) {
        this.questionInfo = questionInfo;
    }

    public String getParentUsername() {
        return parentUsername;
    }

    public void setParentUsername(String parentUsername) {
        this.parentUsername = parentUsername;
    }

    public List<DatavCmsQuestionVo> getCommentNodes() {
        return commentNodes;
    }

    public void setCommentNodes(List<DatavCmsQuestionVo> commentNodes) {
        this.commentNodes = commentNodes;
    }
}
