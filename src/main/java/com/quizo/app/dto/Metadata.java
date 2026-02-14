package com.quizo.app.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class Metadata {
    private String requestId;
    private Instant timestamp;
    private Pagination pagination;
    private String message;
}
