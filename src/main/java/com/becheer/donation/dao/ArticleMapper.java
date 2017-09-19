package com.becheer.donation.dao;

/*
* ArticleMapper
* Creator : xiaokepu
* Date : 2017-09-17
*/


import com.becheer.donation.model.condition.ArticleCondition;
import com.becheer.donation.model.extension.ListArticleExtension;

import java.util.List;

public interface ArticleMapper {

    List<ListArticleExtension> selectListByExample(ArticleCondition condition);

}
