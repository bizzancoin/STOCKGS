package com.xc.utils;


import java.util.*;
import java.util.concurrent.*;


/**
 * @author wangbingchen
 * @Description
 * @create 2021-11-23 14:12
 * 简易的缓存工具，用于做短信验证码校验
 * 此类为常驻内存工具
 */
public class CacheUtil {

    private final static ExecutorService EXECUTOR_SERVICE=new ThreadPoolExecutor(2,2,30, TimeUnit.SECONDS,new ArrayBlockingQueue<>(1000));

    public static void main(String[] args) throws InterruptedException {
        CacheUtil.set("name","zhangsan",2000);
        CacheUtil.set("name","lisi",2000);
        CacheUtil.set("name","ww",500);
        CacheUtil.set("name1","qweqwe",4000);
        Thread.sleep(1000);
        Thread.sleep(1000);
        System.out.println(CacheUtil.get("name"));
        System.out.println(CacheUtil.get("name1"));
        Thread.sleep(1000);
        System.out.println(CacheUtil.get("name"));
        System.out.println(CacheUtil.get("name1"));
        Thread.sleep(1000);
        System.out.println(CacheUtil.get("name"));
        System.out.println(CacheUtil.get("name1"));
        Thread.sleep(1000);
        System.out.println(CacheUtil.get("name"));
        System.out.println(CacheUtil.get("name1"));

    }

    private CacheUtil(){}

    private static final Map<String, CacheUtilBean> CACHE_MAP = new ConcurrentHashMap<>();

    public static void set(String key,Object value,long exprTime){
        //将传入的毫秒数 转换为 将来的时间戳
        CACHE_MAP.put(key,new CacheUtilBean(value,System.currentTimeMillis()+exprTime));
    }

    private static final long DEFAULT_EXPR_TIME = 24*60*60*1000L;
    public static void set(String key,Object value){
        set(key,value, DEFAULT_EXPR_TIME);
    }

    public static void remove(String key){
        CACHE_MAP.remove(key);
    }

    public static Object get(String key){

        CacheUtilBean cacheUtilBean = CACHE_MAP.get(key);
        if(cacheUtilBean==null){
            return null;
        }
        EXECUTOR_SERVICE.submit(()->{
            //获取之后在删除之前的
            removeExp();
        });
        return cacheUtilBean.getValue();
    }

    public static void removeExp() {
        List<String> removeKey = new ArrayList<>();
        Iterator<Map.Entry<String, CacheUtilBean>> iterator = CACHE_MAP.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String, CacheUtilBean> next = iterator.next();
            Long exprTime = next.getValue().getExprTime();
            if(System.currentTimeMillis()>exprTime){
                 iterator.remove();
            }
        }
    }
    static class CacheUtilBean {
        //存的值
        private Object value;
        //过期时间戳 set的时候计算好
        private Long exprTime;

        public CacheUtilBean(Object value, Long exprTime) {
            this.value = value;
            this.exprTime = exprTime;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Long getExprTime() {
            return exprTime;
        }

        public void setExprTime(Long exprTime) {
            this.exprTime = exprTime;
        }
    }

}



