package com.example.projekt_koncowy.controller;

import com.example.projekt_koncowy.dto.UserDto;
import com.example.projekt_koncowy.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> findAll() {
        return userRepositoryImpl.findAll();
    }

    @GetMapping (value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto findById(@PathVariable("id") Integer id){
        return userRepositoryImpl.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Integer createUser(@RequestBody UserDto userDto){
        return userRepositoryImpl.createUser(userDto);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Integer id){
        userRepositoryImpl.delete(id);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto update(@PathVariable("id") Integer id, @RequestBody UserDto userDto){
        userDto.setId(id);
        return userRepositoryImpl.update(userDto);
    }
}
