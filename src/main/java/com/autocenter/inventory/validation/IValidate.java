package com.autocenter.inventory.validation;

public interface IValidate<Req> {

    void validate(Req request) throws RuntimeException;
}
