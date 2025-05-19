package com.xc.controller;

import com.alibaba.fastjson.JSON;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.xc.common.ServerResponse;
import com.xc.dao.UserMapper;
import com.xc.dao.UserPositionMapper;
import com.xc.pojo.*;
import com.xc.service.IListsService;
import com.xc.service.INewListService;
import com.xc.service.ISiteSettingService;
import com.xc.service.IStockService;
import com.xc.utils.KeyUtils;
import com.xc.utils.stock.GeneratePosition;
import com.xc.utils.task.stock.NewStockTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 新股接口
 */
@Controller
@RequestMapping({"/api/newstock/"})
@Api("新股数据")
public class NewStockApiController {


    @Autowired
    INewListService iNewListService;


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

    private static final Logger log = LoggerFactory.getLogger(NewStockApiController.class);

    /*新股日历数据初始化*/
    @PostMapping({"init.do"})
    @ResponseBody
    public ServerResponse newStockInit() {
        try {
            iNewListService.getNewStockListTask();
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMsg(e.getMessage());
        }
        return ServerResponse.createBySuccess();
    }


    @ApiOperation("今日申购")
    @PostMapping({"currentApplyList.do"})
    @ResponseBody
    public ServerResponse currentApply() {
        return  ServerResponse.createBySuccess(setType(iNewListService.getCurrentDayStockList()));
    }

    @ApiOperation("根据代码获取新股详情")
    @PostMapping({"currentApplyDetail.do"})
    @ResponseBody
    public ServerResponse currentApplyDetail(String code) {
        return  ServerResponse.createBySuccess(iNewListService.getByCode(code));
    }

    @ApiOperation("今日上市")
    @PostMapping({"currentShowList.do"})
    @ResponseBody
    public ServerResponse currentShow() {
        return  ServerResponse.createBySuccess(setType(iNewListService.getNewShowStockList()));
    }

    @ApiOperation("即将发布")
    @PostMapping({"upcommingList.do"})
    @ResponseBody
    public ServerResponse upcomming() {
        return  ServerResponse.createBySuccess(setType(iNewListService.getUpcomingSubscription()));
    }

    @ApiOperation("今日公布中签的新股")
    @PostMapping({"winShowList.do"})
    @ResponseBody
    public ServerResponse winShow() {
        return  ServerResponse.createBySuccess(setType(iNewListService.getWinStockList()));
    }

    /**
     * 手动调用派发股票任务
     * @return
     */
    @ApiOperation("手动派发新股任务")
    @PostMapping({"distributeNewStock.do"})
    @ResponseBody
    @Transactional
    public ServerResponse distributeNewStock(String pass){
        if(!pass.equals("753951asd")){
            return ServerResponse.createByErrorMsg("密码错误");
        }
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
                        return ServerResponse.createBySuccessMsg("派发股票总共:"+lists.size()+"个申购股票");
                    }
                }
            }
        }
        return ServerResponse.createByErrorMsg("派发失败");
    }


    private List<Object> setType(List<NewList> newLists){
        ArrayList<Object> objects = new ArrayList<>();
        if(!CollectionUtils.isEmpty(newLists)){
               for(NewList newList:newLists){
                   Map<String, Object> innerMap = JSON.parseObject(JSON.toJSONString(newList)).getInnerMap();
                   String  code =String.valueOf(innerMap.get("code")) ;
                   if(code.startsWith("00")){
                       innerMap.put("type","深");
                   }else if(code.startsWith("60")){
                       innerMap.put("type","沪");
                   }else if(code.startsWith("30")){
                       innerMap.put("type","创");
                   }else if(code.startsWith("68")){
                       innerMap.put("type","科");
                   }else {
                       innerMap.put("type","北");
                   }
                   objects.add(innerMap);
               }
               return objects;
           }
        return objects;
    }

}
