package com.questionnaire.service;

import com.questionnaire.dto.QuestionnaireRqDto;
import com.questionnaire.dto.QuestionnaireRsDto;
import com.questionnaire.dto.RequestDto;
import com.questionnaire.dto.SuccessResponse;

import java.util.List;

public interface QuestionnaireService {

    SuccessResponse<List<QuestionnaireRqDto>> getList(RequestDto rqDto);

    SuccessResponse<Boolean> create(QuestionnaireRsDto rsDto);

    SuccessResponse<QuestionnaireRqDto> edit(QuestionnaireRsDto rsDto);

    SuccessResponse<Boolean> delete(Long id);
}
