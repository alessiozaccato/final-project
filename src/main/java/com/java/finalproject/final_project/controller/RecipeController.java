package com.java.finalproject.final_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.finalproject.final_project.model.Recipe;
import com.java.finalproject.final_project.repositories.IngredientRepository;
import com.java.finalproject.final_project.service.RecipeService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping
    public String index(Authentication authentication, Model model) {
        List<Recipe> recipes = recipeService.findAll();
        model.addAttribute("recipes", recipes);
        model.addAttribute("username", authentication.getName());
        return "recipes/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Recipe recipe = recipeService.getById(id);
        model.addAttribute("recipe", recipe);
        return "recipes/show";
    }

    @GetMapping("/search")
    public String searchByName (@RequestParam (name="name") String name, Authentication authentication, Model model) {
        List<Recipe> recipes = recipeService.findByName(name);
        model.addAttribute("recipes", recipes);
        model.addAttribute("username", authentication.getName());
        return "recipes/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "recipes/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("recipe") Recipe formRecipe, BindingResult bindingResult,Model model ) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepository.findAll());

            return "recipes/create";
        }

        recipeService.create(formRecipe);
        
        return "redirect:/recipes";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("recipe", recipeService.getById(id));
        model.addAttribute("edit", true);
        model.addAttribute("ingredients", ingredientRepository.findAll());

        return "recipes/create";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("recipe") Recipe formRecipe, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepository.findAll());

            return "recipes/create";
        }

        recipeService.update(formRecipe);

        return "redirect:/recipes";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable Integer id){
        Recipe recipe= recipeService.getById(id);

        recipeService.delete(recipe);

        return "redirect:/recipe";
    }    




}
