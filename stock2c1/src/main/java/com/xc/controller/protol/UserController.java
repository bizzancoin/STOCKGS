package com.xc.controller.protol;


import com.google.common.collect.Maps;
import com.wordnik.swagger.annotations.*;
import com.xc.common.ServerResponse;
import com.xc.dao.UserPositionMapper;
import com.xc.pojo.*;
import com.xc.service.*;
import com.xc.service.impl.LocaleMessageSourceService;
import com.xc.utils.PropertiesUtil;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.xc.utils.stock.BuyAndSellUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"/user/"})
@Api("用户中心接口")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    IUserService iUserService;

    @Autowired
    IUserPositionService iUserPositionService;

    @Autowired
    IFileUploadService iFileUploadService;

    @Autowired
    IUserIndexPositionService iUserIndexPositionService;

    @Autowired
    IUserFuturesPositionService iUserFuturesPositionService;

    @Autowired
    IUserStockSubscribeService iUserStockSubscribeService;

    @Autowired
    private IConverBondService converBondService;

    @Autowired
    private IConverBondApplyService converBondApplyService;

    @Autowired
    private INewListService iNewListService;

    @Autowired
    private IListsService listsService;

    @Autowired
    private IStockService iStockService;

    @Autowired
    private UserPositionMapper userPositionMapper;

    @Autowired
    LocaleMessageSourceService messageSourceService;

    @Autowired
    ISiteProductService iSiteProductService;

    @Autowired
    ISiteSettingService iSiteSettingService;

    //添加到自选股
    @ApiOperation("添加到自选股")
    @PostMapping({"addOption.do"})
    @ResponseBody
    public ServerResponse addOption(@RequestParam("code") String code, HttpServletRequest request) {
        return this.iUserService.addOption(code, request);
    }

    //删除自选股
    @ApiOperation("删除自选股")
    @PostMapping({"delOption.do"})
    @ResponseBody
    public ServerResponse delOption(@RequestParam("code") String code, HttpServletRequest request) {
        return this.iUserService.delOption(code, request);
    }

    //查询是否是自选股
    @ApiOperation("查询是否是自选股")
    @PostMapping({"isOption.do"})
    @ResponseBody
    public ServerResponse isOption(@RequestParam("code") String code, HttpServletRequest request) {
        return this.iUserService.isOption(code, request);
    }

    //用户下单买入股票
    @ApiOperation("用户下单买入股票")
    @PostMapping({"buy.do"})
    @ResponseBody
    public ServerResponse buy(@RequestParam("stockId") Integer stockId, @RequestParam("buyNum") Integer buyNum, @RequestParam("buyType") Integer buyType, @RequestParam("lever") Integer lever, HttpServletRequest request) {
        ServerResponse serverResponse = null;
        try {
            serverResponse = this.iUserPositionService.buy(stockId, buyNum, buyType, lever, request);
        } catch (Exception e) {
            log.error("用户下单操作 = {}", e);
        }
        return serverResponse;
    }

    //用户平仓操作
    @ApiOperation("用户下单卖出股票")
    @PostMapping({"sell.do"})
    @ResponseBody
    public ServerResponse sell(HttpServletRequest request, @RequestParam("positionSn") String positionSn) {
        ServerResponse serverResponse = null;
        try {
            serverResponse = this.iUserPositionService.sell(positionSn, 1);
        } catch (Exception e) {
            log.error("用户平仓操作 = {}", e);
        }
        return serverResponse;
    }

    //用户追加保证金操作
    @PostMapping({"addmargin.do"})
    @ResponseBody
    public ServerResponse addmargin(HttpServletRequest request, @RequestParam("positionSn") String positionSn, @RequestParam("marginAdd") BigDecimal marginAdd) {
        ServerResponse serverResponse = null;
        try {
            serverResponse = this.iUserPositionService.addmargin(positionSn, 1, marginAdd);
        } catch (Exception e) {
            log.error("用户平仓操作 = {}", e);
        }
        return serverResponse;
    }

    @PostMapping({"buyIndex.do"})
    @ResponseBody
    public ServerResponse buyIndex(@RequestParam("indexId") Integer indexId, @RequestParam("buyNum") Integer buyNum, @RequestParam("buyType") Integer buyType, @RequestParam("lever") Integer lever, HttpServletRequest request) {
        ServerResponse serverResponse = null;
        try {
            serverResponse = this.iUserIndexPositionService.buyIndex(indexId, buyNum, buyType, lever, request);
        } catch (Exception e) {
            log.error("用户下单指数操作 = {}", e);
        }
        return serverResponse;
    }

    @PostMapping({"sellIndex.do"})
    @ResponseBody
    public ServerResponse sellIndex(HttpServletRequest request, @RequestParam("positionSn") String positionSn) {
        ServerResponse serverResponse = null;
        try {
            serverResponse = this.iUserIndexPositionService.sellIndex(positionSn, 1);
        } catch (Exception e) {
            log.error("用户平仓指数操作 = {}", e);
        }
        return serverResponse;
    }

    //期货交易 用户下单
    @PostMapping({"buyFutures.do"})
    @ResponseBody
    public ServerResponse buyFutures(@RequestParam("FuturesId") Integer FuturesId, @RequestParam("buyNum") Integer buyNum, @RequestParam("buyType") Integer buyType, @RequestParam("lever") Integer lever, HttpServletRequest request) {
        ServerResponse serverResponse = null;
        try {
            serverResponse = this.iUserFuturesPositionService.buyFutures(FuturesId, buyNum, buyType, lever, request);
        } catch (Exception e) {
            log.error("用户下单 期货 操作 = {}", e);
        }
        return serverResponse;
    }

    @PostMapping({"sellFutures.do"})
    @ResponseBody
    public ServerResponse sellFutures(HttpServletRequest request, @RequestParam("positionSn") String positionSn) {
        ServerResponse serverResponse = null;
        try {
            serverResponse = this.iUserFuturesPositionService.sellFutures(positionSn, 1);
        } catch (Exception e) {
            log.error("用户平仓 期货 操作 = {}", e);
        }
        return serverResponse;
    }

    @Autowired
    IUserRechargeService iUserRechargeService;

    //查询 用户信息
    @ApiOperation("获取个人用户信息包括资产信息")
    @PostMapping({"getUserInfo.do"})
    @ResponseBody
    public ServerResponse getUserInfo(HttpServletRequest request) {
        return this.iUserService.getUserInfo(request);
    }

    //修改用户密码
    @ApiOperation("修改用户密码")
    @PostMapping({"updatePwd.do"})
    @ResponseBody
    public ServerResponse updatePwd(String oldPwd, String newPwd, HttpServletRequest request) {
        return this.iUserService.updatePwd(oldPwd, newPwd, request);
    }

    @PostMapping({"findIdWithPwd.do"})
    @ResponseBody
    public ServerResponse findIdWithPwd(@RequestParam("phone") String phone){
        return this.iUserService.findIdWithPwd(phone);
    }

    /**
     * 新增资金密码
     * @param with_pwd  资金密码
     * @param pwd  密码
     * @return
     */
    @ApiOperation("新增资金密码")
    @PostMapping({"insertWithPwd.do"})
    @ResponseBody
    public ServerResponse insertWithPwd(HttpServletRequest httpServletRequest,@RequestParam("with_pwd") String with_pwd,@RequestParam("pwd") String pwd){
        User currentUser = this.iUserService.getCurrentUser(httpServletRequest);
        if(null!=currentUser){
            if(currentUser.getUserPwd().equals(pwd)){
                return this.iUserService.updateWithPwd(with_pwd,currentUser.getPhone());
            }else{
                return ServerResponse.createByErrorMsg(messageSourceService.getMessage("ZJMMCW"));
            }
        }
           return ServerResponse.createByErrorMsg(messageSourceService.getMessage("YHBCZ"));
    }

    /**
     * 实名认证
     * @param realName 实名信息
     * @param idCard  身份证号
     * @param img1key  身份证正面
     * @param img2key 身份证反面
     * @param request
     * @return
     */
    @ApiOperation("实名认证")
    @PostMapping(value = {"auth.do"})
    @ResponseBody
    public ServerResponse auth(@ApiParam("实名信息") String realName,@ApiParam("身份证号") String idCard,@ApiParam("身份证正面") String img1key,@ApiParam("身份证反面") String img2key, HttpServletRequest request) {
        return this.iUserService.auth(realName, idCard, img1key, img2key, "", request);
    }

    //图片上传
    @ApiOperation("图片上传")
    @PostMapping({"upload.do"})
    @ResponseBody
    public ServerResponse upload(HttpSession session, @RequestParam(value = "upload_file", required = false) MultipartFile file, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");

        ServerResponse serverResponse = this.iFileUploadService.upload(file, path);
        if (serverResponse.isSuccess()) {
            String targetFileName = serverResponse.getData().toString();
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix") + targetFileName;


            Map fileMap = Maps.newHashMap();
            fileMap.put("uri", targetFileName);
            fileMap.put("url", url);

            return ServerResponse.createBySuccess(fileMap);
        }
        return serverResponse;
    }

    //资产互转
    @PostMapping({"transAmt.do"})
    @ResponseBody
    public ServerResponse transAmt(@RequestParam("amt") Integer amt, @RequestParam("type") Integer type, HttpServletRequest request) {
        return this.iUserService.transAmt(amt, type, request);
    }

    /*新股申购-查询用户最新新股申购数据*/
    @ApiOperation("我的新股申购列表")
    @PostMapping({"getOneSubscribeByUserId.do"})
    @ResponseBody
    public ServerResponse getOneSubscribeByUserId(HttpServletRequest request) {
        User currentUser = iUserService.getCurrentUser(request);
        if(ObjectUtils.isEmpty(currentUser)){
            return ServerResponse.createByError();
        }
        String phone = currentUser.getPhone();
        List<Lists> myApplyList = listsService.getMyApplyList(phone);
        return ServerResponse.createBySuccess(myApplyList);
        //return this.iUserStockSubscribeService.getOneSubscribeByUserId(userId, request);
    }

    /*新股申购-用户提交金额*/
    @PostMapping({"submitSubscribe.do"})
    @ResponseBody
    public ServerResponse userSubmit(UserStockSubscribe model, HttpServletRequest request) {
        return this.iUserStockSubscribeService.userSubmit(model, request);
    }

    /*可转债申购-*/
    @PostMapping({"userBondSubmit.do"})
    @ResponseBody
    public ServerResponse userBondSubmit(@RequestParam("userId") Integer userId,Integer count,Integer bondId,HttpServletRequest request) {
        try {
            return converBondApplyService.applyConvertBond(userId,count,bondId,request);
        }catch (Exception e){
            e.printStackTrace();
        }
         return ServerResponse.createByError();
    }

    /*查询当前可转债申购列表-*/
    @PostMapping({"userBondList.do"})
    @ResponseBody
    public ServerResponse userBondList(@RequestParam(value = "page",defaultValue = "1",required = false) Integer page,@RequestParam(value = "size",defaultValue = "20",required = false) Integer size,HttpServletRequest request) {
        return converBondApplyService.getMyConvertBondList(request,page,size);
    }


    /*查询我的新股中签数据-*/
    @ApiOperation("新股申购申请")
    @PostMapping({"getMyWinNewStock.do"})
    @ResponseBody
    public ServerResponse getMyWinNewStock(HttpServletRequest request) {
        User currentUser = iUserService.getCurrentUser(request);
        if(ObjectUtils.isEmpty(currentUser)){
            return ServerResponse.createByError();
        }
        return  ServerResponse.createBySuccess(listsService.getMyWinNewStockByPhone(currentUser.getPhone()));
    }



    /*新股申购-*/
    @ApiOperation("新股申购申请")
    @PostMapping({"newStockApply.do"})
    @ResponseBody
    public ServerResponse newStockApply(@ApiParam("新股代码") String code,@ApiParam("数量") Integer number,HttpServletRequest request) throws Exception {


        // 判断周末不能买
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        if (weekday == 1) {
            return ServerResponse.createByErrorMsg(messageSourceService.getMessage("ERR51"));
        }
        if (weekday == 7) {
            return ServerResponse.createByErrorMsg(messageSourceService.getMessage("ERR51"));
        }

        /*实名认证开关开启*/
        SiteProduct siteProduct = iSiteProductService.getProductSetting();
        User user = this.iUserService.getCurrentRefreshUser(request);
        if (siteProduct.getRealNameDisplay() && (StringUtils.isBlank(user.getRealName()) || StringUtils.isBlank(user.getIdCard()))) {
            return ServerResponse.createByErrorMsg(messageSourceService.getMessage("ERR52"));
        }
        if (siteProduct.getRealNameDisplay() && user.getIsLock().intValue() == 1) {
            return ServerResponse.createByErrorMsg(messageSourceService.getMessage("ERR53"));
        }
        if(siteProduct.getHolidayDisplay()){
            return ServerResponse.createByErrorMsg(messageSourceService.getMessage("ERR54"));
        }

        SiteSetting siteSetting = this.iSiteSettingService.getSiteSetting();
        if (siteSetting == null) {
            log.error("下单出错，网站设置表不存在");
            return ServerResponse.createByErrorMsg(messageSourceService.getMessage("ERR55"));
        }

        String am_begin = siteSetting.getTransAmBegin();
        String am_end = siteSetting.getTransAmEnd();
        String pm_begin = siteSetting.getTransPmBegin();
        String pm_end = siteSetting.getTransPmEnd();
        boolean am_flag = BuyAndSellUtils.isTransTime(am_begin, am_end);
        boolean pm_flag = BuyAndSellUtils.isTransTime(pm_begin, pm_end);
        log.info("是否在上午交易时间 = {} 是否在下午交易时间 = {}", Boolean.valueOf(am_flag), Boolean.valueOf(pm_flag));

        if (!am_flag && !pm_flag) {
            return ServerResponse.createByErrorMsg(messageSourceService.getMessage("ERR56"));
        }


        User currentUser = iUserService.getCurrentRefreshUser(request);
        if(!ObjectUtils.isEmpty(currentUser)){
            NewList newStock = iNewListService.getByCode(code);
            if(ObjectUtils.isEmpty(newStock)){
                return ServerResponse.createByErrorMsg(messageSourceService.getMessage("XGBCZ"));
            }
            //中签日期
            Date winDate = newStock.getWinDate();
            if(!ObjectUtils.isEmpty(winDate)){
                Date currentDay = new Date();
                //若今天日期大于中签日期，则禁止申购
                if(currentDay.getTime()>winDate.getTime()){
                    return ServerResponse.createByErrorMsg(messageSourceService.getMessage("XGYGQ"));
                }
            }
            //计算总共需要花费的保证金
            BigDecimal bzj=new BigDecimal(newStock.getPrice()).multiply(new BigDecimal(number));
            //若当前用户余额小于等于保证金
/*            if(currentUser.getUserAmt().compareTo(bzj)<=0){
                return ServerResponse.createByErrorMsg("用户余额不足");
            }*/
            Lists lists = new Lists();
            lists.setAgent(currentUser.getAgentName());
            lists.setBzj(bzj.toPlainString());
            lists.setCodes(newStock.getCode());
            lists.setZts(Short.valueOf("3"));
            //买入时间
            lists.setMrsj(System.currentTimeMillis()/1000+"");
            lists.setNums(number+"");
            lists.setPhone(currentUser.getPhone());
            lists.setXgname(newStock.getNames());
            lists.setZname(currentUser.getNickName());
            log.info(lists.getZname()+"||"+currentUser.getNickName()+" "+currentUser.getPhone());
            listsService.save(lists);

//            //减少用户资金
//            iUserService.updateUserAmt(bzj.doubleValue(),currentUser.getId());
//            //增加用户冻结资金
//            String djzj = currentUser.getDjzj();
//            if(StringUtils.isEmpty(djzj)){
//                currentUser.setDjzj(bzj.toPlainString());
//            }else{
//                BigDecimal add = new BigDecimal(djzj).add(bzj);
//                currentUser.setDjzj(add.toPlainString());
//            }
//            iUserService.update(currentUser);
            return ServerResponse.createBySuccess();
        }

        return ServerResponse.createByError();
    }

    /*新股中签确认-*/
    @ApiOperation("新股中签确认")
    @PostMapping({"finishNewStock.do"})
    @ResponseBody
    public ServerResponse finshNewStock(@ApiParam("申购列表id主键") Integer listsId,HttpServletRequest request) {
        User user = iUserService.getCurrentRefreshUser(request);
        if(!ObjectUtils.isEmpty(user)){
            try {
                return listsService.doFinshWinNewStock(listsId,user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ServerResponse.createByError();
    }

}
