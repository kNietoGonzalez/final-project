package com.example.projekt_koncowy.exceptions;

import com.example.projekt_koncowy.dto.ViolationError;
import lombok.Getter;

import javax.validation.ConstraintViolation;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BadRequestException extends RuntimeException {

    @Getter
    private final List<ViolationError> violations;

    public <T> BadRequestException(Set<ConstraintViolation<T>> violations) {
        super();
        this.violations = violations
                .stream()
                .map(violation -> new ViolationError(violation.getPropertyPath().toString(), violation.getMessage()))
                .collect(Collectors.toList());
    }

    public BadRequestException(String field, String validation) {
        super();
        this.violations = Arrays.asList(new ViolationError(field, validation));
    }
}
