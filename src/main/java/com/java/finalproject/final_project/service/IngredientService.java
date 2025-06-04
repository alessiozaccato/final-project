package com.java.finalproject.final_project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.java.finalproject.final_project.model.Ingredient;
import com.java.finalproject.final_project.repositories.IngredientRepository;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> findAll(){
        return ingredientRepository.findAll();
    }

    public List<Ingredient> findAllByName(){
        return ingredientRepository.findAll(Sort.by("name"));
    }

    public Optional<Ingredient> findById(Integer id){
        return ingredientRepository.findById(id);
    }

    public Ingredient getById(Integer id){
        Optional<Ingredient> ingredientAttempt = ingredientRepository.findById(id);

        return ingredientAttempt.get();
    }

    public Ingredient create(Ingredient ingredient){
        return ingredientRepository.save(ingredient);
    }

    public Ingredient update(Ingredient ingredient){
        return ingredientRepository.save(ingredient);
    }

    public void delete(Ingredient ingredient){
        ingredientRepository.delete(ingredient);
    }

    public void deleteById(Integer id){
        Ingredient ingredient = ingredientRepository.findById(id).get();

        ingredientRepository.delete(ingredient);
    }

    public Boolean existsById(Integer id){
        return ingredientRepository.existsById(id);
    }

    public Boolean exists(Ingredient ingredient){
        return existsById(ingredient.getId());
    }
}
