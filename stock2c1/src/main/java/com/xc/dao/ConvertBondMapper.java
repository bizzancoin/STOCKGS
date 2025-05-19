package com.xc.dao;

import com.xc.pojo.ConvertBond;
import com.xc.pojo.ConvertBondExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConvertBondMapper {
    int countByExample(ConvertBondExample example);

    int deleteByExample(ConvertBondExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ConvertBond record);

    int insertSelective(ConvertBond record);

    List<ConvertBond> selectByExample(ConvertBondExample example);

    ConvertBond selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ConvertBond record, @Param("example") ConvertBondExample example);

    int updateByExample(@Param("record") ConvertBond record, @Param("example") ConvertBondExample example);

    int updateByPrimaryKeySelective(ConvertBond record);

    int updateByPrimaryKey(ConvertBond record);
}