package com.example.projekt_koncowy.service;

import com.example.projekt_koncowy.dto.TypeOfDishDto;
import com.example.projekt_koncowy.model.TypeOfDish;
import com.example.projekt_koncowy.repository.TypeOfDishRepositoryImpl;
import com.example.projekt_koncowy.validation.ICreateRequestValidation;
import com.example.projekt_koncowy.validation.IUpdateRequestValidation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TypeOfDishService extends CommonService{

    private final TypeOfDishRepositoryImpl typeOfDishRepository;

    @Getter
    private final Validator validator;

    public Integer createTypeOfDish(TypeOfDishDto typeOfDishDto) {
        validate(typeOfDishDto, ICreateRequestValidation.class);
        TypeOfDish typeOfDish = new TypeOfDish();
        typeOfDish.setName(typeOfDishDto.getName());
        return typeOfDishRepository.createTypeOfDish(typeOfDish);
    }
    public TypeOfDishDto findById(Integer id) {
        TypeOfDish typeOfDish = typeOfDishRepository.findById(id);
        TypeOfDishDto typeOfDishDto = new TypeOfDishDto();
        typeOfDishDto.setName(typeOfDish.getName());
        typeOfDishDto.setId(typeOfDish.getId());

        return typeOfDishDto;
    }

    public List<TypeOfDishDto> findAll() {
        return typeOfDishRepository.findAll()
                .stream()
                .map(typeOfDish -> new TypeOfDishDto(typeOfDish.getId(), typeOfDish.getName()))
                .collect(Collectors.toList());
    }

    public void delete(Integer id) {
        typeOfDishRepository.delete(id);
    }

    public TypeOfDishDto update(TypeOfDishDto typeOfDishDto) {
        validate(typeOfDishDto, IUpdateRequestValidation.class);
        TypeOfDish saved = typeOfDishRepository.findById(typeOfDishDto.getId());
        saved.setName(typeOfDishDto.getName());
        TypeOfDish updated = typeOfDishRepository.update(saved);
        return new TypeOfDishDto(updated.getId(), updated.getName());
    }
}
