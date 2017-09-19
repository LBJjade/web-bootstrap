package com.becheer.donation.dao;

import com.becheer.donation.model.Log;
import com.becheer.donation.model.condition.LogCondition;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface LogVoMapper {
    long countByExample(LogCondition example);

    int deleteByExample(LogCondition example);

    int deleteByPrimaryKey(Integer id);

    int insert(Log record);

    int insertSelective(Log record);

    List<Log> selectByExample(LogCondition example);

    Log selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Log record, @Param("example") LogCondition example);

    int updateByExample(@Param("record") Log record, @Param("example") LogCondition example);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);
}