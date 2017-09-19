package com.becheer.donation.service.impl;

/*
* ArticleTypeService
* Creator : xiaokepu
* Date : 2017-09-18
*/

import com.becheer.donation.dao.ArticleTypeMapper;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.ArticleTypeExtension;
import com.becheer.donation.service.IArticleTypeService;
import com.becheer.donation.strings.Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleTypeService implements IArticleTypeService{

    @Resource
    private ArticleTypeMapper articleTypeMapper;

    @Override
    public ResponseDto<List<ArticleTypeExtension>> GetPublicyArticleTypeList() {
        List<ArticleTypeExtension> result=articleTypeMapper.selectPublicyArticleType();
        return new ResponseDto<List<ArticleTypeExtension>>(200,Message.PUBLICITY_ARTICLE_TYPE_SUCCESS,result);
    }
}
