package com.becheer.donation.dao;

/*
* BlockMapper
* Creator : xiaokepu
* Date : 2017-09-06
*/

import com.becheer.donation.model.extension.ListArticleExtension;
import com.becheer.donation.model.extension.ListProjectExtension;

import java.util.List;

public interface BlockMapper {

    //根据板块ID获取板块下的项目
    List<ListProjectExtension> getProjectById(long blockId);

    //根据板块ID获取板块下的文章
    List<ListArticleExtension> getArticleById(long blockId);

}
