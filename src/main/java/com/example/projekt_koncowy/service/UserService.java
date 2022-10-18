package com.example.projekt_koncowy.service;

import com.example.projekt_koncowy.dto.RecipeDto;
import com.example.projekt_koncowy.dto.UserDto;
import com.example.projekt_koncowy.exceptions.BadRequestException;
import com.example.projekt_koncowy.model.Ingredient;
import com.example.projekt_koncowy.model.Recipe;
import com.example.projekt_koncowy.model.User;
import com.example.projekt_koncowy.repository.UserRepositoryImpl;
import com.example.projekt_koncowy.validation.ICreateRequestValidation;
import com.example.projekt_koncowy.validation.IUpdateRequestValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepositoryImpl userRepository;
    private final Validator validator;

    public Integer createUser(UserDto userDto) {
        validate(userDto, ICreateRequestValidation.class);
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return userRepository.createUser(user);
    }

    private <T> void validate(final UserDto req, final Class<T> clazz) {
        if (req == null) {
            throw new BadRequestException("Request", "Mandatory");
        }
        final Set<ConstraintViolation<UserDto>> violations = validator.validate(req, clazz);
        if (violations != null && !violations.isEmpty()) {
            throw new BadRequestException(violations);
        }
    }

    public UserDto findById(Integer id) {
        User user = userRepository.findById(id);
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRecipeList(mapRecipes(user.getRecipeList()));
        return userDto;
    }

    private List<RecipeDto> mapRecipes(List<Recipe> recipes) {
        return recipes
                .stream()
                .map(recipe -> new RecipeDto(recipe.getId(), recipe.getName(),
                        recipe.getDescription(), recipe.getRating(), recipe.getEstimation(),
                        recipe.getUser().getId(), recipe.getTypeOfDish(), recipe.getTypeOfCuisine(),
                        recipe.getIngredients().stream().map(Ingredient::getId).collect(Collectors.toSet())))
                .collect(Collectors.toList());
    }

    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail(),
                        user.getPassword(), mapRecipes(user.getRecipeList())))
                .collect(Collectors.toList());
    }

    public void delete(Integer id) {
        userRepository.delete(id);
    }

    public UserDto update(UserDto userDto) {

        validate(userDto, IUpdateRequestValidation.class);
        User saved = userRepository.findById(userDto.getId());
        saved.setName(userDto.getName());
        saved.setEmail(userDto.getEmail());
        saved.setPassword(userDto.getEmail());
        User updated = userRepository.update(saved);
        return new UserDto(updated.getId(), updated.getName(), updated.getEmail(),
                updated.getPassword(), mapRecipes(updated.getRecipeList()));
    }
}
