package com.becheer.donation.service.impl;

import com.becheer.donation.constant.WebConst;
import com.becheer.donation.dao.LogVoMapper;
import com.becheer.donation.model.Log;
import com.becheer.donation.model.LogCondition;
import com.becheer.donation.service.ILogService;
import com.becheer.donation.utils.DateKit;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by BlueT on 2017/3/4.
 */
@Service
public class LogServiceImpl implements ILogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogServiceImpl.class);

    @Resource
    private LogVoMapper logDao;

    @Override
    public void insertLog(Log log) {
        logDao.insert(log);
    }

    @Override
    public void insertLog(String action, String data, String ip, Integer authorId) {
        Log logs = new Log();
        logs.setAction(action);
        logs.setData(data);
        logs.setIp(ip);
        logs.setAuthorId(authorId);
        logs.setCreated(DateKit.getCurrentUnixTime());
        logDao.insert(logs);
    }

    @Override
    public List<Log> getLogs(int page, int limit) {
        LOGGER.debug("Enter getLogs method:page={},linit={}",page,limit);
        if (page <= 0) {
            page = 1;
        }
        if (limit < 1 || limit > WebConst.MAX_POSTS) {
            limit = 10;
        }
        LogCondition logCondition = new LogCondition();
        logCondition.setOrderByClause("id desc");
        PageHelper.startPage((page - 1) * limit, limit);
        List<Log> logs = logDao.selectByExample(logCondition);
        LOGGER.debug("Exit getLogs method");
        return logs;
    }
}
