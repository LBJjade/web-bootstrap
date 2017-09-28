package com.becheer.donation.service.impl;

import com.becheer.donation.dao.ProjectProgressMapper;
import com.becheer.donation.model.ProjectProgress;
import com.becheer.donation.model.condition.ProjectProgressCondition;
import com.becheer.donation.service.IProjectProgressService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
* ProjectProgressService
* Creator : xiaokepu
* Date : 2017-09-25
*/
@Service
public class ProjectProgressServiceImpl implements IProjectProgressService {

    @Resource
    private ProjectProgressMapper projectProgressMapper;

    @Override
    public PageInfo<ProjectProgress> GetProjectProgress(long projectId, int pageSize, int pageNum) {
        ProjectProgressCondition condition=new ProjectProgressCondition();
        condition.setOrderByClause("create_time desc");
        condition.createCriteria().addEnable(1).andProjectIdEqualTo(projectId);
        PageHelper.startPage(pageNum,pageSize);
        List<ProjectProgress> data=projectProgressMapper.SelectByCondition(condition);
        PageInfo<ProjectProgress> pageInfo = new PageInfo<ProjectProgress>(data);
        return pageInfo;
    }
}