package com.quizo.app.dao.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Entity
@Table
@Data
public class Solution {

    @Id
    @Schema(description = "Question to which the solution belongs")
    private UUID questionId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Schema(description = "Correct option ids for the question")
    private List<String> correctOptionIds;

    @Schema(description = "Explanation for the solution")
    private String explanation;
}
