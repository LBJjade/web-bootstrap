package com.becheer.donation.utils;

import com.becheer.donation.controller.ProjectController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.util.ResourceBundle;

/*
* RedisProvider
* Creator : xiaokepu
* Date : 
*/
public class RedisProvider {

//    redis.pool.maxActive=512
//    redis.pool.maxIdle=100
//    redis.pool.maxWait=100000
//    redis.pool.testOnBorrow=true
//    redis.pool.testOnReturn=true
//    redis.ip=192.168.1.26
//    redis.port=6379
//    redis.expire=1200

    protected static final Logger LOGGER = LoggerFactory.getLogger(RedisProvider.class);

    protected static JedisPool jedispool;
    protected static int EXPIRE = 130;
    static{
//        ResourceBundle bundle = ResourceBundle.getBundle("redis");
//        if (bundle == null) {
//            throw new IllegalArgumentException(
//                    "[redis.properties] is not found!");
//        }

//        EXPIRE = Integer.valueOf(bundle.getString("redis.expire"));

        JedisPoolConfig jedisconfig = new JedisPoolConfig();
        jedisconfig.setMaxTotal(512);
        jedisconfig.setMaxIdle(100);
        jedisconfig.setMaxWaitMillis(10000);
        jedisconfig.setTestOnBorrow(false);
        jedisconfig.setTestOnReturn(false);
        jedispool = new JedisPool(jedisconfig, "192.168.1.26",
                6379, 100000,"123456");
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