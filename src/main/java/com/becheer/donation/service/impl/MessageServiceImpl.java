package com.becheer.donation.service.impl;

/*
* MessageServiceImpl
* Creator : xiaokepu
* Date : 2017-09-27
*/

import com.becheer.donation.dao.MessageMapper;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.condition.MessageCondition;
import com.becheer.donation.model.extension.message.MessageExtension;
import com.becheer.donation.service.IMessageService;
import com.becheer.donation.strings.Message;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageServiceImpl implements IMessageService {



    @Resource
    private MessageMapper messageMapper;


    @Override
    public PageInfo<MessageExtension> GetMessageList(long memberId, int pageNum, int pageSize) {
        MessageCondition condition=new MessageCondition();
        condition.setOrderByClause("create_time desc");
        condition.createCriteria().addEnable(1).andMemberIdEqualTo(memberId);
        PageHelper.startPage(pageNum,pageSize);
        List<MessageExtension> data=messageMapper.SelectByCondition(condition);
        PageInfo<MessageExtension> pageInfo = new PageInfo<MessageExtension>(data);
        return pageInfo;
    }

    @Override
    public int GetMemberMessagesNum(long memberId) {
        return messageMapper.GetMemberMessagesNum( memberId);
    }

    @Override
    public ResponseDto ChangeStatus(long id, long memberId) {
        MessageExtension extension=new MessageExtension();
        extension.setId(id);
        extension.setMemberId(memberId);
        int result=messageMapper.ChangeStatus(extension);
        if (result>0){
            return new ResponseDto(200, Message.CHANGE_STATUS_SUCCESS);
        }else{
            return new ResponseDto(500, Message.CHANGE_STATUS_FAILED);
        }
    }

}
