package com.becheer.donation.service.impl;

import com.becheer.donation.dao.IntentionMapper;
import com.becheer.donation.dao.MemberMapper;
import com.becheer.donation.model.Intention;
import com.becheer.donation.model.Member;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.service.IIntentionExtensionService;
import com.becheer.donation.strings.Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IntentionExtensionServiceImpl implements IIntentionExtensionService {
    @Resource
    private IntentionMapper intentionMapper;

    @Resource
    private MemberMapper memberMapper;


    @Override
    public ResponseDto AddIntentionExtension( Intention intention) {
        try {

            int result = intentionMapper.InsertIntention(intention);
            if (result > 0) {
                return new ResponseDto(200, Message.INTENTION_ADD_SUCCESS, intention.getId());
            } else {
                return new ResponseDto(500, Message.SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

}
