package com.becheer.donation.service;

/*
* IBlockService接口
* Creator : xiaokepu
* Date : 2017-09-07
*/

import com.becheer.donation.model.extension.ListArticleExtension;
import com.becheer.donation.model.extension.ListProjectExtension;

import java.util.List;

public interface IBlockService {
    List<ListArticleExtension> getArticleByBlockId(long blockId);

    List<ListProjectExtension> getProjectByBlockId(long blockId);
}
