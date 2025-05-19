package com.xc.service;

import com.xc.common.ServerResponse;
import com.xc.pojo.FundsSetting;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * funds_setting
 * @author lr
 * @date 2020/07/23
 */
public interface IFundsSettingService {

    /**
     * 新增
     */
    int insert(FundsSetting fundsSetting);

    /**
     * 更新
     */
    int update(FundsSetting fundsSetting);

    /**
     * 根据主键 id 查询
     */
    FundsSetting load(int id);

    /**
     * 保存设置
     */
    ServerResponse save(FundsSetting fundsSetting, HttpServletRequest paramHttpServletRequest);

    /*查询第一条数据*/
    FundsSetting getFundsSetting();

}
