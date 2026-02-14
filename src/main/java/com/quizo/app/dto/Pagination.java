package com.quizo.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pagination {
    private int currentPage;
    private int pageSize;
    private int totalPages;
    private long totalElements;

}
