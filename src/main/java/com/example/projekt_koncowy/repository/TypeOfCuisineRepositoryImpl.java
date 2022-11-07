package com.example.projekt_koncowy.repository;

import com.example.projekt_koncowy.exceptions.NotFoundException;
import com.example.projekt_koncowy.model.TypeOfCuisine;
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
public class TypeOfCuisineRepositoryImpl {

    public static final String NOT_EXIST = "Type of dish %d not exist";
    public static final String NOT_EXIST_NAME = "Type of dish %s not exist";
    private final ITypeOfCuisineRepository typeOfCuisineRepository;

    public Integer createTypeOfCuisine(TypeOfCuisine typeOfCuisine) {
        TypeOfCuisine save = typeOfCuisineRepository.save(typeOfCuisine);
        return save.getId();
    }

    public TypeOfCuisine findById(Integer id) {
        Optional<TypeOfCuisine> saved = typeOfCuisineRepository.findById(id);
        return saved.orElseThrow(()-> new NotFoundException(String.format(NOT_EXIST, id)));
    }

    public TypeOfCuisine findByName(String name) {
        Optional<TypeOfCuisine> saved = typeOfCuisineRepository.findByName(name);
        return saved.orElseThrow(()-> new NotFoundException(String.format(NOT_EXIST_NAME, name)));
    }

    public List<TypeOfCuisine> findAll() {
        List<TypeOfCuisine> typeOfCuisine = typeOfCuisineRepository.findAll();
        if (CollectionUtils.isEmpty(typeOfCuisine)){
            throw new NotFoundException("Type of cuisine not found");
        }
        return typeOfCuisine;

    }

    public void delete(Integer id) {
        try {
            typeOfCuisineRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            String error = String.format(NOT_EXIST, id);
            log.error(error);
            throw new NotFoundException(error);
        }
    }

    public TypeOfCuisine update(TypeOfCuisine typeOfCuisine) {
        return typeOfCuisineRepository.save(typeOfCuisine);
    }


}
