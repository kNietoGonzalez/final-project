package com.example.projekt_koncowy.repository;


import com.example.projekt_koncowy.exceptions.NotFoundException;
import com.example.projekt_koncowy.model.TypeOfDish;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TypeOfDishRepositoryImpl {

    public static final String NOT_EXIST = "Type od dish %d not exist";

    public static final String NOT_EXIST_NAME = "Type of dish %s not exist";
    private final ITypeOfDishRepository typeOfDishRepository;

    public Integer createTypeOfDish(TypeOfDish typeOfDish) {
        TypeOfDish save = typeOfDishRepository.save(typeOfDish);
        return save.getId();
    }

    public TypeOfDish findById(Integer id) {
        Optional<TypeOfDish> saved = typeOfDishRepository.findById(id);
        return saved.orElseThrow(()-> new NotFoundException(String.format(NOT_EXIST, id)));
    }

    public TypeOfDish findByName (String name) {
        Optional<TypeOfDish> saved = typeOfDishRepository.findByName(name);
        return saved.orElseThrow(()-> new NotFoundException(String.format(NOT_EXIST_NAME, name)));
    }

    public List<TypeOfDish> findAll() {
        List<TypeOfDish> typeOfDish = typeOfDishRepository.findAll();
        if (CollectionUtils.isEmpty(typeOfDish)){
            throw new NotFoundException("Type of dish not found");
        }
        return typeOfDish;

    }

    public void delete(Integer id) {
        try {
            typeOfDishRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            String error = String.format(NOT_EXIST, id);
            log.error(error);
            throw new NotFoundException(error);
        }
    }

    public TypeOfDish update(TypeOfDish typeOfDish) {
        return typeOfDishRepository.save(typeOfDish);
    }


}
