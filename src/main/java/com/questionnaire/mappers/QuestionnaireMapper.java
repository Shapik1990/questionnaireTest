package com.questionnaire.mappers;

import com.questionnaire.model.dto.QuestionnaireRqDto;
import com.questionnaire.model.dto.QuestionnaireRsDto;
import com.questionnaire.model.entity.Questionnaire;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class QuestionnaireMapper {

    private final QuestionMapper questionMapper;

    public Questionnaire dtoToEntity(QuestionnaireRqDto rqDto){
        Questionnaire questionnaire = new Questionnaire().setName(rqDto.getName())
                                                        .setStartDate(rqDto.getStartDate())
                                                        .setEndDate(rqDto.getEndDate())
                                                        .setActive(rqDto.isActive());

        if (rqDto.getQuestions() != null) {
            questionnaire.setQuestions(questionMapper.questionDtoListToQuestionSet(questionnaire, rqDto));
        }
        return questionnaire;

    }

    public QuestionnaireRsDto entityToDto(Questionnaire questionnaire) {
        QuestionnaireRsDto rsDto = new QuestionnaireRsDto().setId(questionnaire.getId())
                .setName(questionnaire.getName())
                .setStartDate(questionnaire.getStartDate().toString())
                .setEndDate(questionnaire.getEndDate().toString())
                .setActive(questionnaire.isActive());

        if (questionnaire.getQuestions() != null) {
            rsDto.setQuestions(questionMapper.questionSetToQuestionDtoList(questionnaire, rsDto));
        }
        return rsDto;

    }

}
