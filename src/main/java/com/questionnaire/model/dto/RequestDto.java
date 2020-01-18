package com.questionnaire.model.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RequestDto {
    @Valid
    @NotNull
    private List<SortDto> sorts;
    @Valid
    private List<FilterDto> filters;
    @Valid
    private PageDto paging;
}
