package com.becheer.donation.model.extension;

/*
* 扩展类，列表所显示的文章实体
* Creator : xiaokepu
* Date :  2017-09-06
*/

public class ListArticleExtension {

    //文章ID
    private long ArticleId;

    //文章标题
    private String Title;

    //文章摘要
    private String Summary;

    //排序
    private int Sort;

    public long getArticleId() {
        return ArticleId;
    }

    public void setArticleId(long articleId) {
        ArticleId = articleId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public int getSort() {
        return Sort;
    }

    public void setSort(int sort) {
        Sort = sort;
    }
}
