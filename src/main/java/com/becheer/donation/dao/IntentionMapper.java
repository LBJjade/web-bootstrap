package com.becheer.donation.dao;

/*
* IntentionMapper
* Creator : xiaokepu
* Date : 2017-09-28
*/

import com.becheer.donation.model.Intention;
import com.becheer.donation.model.condition.IntentionCondition;
import com.becheer.donation.model.extension.intention.IntentionExtension;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IntentionMapper {
    List<Intention> SelectByCondition(IntentionCondition condition);

    IntentionExtension SelectIntentionById(long intentionId);

    int InsertIntention(Intention intention);

    String generateNo(String iNamePre, String iModule, int iNum, int iNoLength, String oNewNo);

}
