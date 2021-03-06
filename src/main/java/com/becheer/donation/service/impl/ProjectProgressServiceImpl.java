package com.becheer.donation.service.impl;

import com.becheer.donation.configs.FileConfig;
import com.becheer.donation.dao.ProjectProgressMapper;
import com.becheer.donation.model.ProjectProgress;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.condition.ProjectProgressCondition;
import com.becheer.donation.service.IProjectProgressService;
import com.becheer.donation.service.SpringContextUtil;
import com.becheer.donation.strings.Message;
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
        ProjectProgressCondition condition = new ProjectProgressCondition();
        condition.setOrderByClause("create_time desc");
        condition.createCriteria().addEnable(1).andProjectIdEqualTo(projectId);
        PageHelper.startPage(pageNum, pageSize);
        List<ProjectProgress> data = projectProgressMapper.SelectByCondition(condition);
        String fileRoot = ((FileConfig) SpringContextUtil.getBean("fileConfig")).getFileRoot();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getContent() != null) {
                data.get(i).setContent(data.get(i).getContent().replaceAll(Message.REPLACE_HOLDER_PROJECT_CONTENT, fileRoot));
            }
        }
        PageInfo<ProjectProgress> pageInfo = new PageInfo<ProjectProgress>(data);
        return pageInfo;
    }

    @Override
    public ResponseDto insert(long id, String title, String summary, String content, int status) {
        ProjectProgress projectProgress=new ProjectProgress();
        projectProgress.setProjectId(id);
        projectProgress.setTitle(title);
        projectProgress.setSummary(summary);
        projectProgress.setContent(content);
        projectProgress.setStatus(status);
        int result = projectProgressMapper.insertProjectProject(projectProgress);
        if(result > 0){
            return new ResponseDto(200,"success",result);
        }else{
            return new ResponseDto(500,"error");
        }
    }

    @Override
    public ResponseDto batchInsert(List<ProjectProgress> projectProgresses) {
        int result = projectProgressMapper.batchInsertProjectProject(projectProgresses);
        if (result > 0) {
            return new ResponseDto(200, "success", result);
        } else {
            return new ResponseDto(500, "error");
        }
    }
}
