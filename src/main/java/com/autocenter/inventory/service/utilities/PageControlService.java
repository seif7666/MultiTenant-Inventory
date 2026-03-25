package com.autocenter.inventory.service.utilities;

import com.autocenter.inventory.dto.PageControlDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PageControlService {

    public Pageable getPageControl(PageControlDTO pageControlDTO) {
        boolean pagingRequired= pageControlDTO.getPageNumber() != null && pageControlDTO.getPageSize() != null;
        boolean sortRequired= pageControlDTO.getSort() != null && !pageControlDTO.getSort().isBlank();
        if(pagingRequired && sortRequired)
            return PageRequest.of(pageControlDTO.getPageNumber(), pageControlDTO.getPageSize(), Sort.by(pageControlDTO.getSort()));
        if(pagingRequired)
            return PageRequest.of(pageControlDTO.getPageNumber(), pageControlDTO.getPageSize());
        if(sortRequired)
            return Pageable.unpaged(Sort.by(pageControlDTO.getSort()));
        return Pageable.unpaged();
    }
}
