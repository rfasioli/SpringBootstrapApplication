package br.com.rfasioli.bootstrap.api.adapter.input.web.advice

import br.com.rfasioli.bootstrap.api.application.exception.BusinessException
import br.com.rfasioli.bootstrap.api.application.exception.InvalidDataException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalControllerExceptionHandler {
    @ExceptionHandler(BusinessException::class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    fun handleBusinessException(ex: BusinessException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.NOT_ACCEPTABLE)
    }

    @ExceptionHandler(InvalidDataException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleInvalidDataException(ex: InvalidDataException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(RuntimeException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleBookNotFound(ex: RuntimeException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
