package com.becheer.donation.service;

import com.becheer.donation.model.Project;
import com.becheer.donation.model.extension.project.ListProjectExtension;
import com.becheer.donation.model.extension.project.MemberProjectDetailExtension;
import com.becheer.donation.model.extension.project.MemberProjectExtension;
import com.becheer.donation.model.extension.project.ProjectDetailExtension;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IProjectService {

    /**
     * 根据主键id获取项目
    */
    ProjectDetailExtension GetProjectDetail(long id);

    /**
     * 获取项目列表
     */
    PageInfo<ListProjectExtension> GetProjectList(int pageNum,int pageSize);

    /**
     * 根据项目类别获取项目
     */
    List<ListProjectExtension> GetRelationProject(long typeId,long projectId,int nums);

    /**
     * 根据项目类别获取项目
     * @param pageNum
     * @param pageSize
     * @param projectTypeId
     * @return
     */
    PageInfo<ListProjectExtension> GetProjectList(int pageNum,int pageSize,long projectTypeId);

    /**
     * 获取所有项目
     */
    List<ListProjectExtension> GetProjectList(long projectTypeId);

    /**
     *获取会员参与的项目
     */
    PageInfo<MemberProjectExtension>GetProjectList(long memberId, int pageNum, int pageSize);

    /**
     * 获取会员参与的项目详情
     */
    MemberProjectDetailExtension GetMemberProjectDetail(long contractProjectId);
}
