package com.xc.dao;

import com.xc.pojo.NewList;
import com.xc.pojo.NewListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewListMapper {
    int countByExample(NewListExample example);

    int deleteByExample(NewListExample example);

    int deleteByPrimaryKey(Integer newlistId);

    int insert(NewList record);

    int insertSelective(NewList record);

    List<NewList> selectByExample(NewListExample example);

    NewList selectByPrimaryKey(Integer newlistId);

    int updateByExampleSelective(@Param("record") NewList record, @Param("example") NewListExample example);

    int updateByExample(@Param("record") NewList record, @Param("example") NewListExample example);

    int updateByPrimaryKeySelective(NewList record);

    int updateByPrimaryKey(NewList record);
}