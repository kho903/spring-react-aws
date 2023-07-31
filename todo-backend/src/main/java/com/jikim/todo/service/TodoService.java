package com.jikim.todo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jikim.todo.model.TodoEntity;
import com.jikim.todo.persistence.TodoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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

	public List<TodoEntity> createTodo(final TodoEntity entity) {
		validate(entity);

		todoRepository.save(entity);

		log.info("Entity Id : {} is saved.", entity.getId());

		return todoRepository.findByUserId(entity.getUserId());
	}

	private void validate(TodoEntity entity) {
		if (entity == null) {
			log.warn("Entity cannot be null.");
			throw new RuntimeException("Entity cannot be null.");
		}

		if (entity.getUserId() == null) {
			log.warn("Unknown user.");
			throw new RuntimeException("Unknown user.");
		}
	}

	public List<TodoEntity> retrieveTodo(final String userId) {
		return todoRepository.findByUserId(userId);
	}

	public List<TodoEntity> updateTodo(final TodoEntity entity) {
		validate(entity);

		final Optional<TodoEntity> original = todoRepository.findById(entity.getId());

		original.ifPresent(todo -> {
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.isDone());

			todoRepository.save(todo);
		});

		return retrieveTodo(entity.getUserId());
	}
}
