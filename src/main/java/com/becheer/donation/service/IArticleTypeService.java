package com.becheer.donation.service;

import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.ArticleTypeExtension;

import java.util.List;

/*
* IArticleTypeService
* Creator : xiaokepu
* Date : 2017-09-18
*/
public interface IArticleTypeService {

    ResponseDto<List<ArticleTypeExtension>>GetPublicyArticleTypeList();

}
