package com.quizo.app.dto;

import java.time.Instant;

public class Metadata {
    private String requestId;
    private Instant submittedAt;
    private Instant completedAt;
    private Integer processingTimeMs;
    private Pagination pagination;
}
