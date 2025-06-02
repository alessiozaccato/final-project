package com.java.finalproject.final_project.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "recipies")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 10, max = 20, message = "name must have min: 4 char and max: 20")
    @Column(nullable = false, length = 20)
    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String imgUrl;

    @ManyToMany
    @JoinTable(name = "ingredient_recipe", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;

    @ManyToMany
    @JoinTable(name = "Instruction_recipe", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "instruction_id"))
    private List<Instruction> instructions;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Instruction> getInstructions() {
        return this.instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }


}
