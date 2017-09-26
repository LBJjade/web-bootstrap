package com.becheer.donation.service.impl;

import com.becheer.donation.dao.ProjectMapper;
import com.becheer.donation.dao.ProjectTypeMapper;
import com.becheer.donation.model.Project;
import com.becheer.donation.model.condition.ProjectCondition;
import com.becheer.donation.model.extension.project.ListProjectExtension;
import com.becheer.donation.model.extension.project.ProjectDetailExtension;
import com.becheer.donation.service.IProjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectServiceImpl implements IProjectService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Resource
    private ProjectMapper projectMapper;

    @Override
    public PageInfo<ListProjectExtension> GetProjectList(int pageNum, int pageSize) {
        ProjectCondition condition = new ProjectCondition();
        condition.setOrderByClause("create_time desc");
        condition.createCriteria().addEnable(1);
        PageHelper.startPage(pageNum, pageSize);
        List<ListProjectExtension> data = projectMapper.SelectByCondition(condition);
        PageInfo<ListProjectExtension> pageInfo = new PageInfo<ListProjectExtension>(data);
        return pageInfo;
    }

    @Override
    public List<ListProjectExtension> GetRelationProject(long typeId, long projectId, int nums) {
        List<ListProjectExtension> data =projectMapper.SelectRelationProject(typeId,projectId,nums);
        return data;
    }

    @Override
    public PageInfo<ListProjectExtension> GetProjectList(int pageNum, int pageSize, int projectTypeId) {
        ProjectCondition condition=new ProjectCondition();
        condition.setOrderByClause("create_time desc");
        condition.createCriteria().addEnable(1).addProjectType(projectTypeId);
        PageHelper.startPage(pageNum,pageSize);
        List<ListProjectExtension> data = projectMapper.SelectByCondition(condition);
        PageInfo<ListProjectExtension> pageInfo = new PageInfo<ListProjectExtension>(data);
        return pageInfo;
    }

    @Override
    public ProjectDetailExtension GetProjectDetail(long id) {
        return projectMapper.SelectProjectDetail(id);
    }
}