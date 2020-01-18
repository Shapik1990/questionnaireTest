package com.questionnaire.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class QuestionDto implements Serializable {

    private Long id;
    private String text;
    private Long order;
}
