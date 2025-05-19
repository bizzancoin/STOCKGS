package com.xc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xc.common.ServerResponse;
import com.xc.dao.FundsDealerInstitutionsMapper;
import com.xc.pojo.FundsDealerInstitutions;
import com.xc.service.IFundsDealerInstitutionsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配资券商机构
 * @author lr
 * @date 2020/07/24
 */
@Service("IFundsDealerInstitutionsService")
public class FundsDealerInstitutionsServiceImpl implements IFundsDealerInstitutionsService {

    @Resource
    private FundsDealerInstitutionsMapper fundsDealerInstitutionsMapper;


    @Override
    public int insert(FundsDealerInstitutions model) {
        int ret = 0;
        if (model == null) {
            return 0;
        }
        ret = fundsDealerInstitutionsMapper.insert(model);
        return ret;
    }

    @Override
    public int update(FundsDealerInstitutions model) {
        int ret = fundsDealerInstitutionsMapper.update(model);
        return ret>0 ? ret: 0;
    }

    /**
     * 配资券商机构-保存
     */
    @Override
    public ServerResponse save(FundsDealerInstitutions model) {
        int ret = 0;
        if(model!=null && model.getId()>0){
            ret = fundsDealerInstitutionsMapper.update(model);
        } else{
            ret = fundsDealerInstitutionsMapper.insert(model);
        }
        if(ret>0){
            return ServerResponse.createBySuccessMsg("操作成功");
        }
        return ServerResponse.createByErrorMsg("操作失败");
    }

    /*配资券商机构-查询列表*/
    @Override
    public ServerResponse<PageInfo> getList(int pageNum, int pageSize, String keyword, Integer status, HttpServletRequest request){
        Page<FundsDealerInstitutions> page = PageHelper.startPage(pageNum, pageSize);
        List<FundsDealerInstitutions> listData = this.fundsDealerInstitutionsMapper.pageList(pageNum,pageSize,keyword, status);
        PageInfo pageInfo = new PageInfo(page);
        pageInfo.setList(listData);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /*配资券商机构-查询详情*/
    @Override
    public ServerResponse getDetail(int id) {
        return ServerResponse.createBySuccess(this.fundsDealerInstitutionsMapper.load(id));
    }

}
