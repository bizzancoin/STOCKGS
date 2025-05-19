package com.xc.controller.backend;


import com.xc.common.ServerResponse;
import com.xc.pojo.ConvertBond;
import com.xc.pojo.ConvertBondApply;
import com.xc.pojo.User;
import com.xc.service.IConverBondApplyService;
import com.xc.service.IConverBondService;
import com.xc.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 可转债接口
 */
@Controller
@RequestMapping({"/admin/bond/"})
public class AdminBondController {


    private static final Logger log = LoggerFactory.getLogger(AdminBondController.class);

    @Autowired
    private IConverBondService converBondService;

    @Autowired
    private IConverBondApplyService converBondApplyService;

    @Autowired
    private IUserService userService;





    /*查询可转债列表-*/
    @RequestMapping({"bondList.do"})
    @ResponseBody
    public ServerResponse bondList(@RequestParam(value = "page",defaultValue = "1",required = false) Integer page, @RequestParam(value = "size",defaultValue = "20",required = false) Integer size) {
        return converBondService.listByPage(page,size);
    }


    /*可转债添加-*/
    @RequestMapping({"addBond.do"})
    @ResponseBody
    public ServerResponse addBond(ConvertBond convertBond) {
        return converBondService.save(convertBond);
    }


    /*设置中签数量*/
    @RequestMapping({"setBondSucNum.do"})
    @ResponseBody
    public ServerResponse setBondSucNum(Integer bondId,Integer userId,Integer count) {
        ConvertBond bond = converBondService.getById(bondId);

        if(bond==null){
            return ServerResponse.createByErrorMsg("可转债不存在");
        }

        ServerResponse myConvertBond = converBondApplyService.getMyConvertBond(bondId, userId);
        List<ConvertBondApply> convertBondApplyList= (List<ConvertBondApply>)myConvertBond.getData();
        if(convertBondApplyList==null||convertBondApplyList.size()==0){
            return ServerResponse.createByErrorMsg("未找到申请的可转债");
        }
        ConvertBondApply convertBondApply = convertBondApplyList.get(0);
        convertBondApply.setSucNum(count);
        convertBondApply.setRefundMony(new BigDecimal(String.valueOf(bond.getPrice().multiply(new BigDecimal(convertBondApply.getApplyNum()-count+"")).setScale(2))));
        convertBondApply.setSucDate(new Date());
        convertBondApply.setSucMony(bond.getPrice().multiply(new BigDecimal(count)));
        convertBondApply.setStatus(1);
        ServerResponse byUserId = userService.findByUserId(userId);
        User user= (User)byUserId.getData();
        user.setEnableAmt(user.getEnableAmt().add(convertBondApply.getRefundMony()));
        userService.update(user);
        converBondApplyService.update(convertBondApply);

        return ServerResponse.createBySuccess();
    }
}
