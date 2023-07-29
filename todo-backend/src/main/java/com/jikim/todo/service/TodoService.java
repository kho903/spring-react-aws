package com.jikim.todo.service;

import org.springframework.stereotype.Service;

import com.jikim.todo.model.TodoEntity;
import com.jikim.todo.persistence.TodoRepository;

@Service
public class TodoService {

	private final TodoRepository todoRepository;

	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public String testService() {
		TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
		todoRepository.save(entity);
		TodoEntity savedEntity = todoRepository.findById(entity.getId()).get();
		return savedEntity.getTitle();
	}

}
