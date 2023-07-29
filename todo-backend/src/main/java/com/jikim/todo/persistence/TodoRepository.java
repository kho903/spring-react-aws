package com.jikim.todo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jikim.todo.model.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, String> {

	List<TodoEntity> findByUserId(String userId);
}
