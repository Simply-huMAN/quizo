package com.quizo.app.dao.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
@Table
@IdClass(SubmissionQuestionId.class)
@Data
public class SubmissionQuestion {

    @Id
    @Schema(description = "Submission to which the question belongs")
    private UUID submissionId;

    @Id
    @Schema(description = "Question to which the submission question belongs")
    private UUID questionId;

    @Column(updatable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    @Schema(description = "Options selected by the user for the question")
    private List<String> selectedOptionId;

    @Schema(description = "Indicate whether the answer for the question is correct")
    private Boolean isCorrect;

    @Schema(description = "Score obtained for the question")
    private Integer scoreObtained;
}
