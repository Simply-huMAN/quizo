package com.quizo.app.dao.repository;

import com.quizo.app.dao.model.Question;
import com.quizo.app.dao.model.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {

    // Save Question and Solution
    @Query("INSERT INTO Question (id, text, options) VALUES (:#{#question.id}, :#{#question.text}, :#{#question.options})")
    void save(Question question, Solution solution);

    Question findByFormId(UUID formId);

    List<Question> findAllByFormId(UUID formId);
}
