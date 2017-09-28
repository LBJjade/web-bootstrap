package com.becheer.donation.dao;

/*
* IntentionMapper
* Creator : xiaokepu
* Date : 2017-09-28
*/

import com.becheer.donation.model.Intention;
import com.becheer.donation.model.condition.IntentionCondition;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IntentionMapper {
    List<Intention> SelectByCondition(IntentionCondition condition);
}
