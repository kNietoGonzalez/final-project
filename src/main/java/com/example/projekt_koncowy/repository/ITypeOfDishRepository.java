package com.example.projekt_koncowy.repository;

import com.example.projekt_koncowy.model.TypeOfDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITypeOfDishRepository extends JpaRepository<TypeOfDish, Integer> {

    Optional<TypeOfDish> findByName (String name);
}
