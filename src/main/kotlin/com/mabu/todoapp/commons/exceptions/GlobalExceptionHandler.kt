@file:Suppress("unused")

package com.mabu.todoapp.commons.exceptions

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@ControllerAdvice
class GlobalExceptionHandler: ResponseEntityExceptionHandler() {

    @Suppress("unused")
    @ExceptionHandler(NoSuchElementException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNoSuchElementFoundException(exception: NoSuchElementException): ResponseEntity<ErrorResponse> {
        return ResponseEntity<ErrorResponse>(buildErrorResponse(exception.localizedMessage, "${HttpStatus.NOT_FOUND.value()}"), HttpStatus.NOT_FOUND)
    }

    override fun handleNoHandlerFoundException(
        ex: NoHandlerFoundException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        return ResponseEntity(buildErrorResponse("resource not found: ${ex.localizedMessage}", "${HttpStatus.NOT_FOUND.value()}"), status)
    }

    private fun buildErrorResponse(message: String?, errorCode: String?) = ErrorResponse(message, errorCode)
}