package com.quizo.app.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * Composite primary key class for SubmissionQuestion entity.
 * Implements Serializable and provides equals/hashCode methods as required by JPA.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionQuestionId implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID submissionId;
    private UUID questionId;
}
