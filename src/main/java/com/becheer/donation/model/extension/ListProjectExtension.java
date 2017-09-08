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
    private String projectName;

    //创建日期
    private String createTime;

    //项目简介
    private String summary;

    //图片路径
    private String img;

    //排序
    private int sort;

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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
