package com.becheer.donation.service;

import com.becheer.donation.model.extension.project.ListProjectExtension;
import com.becheer.donation.model.extension.project.MemberProjectDetailExtension;
import com.becheer.donation.model.extension.project.MemberProjectExtension;
import com.becheer.donation.model.extension.project.ProjectDetailExtension;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IWxPayService {

    /**
     * 微信支付
     */
    String pay(long id);
}
