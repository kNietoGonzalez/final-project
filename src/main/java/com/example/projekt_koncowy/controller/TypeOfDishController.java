package com.example.projekt_koncowy.controller;

import com.example.projekt_koncowy.dto.TypeOfDishDto;
import com.example.projekt_koncowy.service.TypeOfDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type_of_dishes")
@CrossOrigin(origins = {"http://localhost:4200"})
public class TypeOfDishController {

    @Autowired
    private TypeOfDishService typeOfDishService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TypeOfDishDto>> findAll() {
        return new ResponseEntity<>(typeOfDishService.findAll(), HttpStatus.OK);

    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TypeOfDishDto> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(typeOfDishService.findById(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> createTypeOfDish(@RequestBody TypeOfDishDto typeOfDishDto) {
        return new ResponseEntity<>(typeOfDishService.createTypeOfDish(typeOfDishDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        typeOfDishService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TypeOfDishDto> update(@PathVariable("id") Integer id, @RequestBody TypeOfDishDto typeOfDishDto) {
        typeOfDishDto.setId(id);
        return new ResponseEntity<>(typeOfDishService.update(typeOfDishDto), HttpStatus.OK);
    }
}
