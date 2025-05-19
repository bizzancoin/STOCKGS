package com.xc.utils.redis;


import com.google.common.collect.Lists;

import com.xc.utils.PropertiesUtil;

import java.util.List;

import redis.clients.jedis.JedisPoolConfig;

import redis.clients.jedis.JedisShardInfo;

import redis.clients.jedis.ShardedJedis;

import redis.clients.jedis.ShardedJedisPool;

import redis.clients.util.Hashing;

import redis.clients.util.Sharded;


public class RedisShardedPool {

    private static ShardedJedisPool pool;

    private static Integer maxTotal = Integer.valueOf(

            Integer.parseInt(PropertiesUtil.getProperty("redis.max.total", "20")));


    private static Integer maxIdle = Integer.valueOf(

            Integer.parseInt(PropertiesUtil.getProperty("redis.max.idle", "10")));


    private static Integer minIdle = Integer.valueOf(

            Integer.parseInt(PropertiesUtil.getProperty("redis.min.idle", "2")));


    private static Boolean testOnBorrow = Boolean.valueOf(

            Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.borrow", "true")));


    private static Boolean testOnReturn = Boolean.valueOf(

            Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.return", "true")));


    private static String redisIp1 = PropertiesUtil.getProperty("redis1.ip");

    private static Integer redisPort1 = Integer.valueOf(

            Integer.parseInt(PropertiesUtil.getProperty("redis1.port")));


    private static void initPool() {

        JedisPoolConfig config = new JedisPoolConfig();


        config.setMaxTotal(maxTotal.intValue());

        config.setMaxIdle(maxIdle.intValue());

        config.setMinIdle(minIdle.intValue());


        config.setTestOnBorrow(testOnBorrow.booleanValue());

        config.setTestOnReturn(testOnReturn.booleanValue());


        config.setBlockWhenExhausted(true);


        List<JedisShardInfo> jedisShardInfos = Lists.newArrayList();

        JedisShardInfo info1 = new JedisShardInfo(redisIp1, redisPort1.intValue(), 2000);


        info1.setPassword(PropertiesUtil.getProperty("redis1.pwd"));

        jedisShardInfos.add(info1);


        pool = new ShardedJedisPool(config, jedisShardInfos, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);

    }


    static {

        initPool();

    }


    public static ShardedJedis getJedis() {
        return pool.getResource();
    }


    public static void returnResouce(ShardedJedis jedis) {
        pool.returnResource(jedis);
    }


    public static void returnBrokenResouce(ShardedJedis jedis) {
        pool.returnBrokenResource(jedis);
    }


    public static void main(String[] args) {
    }

}
