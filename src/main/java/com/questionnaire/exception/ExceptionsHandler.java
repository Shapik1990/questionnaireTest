package com.questionnaire.exception;

import com.questionnaire.model.dto.ErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < ex.getBindingResult().getAllErrors().size(); i++) {
            sb.append(" " + ex.getBindingResult().getAllErrors().get(i).getDefaultMessage());
            sb.append(" ");
            sb.append(ex.getBindingResult().getFieldErrors().get(i).getField() + ";");

        }

        return ResponseEntity.of(Optional.of(new ErrorDto("Ошибка валидации :" + sb.toString())));
    }

    @ExceptionHandler(QuestionnaireException.class)
    protected ResponseEntity<Object> notFoundEntity(RuntimeException ex) {
        return new ResponseEntity<>(new ErrorDto(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FilterException.class)
    protected ResponseEntity<Object> wrongParametersFilter(RuntimeException ex) {
        return new ResponseEntity<>(new ErrorDto(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> otherExceptions(RuntimeException ex) {
        return new ResponseEntity<>(new ErrorDto(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
