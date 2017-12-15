package com.becheer.donation.dao;

import com.becheer.donation.model.ProjectProgress;
import com.becheer.donation.model.condition.ProjectProgressCondition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/*
* ProjectProgressMapper
* Creator : xiaokepu
* Date : 2017-09-25
*/
@Component
public interface ProjectProgressMapper {
    List<ProjectProgress> SelectByCondition(ProjectProgressCondition condition);

    int insertProjectProject(ProjectProgress projectProgress);

    int batchInsertProjectProject(List<ProjectProgress> projectProgresses);
}
