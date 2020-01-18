package com.questionnaire.service;

import com.questionnaire.model.dto.QuestionnaireRqDto;
import com.questionnaire.model.dto.QuestionnaireRsDto;
import com.questionnaire.model.dto.RequestDto;
import com.questionnaire.model.dto.ResponseDto;

import java.util.List;

public interface QuestionnaireService {

    ResponseDto<List<QuestionnaireRsDto>> getList(RequestDto rqDto);

    ResponseDto<QuestionnaireRsDto> create(QuestionnaireRqDto rsDto);

    ResponseDto<QuestionnaireRsDto> edit(QuestionnaireRqDto rsDto);

    ResponseDto delete(Long id);
}
