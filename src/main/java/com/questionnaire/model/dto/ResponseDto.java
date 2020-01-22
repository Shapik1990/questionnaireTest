package com.questionnaire.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseDto<T> implements Serializable {
    private T body;
    private Status status;
    private String errorMessage;

    private ResponseDto(T body, Status status) {
        this.body = body;
        this.status = status;
    }

    private ResponseDto(Status status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    private ResponseDto(Status status) {
        this.status = status;
    }

    public static <T> ResponseDto<T> successful(T body) {
        return new ResponseDto<>(body, Status.SUCCESS);
    }

    public static ResponseDto successful() {
        return new ResponseDto(Status.SUCCESS);
    }

    public static ResponseDto error(String errorMessage) {
        return new ResponseDto(Status.ERROR, errorMessage);
    }

    public enum Status{
        SUCCESS,
        ERROR;
    }
}
