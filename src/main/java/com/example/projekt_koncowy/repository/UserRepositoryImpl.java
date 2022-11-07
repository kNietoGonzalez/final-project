package com.example.projekt_koncowy.repository;

import com.example.projekt_koncowy.exceptions.NotFoundException;
import com.example.projekt_koncowy.model.User;
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
public class UserRepositoryImpl {

    public static final String NOT_EXIST = "User %d not exist";
    private final IUserRepository userRepository;

    public Integer createUser(User user) {
        User save = userRepository.save(user);
        return save.getId();
    }


    public User findById(Integer id) {
        Optional<User> saved = userRepository.findById(id);
        return saved.orElseThrow(() -> new NotFoundException(String.format(NOT_EXIST, id)));

    }


    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        if (CollectionUtils.isEmpty(users)){
            throw new NotFoundException("Users not found");
        }
        return users;
    }

    public void delete(Integer id) {
        try{
            userRepository.deleteById(id);
        } catch(EmptyResultDataAccessException e){
            String error = String.format(NOT_EXIST, id);
            log.error(error);
            throw new NotFoundException(error);
        }
    }


    public User update(User user) {
      return userRepository.save(user);
    }
}
