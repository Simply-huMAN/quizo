package com.quizo.app.dao.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table
public class Submission {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private UUID submissionId;

    @Schema(description = "Form to which the submission belongs")
    private UUID formId;

    @Schema(description = "Score obtained for the submission")
    private Integer score;

    @Schema(description = "Remarks for the submission")
    private String remarks;

    @Schema(description = "User who made the submission")
    private UUID userId;

    @Schema(description = "Instant when the submission is started")
    private Instant startedAt;

    /**
     *  Auto submit if the submission is not submitted within the duration of the form
     *  Auto submit if user has started but not submitted the form i.e. closed the form without submitting
     */
    @Schema(description = "Instant when the submission is submitted")
    private Instant submittedAt;

    @Schema(description = "Status of the score evaluation")
    private Enum<Status> evaluationStatus;

    @Schema(description = "Instant when the score is evaluated")
    private Instant scoreEvaluatedAt;
}
