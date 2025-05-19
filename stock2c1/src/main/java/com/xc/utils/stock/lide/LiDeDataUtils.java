package com.xc.utils.stock.lide;

import com.xc.common.StockIndexConst;
import com.xc.utils.HttpClientRequest;
import com.xc.utils.HttpRequest;
import com.xc.utils.redis.RedisShardedPool;
import com.xc.utils.redis.RedisShardedPoolUtils;
import com.xc.utils.stock.SerializeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.ShardedJedis;
import sun.net.www.http.HttpClient;

import java.util.ArrayList;
import java.util.List;

/**
 * 对接立德数据接口 http://ldhqsj.com
 */
public class LiDeDataUtils {

    public static  final  String URL="http://139.196.211.109/stock_currPlus.action";

    public static  final  String URLINDEX="http://139.196.211.109/stock_curr.action";
    //新三板股票接口
    public static  final  String XSBURL="http://139.196.211.109/new3_currPlus.action";
    private static final Logger log = LoggerFactory.getLogger(LiDeDataUtils.class);



    /**
     * 拉取指数全量数据
     */
    public  static List<LideDataVo> pullIndexData(){
        String password = null;
        int size=0;
        List<LideDataVo> lideDataVos=null;
        try {
            password = "52d582b7580d0c019cc05950d3c88829";//Md5Utils.getMD5("jzy123456");
            String params="?username=mry520&password="+password+"&zs=0&id=H00001,399001,399006,399300,399005,399673,399106,399004,399100&column=mc,price,zdf,zde";
            System.out.println(URL+params);
            String s = HttpClientRequest.doGet(URL+params);
            //s=s.replaceFirst("品种代码,开盘价,最高价,最低价,昨收,现价,名称,成交量,成交额,涨跌幅,涨跌额,卖一价,卖二价,卖三价,卖四价,卖五价,买一价,买二价,买三价,买四价,买五价","");
            String[] split = s.trim().split("\r\n");
            lideDataVos = new ArrayList<>();
            for(int i=1;i<split.length;i++){
                String stock= split[i];
                String[] stockSplite = stock.split(",");
                LideDataVo lideDataVo = new LideDataVo();
                lideDataVo.setName(stockSplite[1]);
                lideDataVo.setCode(stockSplite[0]);
                lideDataVo.setPrice(stockSplite[2]);
                lideDataVo.setZdf(stockSplite[3]);
                lideDataVo.setZde(stockSplite[4]);
                lideDataVos.add(lideDataVo);
            }
            size=split.length-1;
            RedisShardedPoolUtils.stockPipSet(lideDataVos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("-------------------全量同步股票结束 {}个--------------",size);
        return lideDataVos;
    }

    /**
     * 拉取沪深全量数据
     */
    public  static void pullData(){
        String password = null;
        int size=0;
        try {
            password = "52d582b7580d0c019cc05950d3c88829";//Md5Utils.getMD5("jzy123456");
            String params="?username=mry520&password="+password+"&zs=0&column=mc,open,high,low,pre,price,vol,e,zdf,zde,s1,s2,s3,s4,s5,b1,b2,b3,b4,b5";
            System.out.println(URL+params);
            String s = HttpClientRequest.doGet(URL+params);
            //s=s.replaceFirst("品种代码,开盘价,最高价,最低价,昨收,现价,名称,成交量,成交额,涨跌幅,涨跌额,卖一价,卖二价,卖三价,卖四价,卖五价,买一价,买二价,买三价,买四价,买五价","");
            String[] split = s.trim().split("\r\n");
            ArrayList<LideDataVo> lideDataVos = new ArrayList<>();
            for(int i=1;i<split.length;i++){
                String stock= split[i];
                String[] stockSplite = stock.split(",");
                LideDataVo lideDataVo = new LideDataVo(stockSplite[0], stockSplite[1], stockSplite[2], stockSplite[3], stockSplite[4], stockSplite[5], stockSplite[6],
                        stockSplite[7], stockSplite[8], stockSplite[9], stockSplite[10], stockSplite[11], stockSplite[12], stockSplite[13]
                        , stockSplite[14], stockSplite[15], stockSplite[16], stockSplite[17], stockSplite[18], stockSplite[19], stockSplite[20]);
                lideDataVos.add(lideDataVo);
            }
            size=split.length;
            RedisShardedPoolUtils.stockPipSet(lideDataVos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("-------------------全量同步股票结束 {}个--------------",size);
    }


    /**
     * 拉取沪深股票盘口数据
     */
    public  static void pullPankouData(){
        String password = null;
        int size=0;
        try {
            password = "52d582b7580d0c019cc05950d3c88829";//Md5Utils.getMD5("jzy123456");
            String params="?username=mry520&password="+password+"&zs=0&column=b1v,b2v,b3v,b4v,b5v,s1v,s2v,s3v,s4v,s5v";
            System.out.println(URL+params);
            String s = HttpClientRequest.doGet(URL+params);
            String[] split =  s.trim().split("\r\n");
            ArrayList<LideDataPanVo> lideDataVos = new ArrayList<>();
            for(int i=1;i<split.length;i++){
                String stock= split[i];
                String[] stockSplite = stock.split(",");
                LideDataPanVo lideDataPanVo = new LideDataPanVo(stockSplite[0], stockSplite[1], stockSplite[2], stockSplite[3], stockSplite[4], stockSplite[5], stockSplite[6],
                        stockSplite[7], stockSplite[8], stockSplite[9], stockSplite[10]);
                lideDataVos.add(lideDataPanVo);
            }
            size=split.length;
            RedisShardedPoolUtils.stockPanPipSet(lideDataVos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("-------------------全量同步盘口股票结束 {}个--------------",size);
    }


    /**
     * 拉取新三板数据
     */
    public  static void pullXSBData(){
        String password = null;
        int size=0;
        try {
            password = "52d582b7580d0c019cc05950d3c88829";//Md5Utils.getMD5("jzy123456");
            String params="?username=mry520&password="+password+"&zs=0&column=mc,open,high,low,pre,price,vol,e,zdf,zde,s1,s2,s3,s4,s5,b1,b2,b3,b4,b5";
            System.out.println(XSBURL+params);
            String s = HttpClientRequest.doGet(XSBURL+params);
            //s=s.replaceFirst("品种代码,开盘价,最高价,最低价,昨收,现价,名称,成交量,成交额,涨跌幅,涨跌额,卖一价,卖二价,卖三价,卖四价,卖五价,买一价,买二价,买三价,买四价,买五价","");
            String[] split = s.trim().split("\r\n");
            ArrayList<LideDataVo> lideDataVos = new ArrayList<>();
            for(int i=1;i<split.length;i++){
                String stock= split[i];
                String[] stockSplite = stock.split(",");
                LideDataVo lideDataVo = new LideDataVo(stockSplite[0], stockSplite[1], stockSplite[2], stockSplite[3], stockSplite[4], stockSplite[5], stockSplite[6],
                        stockSplite[7], stockSplite[8], stockSplite[9], stockSplite[10], stockSplite[11], stockSplite[12], stockSplite[13]
                        , stockSplite[14], stockSplite[15], stockSplite[16], stockSplite[17], stockSplite[18], stockSplite[19], stockSplite[20]);
                lideDataVos.add(lideDataVo);
            }
            size=split.length;
            RedisShardedPoolUtils.stockPipSet(lideDataVos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("-------------------全量同步新三板股票结束 {}个--------------",size);
    }


    /**
     * 拉取新三板股票盘口数据
     */
    public  static void pullXSBPankouData(){
        String password = null;
        int size=0;
        try {
            password = "52d582b7580d0c019cc05950d3c88829";//Md5Utils.getMD5("jzy123456");
            String params="?username=mry520&password="+password+"&zs=0&column=b1v,b2v,b3v,b4v,b5v,s1v,s2v,s3v,s4v,s5v";
            System.out.println(XSBURL+params);
            String s = HttpRequest.doGetZip(XSBURL,params);
            String[] split = s.trim().split("\r\n");
            ArrayList<LideDataPanVo> lideDataVos = new ArrayList<>();
            for(int i=1;i<split.length;i++){
                String stock= split[i];
                String[] stockSplite = stock.split(",");
                LideDataPanVo lideDataPanVo = new LideDataPanVo(stockSplite[0], stockSplite[1], stockSplite[2], stockSplite[3], stockSplite[4], stockSplite[5], stockSplite[6],
                        stockSplite[7], stockSplite[8], stockSplite[9], stockSplite[10]);
                lideDataVos.add(lideDataPanVo);
            }
            size=split.length;
            RedisShardedPoolUtils.stockPanPipSet(lideDataVos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("-------------------全量同步新三板盘口股票结束 {}个--------------",size);
    }



    public static LideDataVo getStock(String code){
        String key="STOCK:" + code;
        LideDataVo lideDataVo = StockIndexConst.STOCKMap.get(key);
        if(!ObjectUtils.isEmpty(lideDataVo)){
            return lideDataVo;
        }
        byte[] bytearr = RedisShardedPoolUtils.get((key).getBytes());
        if(!StringUtils.isEmpty(bytearr)){
            lideDataVo = (LideDataVo) SerializeUtil.unserialize(bytearr,LideDataVo.class);
            return lideDataVo;
        }
        return null;
    }

    public static LideDataPanVo getStockPankou(String code){
        String key="STOCK:PAN:" + code;
        LideDataPanVo lideDataPanVo = StockIndexConst.STOCKPANMap.get(key);
        if(!ObjectUtils.isEmpty(lideDataPanVo)){
            return lideDataPanVo;
        }
        byte[] bytearr= RedisShardedPoolUtils.get(("STOCK:PAN:" + code).getBytes());
        if(!StringUtils.isEmpty(bytearr)){
            lideDataPanVo = (LideDataPanVo) SerializeUtil.unserialize(bytearr,LideDataPanVo.class);
            return lideDataPanVo;
        }
        return null;
    }

    /**
     * 根据code,code 格式批量获取数据
     * @param codeList
     * @return
     */
    public static List<LideDataVo> getStockList(String codeList){
        String[] split = codeList.split(",");
        ArrayList<LideDataVo> objects = new ArrayList<>();
        for(int i=0;i<split.length;i++){
//            if(split[i].startsWith("8")||split[i].startsWith("4")){
//                continue;
//            }
            LideDataVo stock = getStock(split[i]);
            if(!ObjectUtils.isEmpty(stock)){
                objects.add(stock);
            }
//            byte[] bytearr= RedisShardedPoolUtils.get(("STOCK:" + split[i]).getBytes());
//            if(!StringUtils.isEmpty(bytearr)){
//                LideDataVo lideDataVo = (LideDataVo) SerializeUtil.unserialize(bytearr,LideDataVo.class);
//                objects.add(lideDataVo);
//            }
        }
        return objects;
    }


    public static void main(String[] args) throws Exception {
//        String password = Md5Utils.getMD5("password");
//        System.out.println(password);
       // pullData();

//        LideDataVo lideDataVo = new LideDataVo();
//        lideDataVo.setAmount("1111");
//        ShardedJedis jedis = RedisShardedPool.getJedis();
//        String set =jedis.set("STOCK:111".getBytes(), SerializeUtil.serialize(lideDataVo));
//        byte[] bytes = jedis.get("STOCK:111".getBytes());
//        LideDataVo unserialize = SerializeUtil.unserialize(bytes, LideDataVo.class);

//        LideDataVo stock = getStock("688221");
//        System.out.println(stock);
        LiDeDataUtils.pullIndexData();

    }
}
