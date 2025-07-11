package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.model.Todo;

@Service
public class TodoService {
	@Autowired
	@Qualifier("jsonPlaceholderClient")
	private WebClient todoWebClient;

	public Todo getTodo(int todoId) {
		return todoWebClient.get().uri("/todos/" + todoId).retrieve().bodyToMono(Todo.class).block();
	}
}
