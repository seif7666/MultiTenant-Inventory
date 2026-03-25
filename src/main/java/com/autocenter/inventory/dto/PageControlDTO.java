package com.autocenter.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageControlDTO {
    private Integer pageSize;
    private Integer pageNumber;
    private String sort;
}
