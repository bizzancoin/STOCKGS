package com.xc.utils.stock.qq;

import com.xc.common.ServerResponse;
import com.xc.pojo.Stock;
import com.xc.pojo.StockFutures;
import com.xc.pojo.StockIndex;
import com.xc.utils.CacheUtil;
import com.xc.utils.HttpClientRequest;
import com.xc.utils.PropertiesUtil;
import com.xc.utils.redis.JsonUtil;
import com.xc.utils.stock.sina.SinaStockApi;
import com.xc.utils.stock.sina.vo.SinaStockMinData;
import com.xc.vo.stock.k.MinDataVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class QqStockApi {
    public static final String qq_url = PropertiesUtil.getProperty("qq.k.min.url");
    private static final Logger log = LoggerFactory.getLogger(SinaStockApi.class);


    public static void main(String[] args) {
        String s = HttpClientRequest.doGet("http://qt.gtimg.cn/q=s_sh600519");
        QqMiniData sh600519 = QqStockApi.getSimpleMarket("sh600519");
        System.out.println(sh600519);

    }


    /**
     * 根据多个股票传入获取简单的行情
     * @param stockGidList
     */
    public static List<QqMiniData> getSimpleAllMarket(String stockGidList) {
        String url = PropertiesUtil.getProperty("tecent.market.simple.url");
        String str= HttpClientRequest.doGet(url.replace("s_sh600519",stockGidList));
        List<QqMiniData> qqMiniDataList=new ArrayList<>();
        if(!StringUtils.isEmpty(str)){
            String[] split1 = str.split(";");
            for(String s:split1){
                    String[] split = s.split("~");
                    if(split.length>2){
                        QqMiniData qqMiniData = new QqMiniData(split[3], split[4], split[5],split[2]);
                        qqMiniDataList.add(qqMiniData);
                    }
            }
            return qqMiniDataList;
        }
        return null;
    }

    /**
     * 获取简单的行情
     * @param stockGid
     */
    public static QqMiniData getSimpleMarket(String stockGid) {
        String url = PropertiesUtil.getProperty("tecent.market.simple.url");
        QqMiniData qqMiniData= (QqMiniData) CacheUtil.get("SIMPRICE:" + stockGid);
        if(!ObjectUtils.isEmpty(qqMiniData)){
            return qqMiniData;
        }
        String str= HttpClientRequest.doGet(url.replace("sh600519",stockGid));
        if(!StringUtils.isEmpty(str)){
            String[] split = str.split("~");
            if(split.length>5){
                qqMiniData = new QqMiniData(split[3], split[4], split[5]);
                CacheUtil.set("SIMPRICE:"+stockGid,qqMiniData,6000);
                return qqMiniData;
            }
        }
        return new QqMiniData(null,null,null);
    }


    public static ServerResponse<MinDataVO> getGpStockDayK(Stock stock) {
        String minUrl = PropertiesUtil.getProperty("qq.k.min.url");
        minUrl = minUrl.replace("code",stock.getStockGid());

        String hqstr = "";
        try {
            hqstr = HttpClientRequest.doGet(minUrl);
        } catch (Exception e) {
            log.error("获取股票K线分时图出错，错误信息 = {}", e);
        }

        log.info(" qq-code = {} ", stock.getStockGid());
        if (StringUtils.isBlank(hqstr)) {
            return ServerResponse.createByErrorMsg("没有查询到行情数据");
        }
        String qqstr = hqstr.split("=")[1].replace("\";","").replace("\\n\\",",").replace("\n","").replace("\"","");
        String[] liststr = qqstr.split(",");
        List<SinaStockMinData> list = new ArrayList<>();
        for (int i = 1; i<liststr.length; i++){
            String[] dayarry = liststr[i].split(" ");
            SinaStockMinData model = new SinaStockMinData();
            model.setDay("20"+dayarry[0].substring(0,2)+"-"+dayarry[0].substring(2,4)+"-"+dayarry[0].substring(4,6));
            model.setOpen(dayarry[1]);
            model.setHigh(dayarry[3]);
            model.setLow(dayarry[4]);
            model.setClose(dayarry[2]);
            model.setMa_price(new BigDecimal("0").doubleValue());
            model.setMa_volume(dayarry[5]);
            model.setVolume(dayarry[5]);
            list.add(model);
        }
        int size = Integer.valueOf(PropertiesUtil.getProperty("qq.k.min.max.size"));
        if(list.size()>size){
            list = list.subList((list.size()-size-1),list.size());
        }
        MinDataVO minDataVO = new MinDataVO();
        minDataVO.setStockName(stock.getStockName());
        minDataVO.setStockCode(stock.getStockCode());
        minDataVO.setGid(stock.getStockGid());
        minDataVO.setData(list);
        return ServerResponse.createBySuccess(minDataVO);
    }

    public static ServerResponse<MinDataVO> getQqStockDayK(StockFutures stock) {
        String minUrl = PropertiesUtil.getProperty("sina.futures.day.min.url");
        minUrl = minUrl.replace("{code}",stock.getFuturesCode());
        SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd");//设置日期格式
        minUrl = minUrl.replace("{date}",df.format(new Date()));


        String hqstr = "";
        try {
            hqstr = HttpClientRequest.doGet(minUrl);
        } catch (Exception e) {
            log.error("获取股票K线分时图出错，错误信息 = {}", e);
        }

        log.info(" 期货日线-code = {} ", stock.getFuturesGid());
        if (StringUtils.isBlank(hqstr)) {
            return ServerResponse.createByErrorMsg("没有查询到行情数据");
        }
        hqstr = hqstr.split("\\(")[1].replace(");","");
        hqstr = hqstr.replaceAll("date","day");
        hqstr = hqstr.replaceAll("\"\"", "\"");

        List<SinaStockMinData> list = (List<SinaStockMinData>) JsonUtil.string2Obj(hqstr, new TypeReference<List<SinaStockMinData>>(){});
        int size = Integer.valueOf(PropertiesUtil.getProperty("sina.futures.day.min.max.size"));
        if(list.size()>size){
            list = list.subList((list.size()-size-1),list.size());
        }

        MinDataVO minDataVO = new MinDataVO();
        minDataVO.setStockName(stock.getFuturesName());
        minDataVO.setStockCode(stock.getFuturesCode());
        minDataVO.setGid(stock.getFuturesGid());
        minDataVO.setData(list);
        return ServerResponse.createBySuccess(minDataVO);
    }

    /*指数日线*/
    public static ServerResponse<MinDataVO> getQqIndexDayK(StockIndex stock) {
        String minUrl = PropertiesUtil.getProperty("sina.index.day.min.url");
        minUrl = minUrl.replace("{code}",stock.getIndexGid());
        SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd");//设置日期格式
        minUrl = minUrl.replace("{date}",df.format(new Date()));


        String hqstr = "";
        try {
            hqstr = HttpClientRequest.doGet(minUrl);
        } catch (Exception e) {
            log.error("获取股票K线分时图出错，错误信息 = {}", e);
        }

        log.info(" 指数日线-code = {} ", stock.getIndexGid());
        if (StringUtils.isBlank(hqstr)) {
            return ServerResponse.createByErrorMsg("没有查询到行情数据");
        }
        hqstr = hqstr.split(":\\[\\[")[1];
        hqstr = hqstr.split("]]")[0].replace("],[",";");
        String[] liststr = hqstr.split(";");
        List<SinaStockMinData> list = new ArrayList<>();
        for (int i = 1; i<liststr.length; i++){
            String[] dayarry = liststr[i].split(",");
            SinaStockMinData model = new SinaStockMinData();
            model.setDay(filterCode(dayarry[0]));
            model.setOpen(filterCode(dayarry[1]));
            model.setHigh(filterCode(dayarry[3]));
            model.setLow(filterCode(dayarry[4]));
            model.setClose(filterCode(dayarry[2]));
            model.setMa_price(new BigDecimal("0").doubleValue());
            model.setMa_volume(filterCode(dayarry[5].split("\\.")[0]));
            model.setVolume(filterCode(dayarry[5].split("\\.")[0]));
            list.add(model);
        }

        MinDataVO minDataVO = new MinDataVO();
        minDataVO.setStockName(stock.getIndexName());
        minDataVO.setStockCode(stock.getIndexCode());
        minDataVO.setGid(stock.getIndexGid());
        minDataVO.setData(list);
        return ServerResponse.createBySuccess(minDataVO);
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s){
        BigDecimal value = new BigDecimal(s);
        BigDecimal noZeros = value.stripTrailingZeros();
        String result = noZeros.toPlainString();
        return result;
    }

    private static String filterCode(String str) {
        return str.replace("\"","");
    }

    /*股票月线、周线
     * type:month=月，week=周
     * */
    public static ServerResponse<MinDataVO> getGpStockMonthK(Stock stock,String type) {
        String minUrl = PropertiesUtil.getProperty("qq.month.min.url");
        minUrl = minUrl.replace("sz300750",stock.getStockGid());
        minUrl = minUrl.replace("month",type);

        String hqstr = "";
        try {
            hqstr = HttpClientRequest.doGet(minUrl);
            hqstr = hqstr.replace("qfq"+type,type);
        } catch (Exception e) {
            log.error("获取股票K线分时图出错，错误信息 = {}", e);
        }

        log.info(" qq-code = {} ", stock.getStockGid());
        if (StringUtils.isBlank(hqstr)) {
            return ServerResponse.createByErrorMsg("没有查询到行情数据");
        }
        //String qqstr = hqstr.split("=")[1];
        JSONObject json = JSONObject.fromObject(hqstr);
        JSONObject data = json.getJSONObject("data");
        JSONObject listjson = data.getJSONObject(stock.getStockGid());
        JSONArray jsonArray = listjson.getJSONArray(type);

        List<SinaStockMinData> list = new ArrayList<>();
        for (int i = 0; i<jsonArray.size(); i++){
            String string = jsonArray.getString(i).replace("\"","").replace("[","").replace("]","");
            String[] dayarry = string.split(",");
            SinaStockMinData model = new SinaStockMinData();
            model.setDay(dayarry[0]);
            model.setOpen(dayarry[1]);
            model.setHigh(dayarry[3]);
            model.setLow(dayarry[4]);
            model.setClose(dayarry[2]);
            model.setMa_price(new BigDecimal("0").doubleValue());
            model.setMa_volume(dayarry[5]);
            model.setVolume(dayarry[5]);
            list.add(model);
        }

        if(list.size()>50){
            list = list.subList((list.size()-50),list.size());
        }

        MinDataVO minDataVO = new MinDataVO();
        minDataVO.setStockName(stock.getStockName());
        minDataVO.setStockCode(stock.getStockCode());
        minDataVO.setGid(stock.getStockGid());
        minDataVO.setData(list);
        return ServerResponse.createBySuccess(minDataVO);
    }


}
