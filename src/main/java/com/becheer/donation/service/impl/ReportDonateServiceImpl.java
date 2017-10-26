package com.becheer.donation.service.impl;

import com.becheer.donation.dao.ReportDonateMapper;
import com.becheer.donation.model.report.IndexReport;
import com.becheer.donation.service.IReportDonateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/*
* ReportDonateServiceImpl
* Creator : xiaokepu
* Date : 2017-10-26
*/
@Service
public class ReportDonateServiceImpl implements IReportDonateService {

    @Resource
    ReportDonateMapper reportDonateMapper;

    @Override
    public IndexReport GetIndexReport() {
        return reportDonateMapper.SelectIndexReport();
    }
}
