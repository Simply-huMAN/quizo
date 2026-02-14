package com.quizo.app.dao.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;
import java.util.UUID;

@Entity
@Table
public class Solution {

    @Id
    @Schema(description = "Question to which the solution belongs")
    private UUID questionId;

    @Schema(description = "Correct option ids for the question")
    private List<String> optionIds;

    @Schema(description = "Explanation for the solution")
    private String explanation;
}
