package com.quizo.app.dao.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table
@Data
@Builder
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID formId;

    @Schema(description = "Title of the form")
    private String title;

    @Schema(description = "Description of the form")
    @Column(length = 200)
    private String description;

    @Schema(description = "Author of the form")
    private UUID authorId;

    @Schema(description = "Duration (in Minutes) for the form")
    private Integer duration;

    @Schema(description = "Indicate whether the form is a draft")
    private Boolean isDraft = true;

    @Schema(description = "Indicate whether the form is active")
    private Boolean isActive;

    @Column(updatable = false)
    @Builder.Default
    @Schema(description = "Instance when the form is created")
    private Instant createdAt = Instant.now();

    @Schema(description = "Instance when the form is last updated")
    private Instant updatedAt;
}
