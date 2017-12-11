package com.becheer.donation.service;

import com.becheer.donation.model.extension.attach.AttachExtension;

import java.util.List;

/**
 * 包名: com.becheer.donation.service
 * 文件说明: IAttachService
 * 创建人:amber
 * 创建日期: 2017/12/7 19:09
 * 版本:V1.0
 * Copyright © 2017 广州品清科技有限公司
 */

public interface IAttachService {
    List<AttachExtension> getAttach(String refTable, long refId);
}
