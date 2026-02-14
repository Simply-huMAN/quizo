package com.quizo.app.dao.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

import java.util.List;
import java.util.UUID;

@Entity
@Table
@IdClass(SubmissionQuestionId.class)
public class SubmissionQuestion {

    @Id
    @Schema(description = "Submission to which the question belongs")
    private UUID submissionId;

    @Id
    @Schema(description = "Question to which the submission question belongs")
    private UUID questionId;

    @Schema(description = "Options selected by the user for the question")
    private List<String> OptionId;

    @Schema(description = "Indicate whether the answer for the question is correct")
    private Boolean isCorrect;

    @Schema(description = "Score obtained for the question")
    private Integer scoreObtained;
}
