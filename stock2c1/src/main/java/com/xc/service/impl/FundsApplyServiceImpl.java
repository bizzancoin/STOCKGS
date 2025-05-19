package com.xc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;
import com.xc.dao.*;
import com.xc.pojo.*;
import com.xc.service.IFundsApplyService;
import com.xc.service.IFundsSettingService;
import com.xc.service.IUserService;
import com.xc.utils.DateTimeUtil;
import com.xc.utils.KeyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 配资申请
 * @author lr
 * @date 2020/07/24
 */
@Service("IFundsApplyService")
public class FundsApplyServiceImpl implements IFundsApplyService {
    private static final Logger log = LoggerFactory.getLogger(FundsApplyServiceImpl.class);

    @Resource
    private FundsApplyMapper fundsApplyMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserCashDetailMapper userCashDetailMapper;
    @Autowired
    FundsTradingAccountMapper fundsTradingAccountMapper;

    @Autowired
    IUserService iUserService;
    @Autowired
    IFundsSettingService iFundsSettingService;


    @Transactional
    public ServerResponse insert(FundsApply model, HttpServletRequest request) throws Exception {
        int ret = 0;
        if (model == null) {
            return ServerResponse.createBySuccessMsg("操作异常，请稍后重试！");
        }
        User user = this.iUserService.getCurrentRefreshUser(request);
        if(user == null){
            return ServerResponse.createBySuccessMsg("请登录后操作");
        }
        BigDecimal user_enable_amt = user.getEnableAmt();
        //支付金额=保证金+管理费
        BigDecimal pay_amount = model.getMargin().add(model.getManageFee());
        int compareUserAmtInt = user_enable_amt.compareTo(pay_amount);
        log.info("用户可用金额 = {}  实际购买金额 =  {} 比较结果 = {} ", user_enable_amt, pay_amount, compareUserAmtInt);
        if (compareUserAmtInt == -1) {
            return ServerResponse.createByErrorMsg("申请失败，可用金额小于" + pay_amount + "元");
        }

        //自动生成订单编号
        model.setOrderNumber(KeyUtils.getUniqueKey());
        model.setPayAmount(pay_amount);
        ret = fundsApplyMapper.insert(model);
        if(ret>0){
            //修改用户可用余额= 当前余额-支付金额
            BigDecimal reckon_enable = user_enable_amt.subtract(pay_amount);
            user.setEnableAmt(reckon_enable);
            int updateUserCount = this.userMapper.updateByPrimaryKeySelective(user);
            if (updateUserCount > 0) {
                log.info("【用户交易下单】修改用户金额成功");
                UserCashDetail ucd = new UserCashDetail();
                ucd.setPositionId(model.getId());
                ucd.setAgentId(user.getAgentId());
                ucd.setAgentName(user.getAgentName());
                ucd.setUserId(user.getId());
                ucd.setUserName(user.getRealName());
                ucd.setDeType("配资冻结");
                ucd.setDeAmt(model.getPayAmount().multiply(new BigDecimal("-1")));
                ucd.setDeSummary("申请按天配资:" + model.getOrderNumber() + "，冻结金额：" + model.getPayAmount().multiply(new BigDecimal("-1")) );
                ucd.setAddTime(new Date());
                ucd.setIsRead(Integer.valueOf(0));
                int insertSxfCount = this.userCashDetailMapper.insert(ucd);
                if (insertSxfCount > 0) {
                    log.info("【按天配资】申请成功");
                }

            } else {
                log.error("【按天配资】修改用户金额出错");
                throw new Exception("【按天配资】修改用户金额出错");
            }
            return ServerResponse.createBySuccessMsg("申请成功！");
        } else {
            return ServerResponse.createBySuccessMsg("申请失败，请稍后重试！");
        }
    }

    @Override
    public int update(FundsApply model) {
        int ret = fundsApplyMapper.update(model);
        return ret>0 ? ret: 0;
    }

    /**
     * 配资申请-保存
     */
    @Override
    public ServerResponse save(FundsApply model) {
        int ret = 0;
        if(model!=null && model.getId()>0){
            ret = fundsApplyMapper.update(model);
        } else{
            ret = fundsApplyMapper.insert(model);
        }
        if(ret>0){
            return ServerResponse.createBySuccessMsg("操作成功");
        }
        return ServerResponse.createByErrorMsg("操作失败");
    }

