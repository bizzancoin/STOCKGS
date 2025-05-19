package com.xc.dao;

import com.xc.pojo.User;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {

    int deleteByPrimaryKey(Integer paramInteger);

    int insert(User paramUser);

    int insertSelective(User paramUser);

    User selectByPrimaryKey(Integer paramInteger);

    int updateByPrimaryKeySelective(User paramUser);

    int updateByPrimaryKey(User paramUser);

    User findByPhone(String paramString);

    User login(@Param("phone") String paramString1, @Param("userPwd") String paramString2);

    List listByAgent(@Param("realName") String paramString1, @Param("phone") String paramString2, @Param("searchId") Integer paramInteger1, @Param("accountType") Integer paramInteger2);

    List listByAdmin(@Param("realName") String paramString1, @Param("phone") String paramString2, @Param("searchId") Integer paramInteger1, @Param("accountType") Integer paramInteger2);

    int CountUserSize(Integer paramInteger);

    BigDecimal CountUserAmt(Integer paramInteger);

    BigDecimal CountEnableAmt(Integer paramInteger);

    @Select("select with_pwd from `user` where with_pwd=#{with_pwd}")
    String findWithPwd(String with_pwd);

    @Select("select with_pwd from `user` where phone=#{phone}")
    String findIdWithPwd(String phone);

    @Update("update `user` set with_pwd=#{with_pwd} where phone=#{phone}")
    int updateWithPwd(@Param("with_pwd") String paramString1, @Param("phone") String paramString2);

//    @Update("update `user` set user_amt = user_amt + #{user_amt} where id = #{user_id}")
    int updateUserAmt(@Param("user_amt") Double user_amt, @Param("user_id") Integer user_id);

}
