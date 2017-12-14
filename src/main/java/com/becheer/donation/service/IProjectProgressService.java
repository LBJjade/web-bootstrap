package com.becheer.donation.service;

/*
* IProjectProgressService
* Creator : xiaokepu
* Date : 2017-09-25
*/

import com.becheer.donation.model.ProjectProgress;
import com.becheer.donation.model.base.ResponseDto;
import com.github.pagehelper.PageInfo;

public interface IProjectProgressService {
    PageInfo<ProjectProgress> GetProjectProgress(long projectId,int pageSize,int pageNum);

    ResponseDto insert(long id, String title, String summary, String content, int status);
}
