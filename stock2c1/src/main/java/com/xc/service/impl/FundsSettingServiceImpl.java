package com.xc.service.impl;

import com.xc.common.ServerResponse;
import com.xc.dao.FundsSettingMapper;
import com.xc.pojo.FundsSetting;
import com.xc.pojo.SiteSetting;
import com.xc.service.IFundsSettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * funds_setting
 * @author lr
 * @date 2020/07/23
 */
@Service("IFundsSettingService")
public class FundsSettingServiceImpl implements IFundsSettingService {

    @Resource
    private FundsSettingMapper fundsSettingMapper;


    @Override
    public int insert(FundsSetting fundsSetting) {
        int ret = 0;
        // valid
        if (fundsSetting == null) {
            return 0;
        }

        ret = fundsSettingMapper.insert(fundsSetting);
        return ret;
    }

    @Override
    public int update(FundsSetting fundsSetting) {
        int ret = fundsSettingMapper.update(fundsSetting);
        return ret>0 ? ret: 0;
    }


    @Override
    public FundsSetting load(int id) {
        return fundsSettingMapper.load(id);
    }

    @Override
    public ServerResponse save(FundsSetting fundsSetting, HttpServletRequest paramHttpServletRequest){
        int ret = 0;
        if(fundsSetting == null || fundsSetting.getId() == null|| fundsSetting.getId() == 0){
            ret = insert(fundsSetting);
        } else {
            ret = update(fundsSetting);
        }
        if (ret > 0) {
            return ServerResponse.createBySuccessMsg("操作成功");
        }
        return ServerResponse.createByErrorMsg("操作失败");
    }

    /*查询第一条数据*/
    @Override
    public FundsSetting getFundsSetting() {
        FundsSetting fundsSetting = null;
        List list = this.fundsSettingMapper.findAllFundsSetting();
        if (list.size() > 0) {
            fundsSetting = (FundsSetting) list.get(0);
        }
        return fundsSetting;
    }


}
