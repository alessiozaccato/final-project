package com.java.finalproject.final_project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.finalproject.final_project.model.Recipe;

public interface RecipeRepository extends JpaRepository <Recipe,Integer> {

    public List<Recipe> findByNameContaining(String name);

}
