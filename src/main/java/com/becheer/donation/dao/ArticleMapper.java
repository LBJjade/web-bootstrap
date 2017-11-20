package com.becheer.donation.dao;

/*
* ArticleMapper
* Creator : xiaokepu
* Date : 2017-09-17
*/


import com.becheer.donation.model.condition.ArticleCondition;
import com.becheer.donation.model.extension.article.ArticleDetailExtension;
import com.becheer.donation.model.extension.article.ListArticleExtension;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ArticleMapper {

    List<ListArticleExtension> selectListByExample(ArticleCondition condition);

    ArticleDetailExtension selectArticleDetail(long aid);
}
