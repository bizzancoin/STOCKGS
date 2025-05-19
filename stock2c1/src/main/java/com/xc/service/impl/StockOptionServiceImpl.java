package com.xc.service.impl;

 import com.github.pagehelper.PageHelper;

 import com.github.pagehelper.PageInfo;

 import com.google.common.collect.Lists;

 import com.xc.common.ServerResponse;

 import com.xc.dao.StockMapper;

 import com.xc.dao.StockOptionMapper;

 import com.xc.pojo.Stock;

 import com.xc.pojo.StockOption;

 import com.xc.pojo.User;

 import com.xc.service.IStockOptionService;

 import com.xc.service.IUserService;

 import com.xc.utils.stock.lide.LiDeDataUtils;
 import com.xc.utils.stock.sina.SinaStockApi;

 import com.xc.vo.stock.StockOptionListVO;

 import com.xc.vo.stock.StockVO;

 import java.util.List;

 import javax.servlet.http.HttpServletRequest;

 import org.slf4j.Logger;

 import org.slf4j.LoggerFactory;

 import org.springframework.beans.factory.annotation.Autowired;

 import org.springframework.stereotype.Service;
 import org.springframework.util.ObjectUtils;


@Service("iStockOptionService")

 public class StockOptionServiceImpl implements IStockOptionService {

   private static final Logger log = LoggerFactory.getLogger(StockOptionServiceImpl.class);

   @Autowired
   StockOptionMapper stockOptionMapper;

   @Autowired
   IUserService iUserService;

   @Autowired
   StockMapper stockMapper;

   public ServerResponse<PageInfo> findMyStockOptions(String keyWords, HttpServletRequest request, int pageNum, int pageSize) {

     PageHelper.startPage(pageNum, pageSize);
     User user = this.iUserService.getCurrentUser(request);
     List<StockOption> stockOptions = this.stockOptionMapper.findMyOptionByKeywords(user.getId(), keyWords);

     List<StockOptionListVO> stockOptionListVOS = Lists.newArrayList();
     for (StockOption option : stockOptions) {
       StockOptionListVO stockOptionListVO = assembleStockOptionListVO(option);
       stockOptionListVO.setIsOption("1");
       stockOptionListVOS.add(stockOptionListVO);
     }
     PageInfo pageInfo = new PageInfo(stockOptions);

     pageInfo.setList(stockOptionListVOS);

     return ServerResponse.createBySuccess(pageInfo);

   }

   public ServerResponse isOption(Integer uid, String code) {

     StockOption stockOption = this.stockOptionMapper.isOption(uid, code);

     if (stockOption == null) {

       return ServerResponse.createBySuccessMsg("未添加");

     }

     return ServerResponse.createByErrorMsg("已添加");

   }

     public String isMyOption(Integer uid, String code) {
         StockOption stockOption = this.stockOptionMapper.isOption(uid, code);
         if (stockOption == null) {
             return "0";
         }
         return "1";

     }

    private StockOptionListVO assembleStockOptionListVO(StockOption option) {

        StockOptionListVO stockOptionListVO = new StockOptionListVO();



        stockOptionListVO.setId(option.getId().intValue());

        stockOptionListVO.setStockName(option.getStockName());

        stockOptionListVO.setStockCode(option.getStockCode());

        stockOptionListVO.setStockGid(option.getStockGid());

        StockVO stockVO = new StockVO();
        if(option.getStockGid().contains("hf")){
            stockVO = SinaStockApi.assembleStockFuturesVO(SinaStockApi.getSinaStock(option.getStockGid()));
        } else {
            stockVO= SinaStockApi.assembleLideStockVO(LiDeDataUtils.getStock(option.getStockCode()));
            if(ObjectUtils.isEmpty(stockVO)){
                stockVO = SinaStockApi.assembleStockVO(SinaStockApi.getSinaStock(option.getStockGid()));
            }
        }

        stockOptionListVO.setNowPrice(stockVO.getNowPrice());

        stockOptionListVO.setHcrate(stockVO.getHcrate().toString());

        stockOptionListVO.setPreclose_px(stockVO.getPreclose_px());

        stockOptionListVO.setOpen_px(stockVO.getOpen_px());

        Stock stock = this.stockMapper.selectByPrimaryKey(option.getStockId());

        stockOptionListVO.setStock_plate(stock.getStockPlate());

        stockOptionListVO.setStock_type(stock.getStockType());

        return stockOptionListVO;

    }
 }
