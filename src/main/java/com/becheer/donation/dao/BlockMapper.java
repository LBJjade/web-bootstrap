package com.becheer.donation.dao;

/*
* BlockMapper
* Creator : xiaokepu
* Date : 2017-09-06
*/

import com.becheer.donation.model.extension.article.ListArticleExtension;
import com.becheer.donation.model.extension.project.ListProjectExtension;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BlockMapper {

    //根据板块ID获取板块下的项目
    List<ListProjectExtension> selectProjectByBlockId(long blockId);

    //根据板块ID获取板块下的文章
    List<ListArticleExtension> selectArticleByBlockId(long blockId);

}
