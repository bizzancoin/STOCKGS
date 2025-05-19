package com.xc.service.impl;


import com.github.pagehelper.PageHelper;

import com.github.pagehelper.PageInfo;

import com.google.common.collect.Lists;

import com.xc.common.ServerResponse;

import com.xc.dao.StockCoinMapper;

import com.xc.pojo.StockCoin;

import com.xc.service.IStockCoinService;

import com.xc.service.IStockFuturesService;

import com.xc.vo.foreigncurrency.ExchangeVO;

import com.xc.vo.stockfutures.CoinAdminListVO;

import java.math.BigDecimal;
import java.util.Date;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service("iStockCoinService")

public class StockCoinServiceImpl implements IStockCoinService {

    @Autowired
    StockCoinMapper stockCoinMapper;

    @Autowired
    IStockFuturesService iStockFuturesService;


    public ServerResponse<PageInfo> listByAdmin(String coinName, String coinCode, int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);

        List<StockCoin> stockCoins = this.stockCoinMapper.listByAdmin(coinName, coinCode);


        List<CoinAdminListVO> coinAdminListVOS = Lists.newArrayList();

        for (StockCoin stockCoin : stockCoins) {

            CoinAdminListVO coinAdminListVO = assembleCoinAdminListVO(stockCoin);

            coinAdminListVOS.add(coinAdminListVO);

        }


        PageInfo pageInfo = new PageInfo(stockCoins);

        pageInfo.setList(coinAdminListVOS);


        return ServerResponse.createBySuccess(pageInfo);

    }

    private CoinAdminListVO assembleCoinAdminListVO(StockCoin stockCoin) {

        CoinAdminListVO coinAdminListVO = new CoinAdminListVO();


        coinAdminListVO.setId(stockCoin.getId());

        coinAdminListVO.setCoinName(stockCoin.getCoinName());

        coinAdminListVO.setCoinCode(stockCoin.getCoinCode());

        coinAdminListVO.setCoinGid(stockCoin.getCoinGid());

        coinAdminListVO.setDynamicRate(stockCoin.getDynamicRate());

        coinAdminListVO.setDefaultRate(stockCoin.getDefaultRate());

        coinAdminListVO.setIsUse(stockCoin.getIsUse());

        coinAdminListVO.setAddTime(stockCoin.getAddTime());

        coinAdminListVO.setTDesc(stockCoin.gettDesc());


        String nowPrice = "";

        ExchangeVO exchangeVO = null;


        ServerResponse serverResponse = this.iStockFuturesService.queryExchangeVO(stockCoin.getCoinCode());

        if (serverResponse.isSuccess()) {

            exchangeVO = (ExchangeVO) serverResponse.getData();

            if (exchangeVO != null) {

                nowPrice = exchangeVO.getNowPrice();

            }

        }

        coinAdminListVO.setNowPrice(nowPrice);

        /*查询新浪实时汇率*/
        ServerResponse serverResponse1 = iStockFuturesService.getExchangeRate(coinAdminListVO.getCoinCode());
        if (serverResponse1.isSuccess()) {
            coinAdminListVO.setDefaultRate((BigDecimal) serverResponse1.getData());
        }


        return coinAdminListVO;

    }


    public ServerResponse add(StockCoin stockCoin) {

        if (StringUtils.isBlank(stockCoin.getCoinName()) ||

                StringUtils.isBlank(stockCoin.getCoinCode()) ||

                StringUtils.isBlank(stockCoin.getCoinGid())) {

            return ServerResponse.createByErrorMsg("参数不能为空");

        }


        StockCoin coinName = this.stockCoinMapper.selectCoinByName(stockCoin.getCoinName());

        if (coinName != null) {

            return ServerResponse.createByErrorMsg("名称不能重复");

        }

        StockCoin coinCode = this.stockCoinMapper.selectCoinByCode(stockCoin.getCoinCode());

        if (coinCode != null) {

            return ServerResponse.createByErrorMsg("代码不能重复");

        }

        StockCoin coinGid = this.stockCoinMapper.selectCoinByCode(stockCoin.getCoinGid());

        if (coinGid != null) {

            return ServerResponse.createByErrorMsg("gid不能重复");

        }


        stockCoin.setAddTime(new Date());


        int insertCount = this.stockCoinMapper.insert(stockCoin);

        if (insertCount > 0) {

            return ServerResponse.createBySuccessMsg("添加成功");

        }

        return ServerResponse.createByErrorMsg("添加失败");

    }


    public ServerResponse update(StockCoin stockCoin) {

        if (stockCoin.getId() == null) {

            return ServerResponse.createByErrorMsg("修改id不能为空");

        }


        StockCoin dbCoin = this.stockCoinMapper.selectByPrimaryKey(stockCoin.getId());

        if (dbCoin == null) {

            return ServerResponse.createByErrorMsg("基币不存在");

        }


        if (stockCoin.getCoinName() != null) {

            StockCoin coinName = this.stockCoinMapper.selectCoinByName(stockCoin.getCoinName());

            if (coinName != null && coinName.getId() != dbCoin.getId()) {

                return ServerResponse.createByErrorMsg("名称已存在");

            }

        }

        if (stockCoin.getCoinCode() != null) {

            StockCoin coinCode = this.stockCoinMapper.selectCoinByCode(stockCoin.getCoinCode());

            if (coinCode != null && coinCode.getId() != dbCoin.getId()) {

                return ServerResponse.createByErrorMsg("代码已存在");

            }

        }

        if (stockCoin.getCoinGid() != null) {

            StockCoin coinGid = this.stockCoinMapper.selectCoinByCode(stockCoin.getCoinGid());

            if (coinGid != null && coinGid.getId() != dbCoin.getId()) {

                return ServerResponse.createByErrorMsg("gid已存在");

            }

        }


        int updateCount = this.stockCoinMapper.updateByPrimaryKeySelective(stockCoin);

        if (updateCount > 0) {

            return ServerResponse.createBySuccessMsg("修改成功");

        }

        return ServerResponse.createByErrorMsg("修改失败");

    }


    public StockCoin selectCoinByCode(String coinCode) {
        return this.stockCoinMapper.selectCoinByCode(coinCode);
    }


    public List getSelectCoin() {
        return this.stockCoinMapper.getSelectCoin();
    }

}
