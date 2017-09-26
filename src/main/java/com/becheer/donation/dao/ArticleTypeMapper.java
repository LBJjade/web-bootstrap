package com.becheer.donation.dao;

/*
* ArticleTypeMapper
* Creator : xiaokepu
* Date : 2017-09-18
*/

import com.becheer.donation.model.extension.article.ArticleTypeExtension;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ArticleTypeMapper {
    //获取所有信息公开文章类别
    List<ArticleTypeExtension> selectPublicyArticleType();
}
