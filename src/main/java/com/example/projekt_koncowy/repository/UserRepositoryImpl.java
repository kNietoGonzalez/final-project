package com.example.projekt_koncowy.repository;

import com.example.projekt_koncowy.dto.RecipeDto;
import com.example.projekt_koncowy.dto.UserDto;
import com.example.projekt_koncowy.model.Recipe;
import com.example.projekt_koncowy.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl {

    private final UserRepository userRepository;

    public Integer createUser(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        User save = userRepository.save(user);
        return save.getId();
    }

    public UserDto findById (Integer id){
        Optional<User> saved = userRepository.findById(id);
        if (saved.isPresent()){
            User user = saved.get();
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());
            userDto.setPassword(user.getPassword());
            userDto.setRecipeList(mapRecipes(user.getRecipeList()));
            return userDto;
        }
        return null;

    }

    public List <UserDto> findAll () {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getEmail(),
                        user.getPassword(), mapRecipes(user.getRecipeList())))
                .collect(Collectors.toList());
    }

    private List<RecipeDto> mapRecipes(List<Recipe> recipes) {
        return recipes
                .stream()
                .map(recipe -> new RecipeDto(recipe.getId(), recipe.getName(),
                recipe.getDescription(), recipe.getRating(), recipe.isFavourite(), recipe.getEstimation(),
                recipe.getUserId(), recipe.getTypeOfDish(), recipe.getTypeOfCuisine() ))
                .collect(Collectors.toList());
    }

    public void delete (Integer id) {
        userRepository.deleteById(id);
    }

    public UserDto update (UserDto userDto){
        Optional<User> saved = userRepository.findById(userDto.getId());
        if (saved.isPresent()){
            User user = saved.get();
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getEmail());
            return userDto;
        }
        return null;
    }
}
