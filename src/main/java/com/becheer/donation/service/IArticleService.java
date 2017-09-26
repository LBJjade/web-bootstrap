package com.becheer.donation.service;

/*
* IArticleService
* Creator : xiaokepu
* Date : 2017-09-18
*/

import com.becheer.donation.model.extension.article.ListArticleExtension;
import com.github.pagehelper.PageInfo;


public interface IArticleService {
    PageInfo<ListArticleExtension> getPublicyArticleList(int pageNum, int pageSize,long tid);
}
