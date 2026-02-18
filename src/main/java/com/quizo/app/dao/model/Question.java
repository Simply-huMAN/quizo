package com.quizo.app.dao.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID questionId;

    @Schema(description = "Form to which the question belongs")
    private UUID formId;

    @Schema(description = "Text of the question")
    private String text;

    @Schema(description = "Score assigned to the question")
    private String score;

    @Schema(description = "Negative score assigned to the question")
    private String negativeScore;

    @Schema(description = "Indicate whether the question is required")
    private Boolean isRequired;

    @JdbcTypeCode(SqlTypes.JSON)
    @Schema(description = "Options for the question")
    private List<Option> options;

    @Column(updatable = false)
    @Schema(description = "Instant when the question is created")
    private Instant createdAt = Instant.now();
}


