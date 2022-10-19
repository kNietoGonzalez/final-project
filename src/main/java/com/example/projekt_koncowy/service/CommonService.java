package com.example.projekt_koncowy.service;

import com.example.projekt_koncowy.exceptions.BadRequestException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public abstract class CommonService {

    protected abstract Validator getValidator();

    protected  <T, S> void validate(final S req, final Class<T> clazz) {
        if (req == null) {
            throw new BadRequestException("Request", "Mandatory");
        }
        final Set<ConstraintViolation<S>> violations = getValidator().validate(req, clazz);
        if (violations != null && !violations.isEmpty()) {
            throw new BadRequestException(violations);
        }
    }
}
