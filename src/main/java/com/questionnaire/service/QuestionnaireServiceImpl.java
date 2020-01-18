package com.questionnaire.service;

import com.questionnaire.exception.QuestionnaireException;
import com.questionnaire.mappers.QuestionMapper;
import com.questionnaire.mappers.QuestionnaireFilterMapper;
import com.questionnaire.mappers.SortMapper;
import com.questionnaire.model.dto.FilterDto;
import com.questionnaire.model.dto.QuestionnaireRqDto;
import com.questionnaire.model.dto.QuestionnaireRsDto;
import com.questionnaire.model.dto.RequestDto;
import com.questionnaire.mappers.QuestionnaireMapper;
import com.questionnaire.model.dto.ResponseDto;
import com.questionnaire.model.entity.Questionnaire;
import com.questionnaire.repository.QuestionnaireRepository;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class QuestionnaireServiceImpl implements QuestionnaireService{

    private final QuestionnaireMapper mapper;
    private final QuestionnaireRepository repository;
    private final QuestionMapper questionMapper;
    private final SortMapper sortMapper;
    private final QuestionnaireFilterMapper filterMapper;

    private static final String MSG_NOT_FOUND = "Опросник с указанным id не найден";

    @Override
    public ResponseDto<List<QuestionnaireRsDto>> getList(RequestDto rqDto) {

        PageRequest pageRequest = pageRequestBuilder(rqDto);

        Specification<Questionnaire> spec = specificationBuilder(rqDto.getFilters());


        List<QuestionnaireRsDto> list = repository.findAll(spec, pageRequest)
                                                    .stream()
                                                    .map(mapper::entityToDto)
                                                    .collect(Collectors.toList());
        return ResponseDto.successful(list);

    }

    private Specification<Questionnaire> specificationBuilder(List<FilterDto> filters) {
        Specification<Questionnaire> specification = new Specification<Questionnaire>() {
            @Override
            public Predicate toPredicate(Root<Questionnaire> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };

        if (filters == null || filters.isEmpty()) {
            return specification;
        }
        List<Specification<Questionnaire>> specificationList = filters.stream()
                                                                      .map(filterMapper::getSpecificationWithField)
                                                                      .collect(Collectors.toList());


        for (Specification<Questionnaire> spec : specificationList) {
            specification = Specification.where(specification).and(spec);
        }
        return specification;
    }

    private PageRequest pageRequestBuilder(RequestDto dto) {
        int pageNumber = dto.getPaging() != null &&
                            dto.getPaging().getPageNumber() != null ? dto.getPaging().getPageNumber() :
                                                                    0;
        int pageSize = dto.getPaging() != null &&
                            dto.getPaging().getPageSize() != null ? dto.getPaging().getPageSize() :
                                                                10;

        return PageRequest.of(pageNumber, pageSize, sortMapper.getSort(dto.getSorts()));
    }

    @Override
    public ResponseDto create(QuestionnaireRqDto rqDto) {
        Questionnaire questionnaire = mapper.dtoToEntity(rqDto);
        return ResponseDto.successful(mapper.entityToDto(repository.save(questionnaire)));
    }

    @Override
    public ResponseDto<QuestionnaireRsDto> edit(QuestionnaireRqDto rqDto) {
        if (rqDto.getId() == null) {
            throw new QuestionnaireException("Не указан id");
        }
        Questionnaire questionnaire = repository.findById(rqDto.getId())
                                                .orElseThrow(() -> new QuestionnaireException(MSG_NOT_FOUND));

        questionnaire.setName(rqDto.getName())
                .setStartDate(rqDto.getStartDate())
                .setEndDate(rqDto.getEndDate())
                .setActive(rqDto.isActive());

        questionnaire.getQuestions().clear();
        if (rqDto.getQuestions() != null) {
            questionnaire.getQuestions()
                    .addAll(questionMapper.questionDtoListToQuestionSet(questionnaire, rqDto));
        }
        
        repository.flush();
        return ResponseDto.successful(mapper.entityToDto(questionnaire));
    }

    @Override
    public ResponseDto delete(Long id) {
        repository.findById(id).orElseThrow(() -> new QuestionnaireException(MSG_NOT_FOUND));
        repository.deleteById(id);
        return ResponseDto.successful();
    }
}