    /**
     * 配资申请-审核
     */
    /*     */   @Transactional
    /*     */   public ServerResponse audit(FundsApply model, HttpServletRequest request) throws Exception {
        /* 135 */     FundsApply fundsApply = this.fundsApplyMapper.load(model.getId().intValue());
        /* 136 */     int ret = 0;
        /* 137 */     if (model != null && model.getId().intValue() > 0) {
            /* 138 */       User user = this.userMapper.selectByPrimaryKey(fundsApply.getUserId());
            /*     */
            /* 140 */       if (model.getStatus().intValue() == 1) {
                /* 141 */         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                /* 142 */         String begtime = df.format(new Date()).split(" ")[0] + " 0:00:00";
                /* 143 */         Date date = DateTimeUtil.strToDate(begtime);
                /* 144 */         Date begDate = DateTimeUtil.addDay(date, 1);
                /* 145 */         model.setBeginTime(begDate);
                /* 146 */         String endtime = df.format(new Date()).split(" ")[0] + " 23:59:59";
                /* 147 */         Date endDate = DateTimeUtil.strToDate(endtime);
                /* 148 */         endDate = DateTimeUtil.addDay(endDate, model.getTradersCycle().intValue() + 1);
                /* 149 */         model.setEndTime(endDate);
                /* 150 */         model.setEnabledTradingAmount(fundsApply.getTotalTradingAmount());
                /* 151 */         FundsSetting fundsSetting = this.iFundsSettingService.getFundsSetting();
                /*     */
                /* 153 */         BigDecimal lineUnwind = fundsApply.getMargin().multiply(fundsSetting.getDaysUnwind()).add(fundsApply.getFundsAmount()).setScale(2, 4);
                /* 154 */         model.setLineUnwind(lineUnwind);
                /*     */
                /* 156 */         BigDecimal lineWarning = fundsApply.getMargin().multiply(fundsSetting.getDaysWarning()).add(fundsApply.getFundsAmount()).setScale(2, 4);
                /* 157 */         model.setLineWarning(lineWarning);
                /*     */       }
            /* 159 */       model.setAuditTime(DateTimeUtil.getCurrentDate());
            /* 160 */       ret = this.fundsApplyMapper.update(model);
            /* 161 */       if (ret > 0) {
                /* 162 */         BigDecimal user_enable_amt = user.getEnableAmt();
                /*     */
                /* 164 */         if (model.getStatus().intValue() == 1) {
                    /* 165 */           BigDecimal user_all_amt = user.getUserAmt();
                    /*     */
                    /* 167 */           BigDecimal reckon_all = user_all_amt.subtract(fundsApply.getManageFee());
                    /*     */
                    /* 169 */           BigDecimal tradingAmount = user.getTradingAmount().add(fundsApply.getTotalTradingAmount());
                    /* 170 */           log.info("【配资审核通过】用户平总资金  = {} , 可用资金 = {} , 总操盘资金 = {}", new Object[] { reckon_all, user_enable_amt, tradingAmount });
                    /* 171 */           user.setUserAmt(reckon_all);
                    /* 172 */           user.setTradingAmount(tradingAmount);
                    /* 173 */           int updateUserCount = this.userMapper.updateByPrimaryKeySelective(user);
                    /* 174 */           if (updateUserCount > 0) {
                        /* 175 */             log.info("【配资审核通过】修改用户金额成功");
                        /*     */
                        /* 177 */             FundsTradingAccount fundsTradingAccount = this.fundsTradingAccountMapper.getAccountByNumber(model.getSubaccountNumber());
                        /* 178 */             if (fundsTradingAccount != null) {
                            /* 179 */               fundsTradingAccount.setStatus(Integer.valueOf(1));
                            /* 180 */               this.fundsTradingAccountMapper.update(fundsTradingAccount);
                            /*     */             }
                        /*     */
                        /* 183 */             UserCashDetail ucd = new UserCashDetail();
                        /* 184 */             ucd.setPositionId(fundsApply.getId());
                        /* 185 */             ucd.setAgentId(user.getAgentId());
                        /* 186 */             ucd.setAgentName(user.getAgentName());
                        /* 187 */             ucd.setUserId(user.getId());
                        /* 188 */             ucd.setUserName(user.getRealName());
                        /* 189 */             ucd.setDeType("配资审核通过");
                        /* 190 */             ucd.setDeAmt(fundsApply.getPayAmount());
                        /* 191 */             ucd.setDeSummary("申请按天配资:" + fundsApply.getOrderNumber() + "，配资审核通过，解冻保证金到配资账户：" + fundsApply.getPayAmount());
                        /* 192 */             ucd.setAddTime(new Date());
                        /* 193 */             ucd.setIsRead(Integer.valueOf(0));
                        /* 194 */             int insertSxfCount = this.userCashDetailMapper.insert(ucd);
                        /* 195 */             if (insertSxfCount > 0) {
                            /* 196 */               log.info("【配资审核通过】申请成功");
                            /*     */             }
                        /*     */           } else {
                        /* 199 */             log.error("【配资审核通过】修改用户金额出错");
                        /* 200 */             throw new Exception("【配资审核通过】修改用户金额出错");
                        /*     */           }
                    /*     */
                    /*     */         } else {
                    /*     */
                    /* 205 */           BigDecimal reckon_enable = user_enable_amt.add(fundsApply.getPayAmount());
                    /* 206 */           user.setEnableAmt(reckon_enable);
                    /* 207 */           int updateUserCount = this.userMapper.updateByPrimaryKeySelective(user);
                    /* 208 */           if (updateUserCount > 0) {
                        /* 209 */             log.info("【配资审核未通过】修改用户金额成功");
                        /* 210 */             UserCashDetail ucd = new UserCashDetail();
                        /* 211 */             ucd.setPositionId(fundsApply.getId());
                        /* 212 */             ucd.setAgentId(user.getAgentId());
                        /* 213 */             ucd.setAgentName(user.getAgentName());
                        /* 214 */             ucd.setUserId(user.getId());
                        /* 215 */             ucd.setUserName(user.getRealName());
                        /* 216 */             ucd.setDeType("配资审核未通过");
                        /* 217 */             ucd.setDeAmt(fundsApply.getPayAmount());
                        /* 218 */             ucd.setDeSummary("申请按天配资:" + fundsApply.getOrderNumber() + "，配资审核未通过，解冻保证金到余额：" + fundsApply.getPayAmount() + "，原因：" + model.getAuditOpinion());
                        /* 219 */             ucd.setAddTime(new Date());
                        /* 220 */             ucd.setIsRead(Integer.valueOf(0));
                        /* 221 */             int insertSxfCount = this.userCashDetailMapper.insert(ucd);
                        /* 222 */             if (insertSxfCount > 0) {
                            /* 223 */               log.info("【按天配资】申请成功");
                            /*     */             }
                        /*     */           } else {
                        /* 226 */             log.error("【按天配资】修改用户金额出错");
                        /* 227 */             throw new Exception("【按天配资】修改用户金额出错");
                        /*     */           }
                    /*     */         }
                /*     */
                /* 231 */         log.info("配资申请-审核 = {}  实际购买金额 =  {} 比较结果 = {} ", Integer.valueOf(0));
                /*     */       }
            /*     */     }
        /* 234 */     if (ret > 0) {
            /* 235 */       return ServerResponse.createBySuccessMsg("操作成功");
            /*     */     }
        /* 237 */     return ServerResponse.createByErrorMsg("操作失败");
        /*     */   }


