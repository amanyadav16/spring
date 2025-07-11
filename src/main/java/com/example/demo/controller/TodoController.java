package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Todo;
import com.example.demo.service.TodoService;

@RestController
public class TodoController {

	@Autowired
	private TodoService todoservice;

	@GetMapping("/todos/{todoId}")
	public ResponseEntity<Todo> getTodo(@PathVariable int todoId) {
		return ResponseEntity.ok(todoservice.getTodo(todoId));
	}
}
