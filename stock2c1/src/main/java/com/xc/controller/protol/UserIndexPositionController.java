package com.xc.controller.protol;

import com.xc.common.ServerResponse;
import com.xc.service.IUserIndexPositionService;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/user/index/position/"})
public class UserIndexPositionController {
    private static final Logger log = LoggerFactory.getLogger(UserIndexPositionController.class);

    @Autowired
    IUserIndexPositionService iUserIndexPositionService;

    @RequestMapping({"list.do"})
    @ResponseBody
    public ServerResponse list(HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, @RequestParam(value = "state", required = false) Integer state, @RequestParam(value = "indexName", required = false) String indexName, @RequestParam(value = "indexCode", required = false) String indexCode) {
        return this.iUserIndexPositionService.findMyIndexPositionByNameAndCode(indexName, indexCode, state, request, pageNum, pageSize);
    }

    //根据指数代码查询用户最早入仓股票
    @RequestMapping({"findUserIndexPositionByCode.do"})
    @ResponseBody
    public ServerResponse findUserIndexPositionByCode(HttpServletRequest request, @RequestParam(value = "indexGid", required = false) String indexGid) {
        return this.iUserIndexPositionService.findUserIndexPositionByCode(request, indexGid);
    }
}

