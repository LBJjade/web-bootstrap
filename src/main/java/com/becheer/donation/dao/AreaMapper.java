package com.becheer.donation.dao;

import com.becheer.donation.model.Area;

import java.util.List;

/**
 * 包名: com.becheer.donation.dao
 * 文件说明: 描述当文件的用途
 * 创建人:amber
 * 创建日期: 2017/12/12 16:46
 * 版本:V1.0
 * Copyright © 2017 广州品清科技有限公司
 */
public interface AreaMapper {
    List<Area> selectAreaByParentId(long parentId);
}
