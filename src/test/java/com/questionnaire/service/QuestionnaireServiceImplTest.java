package com.questionnaire.service;

import com.questionnaire.model.dto.FilterDto;
import com.questionnaire.model.dto.QuestionDto;
import com.questionnaire.model.dto.QuestionnaireRqDto;
import com.questionnaire.model.dto.QuestionnaireRsDto;
import com.questionnaire.model.dto.RequestDto;
import com.questionnaire.model.dto.ResponseDto;
import com.questionnaire.model.dto.SortDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionnaireServiceImplTest {

    @Autowired
    private QuestionnaireService service;

    private List<QuestionDto> questionDtos;

    @BeforeEach
    public void setUp() {

        QuestionDto questionDto1 = new QuestionDto()
                .setText("Question 1")
                .setOrder(1L);

        QuestionDto questionDto2 = new QuestionDto()
                .setText("Question 2")
                .setOrder(2L);

        questionDtos = new ArrayList<>();
        questionDtos.add(questionDto1);
        questionDtos.add(questionDto2);
    }

    @Test
    @Transactional
    void getList() {
        SortDto sortDto = new SortDto()
                            .setDirection(SortDto.Direction.ASC)
                            .setField("id");

        FilterDto filterDto = new FilterDto()
                                .setField(FilterDto.Field.NAME)
                                .setOperation(FilterDto.Operation.EQUAL)
                                .setValue("QList");

        RequestDto rqDto = new RequestDto();
        rqDto.setFilters(singletonList(filterDto));
        rqDto.setSorts(singletonList(sortDto));

        ResponseDto<List<QuestionnaireRsDto>> rsDto = service.getList(rqDto);

        assertEquals(rsDto.getStatus(), ResponseDto.Status.SUCCESS);
        assertNotNull(rsDto.getBody());
        assertFalse(rsDto.getBody().isEmpty());
        assertEquals(rsDto.getBody().size(), 2);
        assertNull(rsDto.getErrorMessage());
    }

    @Test
    @Transactional
    void create() {
        QuestionnaireRqDto rqDto = new QuestionnaireRqDto()
                                    .setName("Q3")
                                    .setActive(false)
                                    .setStartDate(new Date())
                                    .setEndDate(new Date())
                                    .setQuestions(questionDtos);

        ResponseDto<QuestionnaireRsDto> rsDto = service.create(rqDto);
        assertEquals(rsDto.getStatus(), ResponseDto.Status.SUCCESS);
        assertNotNull(rsDto.getBody());
        assertEquals(rsDto.getBody().getId(), 5);
        assertEquals(rsDto.getBody().getName(), "Q3");
        assertFalse(rsDto.getBody().isActive());
        assertFalse(rsDto.getBody().getQuestions().isEmpty());
        assertEquals(rsDto.getBody().getQuestions().size(), 2);
        assertNull(rsDto.getErrorMessage());
    }

    @Test
    @Transactional
    void edit() {
        QuestionnaireRqDto rqDto = new QuestionnaireRqDto()
                                    .setName("Q1")
                                    .setId(1L)
                                    .setActive(true)
                                    .setStartDate(new Date())
                                    .setEndDate(new Date())
                                    .setQuestions(questionDtos);

        ResponseDto<QuestionnaireRsDto> rsDto = service.edit(rqDto);
        assertEquals(rsDto.getStatus(), ResponseDto.Status.SUCCESS);
        assertNotNull(rsDto.getBody());
        assertEquals(rsDto.getBody().getId(), 1);
        assertEquals(rsDto.getBody().getName(), "Q1");
        assertTrue(rsDto.getBody().isActive());
        assertFalse(rsDto.getBody().getQuestions().isEmpty());
        assertEquals(rsDto.getBody().getQuestions().size(), 2);
        assertNull(rsDto.getErrorMessage());
    }

    @Test
    void delete() {
        ResponseDto rsDto = service.delete(2L);

        assertEquals(rsDto.getStatus(), ResponseDto.Status.SUCCESS);
        assertNull(rsDto.getBody());
        assertNull(rsDto.getErrorMessage());
    }
}