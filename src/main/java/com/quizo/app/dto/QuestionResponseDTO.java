package com.quizo.app.dto;

import com.quizo.app.dao.model.Option;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class QuestionResponseDTO {

    private UUID questionId;
    private String text;
    private String score;
    private String negativeScore;
    private Boolean isRequired;
    private List<Option> options;
}
