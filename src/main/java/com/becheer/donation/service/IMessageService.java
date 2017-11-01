package com.becheer.donation.service;

/*
* IMessageService
* Creator : xiaokepu
* Date : 2017-09-27
*/


import com.becheer.donation.model.extension.message.MessageExtension;
import com.github.pagehelper.PageInfo;

public interface IMessageService {
    PageInfo<MessageExtension> GetMessageList(long memberId, int pageNum, int pageSize);
    int GetMemberMessagesNum(long memberId);
    void ChangeStatus(long id);
}
