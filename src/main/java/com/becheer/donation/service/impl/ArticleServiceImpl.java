package com.becheer.donation.service.impl;

/*
* ArticleServiceImpl
* Creator : xiaokepu
* Date : 2017-09-18
*/

import com.becheer.donation.dao.ArticleMapper;
import com.becheer.donation.model.Project;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.condition.ArticleCondition;
import com.becheer.donation.model.condition.ProjectCondition;
import com.becheer.donation.model.extension.ArticleTypeExtension;
import com.becheer.donation.model.extension.ListArticleExtension;
import com.becheer.donation.service.IArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService{

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public PageInfo<ListArticleExtension> getPublicyArticleList(int pageNum, int pageSize,long tid) {
        ArticleCondition condition = new ArticleCondition();
        condition.createCriteria().andArticleTypeEqual(tid);
        condition.setOrderByClause("create_time desc");
        PageHelper.startPage(pageNum, pageSize);
        List<ListArticleExtension> data = articleMapper.selectListByExample(condition);
        PageInfo<ListArticleExtension> pageInfo = new PageInfo<ListArticleExtension>(data);
        return pageInfo;
    }
}
