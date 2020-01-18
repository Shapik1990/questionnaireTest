package com.questionnaire.exception;

import com.questionnaire.model.dto.ResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;


@RestControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.of(Optional.of(ex.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.of(Optional.of(ex.getMessage()));
    }

    @ExceptionHandler(QuestionnaireException.class)
    protected ResponseDto handleNotFoundEntity(QuestionnaireException ex) {
        return ResponseDto.error(ex.getMessage());
    }

    @ExceptionHandler(FilterException.class)
    protected ResponseDto handleWrongParametersFilter(FilterException ex) {
        return ResponseDto.error(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseDto handleException(Exception ex) {
        return ResponseDto.error(ex.getMessage());
    }
}
