package com.becheer.donation.service.impl;

import com.becheer.donation.dao.AttachMapper;
import com.becheer.donation.model.extension.attach.AttachExtension;
import com.becheer.donation.service.IAttachService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 包名: com.becheer.donation.service.impl
 * 文件说明: 描述当文件的用途
 * 创建人:amber
 * 创建日期: 2017/12/7 19:10
 * 版本:V1.0
 * Copyright © 2017 广州品清科技有限公司
 */
@Service
public class AttachServiceImpl implements IAttachService {
    @Resource
    private AttachMapper attachMapper;

    @Override
    public List<AttachExtension> getAttach(String refTable, long refId) {
        return attachMapper.selectAttach(refTable,refId);
    }
}
