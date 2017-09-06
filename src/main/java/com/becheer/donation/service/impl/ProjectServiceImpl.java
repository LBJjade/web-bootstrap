package com.becheer.donation.service.impl;

import com.becheer.donation.constant.WebConst;
import com.becheer.donation.dao.ContentVoMapper;
import com.becheer.donation.dao.MetaVoMapper;
import com.becheer.donation.dao.ProjectMapper;
import com.becheer.donation.dto.Types;
import com.becheer.donation.exception.TipException;
import com.becheer.donation.model.ContentVo;
import com.becheer.donation.model.ContentVoExample;
import com.becheer.donation.model.Project;
import com.becheer.donation.model.ProjectCondition;
import com.becheer.donation.service.IMetaService;
import com.becheer.donation.service.IProjectService;
import com.becheer.donation.service.IRelationshipService;
import com.becheer.donation.utils.DateKit;
import com.becheer.donation.utils.TaleUtils;
import com.becheer.donation.utils.Tools;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vdurmont.emoji.EmojiParser;
import org.apache.commons.lang3.StringUtils;
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
        LOGGER.debug("Enter getProjectList method");
        ProjectCondition condition = new ProjectCondition();
        condition.setOrderByClause("create_time desc");
        PageHelper.startPage(pageNum, pageSize);
        List<Project> data = projectMapper.selectByExample(condition);
        PageInfo<Project> pageInfo = new PageInfo<Project>(data);
        LOGGER.debug("Exit getProjectList method");
        return pageInfo;
    }

    @Override
    public Project getProject(long id) {
        return projectMapper.selectByPrimaryKey(id);
    }
}
