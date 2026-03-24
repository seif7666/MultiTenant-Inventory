package com.autocenter.inventory.dto;

import com.autocenter.inventory.enums.SubscriptionType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Data
public class DealerDTO {
    private String tenantId;
    @NotNull(message = "Name Must not be Empty!")
    @NotEmpty(message = "Name Must not be Empty!")
    private String name;
    @Email
    private String email;
    private SubscriptionType subscriptionType;
}
