package com.mabu.todoapp.domain.todo

import java.time.LocalDateTime
import javax.validation.Valid
import javax.validation.constraints.NotBlank

data class TodoDto(
    val id: Long?,
    @field:Valid
    @field:NotBlank(message = "name field cannot be blank")
    val name: String,
    val description: String?,
    val createdAt: LocalDateTime,
    val dueDate: LocalDateTime,
    val priority: Priority,
)