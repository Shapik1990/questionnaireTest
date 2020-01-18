package com.questionnaire.mappers;

import com.questionnaire.model.dto.SortDto;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SortMapper {
    private Sort sort;

    public Sort getSort(List<SortDto> sortList) {
        if(sortList == null) {
            return Sort.unsorted();
        }

        sortList.sort(Comparator.comparing(SortDto::getOrder));

        sort = Sort.by(sortList.stream()
                    .map(s -> new Sort.Order(Sort.Direction.valueOf(s.getDirection().getValue()), s.getField()))
                    .collect(Collectors.toList()));
        return sort;
    }
}
