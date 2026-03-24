package com.autocenter.inventory.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class AggregateDTO {
    @JsonAlias("BASIC")
    String basic;
    @JsonAlias("PREMIUM")
    String premium;

}
