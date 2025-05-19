package com.xc.controller.agent;


import com.github.pagehelper.StringUtil;
import com.xc.common.ServerResponse;
import com.xc.dao.UserMapper;
import com.xc.pojo.Lists;
import com.xc.pojo.NewList;
import com.xc.pojo.User;
import com.xc.service.IListsService;
import com.xc.service.INewListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

/**
 * 新股管理接口
 */
@Controller
@RequestMapping({"/agent/newstock/"})
public class AgentNewStockController {


    @Autowired
    IListsService iListsService;

    @Autowired
    INewListService iNewListService;

    @Autowired
    UserMapper userMapper;





    //申购列表查询
    @RequestMapping({"applyList.do"})
    @ResponseBody
    public ServerResponse applyList(String agent, String zname, String phone, String xgname, String codes, String bzj,  Short zts, String mrsj_start, String mrsj_end, String nums, @RequestParam(name = "pageNo",defaultValue = "1") Integer pageNo,@RequestParam(name = "pageSize",defaultValue = "10")  Integer pageSize, HttpServletRequest request) {
        return ServerResponse.createBySuccess(iListsService.listAgentByQueryWordsByPage(agent, zname,phone,  xgname, codes,  bzj,  zts,  mrsj_start, mrsj_end, nums,pageNo,pageSize,request)) ;
    }

    //申购列表添加
    @RequestMapping({"applyAdd.do"})
    @ResponseBody
    public ServerResponse applyAdd(Lists lists) {
        iListsService.save(lists);
        return ServerResponse.createBySuccess();
    }

    //申购列表修改
    @RequestMapping({"applyUpdate.do"})
    @ResponseBody
    public ServerResponse applyUpdate(Lists lists) {
        iListsService.update(lists);
        return ServerResponse.createBySuccess();
    }

    //申购列表删除
    @RequestMapping({"applyDelete.do"})
    @ResponseBody
    public ServerResponse applyDelete(Lists lists) {
        iListsService.deleteById(lists);
        return ServerResponse.createBySuccess();
    }

    //申购列表详情查看
    @RequestMapping({"applyDetail.do"})
    @ResponseBody
    public ServerResponse applyDetail(Integer id) {
        Lists lists = new Lists();
        lists.setListsId(id);
        return ServerResponse.createBySuccess(iListsService.getById(lists)) ;
    }


    /**
     * 申请列表审核
     * @param lists
     * @return
     */
    @RequestMapping({"applyVerify.do"})
    @ResponseBody
    public ServerResponse applyVerify(Lists lists) {
        Lists l = iListsService.getById(lists);
        l.setZts(lists.getZts());
        if(!StringUtil.isEmpty(lists.getNums())){
            l.setWinums(lists.getNums());
        }
        if(l.getZts().equals(Short.valueOf("1"))){
            NewList newList = iNewListService.getByCode(l.getCodes());
            if(!ObjectUtils.isEmpty(newList)){
                String price = newList.getPrice();
                BigDecimal bPrice = new BigDecimal(price);
                l.setBzj(bPrice.multiply(new BigDecimal(l.getWinums())).setScale(2, RoundingMode.DOWN).toPlainString());
                l.setMrsj(String.valueOf(System.currentTimeMillis()/1000));
            }else{
                return ServerResponse.createByErrorMsg("新股不存在") ;
            }
        }
        iListsService.applyVerify(l);
        return ServerResponse.createBySuccess() ;
    }


    /**
     * 获取申请列表信息
     * @return
     */
    @RequestMapping({"getApplyInfo.do"})
    @ResponseBody
    public ServerResponse getApplyInfo(String phone,String listsId) {
        if(StringUtil.isEmpty(phone)&&StringUtil.isEmpty(listsId)){
            return ServerResponse.createByErrorMsg("参数不能为空");
        }
        Lists lists = new Lists();
        lists.setListsId(Integer.valueOf(listsId));
        Lists l = iListsService.getById(lists);
        User user = userMapper.findByPhone(phone);
        HashMap<String, Object> map = new HashMap<>();
        map.put("user",user);
        map.put("applyInfo",l);
        return ServerResponse.createBySuccess(map) ;
    }


    /**
     * 用户余额查询
     * @param phone
     * @return
     */
    @RequestMapping({"userBalanceByPhone.do"})
    @ResponseBody
    public ServerResponse userBalanceByPhone(String phone) {
        return ServerResponse.createBySuccess(userMapper.findByPhone(phone)) ;
    }
}
