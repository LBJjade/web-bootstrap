package com.becheer.donation.dao;

import com.becheer.donation.model.extension.attach.AttachExtension;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 包名: com.becheer.donation.dao
 * 文件说明: AttachMapper
 * 创建人:amber
 * 创建日期: 2017/12/7 18:45
 * 版本:V1.0
 * Copyright © 2017 广州品清科技有限公司
 */

@Component
public interface AttachMapper {
    List<AttachExtension> selectAttach(String refTable,long refId);
}
