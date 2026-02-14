package com.quizo.app.dao.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a single option choice for a question.
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Option {
    private String id;
    private String value;
}
