package com.taskmanager.mapper;

import com.taskmanager.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user(username, password, created_at) VALUES(#{username}, #{password}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Select("SELECT * FROM user WHERE username = #{username}")
    @Results({
            @Result(property = "createdAt", column = "created_at")
    })
    User findByUsername(String username);

    @Select("SELECT * FROM user WHERE id = #{id}")
    @Results({
            @Result(property = "createdAt", column = "created_at")
    })
    User findById(Long id);
}