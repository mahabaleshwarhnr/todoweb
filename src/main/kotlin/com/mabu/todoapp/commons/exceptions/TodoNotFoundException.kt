package com.mabu.todoapp.commons.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class TodoNotFoundException(override val message: String?): NoSuchElementException(message)
