package com.example.projekt_koncowy.repository;

import com.example.projekt_koncowy.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IIngredientRepository extends JpaRepository<Ingredient, Integer> {
}
