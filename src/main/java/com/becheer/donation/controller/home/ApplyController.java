package com.becheer.donation.controller.home;

/*
* ApplyController
* Creator : xiaokepu
* Date : 2017-09-28
*/

import com.becheer.donation.controller.BaseController;
import com.becheer.donation.model.Intention;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.intention.IntentionExtension;
import com.becheer.donation.model.extension.member.MemberSessionExtension;
import com.becheer.donation.model.extension.message.MessageExtension;
import com.becheer.donation.model.extension.progress.ProgressExtension;
import com.becheer.donation.service.IIntentionService;
import com.becheer.donation.service.IProgressService;
import com.becheer.donation.strings.Message;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/home/apply")
public class ApplyController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplyController.class);

    @Resource
    private IIntentionService intentionService;

    @Resource
    private IProgressService progressService;

    @GetMapping(value = "")
    public String index(HttpServletRequest request) {
        return this.render("home/apply");
    }

    @GetMapping(value = "/{applyId}")
    public String GetApplyDetail(HttpServletRequest request,@PathVariable long applyId) {

        try{
            long tempId=Long.valueOf(applyId);
            IntentionExtension result = intentionService.GetIntention(applyId);
            if (result==null) {
                return render_404();
            }else{
                request.setAttribute("apply",result);
                return this.render("home/apply_detail");
            }
        }catch(Exception ex){
            LOGGER.error("GetApplyDetail", ex);
            return render_404();
        }
    }

    @PostMapping("/list")
    @ResponseBody
    public ResponseDto GetList(HttpServletRequest request, @RequestParam int pageSize, @RequestParam int pageNum){
        if (pageSize<1||pageSize>50){
            pageSize=5;
        }
        if (pageNum<1){
            pageNum=1;
        }
        try {
            MemberSessionExtension currentMember=GetCurrentUser(request);
            PageInfo<Intention> result=intentionService.GetIntentionList(currentMember.getMemberId(),pageNum,pageSize);
            return new ResponseDto(200, Message.INTENTION_GET_SUCCESS,result);
        }catch(Exception ex){
            LOGGER.error("GetProjectType", ex);
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    @PostMapping("/progress")
    @ResponseBody
    public ResponseDto GetAllprogress(HttpServletRequest request, @RequestParam long applyId){
        try {
            List<ProgressExtension> result=progressService.GetAllProgress(applyId,"dnt_intention");
            return new ResponseDto(200, Message.MEMBER_INTENTION_PROGRESS_SUCCESS,result);
        }catch(Exception ex){
            LOGGER.error("GetAllprogress", ex);
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseDto AddProgress(HttpServletRequest request,@RequestParam String content,@RequestParam long applyId){
        try {
            MemberSessionExtension currentMember=GetCurrentUser(request);
            long result=progressService.AddProgress(content,content,"dnt_intention",applyId,currentMember.getMemberId(),1);
            if (result>0){
                return new ResponseDto(200,Message.MEMBER_INTENTION_PROGRESS_ADD_SUCCESS);
            }else{
                return new ResponseDto(400,Message.MEMBER_INTENTION_PROGRESS_ADD_FAILED);
            }
        }catch(Exception ex){
            LOGGER.error("AddProgress", ex);
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }
}
