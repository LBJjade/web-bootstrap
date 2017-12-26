package com.becheer.donation.service;

import com.becheer.donation.model.Progress;
import com.becheer.donation.model.extension.progress.ProgressExtension;

import java.util.List;

/*
* IProgressService
* Creator : xiaokepu
* Date : 2017-10-07
*/
public interface IProgressService {
    List<ProgressExtension>GetAllProgress(long refRecordId,String refTable);

    long AddProgress(String title, String content, String refTable, long refRecordId, long memberId, int enable, int progressType);

    int batchInsert(List<Progress> progresses);
}
