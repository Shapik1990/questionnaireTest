package com.questionnaire.controller;

import com.questionnaire.model.dto.QuestionnaireRqDto;
import com.questionnaire.model.dto.QuestionnaireRsDto;
import com.questionnaire.model.dto.RequestDto;
import com.questionnaire.model.dto.ResponseDto;
import com.questionnaire.service.QuestionnaireService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api
@Data
@RestController
@RequestMapping("v1/questionnaire")
public class QuestionnaireController {

    private final QuestionnaireService service;

    @ApiOperation("list")
    @PostMapping("/list")
    public ResponseDto<List<QuestionnaireRsDto>> getList(@Valid @RequestBody RequestDto rqDto) {
        return service.getList(rqDto);
    }

    @ApiOperation("create")
    @PutMapping("/create")
    public ResponseDto<QuestionnaireRsDto> create(@Valid @RequestBody QuestionnaireRqDto rqDto) {
        return service.create(rqDto);
    }

    @ApiOperation("edit")
    @PostMapping("/edit")
    public ResponseDto<QuestionnaireRsDto> edit(@Valid @RequestBody QuestionnaireRqDto rqDto) {
        return service.edit(rqDto);
    }

    @ApiOperation("delete")
    @DeleteMapping("/delete")
    public ResponseDto delete(@Valid @RequestParam Long id) {
        return service.delete(id);
    }
}
