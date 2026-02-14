package com.quizo.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class FormDTO {

    private String title;
    private String description;
    private UUID authorId;
    private Integer duration;
    private Boolean isDraft;
    private Boolean isActive;
    private List<QuestionDTO> questions;

}
