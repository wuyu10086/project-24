package com.taskmanager.mapper;

import com.taskmanager.entity.Task;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface TaskMapper {

    @Insert("INSERT INTO task(title, description, priority, deadline, user_id, status, created_at) " +
            "VALUES(#{title}, #{description}, #{priority}, #{deadline}, #{userId}, #{status}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Task task);

    @Select("SELECT * FROM task WHERE user_id = #{userId} ORDER BY created_at DESC")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "createdAt", column = "created_at")
    })
    List<Task> findByUserId(Long userId);

    @Update("UPDATE task SET title=#{title}, description=#{description}, priority=#{priority}, " +
            "deadline=#{deadline}, status=#{status} WHERE id=#{id}")
    void update(Task task);

    @Delete("DELETE FROM task WHERE id = #{id}")
    void delete(Long id);

    @Select("SELECT * FROM task WHERE id = #{id}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "createdAt", column = "created_at")
    })
    Task findById(Long id);
}