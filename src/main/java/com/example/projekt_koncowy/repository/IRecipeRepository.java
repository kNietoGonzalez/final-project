package com.example.projekt_koncowy.repository;

import com.example.projekt_koncowy.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRecipeRepository extends JpaRepository<Recipe, Integer> {
}
