package com.questionnaire.mappers;

import com.questionnaire.exception.FilterException;
import com.questionnaire.model.dto.FilterDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Component
public interface FilterMapper<T> {

    Specification<T> getSpecificationWithField(FilterDto dto);

    default  <E extends Comparable> Specification<T> getSpecification(FilterDto dto, E value) {

        return new Specification<T>() {

            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                switch (dto.getOperation()) {
                    case EQUAL:
                        return criteriaBuilder.equal(root.get(dto.getField().getValue()), value);
                    case NOT_EQUAL:
                        return criteriaBuilder.notEqual(root.get(dto.getField().getValue()), value);
                    case GTE:
                        return criteriaBuilder.greaterThanOrEqualTo(root.get(dto.getField().getValue()), value);
                    case GT:
                        return criteriaBuilder.greaterThan(root.get(dto.getField().getValue()), value);
                    case LT:
                        return criteriaBuilder.lessThan(root.get(dto.getField().getValue()), value);
                    case LTE:
                        return criteriaBuilder.lessThanOrEqualTo(root.get(dto.getField().getValue()), value);
                }
                throw new FilterException("Указанная операция сравнения не найдена");
            }
        };
    }
}
