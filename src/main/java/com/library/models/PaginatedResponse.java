package com.library.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PaginatedResponse<T> {
    private long totalCount;
    private long filteredCount;
    private long totalPages;
    private List<T> content;

}
