package com.xc.service;

import com.xc.common.ServerResponse;

public interface RealTimeService {

    ServerResponse deleteRealTime();

    ServerResponse deleteFuturesRealTime();

    ServerResponse findStock(String paramString);

}
