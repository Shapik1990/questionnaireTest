package com.questionnaire.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Accessors(chain = true)
public class RequestDto {
    @Valid
    @NotNull
    @NotEmpty
    private List<SortDto> sorts;
    @Valid
    private List<FilterDto> filters;
    @Valid
    private PageDto paging;
}
