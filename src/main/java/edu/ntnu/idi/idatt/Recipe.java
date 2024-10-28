package edu.ntnu.idi.idatt;

import java.util.ArrayList;

public class Recipe {
    String recipeName;
    String recipeDescription;
    ArrayList<String> ingredients;
    ArrayList<Double> amounts;
    ArrayList<String> unit;

    public Recipe(String recipeName, String recipeDescription, ArrayList<String> ingredients, ArrayList<Double> amounts, ArrayList<String> unit) {
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.ingredients = ingredients;
        this.amounts = amounts;
        this.unit = unit;
    }

    //make function that checks if the required ingredients are in the storage, return true/false

    //function that tells what ingredients are missing

    //function that makes it possible to edit the recipes
}
