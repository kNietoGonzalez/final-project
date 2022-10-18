package com.example.projekt_koncowy.repository;


import com.example.projekt_koncowy.exceptions.NotFoundException;
import com.example.projekt_koncowy.model.Ingredient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class IngredientRepositoryImpl {

    public static final String NOT_EXIST = "Ingredient %d not exist";

    private final IIngredientRepository ingredientRepository;

    public Integer createIngredient(Ingredient ingredient) {
        Ingredient save = ingredientRepository.save(ingredient);
        return save.getId();
    }

    @Transactional
    public Ingredient findById(Integer id) {
        Optional<Ingredient> saved = ingredientRepository.findById(id);
        return saved.orElseThrow(()-> new NotFoundException(String.format(NOT_EXIST, id)));
    }

    @Transactional
    public List<Ingredient> findAll() {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        if (CollectionUtils.isEmpty(ingredients)){
            throw new NotFoundException("Ingredient not found");
        }
        return ingredients;
    }

    public void delete(Integer id) {
        try {
            ingredientRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            String error = String.format(NOT_EXIST, id);
            log.error(error);
            throw new NotFoundException(error);
        }
    }

    @Transactional
    public Ingredient update(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }
}
