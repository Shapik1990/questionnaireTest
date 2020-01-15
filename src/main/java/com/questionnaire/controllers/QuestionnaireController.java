package com.questionnaire.controllers;

import com.questionnaire.dto.QuestionnaireRqDto;
import com.questionnaire.dto.QuestionnaireRsDto;
import com.questionnaire.dto.RequestDto;
import com.questionnaire.dto.SuccessResponse;
import com.questionnaire.service.QuestionnaireService;
import lombok.Data;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Data
@RestController
@RequestMapping("v1/questionnaire")
public class QuestionnaireController {

    private QuestionnaireService service;

    @PostMapping
    @RequestMapping("/list")
    public SuccessResponse<List<QuestionnaireRqDto>> getList(@RequestBody RequestDto rqDto) {
        return service.getList(rqDto);
    }

    @PutMapping
    @RequestMapping("/create")
    public SuccessResponse<Boolean> create(@RequestBody QuestionnaireRsDto rsDto) {
        return service.create(rsDto);
    }

    @PostMapping
    @RequestMapping("/edit")
    public SuccessResponse<QuestionnaireRqDto> edit(@RequestBody QuestionnaireRsDto rsDto) {
        return service.edit(rsDto);
    }

    @DeleteMapping
    @RequestMapping("/delete")
    public SuccessResponse<Boolean> delete(Long id) {
        return service.delete(id);
    }
}
