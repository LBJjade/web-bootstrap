package com.becheer.donation.service;

/*
* IArticleService
* Creator : xiaokepu
* Date : 2017-09-18
*/

import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.ArticleTypeExtension;
import com.becheer.donation.model.extension.ListArticleExtension;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface IArticleService {
    PageInfo<ListArticleExtension> getPublicyArticleList(int pageNum, int pageSize,long tid);
}
