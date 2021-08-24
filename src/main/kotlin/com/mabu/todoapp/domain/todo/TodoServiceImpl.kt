package com.mabu.todoapp.domain.todo

import com.mabu.todoapp.commons.exceptions.TodoNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import java.io.IOException

@Service
class TodoServiceImpl(@Autowired private val todoRepository: TodoRepository): TodoService {

    override fun createTodo(todoDto: TodoDto) {
        todoRepository.save(createTodoFromDto(todoDto))
    }

    override fun fetchTodoList(): List<TodoDto> {
       return todoRepository.findAll().map { createTodoDtoFromTodo(it) }
    }

    @Throws(IOException::class)
    override fun deleteTodo(id: Long) {
       val isExist = todoRepository.existsById(id)
        if (!isExist) throw TodoNotFoundException("todo not exist with id $id")
        todoRepository.deleteById(id)
    }

    @Transactional
    @kotlin.jvm.Throws(Exception::class)
    override fun update(todoDto: TodoDto) {
        todoDto.id?.let {
           if (todoRepository.findById(it).isPresent) {
               todoRepository.save(createTodoFromDto(todoDto))
           } else {
               throw TodoNotFoundException("todo item not exist with id: $it")
           }
        } ?: kotlin.run {
            throw IOException("id cannot be null or empty")
        }
    }


    private fun createTodoFromDto(todoDto: TodoDto): Todo {
       return Todo(todoDto.id ?: 0, todoDto.name, todoDto.description, todoDto.createdAt, todoDto.dueDate, todoDto.priority)
    }

    private fun createTodoDtoFromTodo(todo: Todo): TodoDto {
        return TodoDto(todo.id, todo.name, todo.description, todo.createdAt, todo.dueDate, todo.priority)
    }
}