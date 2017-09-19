package com.becheer.donation.service.impl;

import com.becheer.donation.dao.ProjectMapper;
import com.becheer.donation.model.Project;
import com.becheer.donation.model.condition.ProjectCondition;
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
    public PageInfo<Project> getProjectList(int pageNum, int pageSize) {
        ProjectCondition condition = new ProjectCondition();
        condition.setOrderByClause("create_time desc");
        PageHelper.startPage(pageNum, pageSize);
        List<Project> data = projectMapper.selectByExample(condition);
        PageInfo<Project> pageInfo = new PageInfo<Project>(data);
        return pageInfo;
    }

    @Override
    public Project getProject(long id) {
        return projectMapper.selectByPrimaryKey(id);
    }
}
