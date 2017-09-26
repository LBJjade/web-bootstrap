package com.becheer.donation.dao;

/*
* ProjectTypeMapper
* Creator : xiaokepu
* Date : 2017-09-21
*/

import com.becheer.donation.model.extension.project.ListProjectTypeExtension;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProjectTypeMapper {
    List<ListProjectTypeExtension> SelectProjectType ();
}
