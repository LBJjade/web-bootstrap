package com.becheer.donation.dao;

import com.becheer.donation.model.Project;
import com.becheer.donation.model.condition.ProjectCondition;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProjectMapper {

    List<Project> selectByExample(ProjectCondition condition);

    Project selectByPrimaryKey(long id);
}
