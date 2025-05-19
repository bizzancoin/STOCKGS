package com.xc.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;
import com.xc.dao.FundsAppendMapper;
import com.xc.dao.FundsApplyMapper;
import com.xc.dao.FundsLeverMapper;
import com.xc.pojo.FundsAppend;
import com.xc.pojo.FundsApply;
import com.xc.pojo.FundsLever;
import com.xc.pojo.User;
import com.xc.service.IFundsAppendService;
import com.xc.service.IFundsApplyService;
import com.xc.service.IUserService;
import com.xc.utils.DateTimeUtil;
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
 * 配资追加申请
 * @author lr
 * @date 2020/07/24
 */
@Service("IFundsAppendService")
public class FundsAppendServiceImpl implements IFundsAppendService {

    @Resource
    private FundsAppendMapper fundsAppendMapper;

    @Autowired
    IUserService iUserService;

    @Autowired
    FundsApplyMapper fundsApplyMapper;

    @Autowired
    FundsLeverMapper fundsLeverMapper;


    @Override
    public int insert(FundsAppend model) {
        int ret = 0;
        if (model == null) {
            return 0;
        }
        ret = fundsAppendMapper.insert(model);
        return ret;
    }

    @Override
    public int update(FundsAppend model) {
        int ret = fundsAppendMapper.update(model);
        return ret>0 ? ret: 0;
    }

    /**
     * 配资追加申请-保存
     */
    @Transactional
    public ServerResponse save(FundsAppend model, HttpServletRequest request) {
        int ret = 0;
        if(model.getApplyId() == null || model.getApplyId() == 0){
            return ServerResponse.createByErrorMsg("操作异常，请稍后再试");
        }
        FundsApply fundsApply = fundsApplyMapper.load(model.getApplyId());
        if(fundsApply == null){
            return ServerResponse.createByErrorMsg("子账户不存在，请稍后再试");
        }
        User user = this.iUserService.getCurrentRefreshUser(request);
        if(user == null){
            return ServerResponse.createBySuccessMsg("请登录后操作");
        }
        //修改+审核
        if(model!=null && model.getId()>0){
            FundsAppend fundsAppend = fundsAppendMapper.load(model.getId());
            model.setAuditTime(DateTimeUtil.getCurrentDate());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            // 追加类型：1、扩大配资，2、追加保证金，3、续期，4、终止操盘，5、提前盈利
            if(fundsAppend.getAppendType() == 1){

            } else if(fundsAppend.getAppendType() == 3){
                String endtime = df.format(fundsApply.getEndTime());
                Date endDate = DateTimeUtil.strToDate(endtime);
                endDate = DateTimeUtil.addDay(endDate, fundsAppend.getAppendCycle());
                model.setEndTime(endDate);
                model.setTradersCycle(fundsAppend.getTradersCycle() + fundsAppend.getAppendCycle());
                //修改子账户结束时间
                fundsApply.setEndTime(endDate);
                fundsApplyMapper.update(fundsApply);
                //修改用户余额，待完善

            } else if(fundsAppend.getAppendType() == 4){
                //修改子账户状态
                if(model.getStatus() == 1){
                    fundsApply.setStatus(4);
                    fundsApplyMapper.update(fundsApply);
                }
                //修改用户余额，待完善

            }
            ret = fundsAppendMapper.update(model);
        } else{// 提交申请
            model.setUserId(user.getId());
            model.setUserName(user.getRealName());
            model.setUserPhone(user.getPhone());
            model.setMargin(fundsApply.getMargin());
            model.setFundsAmount(fundsApply.getFundsAmount());
            model.setLever(fundsApply.getLever());
            model.setManageFee(fundsApply.getManageFee());
            model.setTotalTradingAmount(fundsApply.getTotalTradingAmount());
            model.setLineWarning(fundsApply.getLineWarning());
            model.setLineUnwind(fundsApply.getLineUnwind());
            model.setRatioWarning(fundsApply.getRatioWarning());
            model.setRatioUnwind(fundsApply.getRatioUnwind());
            model.setEndTime(fundsApply.getEndTime());

            // 追加类型：1、扩大配资，2、追加保证金，3、续期，4、终止操盘，5、提前盈利
            if(model.getAppendType() == 1){
                model.setTradersCycle(fundsApply.getTradersCycle());
                FundsLever fundsLever = fundsLeverMapper.getLeverRateInfo(1, fundsApply.getLever());
                BigDecimal appendServiceFee = model.getAppendMargin().multiply(fundsLever.getManageRate());
                model.setAppendServiceFee(appendServiceFee);
                model.setPayAmount(model.getAppendMargin());
            } else if(model.getAppendType() == 2) {
                model.setTradersCycle(fundsApply.getTradersCycle());
                model.setPayAmount(model.getAppendMargin());
            } else if(model.getAppendType() == 3){
                model.setTradersCycle(model.getAppendCycle());
                model.setPayAmount(model.getAppendServiceFee());
            } else if(model.getAppendType() == 4){
                int isAppendEnd = fundsAppendMapper.isAppendEnd(model.getApplyId());
                if(isAppendEnd>0){
                    return ServerResponse.createByErrorMsg("申请已提交，无需重复操作！");
                }
                model.setTradersCycle(model.getAppendCycle());
                model.setPayAmount(model.getAppendServiceFee());
            }
            ret = fundsAppendMapper.insert(model);
        }
        if(ret>0){
            return ServerResponse.createBySuccessMsg("操作成功");
        }
        return ServerResponse.createByErrorMsg("操作失败");
    }

    /*配资追加申请-查询列表*/
    @Override
    public ServerResponse<PageInfo> getList(int pageNum, int pageSize, String keyword, Integer status, Integer userId, Integer appendType, HttpServletRequest request){
        PageHelper.startPage(pageNum, pageSize);
        List<FundsAppend> listData = this.fundsAppendMapper.pageList(pageNum, pageSize, keyword, status, userId, appendType);
        PageInfo pageInfo = new PageInfo(listData);
        pageInfo.setList(listData);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /*配资追加申请-查询详情*/
    @Override
    public ServerResponse getDetail(int id) {
        return ServerResponse.createBySuccess(this.fundsAppendMapper.load(id));
    }

}