package com.xc.service.impl;


import com.xc.common.ServerResponse;

import com.xc.dao.SiteFuturesSettingMapper;

import com.xc.pojo.SiteFuturesSetting;

import com.xc.service.ISiteFuturesSettingService;

import java.util.List;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service("iSiteFuturesSettingService")

public class SiteFuturesSettingServiceImpl

        implements ISiteFuturesSettingService {

    private static final Logger log = LoggerFactory.getLogger(SiteFuturesSettingServiceImpl.class);


    @Autowired

    SiteFuturesSettingMapper siteFuturesSettingMapper;


    public SiteFuturesSetting getSetting() {

        SiteFuturesSetting siteIndexSetting = null;

        List list = this.siteFuturesSettingMapper.selectAllSiteSetting();

        if (list.size() > 0) {

            siteIndexSetting = (SiteFuturesSetting) list.get(0);

        }
        return siteIndexSetting;
    }

    public ServerResponse update(SiteFuturesSetting siteFuturesSetting) {
        if (siteFuturesSetting.getId() == null) {
            return ServerResponse.createByErrorMsg("修改 id 不能为空");
        }

        SiteFuturesSetting dbsetting = this.siteFuturesSettingMapper.selectByPrimaryKey(siteFuturesSetting.getId());

        if (dbsetting == null) {
            return ServerResponse.createByErrorMsg("不存在该设置");
        }
        dbsetting.setBuyMaxPercent(siteFuturesSetting.getBuyMaxPercent());
        dbsetting.setForceSellPercent(siteFuturesSetting.getForceSellPercent());
        dbsetting.setBuySameTimes(siteFuturesSetting.getBuySameTimes());
        dbsetting.setBuySameNums(siteFuturesSetting.getBuySameNums());
        dbsetting.setBuyNumTimes(siteFuturesSetting.getBuyNumTimes());
        dbsetting.setBuyNumLots(siteFuturesSetting.getBuyNumLots());
        dbsetting.setForceStopRemindRatio(siteFuturesSetting.getForceStopRemindRatio());

        int updateCount = this.siteFuturesSettingMapper.updateByPrimaryKey(dbsetting);
        if (updateCount > 0) {
            return ServerResponse.createBySuccessMsg("修改成功");
        }
        return ServerResponse.createByErrorMsg("修改失败");
    }

}
