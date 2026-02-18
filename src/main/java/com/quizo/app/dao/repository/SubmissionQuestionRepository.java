package com.quizo.app.dao.repository;

import com.quizo.app.dao.model.SubmissionQuestion;
import com.quizo.app.dao.model.SubmissionQuestionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionQuestionRepository extends JpaRepository<SubmissionQuestion, SubmissionQuestionId> {
}
