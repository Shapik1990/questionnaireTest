package com.questionnaire.mappers;

import com.questionnaire.exception.FilterException;
import com.questionnaire.model.dto.FilterDto;
import com.questionnaire.model.entity.Questionnaire;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class QuestionnaireFilterMapper implements FilterMapper<Questionnaire> {

    @Override
    public Specification<Questionnaire> getSpecificationWithField(FilterDto dto) {

        switch (dto.getField()) {
            case ACTIVE:
                if (dto.getValue().equals("true") || dto.getValue().equals("false")) {
                    return getSpecification(dto, dto.getValue().equals("true"));
                }
                throw new FilterException("Некорректное значение поля active");
            case ID:
                if (!dto.getValue().matches("-?\\d+(\\.\\d+)?")) {
                    throw new FilterException("Некорректное значение поля id, не является целым числом");
                }
                return getSpecification(dto, Long.valueOf(dto.getValue()));
            case NAME:
                return getSpecification(dto, dto.getValue());
            case END_DATE:
            case START_DATE:
                try {
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
                    Date date = formatter.parse(dto.getValue());
                    return getSpecification(dto, date);
                } catch (ParseException e) {
                    throw new FilterException("Неверный формат даты");
                }
        }
        throw new FilterException("Неверно указано поле для фильтрации");
    }
}
