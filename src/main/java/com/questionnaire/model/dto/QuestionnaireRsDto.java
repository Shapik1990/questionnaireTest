package com.questionnaire.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuestionnaireRsDto implements Serializable {

    private Long id;
    private String name;
    private String startDate;
    private String endDate;
    private boolean active;
    private List<QuestionDto> questions;
}
