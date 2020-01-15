package com.questionnaire.mapper;

import com.questionnaire.dto.QuestionnaireRqDto;
import com.questionnaire.dto.QuestionnaireRsDto;
import com.questionnaire.model.Questionnaire;
import org.springframework.stereotype.Component;

@Component
public class QuestionnaireMapper {

    public Questionnaire dtoToEntity(QuestionnaireRsDto rqDto){
        return new Questionnaire();
    }

    public QuestionnaireRqDto entityToDto(Questionnaire questionnaire) {
        return new QuestionnaireRqDto();
    }
}
