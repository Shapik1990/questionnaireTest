package com.questionnaire.model.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class FilterDto {
    @NotNull
    private Field field;
    @NotNull
    private Operation operation;
    @NotNull
    private String value;

    public enum Operation {
        EQUAL("eq"),
        NOT_EQUAL("notEq"),
        GTE("gte"),
        GT("gt"),
        LT("lt"),
        LTE("lte");

        private String value;

        private Operation(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }
    }

    public enum Field {
        ID("id"),
        NAME("name"),
        START_DATE("startDate"),
        END_DATE("endDate"),
        ACTIVE("active");

        private String value;

        private Field (String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return value;
        }
    }
}
