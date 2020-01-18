package com.questionnaire.repository;

import com.questionnaire.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionRepository extends JpaRepository<Question, Long> {

}
