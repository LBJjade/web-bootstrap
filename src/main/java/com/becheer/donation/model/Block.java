package com.becheer.donation.model;

/*
* 板块类
* Creator : xiaokepu
* Date : 2017-09-06
*/

import com.becheer.donation.model.base.BaseModel;

public class Block extends BaseModel {

    private long id;

    private String blockName;

    private String summary;

    private int articleLimit;

    private int enable;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getArticleLimit() {
        return articleLimit;
    }

    public void setArticleLimit(int articleLimit) {
        this.articleLimit = articleLimit;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }
}
