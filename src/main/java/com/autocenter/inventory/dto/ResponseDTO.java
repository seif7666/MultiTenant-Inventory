package com.autocenter.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO {
    private Integer statusCd;
    private String  statusDesc;
}
