package com.becheer.donation.utils;

import com.becheer.donation.strings.ConstString;
import org.thymeleaf.expression.Maps;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
* RedisUtil redis 工具类
* Creator : xiaokepu
* Date : 2017-10-19
*/
public class RedisUtil extends RedisProvider {

    /**
     * 删除会员Redis缓存
     *
     * @param memberId
     */
    public static void delMemberKey(long memberId) {
        RedisUtil.DelKey(ConstString.REDIS_BACKEDN_KEY + ":" + ConstString.TABLE_MEMBER + ":" + memberId);
    }

    /**
     * 删除合同Redis缓存
     *
     * @param contractId
     */
    public static void delContractkey(long contractId) {
        RedisUtil.DelKey(ConstString.REDIS_BACKEDN_KEY + ":" + ConstString.TABLE_CONTRACT + ":" + contractId);
    }

    /**
     * 删除合同Redis缓存
     *
     * @param contractProjectId
     */
    public static void delContractProjectkey(long contractProjectId) {
        RedisUtil.DelKey(ConstString.REDIS_BACKEDN_KEY + ":" + ConstString.TABLE_CONTRACT_PROJECT + ":" + contractProjectId);
    }

    /**
     * 删除合同Redis缓存
     *
     * @param contractProjectAcceptorId
     */
    public static void delContractProjectAcceptorkey(long contractProjectAcceptorId) {
        RedisUtil.DelKey(ConstString.REDIS_BACKEDN_KEY + ":" + ConstString.TABLE_CONTRACT_PROJECT_ACCEPTOR + ":" + contractProjectAcceptorId);
    }

    /**
     * 删除申诉Redis缓存
     *
     * @param appealId
     */
    public static void delAppealkey(long appealId) {
        RedisUtil.DelKey(ConstString.REDIS_BACKEDN_KEY + ":" + ConstString.TABLE_APPEAL + ":" + appealId);
    }

    /**
     * 删除paymentplan缓存
     *
     * @param paymentPlanId
     */
    public static void delPaymentPlankey(long paymentPlanId) {
        RedisUtil.DelKey(ConstString.REDIS_BACKEDN_KEY + ":" + ConstString.TABLE_PAYMENT_PLAN + ":" + paymentPlanId);
    }

    public static String SetKey(String key, String value) {
        Jedis jedis = null;
        String rtn = null;
        try {
            jedis = GetJedis();
            rtn = jedis.setex(key, EXPIRE, value);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            jedispool.returnBrokenResource(jedis);
        } finally {
            ReturnResource(jedispool, jedis);
        }
        return rtn;
    }

    public static String GetKey(String key) {
        Jedis jedis = null;
        String rtn = null;
        try {
            jedis = GetJedis();
            rtn = jedis.get(key);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            jedispool.returnBrokenResource(jedis);
        } finally {
            ReturnResource(jedispool, jedis);
        }
        return rtn;
    }


    public static void DelKey(String key) {
        Jedis jedis = null;
        try {
            jedis = GetJedis();
            jedis.del(key);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            jedispool.returnBrokenResource(jedis);
        } finally {
            ReturnResource(jedispool, jedis);
        }
    }

    public static void Flush() {
        Jedis jedis = null;
        jedis = GetJedis();
        jedis.flushAll();
    }

    public static long DelKeyArray(String[] key) {
        Jedis jedis = null;
        Long rtn = null;
        try {
            jedis = GetJedis();
            rtn = jedis.del(key);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            jedispool.returnBrokenResource(jedis);
        } finally {
            ReturnResource(jedispool, jedis);
        }
        return rtn;
    }
}
