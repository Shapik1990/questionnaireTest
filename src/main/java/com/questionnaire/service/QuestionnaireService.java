package com.questionnaire.service;

import com.questionnaire.model.dto.QuestionnaireRqDto;
import com.questionnaire.model.dto.QuestionnaireRsDto;
import com.questionnaire.model.dto.RequestDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuestionnaireService {

    ResponseEntity<List<QuestionnaireRsDto>> getList(RequestDto rqDto);

    ResponseEntity<Boolean> create(QuestionnaireRqDto rsDto);

    ResponseEntity<QuestionnaireRsDto> edit(QuestionnaireRqDto rsDto);

    ResponseEntity<Boolean> delete(Long id);
}
