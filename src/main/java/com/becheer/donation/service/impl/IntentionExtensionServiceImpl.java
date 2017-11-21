package com.becheer.donation.service.impl;

import com.becheer.donation.dao.IntentionExtensionMapper;
import com.becheer.donation.model.base.ResponseDto;
import com.becheer.donation.model.extension.intention.IntentionDonateExtension;
import com.becheer.donation.service.IIntentionExtensionService;
import com.becheer.donation.strings.Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IntentionExtensionServiceImpl implements IIntentionExtensionService{
    @Resource
    private IntentionExtensionMapper intentionExtensionMapper;


    @Override
    public ResponseDto AddIntentionExtension(IntentionDonateExtension intentionDonateExtension) {
        try {
            int result = intentionExtensionMapper.InsertIntention(intentionDonateExtension);
            if (result > 0) {
                return new ResponseDto(200, Message.INTENTION_ADD_SUCCESS);
            } else {
                return new ResponseDto(500, Message.SERVER_ERROR);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseDto(500, Message.SERVER_ERROR);
        }
    }

}
