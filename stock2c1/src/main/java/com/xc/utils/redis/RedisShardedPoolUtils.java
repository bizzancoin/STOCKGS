package com.xc.utils.redis;

import com.xc.common.StockIndexConst;
import com.xc.utils.stock.SerializeUtil;
import com.xc.utils.stock.lide.LideDataPanVo;
import com.xc.utils.stock.lide.LideDataVo;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;

import java.util.List;


public class RedisShardedPoolUtils {

    private static final Logger log = LoggerFactory.getLogger(RedisShardedPoolUtils.class);

    public static String set(String key, String value) {

        ShardedJedis jedis = null;

        String result = null;

        try {

            jedis = RedisShardedPool.getJedis();

            result = jedis.set(key, value);

        } catch (Exception e) {

            log.error("redis set key: {} value: {} error", new Object[]{key, value, e});

            RedisShardedPool.returnBrokenResouce(jedis);

            return result;

        }

        RedisShardedPool.returnResouce(jedis);

        return result;

    }

    public static String get(String key) {

        ShardedJedis jedis = null;

        String result = null;


        try {

            jedis = RedisShardedPool.getJedis();

            result = jedis.get(key);


        } catch (Exception e) {

            log.error("redis get key: {} error", key, e);

            RedisShardedPool.returnBrokenResouce(jedis);

            return result;

        }

        RedisShardedPool.returnResouce(jedis);

        return result;

    }

    public static byte[] get(byte[] key) {

        ShardedJedis jedis = null;

        byte[] result = null;


        try {

            jedis = RedisShardedPool.getJedis();

            result = jedis.get(key);


        } catch (Exception e) {

            log.error("redis get key: {} error", key, e);

            RedisShardedPool.returnBrokenResouce(jedis);

            return result;

        }

        RedisShardedPool.returnResouce(jedis);

        return result;

    }


    public static String setEx(String key, String value, int exTime) {

        ShardedJedis jedis = null;

        String result = null;

        try {

            jedis = RedisShardedPool.getJedis();

            result = jedis.setex(key, exTime, value);

        } catch (Exception e) {

            log.error("redis setEx key: {} value: {}   error...", new Object[]{key, value, e});

            RedisShardedPool.returnBrokenResouce(jedis);

            return result;

        }

        RedisShardedPool.returnResouce(jedis);

        return result;

    }

    /**
     * 股票数据专用批量写入工具
     * @param lideDataVos
     * @return
     */
    public static void stockPipSet(List<LideDataVo> lideDataVos) {

        ShardedJedis jedis = null;
        try {

            jedis = RedisShardedPool.getJedis();
            ShardedJedisPipeline pipelined = jedis.pipelined();

            for(LideDataVo lideDataVo:lideDataVos){
                StockIndexConst.STOCKMap.put("STOCK:"+lideDataVo.getCode(),lideDataVo);
                pipelined.set(("STOCK:"+lideDataVo.getCode()).getBytes(), SerializeUtil.serialize(lideDataVo));
            }
            pipelined.sync();

        } catch (Exception e) {

            log.error("redis stockPipSet error...");

            RedisShardedPool.returnBrokenResouce(jedis);

        }finally {
            RedisShardedPool.returnResouce(jedis);
        }

    }


    /**
     * 股票盘口数据专用批量写入工具
     * @param lideDataPanVos
     * @return
     */
    public static void stockPanPipSet(List<LideDataPanVo> lideDataPanVos) {

        ShardedJedis jedis = null;
        try {

            jedis = RedisShardedPool.getJedis();
            ShardedJedisPipeline pipelined = jedis.pipelined();

            for(LideDataPanVo lideDataPanVo:lideDataPanVos){
                pipelined.set(("STOCK:PAN:"+lideDataPanVo.getCode()).getBytes(), SerializeUtil.serialize(lideDataPanVo));
                StockIndexConst.STOCKPANMap.put("STOCK:PAN:"+lideDataPanVo.getCode(),lideDataPanVo);
            }
            pipelined.sync();

        } catch (Exception e) {

            log.error("redis stockPanPipSet error...");

            RedisShardedPool.returnBrokenResouce(jedis);

        }finally {
            RedisShardedPool.returnResouce(jedis);
        }

    }


    public static Long expire(String key, int exTime) {

        ShardedJedis jedis = null;

        Long result = null;


        try {

            jedis = RedisShardedPool.getJedis();

            result = jedis.expire(key, exTime);

        } catch (Exception e) {

            log.error("redis expire key: {}  error ", key, e);

            RedisShardedPool.returnBrokenResouce(jedis);

            return result;

        }

        RedisShardedPool.returnResouce(jedis);

        return result;

    }


    public static Long del(String key) {

        ShardedJedis jedis = null;

        Long result = null;


        try {

            jedis = RedisShardedPool.getJedis();

            result = jedis.del(key);

        } catch (Exception e) {

            log.error("redis del key: {} error ", key, e);

            RedisShardedPool.returnBrokenResouce(jedis);

            return result;

        }

        RedisShardedPool.returnResouce(jedis);

        return result;

    }


    public static void main(String[] args) {
        ShardedJedis jedis = RedisShardedPool.getJedis();

        System.out.println(get("key1"));

        ShardedJedisPipeline pipelined = jedis.pipelined();




        System.out.println(get("key2"));

        System.out.println(get("key3"));

        System.out.println("redis shaded pool utils end ...");

    }

}