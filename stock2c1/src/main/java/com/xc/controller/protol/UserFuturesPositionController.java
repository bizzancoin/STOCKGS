package com.xc.controller.protol;

import com.xc.common.ServerResponse;
import com.xc.service.IUserFuturesPositionService;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping({"/user/futures/position/"})
public class UserFuturesPositionController {
    private static final Logger log = LoggerFactory.getLogger(UserFuturesPositionController.class);

    @Autowired
    IUserFuturesPositionService iUserFuturesPositionService;

    //查询所有期货持仓/平仓信息
    @RequestMapping({"list.do"})
    @ResponseBody
    public ServerResponse list(HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "state", required = false) Integer state, @RequestParam(value = "fuName", required = false) String fuName, @RequestParam(value = "fuCode", required = false) String fuCode) {
        return this.iUserFuturesPositionService.findMyFuturesPositionByNameAndCode(fuName, fuCode, state, request, pageNum, pageSize);
    }

    //根据期货代码查询用户最早入仓股票
    @RequestMapping({"findUserFuturesPositionByCode.do"})
    @ResponseBody
    public ServerResponse findUserFuturesPositionByCode(HttpServletRequest request, @RequestParam(value = "futuresGid", required = false) String futuresGid) {
        return this.iUserFuturesPositionService.findUserFuturesPositionByCode(request, futuresGid);
    }
}
