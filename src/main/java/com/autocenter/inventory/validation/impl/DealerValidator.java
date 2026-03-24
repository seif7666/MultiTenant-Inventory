package com.autocenter.inventory.validation.impl;

import com.autocenter.inventory.dto.DealerDTO;
import com.autocenter.inventory.validation.IValidate;
import org.springframework.stereotype.Component;

@Component
public class DealerValidator implements IValidate<DealerDTO> {
    @Override
    public void validate(DealerDTO request) throws RuntimeException {

    }
}
