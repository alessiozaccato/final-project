package com.java.finalproject.final_project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.java.finalproject.final_project.model.Recipe;
import com.java.finalproject.final_project.repositories.RecipeRepository;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public List<Recipe> findAll(){
        return recipeRepository.findAll();
    }

    public List<Recipe> findAllByName(){
        return recipeRepository.findAll(Sort.by("name"));
    }

     public List<Recipe> findByName(String name){
        return recipeRepository.findByNameContaining(name);
    }

    public Optional<Recipe> findById(Integer id){
        return recipeRepository.findById(id);
    }

    public Recipe getById(Integer id){
        Optional<Recipe> recipeAttempt = recipeRepository.findById(id);

        return recipeAttempt.get();
    }

    public Recipe create(Recipe recipe){
        return recipeRepository.save(recipe);
    }

    public Recipe update(Recipe recipe){
        return recipeRepository.save(recipe);
    }

    public void delete(Recipe recipe){
        recipeRepository.delete(recipe);
    }

    public void deleteById(Integer id){
        Recipe recipe = recipeRepository.findById(id).get();

        recipeRepository.delete(recipe);
    }

    public Boolean existsById(Integer id){
        return recipeRepository.existsById(id);
    }

    public Boolean exists(Recipe recipe){
        return existsById(recipe.getId());
    }

}
