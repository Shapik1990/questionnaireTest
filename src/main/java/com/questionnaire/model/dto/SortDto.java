package com.questionnaire.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;


@Data
@Accessors(chain = true)
public class SortDto {

    @NotNull
    private Direction direction;
    @NotNull
    private String field;
    @NotNull
    private Long order;


    public enum Direction {
        @JsonProperty("asc")
        ASC("ASC"),
        @JsonProperty("desc")
        DESC("DESC");

        private String value;

        private Direction(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }
}
