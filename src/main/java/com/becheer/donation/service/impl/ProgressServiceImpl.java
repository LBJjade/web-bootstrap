package com.becheer.donation.service.impl;

import com.becheer.donation.dao.ProgressMapper;
import com.becheer.donation.model.extension.progress.ProgressExtension;
import com.becheer.donation.service.IProgressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
