package com.becheer.donation.controller;

import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.article.ArticleDetailExtension;
import com.becheer.donation.model.extension.article.ListArticleExtension;
import com.becheer.donation.service.IArticleService;
import com.becheer.donation.strings.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/*
* ArticleController
* Creator : xiaokepu
* Date : 2017-11-17
*/
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplyController.class);

    @Resource
    IArticleService articleService;

    @GetMapping("/{articleId}")
    public String getArticle(HttpServletRequest request, @PathVariable long articleId){
        try{
        request.setAttribute("config", fileConfig);
        request.setAttribute("aid",articleId);
        return this.render("article");
        }catch (Exception ex) {
            LOGGER.error("getArticle", ex.getMessage());
            return render_404();
        }
    }

    @PostMapping("/list")
    @ResponseBody
    public ResponseDto getAllArticle(HttpServletRequest request, @RequestParam long articleTypeId){
        try{
            List<ListArticleExtension> articleExtensionList = articleService.getAllArticle(articleTypeId);
            return new ResponseDto(200,Message.ARTICLE_GET_BY_TYPE_SUCCESS,articleExtensionList);
        }catch (Exception ex){
            LOGGER.error("getAllArticle", ex.getMessage());
            return ResponseDto.GetResponse(500, Message.SERVER_ERROR);
        }
    }
    @PostMapping("/detail")
    @ResponseBody
    public ResponseDto getArticleDetail(HttpServletRequest request,@RequestParam long articleId){
        try{
            ArticleDetailExtension result=articleService.getArticleDetail(articleId);
            return new ResponseDto(200,Message.ARTICLE_DETAIL_GET_SUCCESS,result);
        }catch (Exception ex){
            LOGGER.error("getArticleDetail", ex.getMessage());
            return ResponseDto.GetResponse(500, Message.SERVER_ERROR);
        }
    }


}
