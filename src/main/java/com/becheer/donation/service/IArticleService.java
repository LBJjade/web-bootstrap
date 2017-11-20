package com.becheer.donation.service;

/*
* IArticleService
* Creator : xiaokepu
* Date : 2017-09-18
*/

import com.becheer.donation.model.extension.article.ArticleDetailExtension;
import com.becheer.donation.model.extension.article.ListArticleExtension;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface IArticleService {
    PageInfo<ListArticleExtension> getPublicyArticleList(int pageNum, int pageSize,long tid);

    List<ListArticleExtension>getAllArticle(long tid);

    ArticleDetailExtension getArticleDetail(long aid);
}
