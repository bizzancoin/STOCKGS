 package com.xc.controller.protol;

 import com.github.pagehelper.PageInfo;
 import com.wordnik.swagger.annotations.Api;
 import com.wordnik.swagger.annotations.ApiOperation;
 import com.xc.common.ServerResponse;
 import com.xc.dao.UserPositionMapper;
 import com.xc.pojo.User;
 import com.xc.pojo.UserPosition;
 import com.xc.service.IUserPositionService;
 import javax.servlet.http.HttpServletRequest;

 import com.xc.service.IUserService;
 import com.xc.utils.CacheUtil;
 import com.xc.vo.position.PositionVO;
 import com.xc.vo.position.UserPositionVO;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;

 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;

 @Controller
 @RequestMapping({"/user/position/"})
 @Api("用户股票持仓")
 public class UserPositionController {
     private static final Logger log = LoggerFactory.getLogger(UserPositionController.class);

     @Autowired
     IUserPositionService iUserPositionService;

     @Autowired
     IUserService iUserService;

     @Autowired
     UserPositionMapper userPositionMapper;

     //查询所有融资平仓/持仓信息
     @ApiOperation("查询所有平仓/持仓信息")
     @PostMapping({"list.do"})
     @ResponseBody
     public ServerResponse list(HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "state", required = false) Integer state, @RequestParam(value = "stockCode", required = false) String stockCode, @RequestParam(value = "stockSpell", required = false) String stockSpell) {
         return this.iUserPositionService.findMyPositionByCodeAndSpell(stockCode, stockSpell, state, request, pageNum, pageSize);
     }

     //查询所有融资平仓/持仓信息
     @ApiOperation("根据positionSn查询交易详情")
     @PostMapping({"findBySn.do"})
     @ResponseBody
     public ServerResponse findBySn(HttpServletRequest request, @RequestParam(value = "positionSn",required = true) String positionSn) {
        return  ServerResponse.createBySuccess(this.userPositionMapper.findPositionBySn(positionSn));
     }

     //查询当前持仓信息
     @ApiOperation("查询当前持仓信息")
     @PostMapping({"currentPosition.do"})
     @ResponseBody
     public ServerResponse currentPosition(HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "state", required = false) Integer state, @RequestParam(value = "stockCode", required = false) String stockCode, @RequestParam(value = "stockSpell", required = false) String stockSpell) {
         User currentUser = iUserService.getCurrentUser(request);
         List<UserPositionVO> positionList = this.iUserPositionService.findPositionByUserIdAndSellIdIsNull(currentUser.getId(), pageNum, pageSize);
         PageInfo<UserPositionVO> pageInfo = new PageInfo<>(positionList);
         return ServerResponse.createBySuccess(pageInfo);
     }

     //查询当前持仓信息
     @ApiOperation("查询当前持仓信息包含总仓位信息")
     @PostMapping({"currentPositionZF.do"})
     @ResponseBody
     public ServerResponse currentPositionZF(HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "state", required = false) Integer state, @RequestParam(value = "stockCode", required = false) String stockCode, @RequestParam(value = "stockSpell", required = false) String stockSpell) {
         User currentUser = iUserService.getCurrentUser(request);
         List<UserPositionVO> positionList = this.iUserPositionService.findPositionByUserIdAndSellIdIsNull(currentUser.getId(), pageNum, pageSize);
         PositionVO positionVO = this.iUserPositionService.findUserPositionAllProfitAndLose(positionList);
         PageInfo<UserPositionVO> pageInfo = new PageInfo<>(positionList);
         Map<String,Object> result=new HashMap<String,Object>();
         result.put("pageInfo",pageInfo);
         result.put("position",positionVO);
         return ServerResponse.createBySuccess(result);
     }


     //查询当前成交回报
     @ApiOperation("查询当前成交回报")
     @PostMapping({"historyPosition.do"})
     @ResponseBody
     public ServerResponse historyPosition(HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "state", required = false) Integer state, @RequestParam(value = "stockCode", required = false) String stockCode, @RequestParam(value = "stockSpell", required = false) String stockSpell) {
         User currentUser = iUserService.getCurrentUser(request);
         List<UserPosition> positionList = this.iUserPositionService.findPositionByUserIdAndSellIdNotNull(currentUser.getId(), pageNum, pageSize);
         PageInfo<UserPosition> pageInfo = new PageInfo<>(positionList);
         return ServerResponse.createBySuccess(pageInfo);
     }


     //根据股票代码查询用户最早入仓股票
     @ApiOperation("根据股票代码查询用户持仓股票")
     @PostMapping({"findUserPositionByCode.do"})
     @ResponseBody
     public ServerResponse findUserPositionByCode(HttpServletRequest request, @RequestParam(value = "stockCode", required = false) String stockCode) {
         return this.iUserPositionService.findUserPositionByCode(request, stockCode);
     }
 }

