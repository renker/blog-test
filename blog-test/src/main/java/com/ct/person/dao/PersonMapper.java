package com.ct.person.dao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ct.person.entity.Person;

@Repository
public interface PersonMapper {
    int deleteByPrimaryKey(String id);

    int insert(Person record);

    int insertSelective(Person record);

    Person selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKey(Person record);
    
    
    Person selectByAccount(@Param("account")String account);
    
    Person selectByAccountAndPassword(@Param("account")String account, @Param("password")String password);
}