package com.java.finalproject.final_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.finalproject.final_project.model.Ingredient;

public interface IngredientRepository extends JpaRepository <Ingredient, Integer> {

}
