package com.quizo.app.dao.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table
public class Form {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private UUID formId;

    @Schema(description = "Title of the form")
    private String formTitle;

    @Schema(description = "Description of the form")
    @Column(length = 200)
    private String formDescription;

    @Schema(description = "Author of the form")
    private UUID authorId;

    @Schema(description = "Duration (in Minutes) for the form")
    private Integer duration;

    @Schema(description = "Indicate whether the form is a draft")
    private Boolean isDraft;

    @Schema(description = "Indicate whether the form is active")
    private Boolean isActive;

    @Schema(description = "Instance when the form is created")
    private Instant createdAt;

    @Schema(description = "Instance when the form is last updated")
    private Instant updatedAt;
}
