package com.quizo.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class SubmissionQuestionDTO {

    private UUID submissionId;
    private UUID questionId;
    private List<String> selectedOptionId;
}
