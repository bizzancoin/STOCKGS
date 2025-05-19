package com.xc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;
import com.xc.dao.FundsLeverMapper;
import com.xc.pojo.AgentAgencyFee;
import com.xc.pojo.FundsLever;
import com.xc.service.IFundsLeverService;
import com.xc.vo.agent.AgentAgencyFeeVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * funds_lever
 * @author lr
 * @date 2020/07/23
 */
@Service("IFundsLeverService")
public class FundsLeverServiceImpl implements IFundsLeverService {

    @Resource
    private FundsLeverMapper fundsLeverMapper;


    @Override
    public int insert(FundsLever fundsLever) {
        int ret = 0;
        // valid
        if (fundsLever == null) {
            return 0;
        }
        ret = fundsLeverMapper.insert(fundsLever);
        return ret;

    }


    @Override
    public int delete(int id) {
        int ret = fundsLeverMapper.delete(id);
        return ret>0 ? ret: 0;
    }


    @Override
    public int update(FundsLever fundsLever) {
        int ret = fundsLeverMapper.update(fundsLever);
        return ret>0 ? ret: 0;
    }

    /*查询配资杠杆列表*/
    @Override
    public ServerResponse<PageInfo> getFundsLeverList(int pageNum, int pageSize, HttpServletRequest request){
        Page<FundsLever> page = PageHelper.startPage(pageNum, pageSize);
        List<FundsLever> fundsLevers = this.fundsLeverMapper.pageList(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(page);
        pageInfo.setList(fundsLevers);
        return ServerResponse.createBySuccess(pageInfo);
    }

    //配资杠杆列表保存
    @Override
    public ServerResponse saveFundsLever(FundsLever fundsLever) {
        int ret = 0;
        if(fundsLever!=null && fundsLever.getId()>0){
            ret = fundsLeverMapper.update(fundsLever);
        } else{
            ret = fundsLeverMapper.insert(fundsLever);
        }
        if(ret>0){
            return ServerResponse.createBySuccessMsg("操作成功");
        }
        return ServerResponse.createByErrorMsg("操作失败");
    }

    /*查询配资类型杠杆*/
    @Override
    public ServerResponse getFundsTypeList(Integer cycleType){
        List<FundsLever> fundsLevers = this.fundsLeverMapper.getFundsTypeList(cycleType);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(fundsLevers);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /*配资杠杆-查询杠杆费率*/
    @Override
    public ServerResponse getLeverRateInfo(Integer cycleType, Integer lever){
        FundsLever fundsLevers = this.fundsLeverMapper.getLeverRateInfo(cycleType, lever);
        return ServerResponse.createBySuccess(fundsLevers);
    }



}
