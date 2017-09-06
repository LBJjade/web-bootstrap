package com.becheer.donation.model.extension;

/*
* 扩展类，列表所展示的项目实体
* Creator : xiaokepu
* Date : 2017-09-06
*/

public class ListProjectExtension {

    //项目ID
    private long projectId;

    //项目标题
    private String title;

    //创建日期
    private String createDate;

    //项目简介
    private String summary;

    //排序
    private int sort;

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

}
