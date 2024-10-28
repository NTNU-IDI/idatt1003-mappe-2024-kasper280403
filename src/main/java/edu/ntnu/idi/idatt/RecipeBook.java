package edu.ntnu.idi.idatt;

import java.util.HashMap;

public class RecipeBook {
    private static final HashMap<String, Recipe> recipeBook = new HashMap<>();

    public RecipeBook(String recipeName, Recipe recipe) {
        recipeBook.put(recipeName, recipe);
    }
}
