package com.becheer.donation.service;

import com.becheer.donation.model.Area;

import java.util.List;

/**
 * 包名: com.becheer.donation.service
 * 文件说明: 区域信息服务类接口
 * 创建人:amber
 * 创建日期: 2017/12/12 17:26
 * 版本:V1.0
 * Copyright © 2017 广州品清科技有限公司
 */

public interface IAreaService {
    List<Area> selectAreaByParentId(long parentId);
}
