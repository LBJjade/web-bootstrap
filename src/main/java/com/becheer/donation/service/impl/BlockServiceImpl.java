package com.becheer.donation.service.impl;

import com.becheer.donation.dao.BlockMapper;
import com.becheer.donation.model.extension.ListArticleExtension;
import com.becheer.donation.model.extension.ListProjectExtension;
import com.becheer.donation.service.IBlockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
* BlockServiceImpl 板块Service类
* Creator : xiaokepu
* Date : 2017-09-07
*/

@Service
public class BlockServiceImpl  implements IBlockService{
    private static final Logger LOGGER = LoggerFactory.getLogger(BlockServiceImpl.class);

    @Resource
    private BlockMapper blockMapper;

    //根据板块id 获取板块下的文章
    @Override
    public List<ListArticleExtension> getArticleByBlockId(long blockId) {
        LOGGER.debug("Enter getArticleByBlockId method");
        List<ListArticleExtension> result=blockMapper.selectArticleByBlockId(blockId);
        LOGGER.debug("Exit getArticleByBlockId method");
        return result;
    }

    //根据板块id 获取板块下的项目
    @Override
    public List<ListProjectExtension> getProjectByBlockId(long blockId) {
        LOGGER.debug("Enter getProjectByBlockId method");
        List<ListProjectExtension> result=blockMapper.selectProjectByBlockId(blockId);
        LOGGER.debug("Exit getProjectByBlockId method");
        return result;
    }
}
