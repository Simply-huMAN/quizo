package com.quizo.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class SubmissionDTO {

    private UUID formId;
    private UUID userId;
    private List<SubmissionQuestionDTO> answers;
}
