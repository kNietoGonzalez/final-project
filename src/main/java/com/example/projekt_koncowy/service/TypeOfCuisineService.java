package com.example.projekt_koncowy.service;

import com.example.projekt_koncowy.dto.TypeOfCuisineDto;
import com.example.projekt_koncowy.model.TypeOfCuisine;
import com.example.projekt_koncowy.repository.TypeOfCuisineRepositoryImpl;
import com.example.projekt_koncowy.validation.ICreateRequestValidation;
import com.example.projekt_koncowy.validation.IUpdateRequestValidation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TypeOfCuisineService extends CommonService {

    private final TypeOfCuisineRepositoryImpl typeOfCuisineRepository;

    @Getter
    private final Validator validator;

    public Integer createTypeOfCuisine (TypeOfCuisineDto typeOfCuisineDto) {
        validate(typeOfCuisineDto, ICreateRequestValidation.class);
        TypeOfCuisine typeOfCuisine = new TypeOfCuisine();
        typeOfCuisine.setName(typeOfCuisineDto.getName());
        return typeOfCuisineRepository.createTypeOfCuisine(typeOfCuisine);
    }
    public TypeOfCuisineDto findById(Integer id) {
        TypeOfCuisine typeOfCuisine = typeOfCuisineRepository.findById(id);
        TypeOfCuisineDto typeOfCuisineDto = new TypeOfCuisineDto();
        typeOfCuisineDto.setName(typeOfCuisine.getName());
        typeOfCuisineDto.setId(typeOfCuisine.getId());

        return typeOfCuisineDto;
    }

    public List<TypeOfCuisineDto> findAll() {
        return typeOfCuisineRepository.findAll()
                .stream()
                .map(typeOfCuisine -> new TypeOfCuisineDto(typeOfCuisine.getId(), typeOfCuisine.getName()))
                .collect(Collectors.toList());
    }

    public void delete(Integer id) {
        typeOfCuisineRepository.delete(id);
    }

    public TypeOfCuisineDto update(TypeOfCuisineDto typeOfCuisineDto) {
        validate(typeOfCuisineDto, IUpdateRequestValidation.class);
        TypeOfCuisine saved = typeOfCuisineRepository.findById(typeOfCuisineDto.getId());
        saved.setName(typeOfCuisineDto.getName());
        TypeOfCuisine updated = typeOfCuisineRepository.update(saved);
        return new TypeOfCuisineDto(updated.getId(), updated.getName());
    }
}
