package com.becheer.donation.dao;

/*
* MessageMapper
* Creator : xiaokepu
* Date : 2017-09-27
*/

import com.becheer.donation.model.condition.MessageCondition;
import com.becheer.donation.model.extension.message.MessageExtension;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MessageMapper {
    List<MessageExtension> SelectByCondition(MessageCondition condition);
}
