package com.becheer.donation.service;

/*
* IBlockService接口
* Creator : xiaokepu
* Date : 2017-09-07
*/

import com.becheer.donation.model.extension.article.ListArticleExtension;
import com.becheer.donation.model.extension.project.ListProjectExtension;

import java.util.List;

public interface IBlockService {
    List<ListArticleExtension> GetArticleByBlockId(long blockId);

    List<ListProjectExtension> GetProjectByBlockId(long blockId);
}
