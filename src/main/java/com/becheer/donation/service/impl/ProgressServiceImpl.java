package com.becheer.donation.service.impl;

import com.becheer.donation.dao.ProgressMapper;
import com.becheer.donation.model.Progress;
import com.becheer.donation.model.extension.progress.ProgressExtension;
import com.becheer.donation.service.IProgressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/*
* ProgressServiceImpl
* Creator : xiaokepu
* Date : 2017-10-07
*/

@Service
public class ProgressServiceImpl implements IProgressService{

    @Resource
    ProgressMapper progressMapper;

    @Override
    public List<ProgressExtension> GetAllProgress(long refRecordId,String refTable) {
        return progressMapper.SelectAllProgress(refRecordId,refTable);
    }

    @Override
    public long AddProgress(String title, String content, String refTable, long refRecordId, long memberId, int enable) {
        Progress progress=new Progress();
//        progress.setId(UUID.GetInt64UUID());
        progress.setTitle(title);
        progress.setContent(content);
        progress.setRefTable(refTable);
        progress.setRefRecordId(refRecordId);
        progress.setCreateMemberId(memberId);
        progress.setEnable(1);
        progress.setCreateTime(new Date());
        progress.setProgressType(3);
        progressMapper.InsertProgress(progress);
        return progress.getId();
    }

    @Override
    public int batchInsert(List<Progress> progresses) {
        return progressMapper.batchAdd(progresses);
    }
}
