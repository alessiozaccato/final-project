package com.java.finalproject.final_project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.finalproject.final_project.model.Recipe;
import com.java.finalproject.final_project.service.RecipeService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@CrossOrigin
@RequestMapping("/api/recipes")

public class RecipeRestController {

    @Autowired
    private RecipeService RecipeService;

    @GetMapping
    public List<Recipe> index() {
        List<Recipe> recipes = RecipeService.findAll();
        return recipes;
    }

    @GetMapping("/sortByName")
    public List<Recipe> indexByName() {
        List<Recipe> recipes = RecipeService.findAllByName();
        return recipes;
    }

    @GetMapping("/search")
public ResponseEntity<List<Recipe>> searchByName(@RequestParam String name) {
    List<Recipe> recipes = RecipeService.findByName(name);
    if (recipes.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(recipes, HttpStatus.OK);
}
    

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> show(@PathVariable Integer id) {
        Optional<Recipe> recipesAttempt = RecipeService.findById(id);
        if (recipesAttempt.isEmpty()) {
            return new ResponseEntity<Recipe>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Recipe>(recipesAttempt.get(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Recipe> store(@Valid @RequestBody Recipe recipe) {
        return new ResponseEntity<Recipe>(RecipeService.create(recipe), HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Recipe> patch(@PathVariable Integer id, @Valid @RequestBody Recipe recipe) {
        if (!RecipeService.existsById(id)) {
            return new ResponseEntity<Recipe>(HttpStatus.NOT_FOUND);
        }
        recipe.setId(id);
        return new ResponseEntity<Recipe>(RecipeService.update(recipe), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Recipe> delete(@PathVariable Integer id) {
        if (!RecipeService.existsById(id)) {
            return new ResponseEntity<Recipe>(HttpStatus.NOT_FOUND);

        }
        RecipeService.deleteById(id);
        return new ResponseEntity<Recipe>(HttpStatus.OK);

    }

}
