package com.quizo.app.dao.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table
public class Question {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private UUID questionId;

    @Schema(description = "Form to which the question belongs")
    private UUID formId;

    @Schema(description = "Text of the question")
    private String questionText;

    @Schema(description = "Score assigned to the question")
    private String questionScore;

    @Schema(description = "Negative score assigned to the question")
    private String negativeScore;

    @Schema(description = "Indicate whether the question is required")
    private Boolean isRequired;

    @Column(name = "\"order\"")
    @Schema(description = "Indicate order for the options to be displayed")
    private Integer order;

    @Embedded
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "options")
    @Schema(description = "Options for the question")
    private List<Option> options;

    @Schema(description = "Instant when the question is created")
    private Instant createdAt;
}
