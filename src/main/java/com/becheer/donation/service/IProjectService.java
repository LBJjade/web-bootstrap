package com.becheer.donation.service;

import com.becheer.donation.model.Project;
import com.github.pagehelper.PageInfo;

public interface IProjectService {

    /**
     * 根据主键id获取项目
    */
    Project getProject(long id);

    /**
     * 获取项目列表
     */
    PageInfo<Project> getProjectList(int pageNum,int pageSize);
}
