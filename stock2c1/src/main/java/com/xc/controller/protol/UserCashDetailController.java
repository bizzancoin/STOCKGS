 package com.xc.controller.protol;


 import com.wordnik.swagger.annotations.Api;
 import com.wordnik.swagger.annotations.ApiOperation;
 import com.xc.common.ServerResponse;
 import com.xc.service.ISiteMessageService;
 import com.xc.service.IUserCashDetailService;
 import javax.servlet.http.HttpServletRequest;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;
 import org.springframework.web.bind.annotation.ResponseBody;

 @Controller
 @RequestMapping({"/user/cash/"})
 @Api("用户资产明细")
 public class UserCashDetailController {
     @Autowired
     IUserCashDetailService iUserCashDetailService;
     @Autowired
     ISiteMessageService iSiteMessageService;

     //查询所有资产明细
     @ApiOperation("查询所有资产明细")
     @PostMapping({"list.do"})
     @ResponseBody
     public ServerResponse list(HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "positionId", required = false) Integer positionId) {
         return this.iUserCashDetailService.findUserCashDetailList(positionId, request, pageNum, pageSize);
     }

     //查询用户站内消息
     @ApiOperation("查询用户站内消息")
     @PostMapping({"getMessagelist.do"})
     @ResponseBody
     public ServerResponse getMessagelist(HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "userId", required = false) Integer userId) {
         return this.iSiteMessageService.getSiteMessageByUserIdList(pageNum, pageSize, userId,request);
     }

     //用户站内消息状态变已读
     @ApiOperation("用户站内消息状态变已读")
     @PostMapping({"updateMessageStatus.do"})
     @ResponseBody
     public ServerResponse updateMessageStatus(HttpServletRequest request) {
         this.iSiteMessageService.updateMessageStatus(request);
         return ServerResponse.createBySuccess("查看成功");
     }

     //查询用户未读消息数
     @ApiOperation("查询用户未读消息数")
     @PostMapping({"getUnreadCount.do"})
     @ResponseBody
     public ServerResponse getUnreadCount(HttpServletRequest request) {
         int k = this.iSiteMessageService.getUnreadCount(request);
         return ServerResponse.createBySuccess(k);
     }

 }