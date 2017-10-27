package com.becheer.donation.dao;

import com.becheer.donation.model.report.IndexReport;
import org.springframework.stereotype.Component;

/*
* ReportDonateMapper 捐赠报表Mapper
* Creator : xiaokepu
* Date : 2017-10-26
*/
@Component
public interface ReportDonateMapper {
    IndexReport SelectIndexReport();
}