package com.example.projekt_koncowy.repository;

import com.example.projekt_koncowy.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRecipeRepository extends JpaRepository<Recipe, Integer> {
    List<Recipe> findByUserId(Integer userId);

    List<Recipe> findByTypeOfDishId (Integer typeOfDishId);

    List<Recipe> findByTypeOfCuisineId (Integer typeOfCuisineId);

}
