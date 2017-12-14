package com.becheer.donation.utils;

import com.becheer.donation.configs.RedisConfig;
import com.becheer.donation.service.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/*
* RedisProvider
* Creator : xiaokepu
* Date : 
*/
public class RedisProvider {
    protected static final Logger LOGGER = LoggerFactory.getLogger(RedisProvider.class);

    protected static JedisPool jedispool;

    protected static int EXPIRE = 130;
    static{
        RedisConfig redisConfig=(RedisConfig)SpringContextUtil.getBean("redisConfig");

        JedisPoolConfig jedisconfig = new JedisPoolConfig();
        jedisconfig.setMaxTotal(redisConfig.getMaxTotal());
        jedisconfig.setMaxIdle(redisConfig.getMaxIdle());
        jedisconfig.setMaxWaitMillis(redisConfig.getMaxWait());
        jedisconfig.setTestOnBorrow(false);
        jedisconfig.setTestOnReturn(false);
        jedispool = new JedisPool(jedisconfig, redisConfig.getIp(),
                redisConfig.getPort(), redisConfig.getMaxWait(),redisConfig.getPwd());
    }

    public static Jedis GetJedis() {
        Jedis jedis = null;
        try {
            jedis = jedispool.getResource();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            jedis = jedispool.getResource();
        }
        return jedis;
    }

    public static void ReturnResource(JedisPool pool, Jedis jedis) {
        if (jedis != null) {
            pool.returnResource(jedis);
        }
    }
}