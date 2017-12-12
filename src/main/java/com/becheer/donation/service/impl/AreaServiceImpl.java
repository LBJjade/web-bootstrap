package com.becheer.donation.service.impl;

import com.becheer.donation.dao.AreaMapper;
import com.becheer.donation.model.Area;
import com.becheer.donation.service.IAreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 包名: com.becheer.donation.service.impl
 * 文件说明: 区域信息服务类
 * 创建人:amber
 * 创建日期: 2017/12/12 17:29
 * 版本:V1.0
 * Copyright © 2017 广州品清科技有限公司
 */
@Service
public class AreaServiceImpl implements IAreaService {

    @Resource
    private AreaMapper areaMapper;

    @Override
    public List<Area> selectAreaByParentId(long parentId) {
        return areaMapper.selectAreaByParentId(parentId);
    }
}
