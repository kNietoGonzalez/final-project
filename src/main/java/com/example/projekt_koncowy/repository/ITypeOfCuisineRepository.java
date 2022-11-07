package com.example.projekt_koncowy.repository;

import com.example.projekt_koncowy.model.TypeOfCuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITypeOfCuisineRepository extends JpaRepository<TypeOfCuisine, Integer> {

   Optional<TypeOfCuisine> findByName (String name);
}
