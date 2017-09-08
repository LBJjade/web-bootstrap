package com.becheer.donation.controller;

/*
* 板块内容控制器
* Creator : xiaokepu
* Date : 2017-09-07
*/

import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.ListProjectExtension;
import com.becheer.donation.service.IBlockService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BlockController extends BaseController {

    @Resource
    private IBlockService blockService;


    @GetMapping(value = "blockProject/{blockId}")
    @ResponseBody
    public ResponseDto GetProject(HttpServletRequest request, @PathVariable int blockId) {
        try {
            if (blockId <= 0) {
                return ResponseDto.GetResponse(400, "Individual blockId", null);
            }
            List<ListProjectExtension> result = blockService.getProjectByBlockId(blockId);
            return ResponseDto.GetResponse(200, "Success", result);
        } catch (Exception ex) {
            return ResponseDto.GetResponse(500, ex.getMessage(), null);
        }
    }
}
