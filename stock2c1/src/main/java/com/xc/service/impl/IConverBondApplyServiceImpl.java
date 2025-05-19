package com.xc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qiniu.common.QiniuException;
import com.xc.common.ServerResponse;
import com.xc.dao.ConvertBondApplyMapper;
import com.xc.dao.UserCashDetailMapper;
import com.xc.pojo.*;
import com.xc.service.*;
import com.xc.utils.stock.BuyAndSellUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;


@Service("IConverBondApplyService")
public class IConverBondApplyServiceImpl implements IConverBondApplyService {


    private static final Logger log = LoggerFactory.getLogger(IConverBondApplyServiceImpl.class);

    @Autowired
    private ConvertBondApplyMapper convertBondApplyMapper;

    @Autowired
    private IConverBondService converBondService;

    @Autowired
    private ISiteProductService iSiteProductService;

    @Autowired
    private ISiteSettingService iSiteSettingService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private UserCashDetailMapper userCashDetailMapper;


    /**
     * 获取自己的可转债申购列表
     * @param request
     * @param page
     * @param size
     * @return
     */
    public ServerResponse getMyConvertBondList(HttpServletRequest request,Integer page,Integer size){
        User user = this.iUserService.getCurrentRefreshUser(request);
        if(user== null){
            return ServerResponse.createByErrorMsg("用户不存在");
        }
        ConvertBondApplyExample convertBondApplyExample = new ConvertBondApplyExample();
        ConvertBondApplyExample.Criteria criteria = convertBondApplyExample.createCriteria();
        criteria.andUserIdEqualTo(user.getId());
        PageHelper.startPage(page,size);
        return ServerResponse.createBySuccess(new PageInfo<>( convertBondApplyMapper.selectByExample(convertBondApplyExample)));
    }

    public void update(ConvertBondApply convertBondApply){
          convertBondApplyMapper.updateByPrimaryKeySelective(convertBondApply);
    }


    /**
     * 获取自己的可转债申购
     * @return
     */
    public ServerResponse getMyConvertBond(Integer bondId,Integer userId){
        ConvertBondApplyExample convertBondApplyExample = new ConvertBondApplyExample();
        ConvertBondApplyExample.Criteria criteria = convertBondApplyExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andIdEqualTo(bondId);
        return ServerResponse.createBySuccess(convertBondApplyMapper.selectByExample(convertBondApplyExample));
    }


    /**
     * 申请可转债接口
     * @param userId
     * @param count
     * @param bondId
     * @param request
     * @return
     * @throws Exception
     */
    @Transactional
    public ServerResponse applyConvertBond(Integer userId, Integer count,Integer bondId, HttpServletRequest request) throws Exception{
        // 判断周末不能买
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        if (weekday == 1) {
            return ServerResponse.createByErrorMsg("周末不能购买！");
        }
        if (weekday == 7) {
            return ServerResponse.createByErrorMsg("周末不能购买！");
        }

        /*实名认证开关开启*/
        SiteProduct siteProduct = iSiteProductService.getProductSetting();
        User user = this.iUserService.getCurrentRefreshUser(request);
        if (siteProduct.getRealNameDisplay() && (StringUtils.isBlank(user.getRealName()) || StringUtils.isBlank(user.getIdCard()))) {
            return ServerResponse.createByErrorMsg("下单失败，请先实名认证");
        }

        //查看申购日期核截至日期
        ConvertBond bond = converBondService.getById(bondId);

        if(bond==null){
            return ServerResponse.createByErrorMsg("可转债不存在");
        }

        if(bond.getStatus()==null||bond.getStatus().equals(0)){
            return ServerResponse.createByErrorMsg("可转债以暂停申请");
        }
        Date date = new Date();
        if(bond.getApplyDate().getTime()>date.getTime()){
            return ServerResponse.createByErrorMsg("申请日期未到");
        }

        if(bond.getPubDate().getTime()<date.getTime()){
            return ServerResponse.createByErrorMsg("申请日期已经截至");
        }

        SiteSetting siteSetting = this.iSiteSettingService.getSiteSetting();
        String am_begin = siteSetting.getTransAmBegin();
        String am_end = siteSetting.getTransAmEnd();
        String pm_begin = siteSetting.getTransPmBegin();
        String pm_end = siteSetting.getTransPmEnd();

        boolean am_flag = BuyAndSellUtils.isTransTime(am_begin, am_end);
        boolean pm_flag = BuyAndSellUtils.isTransTime(pm_begin, pm_end);
        log.info("是否在上午交易时间 = {} 是否在下午交易时间 = {}", Boolean.valueOf(am_flag), Boolean.valueOf(pm_flag));
        if (!am_flag && !pm_flag) {
                return ServerResponse.createByErrorMsg("申购，不在交易时段内");
        }
        if(siteProduct.getHolidayDisplay()){
                return ServerResponse.createByErrorMsg("周末或节假日不能交易！");
        }


        if(count<=0){
            return ServerResponse.createByErrorMsg("购买数量不对");
        }

        BigDecimal price = bond.getPrice();
        BigDecimal allNeedMoney=price.multiply(new BigDecimal(count));

        //获取可用沪深账户余额
        BigDecimal enableAmt = user.getEnableAmt();

        if(enableAmt.compareTo(allNeedMoney)<0){
            return ServerResponse.createByErrorMsg("沪深账户余额不足！");
        }

        if(count*10>bond.getApplyLimit()){
            return ServerResponse.createByErrorMsg("超过最大限额！");
        }

        //扣除用户余额
        user.setEnableAmt(enableAmt.subtract(allNeedMoney));
        iUserService.update(user);

        //插入申请可转债记录
        ConvertBondApply convertBondApply = new ConvertBondApply();
        convertBondApply.setAgentId(user.getAgentId());
        convertBondApply.setApplyDate(new Date());
        convertBondApply.setApplyMoney(allNeedMoney);
        convertBondApply.setApplyNum(count);
        convertBondApply.setBondId(bondId);
        convertBondApply.setRefundMony(BigDecimal.ZERO);
        convertBondApply.setStatus(0);
        convertBondApply.setTel(user.getPhone());
        convertBondApplyMapper.insert(convertBondApply);


        //插入资金明细
        UserCashDetail ucd = new UserCashDetail();
        ucd.setPositionId(bondId);
        ucd.setAgentId(user.getAgentId());
        ucd.setAgentName(user.getAgentName());
        ucd.setUserId(user.getId());
        ucd.setUserName(user.getRealName());
        ucd.setDeType("申购可转债");
        ucd.setDeAmt(allNeedMoney);
        ucd.setDeSummary("申购可转债，" + bond.getBondBuyCode() + "/" + bond.getBondName() + ",占用本金：" + allNeedMoney );
        ucd.setAddTime(new Date());
        ucd.setIsRead(Integer.valueOf(0));

        int insertSxfCount = this.userCashDetailMapper.insert(ucd);
        if (insertSxfCount > 0) {
            /*//核算代理收入-平仓手续费
            iAgentAgencyFeeService.AgencyFeeIncome(2,userPosition.getPositionSn());
            //核算代理收入-分红
            iAgentAgencyFeeService.AgencyFeeIncome(4,userPosition.getPositionSn());*/
            log.info("【用户平仓】保存明细记录成功");
        } else {
            log.error("用户平仓】保存明细记录出错");
        }
        return ServerResponse.createBySuccess();
    }

}
