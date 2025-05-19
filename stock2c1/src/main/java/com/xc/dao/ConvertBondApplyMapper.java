package com.xc.dao;

import com.xc.pojo.ConvertBondApply;
import com.xc.pojo.ConvertBondApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConvertBondApplyMapper {
    int countByExample(ConvertBondApplyExample example);

    int deleteByExample(ConvertBondApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ConvertBondApply record);

    int insertSelective(ConvertBondApply record);

    List<ConvertBondApply> selectByExample(ConvertBondApplyExample example);

    ConvertBondApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ConvertBondApply record, @Param("example") ConvertBondApplyExample example);

    int updateByExample(@Param("record") ConvertBondApply record, @Param("example") ConvertBondApplyExample example);

    int updateByPrimaryKeySelective(ConvertBondApply record);

    int updateByPrimaryKey(ConvertBondApply record);
}