    /*配资申请-查询列表*/
    @Override
    public ServerResponse<PageInfo> getList(int pageNum, int pageSize, String keyword, Integer status, HttpServletRequest request){
        PageHelper.startPage(pageNum, pageSize);
        List<FundsApply> listData = this.fundsApplyMapper.pageList(pageNum, pageSize, keyword, status);
        PageInfo pageInfo = new PageInfo(listData);
        pageInfo.setList(listData);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /*配资申请-查询详情*/
    @Override
    public ServerResponse getDetail(int id) {
        return ServerResponse.createBySuccess(this.fundsApplyMapper.load(id));
    }

    /**
     * 配资申请-用户配资列表
     */
    @Override
    public ServerResponse<PageInfo> getUserApplyList(int pageNum, int pageSize, int userId, HttpServletRequest request){
        User user = this.iUserService.getCurrentRefreshUser(request);
        PageHelper.startPage(pageNum, pageSize);
        List<FundsApply> listData = this.fundsApplyMapper.getUserApplyList(pageNum, pageSize, user.getId());
        PageInfo pageInfo = new PageInfo(listData);
        pageInfo.setList(listData);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /**
     * 配资申请-用户操盘中子账户
     */
    @Override
    public ServerResponse<PageInfo> getUserEnabledSubaccount(HttpServletRequest request){
        User user = this.iUserService.getCurrentRefreshUser(request);
        List<FundsApply> listData = this.fundsApplyMapper.getUserEnabledSubaccount(user.getId());
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(listData);
        return ServerResponse.createBySuccess(pageInfo);
    }

}
