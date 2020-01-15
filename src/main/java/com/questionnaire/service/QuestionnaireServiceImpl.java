package com.questionnaire.service;

import com.questionnaire.dto.QuestionnaireRqDto;
import com.questionnaire.dto.QuestionnaireRsDto;
import com.questionnaire.dto.RequestDto;
import com.questionnaire.dto.SuccessResponse;
import com.questionnaire.mapper.QuestionnaireMapper;
import com.questionnaire.model.Questionnaire;
import com.questionnaire.repository.QuestionnaireRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class QuestionnaireServiceImpl implements QuestionnaireService{

    private QuestionnaireMapper mapper;
    private QuestionnaireRepository repository;

    @Override
    public SuccessResponse<List<QuestionnaireRqDto>> getList(RequestDto rqDto) {
        return null;
    }

    @Override
    public SuccessResponse<Boolean> create(QuestionnaireRsDto rsDto) {
        Questionnaire questionnaire = mapper.dtoToEntity(rsDto);
        repository.save(questionnaire);
        return new SuccessResponse<>();
    }

    @Override
    public SuccessResponse<QuestionnaireRqDto> edit(QuestionnaireRsDto rsDto) {
        return null;
    }

    @Override
    public SuccessResponse<Boolean> delete(Long id) {
        repository.deleteById(id);
        return new SuccessResponse<>();
    }
}
