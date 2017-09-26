package com.becheer.donation.dao;

import com.becheer.donation.model.Project;
import com.becheer.donation.model.condition.ProjectCondition;
import com.becheer.donation.model.extension.project.ListProjectExtension;
import com.becheer.donation.model.extension.project.ProjectDetailExtension;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProjectMapper {

    List<ListProjectExtension> SelectByCondition(ProjectCondition condition);

    List<ListProjectExtension> SelectRelationProject(long typeId,long projectId,int nums);

//    List<ListProjectExtension> SelectProjectByTypeId(long projectTypeId,int nums);

    ProjectDetailExtension SelectProjectDetail(long projectId);

}
