package com.xc.controller.backend;

import com.xc.common.ServerResponse;
import com.xc.pojo.FundsAppend;
import com.xc.pojo.FundsApply;
import com.xc.service.IFundsAppendService;
import com.xc.service.IFundsApplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping({"/admin/funds/apply/"})
public class AdminFundsApplyController {
    private static final Logger log = LoggerFactory.getLogger(AdminAgentController.class);
    @Autowired
    IFundsApplyService iFundsApplyService;

    @Autowired
    IFundsAppendService iFundsAppendService;

    //配资申请-列表查询
    @RequestMapping({"getApplyList.do"})
    @ResponseBody
    public ServerResponse getApplyList(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "12") int pageSize, @RequestParam(value = "keyword", defaultValue = "") String keyword, @RequestParam(value = "status", required = false) Integer status, HttpServletRequest request) {
        return ServerResponse.createBySuccess(this.iFundsApplyService.getList(pageNum, pageSize, keyword, status, request));
    }

    //配资申请-保存
    @RequestMapping({"saveApply.do"})
    @ResponseBody
    public ServerResponse saveApply(FundsApply model) {
        return ServerResponse.createBySuccess(this.iFundsApplyService.save(model));
    }

    //配资申请-审核
    @RequestMapping({"auditApply.do"})
    @ResponseBody
    public ServerResponse auditApply(FundsApply fundsApply, HttpServletRequest request) throws Exception  {
        return this.iFundsApplyService.audit(fundsApply, request);
    }

    //配资追加申请-查询用户追加列表
    @RequestMapping({"getAppendList.do"})
    @ResponseBody
    public ServerResponse getAppendList(HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "status", required = false) Integer status, @RequestParam(value = "userId", required = false) Integer userId, @RequestParam(value = "appendType", required = false) Integer appendType) {
        return this.iFundsAppendService.getList(pageNum, pageSize, keyword, status, userId, appendType, request);
    }

    //配资追加申请-查询详情
    @RequestMapping({"getAppendDetail.do"})
    @ResponseBody
    public ServerResponse getAppendDetail(Integer id) {
        return this.iFundsAppendService.getDetail(id);
    }

    //配资追加申请-保存
    @RequestMapping({"saveAppendApply.do"})
    @ResponseBody
    public ServerResponse saveAppendApply(FundsAppend fundsApply, HttpServletRequest request) throws Exception {
        return this.iFundsAppendService.save(fundsApply, request);
    }


}
