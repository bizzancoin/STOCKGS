package com.xc.service;

import com.xc.common.ServerResponse;
import com.xc.pojo.SiteProduct;

public interface ISiteProductService {
  ServerResponse update(SiteProduct paramSiteProduct);

  SiteProduct getProductSetting();
}
