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
//    int GetMessagesNum();
    int GetMemberMessagesNum(long member_id);
    void ChangeStatus(int id);
//    int GetStatus(int id);
}
