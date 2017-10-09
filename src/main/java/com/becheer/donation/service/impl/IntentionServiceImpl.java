package com.becheer.donation.service.impl;

import com.becheer.donation.dao.IntentionMapper;
import com.becheer.donation.model.Intention;
import com.becheer.donation.model.condition.IntentionCondition;
import com.becheer.donation.model.extension.intention.IntentionExtension;
import com.becheer.donation.model.extension.message.MessageExtension;
import com.becheer.donation.service.IIntentionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
* IntentionServiceImpl
* Creator : xiaokepu
* Date : 2017-09-28
*/
@Service
public class IntentionServiceImpl implements IIntentionService {

    @Resource
    private IntentionMapper intentionMapper;

    @Override
    public PageInfo<Intention> GetIntentionList(long memberId, int pageNum, int pageSize) {
        IntentionCondition condition=new IntentionCondition();
        condition.setOrderByClause("create_time desc");
        condition.createCriteria().andMemberIdEqualTo(memberId);
        PageHelper.startPage(pageNum,pageSize);
        List<Intention> data=intentionMapper.SelectByCondition(condition);
        PageInfo<Intention> pageInfo = new PageInfo<Intention>(data);
        return pageInfo;
    }

    @Override
    public IntentionExtension GetIntention(long intentionId) {
        return intentionMapper.SelectIntentionById(intentionId);
    }
}
