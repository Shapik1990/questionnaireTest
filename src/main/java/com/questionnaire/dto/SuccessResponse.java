package com.questionnaire.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SuccessResponse<T> implements Serializable {

    private T data;
}
