package com.company.online_video.dto.pagination;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationDTO {
    private int pageSize;
    private int pageNumber;
    private int totalPages;
    private long totalElements;
    private long numberOfElements;

    private boolean last;
    private boolean first;
    private boolean empty;
}
