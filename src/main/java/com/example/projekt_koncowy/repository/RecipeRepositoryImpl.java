package com.example.projekt_koncowy.repository;

import com.example.projekt_koncowy.dto.RecipeFilterDto;
import com.example.projekt_koncowy.exceptions.NotFoundException;
import com.example.projekt_koncowy.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class RecipeRepositoryImpl {


    public static final String NOT_EXIST = "Recipe %d not found";
    private final IRecipeRepository recipeRepository;

    private final EntityManager em;

    public List<Recipe> recipeFilter(RecipeFilterDto filter) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Recipe> cq = cb.createQuery(Recipe.class);
        final Root<Recipe> root = cq.from(Recipe.class);
        final List<Predicate> predicates = new ArrayList<>();
        if (filter.getName() != null && !filter.getName().isEmpty()) {
            predicates.add(cb.equal(root.get("name"), filter.getName()));
        }
        if (filter.getUser() != null) {
            Join<Recipe, User> join = root.join("user", JoinType.LEFT);
            predicates.add(cb.equal(join.get("id"), filter.getUser()));
        }
        if (filter.getTypeOfDish() != null) {
            Join<Recipe, TypeOfDish> join = root.join("typeOfDish", JoinType.LEFT);
            predicates.add(cb.equal(join.get("id"), filter.getTypeOfDish()));
        }
        if (filter.getTypeOfCuisine() != null) {
            Join<Recipe, TypeOfCuisine> join = root.join("typeOfCuisine", JoinType.LEFT);
            predicates.add(cb.equal(join.get("id"), filter.getTypeOfCuisine()));
        }
        if (filter.getIngredients() != null) {
            CriteriaBuilder.In<Integer> corePredicateIn = cb.in(root.get("ingredients").get("id"));
            filter.getIngredients().forEach(corePredicateIn::value); // Setting opts into IN
            Predicate p1CorePredicate = cb.and(
                    cb.in(root.get("ingredients").in(corePredicateIn)));
            predicates.add(p1CorePredicate);
        }
        cq.where(predicates.toArray(new Predicate[]{}));
        List<Recipe> result = em.createQuery(cq).getResultList();
        return result;


    }

    public Integer createRecipe(Recipe recipe) {
        Recipe save = recipeRepository.save(recipe);
        return save.getId();
    }


    public Recipe findById(Integer id) {
        Optional<Recipe> saved = recipeRepository.findById(id);
        return saved.orElseThrow(() -> new NotFoundException(String.format(NOT_EXIST, id)));
    }

    public List<Recipe> findByUser(Integer userId) {
        List<Recipe> byUserId = recipeRepository.findByUserId(userId);
        if (CollectionUtils.isEmpty(byUserId)) {
            throw new NotFoundException("Recipe not found");
        }
        return byUserId;
    }

    public List<Recipe> findByTypeOfDish(Integer typeOfDishId) {
        List<Recipe> byTypeOfDishId = recipeRepository.findByTypeOfDishId(typeOfDishId);
        if (CollectionUtils.isEmpty(byTypeOfDishId)) {
            throw new NotFoundException("Type of Dish not found");
        }
        return byTypeOfDishId;
    }

    public List<Recipe> findByTypeOfCuisine(Integer typeOfCuisineId) {
        List<Recipe> byTypeOfCuisineId = recipeRepository.findByTypeOfCuisineId(typeOfCuisineId);
        if (CollectionUtils.isEmpty(byTypeOfCuisineId)) {
            throw new NotFoundException("Type of cuisine not found");
        }
        return byTypeOfCuisineId;
    }

    public List<Recipe> findAll() {
        List<Recipe> recipes = recipeRepository.findAll();
        if (CollectionUtils.isEmpty(recipes)) {
            throw new NotFoundException("Recipe not found");
        }
        return recipes;
    }

    public void delete(Integer id) {
        try {
            recipeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            String error = String.format(NOT_EXIST, id);
            log.error(error);
            throw new NotFoundException(error);
        }
    }


    public Recipe update(Recipe recipe) {
        return recipeRepository.save(recipe);
    }
}
