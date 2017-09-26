package com.becheer.donation.service.impl;

import com.becheer.donation.dao.ProjectTypeMapper;
import com.becheer.donation.model.extension.project.ListProjectTypeExtension;
import com.becheer.donation.service.IProjectTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
* 
* Creator : xiaokepu
* Date : 
*/

@Service
public class ProjectTypeServiceImpl implements IProjectTypeService {

    @Resource
    ProjectTypeMapper projectTypeMapper;

    @Override
    public List<ListProjectTypeExtension> GetProjectType() {
        return projectTypeMapper.SelectProjectType();
    }
}
