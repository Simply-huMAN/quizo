package com.quizo.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Pagination {
    private int page;
    private int pageSize;
    private int totalPages;
    private long totalItems;

}
