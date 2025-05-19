package com.xc.service;

import com.xc.common.ServerResponse;
import com.xc.pojo.ConvertBond;

public interface IConverBondService {

    public ServerResponse listByPage(Integer page, Integer size);

    public ConvertBond getById(Integer id);

    public ServerResponse save(ConvertBond convertBond);
}
