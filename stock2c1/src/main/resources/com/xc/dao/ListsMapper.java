package com.xc.dao;

import com.xc.pojo.Lists;
import com.xc.pojo.ListsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ListsMapper {
    int countByExample(ListsExample example);

    int deleteByExample(ListsExample example);

    int deleteByPrimaryKey(Integer listsId);

    int insert(Lists record);

    int insertSelective(Lists record);

    List<Lists> selectByExample(ListsExample example);

    Lists selectByPrimaryKey(Integer listsId);

    int updateByExampleSelective(@Param("record") Lists record, @Param("example") ListsExample example);

    int updateByExample(@Param("record") Lists record, @Param("example") ListsExample example);

    int updateByPrimaryKeySelective(Lists record);

    int updateByPrimaryKey(Lists record);
}