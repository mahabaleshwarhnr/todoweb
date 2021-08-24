package com.mabu.todoapp.domain.todo

import java.io.IOException

interface TodoService {
    fun createTodo(todoDto: TodoDto)
    fun fetchTodoList(): List<TodoDto>
    @Throws(IOException::class)
    fun deleteTodo(id: Long)
    @Throws(IOException::class)
    fun update(todoDto: TodoDto)
}