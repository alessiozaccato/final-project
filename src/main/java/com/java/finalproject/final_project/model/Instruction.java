// package com.java.finalproject.final_project.model;

// import java.util.List;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.ManyToMany;
// import jakarta.persistence.Table;
// import jakarta.validation.constraints.NotNull;

// @Entity
// @Table(name="instructions")
// public class Instruction {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Integer id;

//     @NotNull(message = "the procedure cannot be null")
//     private String description;

//     @ManyToMany(mappedBy = "instructions")
//     private List<Recipe> recipies;


//     public Integer getId() {
//         return this.id;
//     }

//     public void setId(Integer id) {
//         this.id = id;
//     }

//     public String getDescription() {
//         return this.description;
//     }

//     public void setDescription(String description) {
//         this.description = description;
//     }

//     public List<Recipe> getRecipies() {
//         return this.recipies;
//     }

//     public void setRecipies(List<Recipe> recipies) {
//         this.recipies = recipies;
//     }

// }
