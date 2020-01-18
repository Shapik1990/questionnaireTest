package com.questionnaire.mappers;

import com.questionnaire.model.dto.QuestionDto;
import com.questionnaire.model.dto.QuestionnaireRqDto;
import com.questionnaire.model.dto.QuestionnaireRsDto;
import com.questionnaire.model.entity.Question;
import com.questionnaire.model.entity.Questionnaire;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {

    public Question dtoToEntity(QuestionDto dto){
        return new Question().setId(dto.getId())
                                .setText(dto.getText())
                                .setOrder(dto.getOrder());
    }

    public QuestionDto entityToDto(Question questionnaire) {
        return new QuestionDto().setId(questionnaire.getId())
                                .setOrder(questionnaire.getOrder())
                                .setText(questionnaire.getText());
    }

    public Set<Question> questionDtoListToQuestionSet(Questionnaire questionnaire, QuestionnaireRqDto rqDto) {
        return rqDto.getQuestions().stream()
                .map(this::dtoToEntity)
                .peek(question -> question.setQuestionnaire(questionnaire))
                .collect(Collectors.toSet());

    }

    public List<QuestionDto> questionSetToQuestionDtoList(Questionnaire questionnaire, QuestionnaireRsDto rsDto) {
        return questionnaire.getQuestions().stream()
                .sorted(Comparator.comparing(Question::getOrder))
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

}
