package com.becheer.donation.dao;

import com.becheer.donation.model.ProjectProgress;
import com.becheer.donation.model.condition.ProjectProgressCondition;

import java.util.List;

/*
* ProjectProgressMapper
* Creator : xiaokepu
* Date : 2017-09-25
*/
public interface ProjectProgressMapper {
    List<ProjectProgress> SelectByCondition(ProjectProgressCondition condition);
}
