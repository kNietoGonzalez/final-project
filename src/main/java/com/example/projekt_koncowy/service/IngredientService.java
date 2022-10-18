package com.example.projekt_koncowy.service;

import com.example.projekt_koncowy.dto.IngredientDto;
import com.example.projekt_koncowy.exceptions.BadRequestException;
import com.example.projekt_koncowy.model.Ingredient;
import com.example.projekt_koncowy.repository.IngredientRepositoryImpl;
import com.example.projekt_koncowy.validation.ICreateRequestValidation;
import com.example.projekt_koncowy.validation.IUpdateRequestValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class IngredientService {

    private final IngredientRepositoryImpl ingredientRepository;

    private final Validator validator;

    public Integer createIngredient(IngredientDto ingredientDto) {
        validate(ingredientDto, ICreateRequestValidation.class);
        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientDto.getName());
        ingredient.setQuantity(ingredientDto.getQuantity());
        return ingredientRepository.createIngredient(ingredient);
    }

    private <T> void validate(final IngredientDto req, final Class<T> clazz) {
        if (req == null) {
            throw new BadRequestException("Request", "Mandatory");
        }
        final Set<ConstraintViolation<IngredientDto>> violations = validator.validate(req, clazz);
        if (violations != null && !violations.isEmpty()) {
            throw new BadRequestException(violations);
        }
    }

    public IngredientDto findById(Integer id) {
        Ingredient ingredient = ingredientRepository.findById(id);
        IngredientDto ingredientDto = new IngredientDto();
        ingredientDto.setName(ingredient.getName());
        ingredientDto.setId(ingredient.getId());
        ingredientDto.setQuantity(ingredient.getQuantity());
        return ingredientDto;
    }

    public List<IngredientDto> findAll() {
        return ingredientRepository.findAll()
                .stream()
                .map(ingredient -> new IngredientDto(ingredient.getId(), ingredient.getName(), ingredient.getQuantity()))
                .collect(Collectors.toList());
    }

    public void delete(Integer id) {
        ingredientRepository.delete(id);
    }

    public IngredientDto update(IngredientDto ingredientDto) {
        validate(ingredientDto, IUpdateRequestValidation.class);
        Ingredient saved = ingredientRepository.findById(ingredientDto.getId());
        saved.setName(ingredientDto.getName());
        saved.setQuantity(ingredientDto.getQuantity());
        Ingredient updated = ingredientRepository.update(saved);
        return new IngredientDto(updated.getId(), updated.getName(), updated.getQuantity());
    }
}
