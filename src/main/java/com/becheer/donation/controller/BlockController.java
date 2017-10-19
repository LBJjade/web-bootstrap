package com.becheer.donation.controller;

/*
* 板块内容控制器
* Creator : xiaokepu
* Date : 2017-09-07
*/

import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.article.ListArticleExtension;
import com.becheer.donation.model.extension.project.ListProjectExtension;
import com.becheer.donation.service.IBlockService;
import com.becheer.donation.strings.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/block")
public class BlockController extends BaseController {

    @Resource
    private IBlockService blockService;

    @PostMapping(value = "/project")
    @ResponseBody
    public ResponseDto GetProject(HttpServletRequest request, @RequestParam long blockId) {
        try {
            if (blockId <= 0) {
                return ResponseDto.GetResponse(400, Message.PARAMETER_ERROR);
            }
            List<ListProjectExtension> result = blockService.GetProjectByBlockId(blockId);
            return ResponseDto.GetResponse(200,Message.BLOCK_GET_PROJECT_SUCCESS, result);
        } catch (Exception ex) {
            return ResponseDto.GetResponse(500,Message.SERVER_ERROR);
        }
    }

    @PostMapping(value = "/article")
    @ResponseBody
    public ResponseDto GetArticle(HttpServletRequest request, @RequestParam int blockId){
        try {
            if (blockId <= 0) {
                return ResponseDto.GetResponse(400, Message.PARAMETER_ERROR);
            }
            List<ListArticleExtension> result = blockService.GetArticleByBlockId(blockId);
            return ResponseDto.GetResponse(200,Message.BLOCK_GET_ARTICLE_SUCCESS, result);
        } catch (Exception ex) {
            return ResponseDto.GetResponse(500,Message.SERVER_ERROR);
        }
    }
}
