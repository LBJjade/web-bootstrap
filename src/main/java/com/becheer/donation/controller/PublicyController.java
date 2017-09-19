package com.becheer.donation.controller;

import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.ListArticleExtension;
import com.becheer.donation.service.IArticleService;
import com.becheer.donation.service.IArticleTypeService;
import com.becheer.donation.strings.Message;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
* PublicyController 信息公开页面控制器
* Creator : xiaokepu
* Date : 2017-09-17
*/
@Controller
@RequestMapping("/publicity")
public class PublicyController extends BaseController{

    @Resource
    private IArticleTypeService articleTypeService;

    @Resource
    private IArticleService articleService;

    @GetMapping(value="")
    public String View(HttpServletRequest request){
        return this.render("publicity");
    }


    @PostMapping(value= "/type" )
    @ResponseBody
    public ResponseDto GetPublicityArticleType(HttpServletRequest request){
        try{
            return articleTypeService.GetPublicyArticleTypeList();
        }catch (Exception ex){
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    @PostMapping(value = "/article")
    @ResponseBody
    public ResponseDto GetArticle(HttpServletRequest request, @RequestParam long tid,@RequestParam int pageSize,@RequestParam int pageNum){
        if (pageNum<=0){
            pageNum=1;
        }
        if (pageSize>50||pageSize<=0){
            pageSize=10;
        }
        try{
            PageInfo<ListArticleExtension> resultList= articleService.getPublicyArticleList(pageNum,pageSize,tid);
            return new ResponseDto(200,Message.PUBLICITY_ARTICLE_GET_LIST_SUCCESS,resultList);
        }catch (Exception ex){
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }
}
