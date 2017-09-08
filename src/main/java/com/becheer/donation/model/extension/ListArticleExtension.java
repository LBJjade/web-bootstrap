package com.becheer.donation.model.extension;

/*
* 扩展类，列表所显示的文章实体
* Creator : xiaokepu
* Date :  2017-09-06
*/

public class ListArticleExtension {

    //文章ID
    private long articleId;

    //文章标题
    private String title;

    //文章摘要
    private String summary;

    //创建时间
    private String createTime;

    //图片路径
    private String img;

    //排序
    private int sort;

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
