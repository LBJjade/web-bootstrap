package com.becheer.donation.model.extension;

/*
* 文章类别扩展类
* Creator : xiaokepu
* Date : 2017-09-17
*/

public class ArticleTypeExtension {

    public long id;

    public long parentId;

    public String articleTypeName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getArticleTypeName() {
        return articleTypeName;
    }

    public void setArticleTypeName(String articleTypeName) {
        this.articleTypeName = articleTypeName;
    }
}
