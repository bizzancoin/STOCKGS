package com.xc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;
import com.xc.dao.ConvertBondMapper;
import com.xc.pojo.ConvertBond;
import com.xc.pojo.ConvertBondExample;
import com.xc.service.IConverBondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("IConverBondService")
public class IConverBondServiceImpl  implements IConverBondService {


    @Autowired
    private ConvertBondMapper convertBondMapper;


    public ServerResponse listByPage(Integer page,Integer size){
        PageHelper.startPage(page,size);
        ConvertBondExample convertBondExample = new ConvertBondExample();
        List<ConvertBond> convertBonds = convertBondMapper.selectByExample(convertBondExample);
        PageInfo pageInfo = new PageInfo(convertBonds);
        return ServerResponse.createBySuccess(pageInfo);
    }


    public ConvertBond getById(Integer id){

        return  convertBondMapper.selectByPrimaryKey(id);
    }

    public ServerResponse save(ConvertBond convertBond){
        ConvertBondExample convertBondExample = new ConvertBondExample();
        ConvertBondExample.Criteria criteria = convertBondExample.createCriteria();
        criteria.andBondBuyCodeEqualTo(convertBond.getBondBuyCode());
        criteria.andBondCodeEqualTo(convertBond.getBondCode());

        List<ConvertBond> convertBonds = convertBondMapper.selectByExample(convertBondExample);
        if(convertBonds!=null){
            return ServerResponse.createByErrorMsg("可转债已存在");
        }
        convertBondMapper.insert(convertBond);
        return ServerResponse.createBySuccess();
    }



}
