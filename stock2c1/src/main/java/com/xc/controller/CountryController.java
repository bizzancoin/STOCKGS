package com.xc.controller;

import com.xc.common.ServerResponse;
import com.xc.dao.CountryMapper;
import com.xc.pojo.Country;
import com.xc.pojo.CountryExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping({"/api/country/"})
public class CountryController {

    private static final Logger log = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryMapper countryMapper;

    /**
     * 获取支持的注册国家区号
     * @return
     */
    @RequestMapping(value = "/support.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse allCountry() {
        ServerResponse result = null;
        CountryExample countryExample = new CountryExample();
        List<Country> list = countryMapper.selectByExample(countryExample);
        Iterator<Country> iterator = list.iterator();
        while (iterator.hasNext()){
            Country country = iterator.next();
            if(country.getEnName().equals("China")){
                iterator.remove();
            }
        }
        return ServerResponse.createBySuccess(list);
    }
}
