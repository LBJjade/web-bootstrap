package com.becheer.donation.service.impl;

/*
* ArticleServiceImpl
* Creator : xiaokepu
* Date : 2017-09-18
*/

import com.becheer.donation.dao.ArticleMapper;
import com.becheer.donation.model.condition.ArticleCondition;
import com.becheer.donation.model.extension.article.ArticleDetailExtension;
import com.becheer.donation.model.extension.article.ListArticleExtension;
import com.becheer.donation.service.IArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public PageInfo<ListArticleExtension> getPublicyArticleList(int pageNum, int pageSize, long tid) {
        ArticleCondition condition = new ArticleCondition();
        condition.createCriteria().andArticleTypeEqual(tid);
        condition.setOrderByClause("create_time desc");
        PageHelper.startPage(pageNum, pageSize);
        List<ListArticleExtension> data = articleMapper.selectListByExample(condition);
        PageInfo<ListArticleExtension> pageInfo = new PageInfo<ListArticleExtension>(data);
        return pageInfo;
    }

    @Override
    public List<ListArticleExtension> getAllArticle(long tid) {
        ArticleCondition condition = new ArticleCondition();
        condition.createCriteria().andArticleTypeEqual(tid).andEnableEqual(1);
        condition.setOrderByClause("create_time desc");
        List<ListArticleExtension> result = articleMapper.selectListByExample(condition);
        return result;
    }

    @Override
    public ArticleDetailExtension getArticleDetail(long aid) {
        return articleMapper.selectArticleDetail(aid);
    }
}
