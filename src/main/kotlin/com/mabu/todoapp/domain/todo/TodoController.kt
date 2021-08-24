package com.mabu.todoapp.domain.todo

import com.mabu.todoapp.commons.exceptions.TodoNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("todo")
class TodoController(private val todoService: TodoService) {

    @PostMapping("create")
    @Throws(Exception::class)
    @ResponseStatus(code = HttpStatus.CREATED)
    fun createTodo(@RequestBody @Valid todoRequest: TodoDto)   = todoService.createTodo(todoRequest)

    @GetMapping("fetch")
    fun fetchTodoList(): List<TodoDto> = todoService.fetchTodoList()

    @DeleteMapping("delete/{id}")
    @Throws(Exception::class)
    fun deleteTodo(@PathVariable id: Long)  {
        todoService.deleteTodo(id)
    }

    @PutMapping("update")
    @Throws(Exception::class)
    fun deleteTodo(@RequestBody @Valid todoDto: TodoDto)  {
        todoService.update(todoDto)
    }

}

