package com.xc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.xc.dao.NewListMapper;
import com.xc.pojo.NewList;
import com.xc.pojo.NewListExample;
import com.xc.service.INewListService;
import com.xc.utils.DateTimeUtil;
import com.xc.utils.HttpRequest;
import com.xc.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

@Service("INewListService")
public class INewListServiceImpl implements INewListService {


    private static final Logger log = LoggerFactory.getLogger(INewListServiceImpl.class);

    @Autowired
    private NewListMapper newListMapper;

    @Override
    public NewList save(NewList newList) {
//        NewListExample newListExample = new NewListExample();
//        NewListExample.Criteria criteria = newListExample.createCriteria();
//        criteria.andCodeEqualTo(newList.getCode());
//        List<NewList> newLists = newListMapper.selectByExample(newListExample);
//        if(!CollectionUtils.isEmpty(newLists)){
//            
//        }
        newListMapper.insertSelective(newList);
        return newList;
    }

    @Override
    public void update(NewList newList) {
        newListMapper.updateByPrimaryKeySelective(newList);
    }

    @Override
    public NewList getByCode(String code) {
        NewListExample newListExample = new NewListExample();
        NewListExample.Criteria criteria = newListExample.createCriteria();
        criteria.andCodeEqualTo(code);
        List<NewList> newLists = newListMapper.selectByExample(newListExample);
        if(CollectionUtils.isEmpty(newLists)){
            return null;
        }
        return  newLists.get(0);
    }

    @Override
    public List<NewList> getUpcomingSubscription() {
        String dateToStr = DateTimeUtil.dateToStr(new Date(), DateTimeUtil.YMD_FORMAT);
        Date nowDate = DateTimeUtil.strToDateyyyyMMdd(dateToStr);
        NewListExample newListExample = new NewListExample();
        NewListExample.Criteria criteria = newListExample.createCriteria();
        criteria.andIssueDateGreaterThan(nowDate);
        criteria.andZtEqualTo(Byte.valueOf("1"));
        return newListMapper.selectByExample(newListExample);
    }

    @Override
    public List<NewList> getCurrentDayStockList() {
        String dateToStr = DateTimeUtil.dateToStr(new Date(), DateTimeUtil.YMD_FORMAT);
        Date nowDate = DateTimeUtil.strToDateyyyyMMdd(dateToStr);
        NewListExample newListExample = new NewListExample();
        NewListExample.Criteria criteria = newListExample.createCriteria();
        criteria.andIssueDateEqualTo(nowDate);
        criteria.andZtEqualTo(Byte.valueOf("1"));
        return newListMapper.selectByExample(newListExample);
    }

    @Override
    public List<NewList> getNewShowStockList() {
        String dateToStr = DateTimeUtil.dateToStr(new Date(), DateTimeUtil.YMD_FORMAT);
        Date nowDate = DateTimeUtil.strToDateyyyyMMdd(dateToStr);
        NewListExample newListExample = new NewListExample();
        NewListExample.Criteria criteria = newListExample.createCriteria();
        criteria.andListDateEqualTo(nowDate);
        criteria.andZtEqualTo(Byte.valueOf("1"));
        return newListMapper.selectByExample(newListExample);
    }

    @Override
    public List<NewList> getWinStockList() {
        String dateToStr = DateTimeUtil.dateToStr(new Date(), DateTimeUtil.YMD_FORMAT);
        Date nowDate = DateTimeUtil.strToDateyyyyMMdd(dateToStr);
        NewListExample newListExample = new NewListExample();
        NewListExample.Criteria criteria = newListExample.createCriteria();
        criteria.andWinDateEqualTo(nowDate);
        criteria.andZtEqualTo(Byte.valueOf("1"));
        return newListMapper.selectByExample(newListExample);
    }

    @Override
    public void getNewStockListTask() throws Exception {
            String url = PropertiesUtil.getProperty("dfcf.new.stock.url");
            String s = HttpRequest.doGet(url, null);
            JSONObject jsonObject = JSON.parseObject(s);
            JSONObject result = jsonObject.getJSONObject("result");
            JSONArray data = result.getJSONArray("data");
            for(int i=0;i<data.size();i++){
                JSONObject o = data.getJSONObject(i);
                //先判断当前新股是否插入
                NewList newStock = this.getByCode(o.getString("SECURITY_CODE"));
                if(null==newStock){
                    NewList newList = new NewList();
                    newList.setCode(o.getString("SECURITY_CODE"));
                    newList.setIssueDate(o.getDate("APPLY_DATE"));
                    newList.setListDate(o.getDate("LISTING_DATE")==null?null:o.getDate("LISTING_DATE"));
                    newList.setWinDate(o.getDate("BALLOT_NUM_DATE")==null?null:o.getDate("BALLOT_NUM_DATE"));
                    newList.setNames(o.getString("SECURITY_NAME"));
                    newList.setPe(o.getString("AFTER_ISSUE_PE")==null?null:o.getString("AFTER_ISSUE_PE"));
                    newList.setPrice(o.getString("ISSUE_PRICE"));
                    newList.setZt(Byte.valueOf("1"));
                    this.save(newList);
                }
            }
    }

    @Override
    public PageInfo listByNewList(String names, String code, String price, Byte zt, Integer pageNo, Integer pageSize) {
        NewListExample newListExample = new NewListExample();
        NewListExample.Criteria criteria = newListExample.createCriteria();
        if(StringUtil.isNotEmpty(names)){
            criteria.andNamesEqualTo(names);
        }
        if(StringUtil.isNotEmpty(code)){
            criteria.andCodeEqualTo(code);
        }
        if(StringUtil.isNotEmpty(price)){
            criteria.andPriceEqualTo(price);
        }
        if(!ObjectUtils.isEmpty(zt)){
            criteria.andZtEqualTo(zt);
        }
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo( newListMapper.selectByExample(newListExample));
    }

    @Override
    public void deleteById(Integer id) {
        newListMapper.deleteByPrimaryKey(id);
    }

}
