package com.example.projekt_koncowy.controller;

import com.example.projekt_koncowy.dto.TypeOfCuisineDto;
import com.example.projekt_koncowy.service.TypeOfCuisineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type_of_cuisines")
@CrossOrigin(origins = {"http://localhost:4200"})
public class TypeOfCuisineController {

    @Autowired
    private TypeOfCuisineService typeOfCuisineService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TypeOfCuisineDto>> findAll() {
        return new ResponseEntity<>(typeOfCuisineService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TypeOfCuisineDto> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(typeOfCuisineService.findById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> createTypeOfCuisine(@RequestBody TypeOfCuisineDto typeOfCuisineDto) {
        return new ResponseEntity<>(typeOfCuisineService.createTypeOfCuisine(typeOfCuisineDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        typeOfCuisineService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TypeOfCuisineDto> update(@PathVariable("id") Integer id, @RequestBody TypeOfCuisineDto typeOfCuisineDto) {
        typeOfCuisineDto.setId(id);
        return new ResponseEntity<>(typeOfCuisineService.update(typeOfCuisineDto), HttpStatus.OK);
    }
}
