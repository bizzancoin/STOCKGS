package com.xc.vo.stock;


import com.wordnik.swagger.models.auth.In;
import com.xc.utils.StringUtils;

/**
 * 涨跌幅分别基础柱状单元
 */
public class ChartCellVO {

    private String xAxis;
    private String yAxis;
    //是否涨跌 -1跌 0平 1涨
    private Integer type;

    public ChartCellVO(String yAxis, String xAxis ) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        String substring = xAxis.substring(xAxis.length() - 1, xAxis.length());
        if(substring.equals("涨")){
            type=1;
        }else if(substring.equals("平")){
            type=0;
        }else {
            type=-1;
        }

    }

    public String getxAxis() {
        return xAxis;
    }

    public void setxAxis(String xAxis) {
        this.xAxis = xAxis;
    }

    public String getyAxis() {
        return yAxis;
    }

    public void setyAxis(String yAxis) {
        this.yAxis = yAxis;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
