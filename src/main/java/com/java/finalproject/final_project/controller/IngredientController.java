package com.java.finalproject.final_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.finalproject.final_project.model.Ingredient;
import com.java.finalproject.final_project.model.Recipe;
import com.java.finalproject.final_project.service.IngredientService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public String getMethodName(Model model) {
        List<Ingredient> ingredients = ingredientService.findAll();
        model.addAttribute("ingredients", ingredients);
        return "ingredients/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Ingredient ingredient = ingredientService.getById(id);
        model.addAttribute("ingredient", ingredient);
        return "ingredients/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "ingredients/create-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create-edit";
        }

        ingredientService.create(formIngredient);

        return "redirect:/ingredients";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("ingredient", ingredientService.getById(id));
        model.addAttribute("edit", true);
        return "ingredients/create-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create-edit";
        }

        ingredientService.update(formIngredient);

        return "redirect:/ingredients";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        Ingredient ingredientToRemove = ingredientService.getById(id);

        for (Recipe linkedRecipe : ingredientToRemove.getRecipies()) {
            linkedRecipe.getIngredients().remove(ingredientToRemove);
        }
        ingredientService.delete(ingredientToRemove);

        return"redirect:/ingredients";
    }
}