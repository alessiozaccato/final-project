package com.java.finalproject.final_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.finalproject.final_project.model.Recipe;
import com.java.finalproject.final_project.service.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

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
}
