package com.becheer.donation.dao;

import com.becheer.donation.model.Progress;
import com.becheer.donation.model.extension.progress.ProgressExtension;

import java.util.List;

/*
* ProgressMapper
* Creator : xiaokepu
* Date : 2017-10-07
*/
public interface ProgressMapper {
    List<ProgressExtension>SelectAllProgress(long refRecordId,String refTable);

    long InsertProgress(Progress progress);
}
