package com.xc.utils.task.stock;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xc.common.ServerResponse;
import com.xc.dao.UserMapper;
import com.xc.dao.UserPositionMapper;
import com.xc.pojo.*;
import com.xc.service.*;
import com.xc.utils.HttpClientRequest;
import com.xc.utils.HttpRequest;
import com.xc.utils.KeyUtils;
import com.xc.utils.PropertiesUtil;
import com.xc.utils.stock.GeneratePosition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 新股申购接口抓取任务
 */

@Component
public class NewStockTask {

    private static final Logger log = LoggerFactory.getLogger(NewStockTask.class);
    @Autowired
    private INewListService iNewListService;

    @Autowired
    private IStockService iStockService;

    @Autowired
    private IListsService iListsService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserPositionMapper userPositionMapper;

    @Autowired
    private ISiteSettingService iSiteSettingService;

    /**
     * 每天工作日 下午4点执行一次
     */
    @Scheduled(cron = "0 0 21 * * MON-FRI")
    public void getNewStockTask() {
        log.info("----------每天工作日下午4点抓取新股日历数据开始--------------");
        Integer count = 0;
        try {
            String url = PropertiesUtil.getProperty("dfcf.new.stock.url");
            String s = HttpRequest.doGet(url, null);
            JSONObject jsonObject = JSON.parseObject(s);
            JSONObject result = jsonObject.getJSONObject("result");
            JSONArray data = result.getJSONArray("data");
            for (int i = 0; i < data.size(); i++) {
                JSONObject o = data.getJSONObject(i);
                //先判断当前新股是否插入
                NewList newStock = iNewListService.getByCode(o.getString("SECURITY_CODE"));
                NewList newList = new NewList();
                newList.setCode(o.getString("SECURITY_CODE"));
                newList.setIssueDate(o.getDate("APPLY_DATE"));
                newList.setListDate(o.getDate("LISTING_DATE") == null ? null : o.getDate("LISTING_DATE"));
                newList.setWinDate(o.getDate("BALLOT_NUM_DATE") == null ? null : o.getDate("BALLOT_NUM_DATE"));
                newList.setNames(o.getString("SECURITY_NAME"));
                newList.setPe(o.getString("AFTER_ISSUE_PE") == null ? null : o.getString("AFTER_ISSUE_PE"));
                newList.setPrice(o.getString("ISSUE_PRICE"));
                newList.setZt(Byte.valueOf("1"));
                if (null == newStock) {
                    iNewListService.save(newList);
                    count++;
                } else {
                    newList.setNewlistId(newStock.getNewlistId());
                    iNewListService.update(newList);
                }
            }
            log.info("----------每天工作日下午4点抓取新股日历数据结束 新增新股数量：{}--------------", count);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


//
//    /**
//     * 每天工作日 下午9.28点执行一次 保存新股票
//     */
//    @Scheduled(cron = "0 28 9  * * MON-FRI")
//    public void saveNewStockTask(){
//        for(int pageNo=1 ;pageNo<2;pageNo++){
//            String market_url = "https://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData?page="+pageNo+"&num=100&sort=changepercent&asc=0&node=hs_a&symbol=&_s_r_a=setlen";
//            String result = null;
//            try {
//                log.info("新浪接口：{}",market_url);
//                result = HttpClientRequest.doGet(market_url);
//                com.alibaba.fastjson.JSONArray jsonArray = JSON.parseArray(result);
//                if(jsonArray!=null&&jsonArray.size()>0){
//                    for(int i=0;i<jsonArray.size();i++){
//                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                        this.iStockService.addStock(jsonObject.getString("name"),jsonObject.getString("code")
//                                ,jsonObject.getString("symbol").substring(0,2),"cc",0,0);
//                    }
//                }
//                TimeUnit.MILLISECONDS.sleep(500);
//            } catch (Exception e) {
//                log.error("e = {}", e);
//            }
//        }
//    }


    /**
     * 每天工作日 上午8点20执行一次 保存新股票
     */
    @Scheduled(cron = "0 20 8  * * MON-FRI")
    public void saveNewStockTask() {
        List<NewList> newShowStockList = iNewListService.getNewShowStockList();
        if (!CollectionUtils.isEmpty(newShowStockList)) {
            for (NewList newList : newShowStockList) {
                String codes = newList.getCode();
                String plateType = "sh";
                if (codes.startsWith("00") || codes.startsWith("30")) {
                    plateType = "sz";
                } else if (codes.startsWith("8") || codes.startsWith("4")) {
                    plateType = "bj";
                }
                ServerResponse response = this.iStockService.addStock(newList.getNames(), newList.getCode()
                        , plateType, "cc", 0, 0);
                if (!response.isSuccess()) {
                    //若提前添加过，需要更新股票锁定状态-》解锁状态
                    Stock stock = (Stock) response.getData();
                    stock.setIsLock(0);
                    iStockService.updateStock(stock);
                }
            }
        }

    }


    /**
     * 每天工作日 上午8点40执行一次 申购新股派发操作
     */
    @Scheduled(cron = "0 40 8  * * MON-FRI")
    public void newStockDistributeTask() {
        List<NewList> newShowStockList = iNewListService.getNewShowStockList();
        if (!CollectionUtils.isEmpty(newShowStockList)) {
            SiteSetting siteSetting = this.iSiteSettingService.getSiteSetting();
            for (NewList newList : newShowStockList) {
                Stock stock = iStockService.findStockByCode(newList.getCode()).getData();
                if (!ObjectUtils.isEmpty(stock)) {
                    //若提前添加过，则派发股票
                    List<Lists> lists = iListsService.getNewStockByCodeAndZts(newList.getCode(), Short.valueOf("4"));
                    if (!CollectionUtils.isEmpty(lists)) {
                        for(Lists l:lists){
                            User user = userMapper.findByPhone(l.getPhone());
                            //第二步骤加入用户持仓
                            UserPosition userPosition = new UserPosition();
                            userPosition.setPositionType(user.getAccountType());
                            userPosition.setPositionSn(KeyUtils.getUniqueKey());
                            userPosition.setUserId(user.getId());
                            userPosition.setNickName(user.getRealName());
                            userPosition.setAgentId(user.getAgentId());
                            userPosition.setStockCode(stock.getStockCode());
                            userPosition.setStockName(stock.getStockName());
                            userPosition.setStockGid(stock.getStockGid());
                            userPosition.setStockSpell(stock.getStockSpell());
                            userPosition.setBuyOrderId(GeneratePosition.getPositionId());
                            userPosition.setBuyOrderTime(new Date());
                            userPosition.setBuyOrderPrice(new BigDecimal(newList.getPrice()));
                            userPosition.setOrderDirection("买涨");
                            userPosition.setStockPlate(stock.getStockPlate());
                            userPosition.setOrderNum(Integer.parseInt(l.getWinums()));
                            //锁定股票
//                            userPosition.setIsLock(1);
//                            userPosition.setLockMsg("新股暂未上市，不可出售！");
                            userPosition.setOrderLever(1);
                            userPosition.setOrderTotalPrice(new BigDecimal(l.getBzj()));
                            userPosition.setOrderStayFee(BigDecimal.ZERO);
                            BigDecimal buy_amt=userPosition.getOrderTotalPrice();

                            BigDecimal buy_fee_amt = buy_amt.multiply(siteSetting.getBuyFee()).setScale(2, 4);
                            log.info("用户购买手续费（配资后总资金 * 百分比） = {}", buy_fee_amt);
                            userPosition.setOrderFee(buy_fee_amt);

                            BigDecimal buy_yhs_amt = buy_amt.multiply(siteSetting.getDutyFee()).setScale(2, 4);
                            log.info("用户购买印花税（配资后总资金 * 百分比） = {}", buy_yhs_amt);
                            userPosition.setOrderSpread(buy_yhs_amt);

                            //点差费
                            userPosition.setSpreadRatePrice(BigDecimal.ZERO);
                            //设置收益
                            userPosition.setProfitAndLose(BigDecimal.ZERO);
                            //设置总收益包含手续费印花税
                            BigDecimal all_profit_and_lose = userPosition.getProfitAndLose().subtract(buy_fee_amt).subtract(buy_yhs_amt);
                            userPosition.setAllProfitAndLose(all_profit_and_lose);
                            userPosition.setOrderStayDays(Integer.valueOf(0));
                            this.userPositionMapper.insertSelective(userPosition);

                            //加上总资产
                            user.setUserAmt(user.getUserAmt().add(userPosition.getOrderTotalPrice()));
                            this.userMapper.updateByPrimaryKeySelective(user);
                            //保存申购状态为派发状态
                            l.setZts(Short.valueOf("5"));
                            this.iListsService.save(l);
                        }
                    }
                }
            }
        }

    }

}
