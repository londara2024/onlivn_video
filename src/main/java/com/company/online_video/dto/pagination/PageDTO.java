package com.company.online_video.dto.pagination;

import com.company.online_video.dto.response.VideoResponseDTO;
import com.company.online_video.entity.Video;
import com.company.online_video.mapper.VideoMappers;
import com.company.online_video.service.UsersService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageDTO {
    private List<?> list;
    private PaginationDTO pagination;

    public PageDTO(Page<?> page) {
        this.list = page.getContent();
        this.pagination = PaginationDTO.builder()
                .empty(page.isEmpty())
                .first(page.isFirst())
                .last(page.isLast())
                .pageSize(page.getPageable().getPageSize())
                .pageNumber(page.getPageable().getPageNumber() + 1)
                .totalPages(page.getTotalPages())
                .numberOfElements(page.getNumberOfElements())
                .totalElements(page.getTotalElements())
                .build();
    }
}
