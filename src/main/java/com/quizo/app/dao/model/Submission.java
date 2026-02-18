package com.quizo.app.dao.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table
@Data
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID submissionId;

    @Schema(description = "Form to which the submission belongs")
    private UUID formId;

    @Schema(description = "Score obtained for the submission")
    private Integer totalScore;

    @Schema(description = "Remarks for the submission")
    private String remarks;

    @Schema(description = "User who made the submission")
    private UUID userId;

    //TASK: Handle zombie submission i.e. user has started but not submitted the form i.e. closed the form without submitting
    @Schema(description = "Instant when the submission is started")
    private Instant startedAt;

    /**
     *  Auto submit if the submission is not submitted within the duration of the form
     *  Auto submit if user has started but not submitted the form i.e. closed the form without submitting
     */
    @Schema(description = "Instant when the submission is submitted")
    private Instant submittedAt = Instant.now();

    @Schema(description = "Status of the score evaluation")
    private Enum<Status> evaluationStatus = Status.PENDING;

    @Schema(description = "Instant when the score is evaluated")
    private Instant scoreEvaluatedAt;
}
