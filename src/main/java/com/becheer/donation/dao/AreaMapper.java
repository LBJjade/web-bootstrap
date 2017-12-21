package com.becheer.donation.dao;

import com.becheer.donation.model.Area;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 包名: com.becheer.donation.dao
 * 文件说明: 省市区三级联动Mapper
 * 创建人:amber
 * 创建日期: 2017/12/12 16:46
 * 版本:V1.0
 * Copyright © 2017 广州品清科技有限公司
 */

@Component
public interface AreaMapper {
    List<Area> selectAreaByParentId(long parentId);

    Map selectAreaByAreaId(long areaId);
}
