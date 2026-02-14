package com.quizo.app.dto;

import com.quizo.app.dao.model.Option;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class QuestionDTO {

    private String text;
    private String score;
    private String negativeScore;
    private Boolean isRequired;
    private List<Option> options;

    private List<String> correctOptionIds;
    private String explanation;
}
