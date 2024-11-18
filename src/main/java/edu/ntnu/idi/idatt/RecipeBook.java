package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents a Recipe Book which manages a collection of recipes.
 * It allows for adding new recipes, checking for makeable recipes based on storage,
 * and retrieving recipe names and keys.
 */
public class RecipeBook {
    // Static HashMap to store recipes with their names as keys
    private static final HashMap<String, Recipe> recipeBook = new HashMap<>();

    /**
     * Default constructor for RecipeBook.
     */
    public RecipeBook() {}

    /**
     * Adds a new recipe to the recipe book.
     *
     * @param recipeName The name of the recipe.
     * @param recipeDescription A description of the recipe.
     * @param ingredients A list of ingredient names.
     * @param amounts A list of ingredient amounts.
     * @param unit A list of units for the ingredients.
     */
    public void addRecipe(String recipeName, String recipeDescription, ArrayList<String> ingredients, ArrayList<Double> amounts, ArrayList<String> unit) {
        String key = recipeName.toLowerCase();
        key = key.replaceAll(" ", "");
        recipeBook.put(key, new Recipe(recipeName, recipeDescription, ingredients, amounts, unit));
    }

    /**
     * Creates a list of recipes that can be made with the given storage.
     *
     * @param storage The storage containing available ingredients.
     * @return A list of recipe names that can be made.
     */
    public ArrayList<String> makeAbleList(Storage storage) {
        ArrayList<String> makeAble = new ArrayList<>();
        for(String key : recipeBook.keySet()) {
            if(recipeBook.get(key).isMakeAble(false, storage)){
                makeAble.add(recipeBook.get(key).recipeName);
            }
        }
        return makeAble;
    }

    /**
     * Retrieves a list of recipe names in the recipe book.
     *
     * @return A list of recipe names.
     */
    public ArrayList<String> getRecipeNames() {
        ArrayList<String> recipeNames = new ArrayList<>();
        for(String key: recipeBook.keySet()){
            Recipe recipe = recipeBook.get(key);
            recipeNames.add(recipe.recipeName);
        }

        return recipeNames;
    }

    /**
     * Retrieves a list of recipe keys in the recipe book.
     *
     * @return A list of recipe keys.
     */
    public ArrayList<String> getRecipeKeys() {
        return new ArrayList<>(recipeBook.keySet());
    }

    /**
     * Retrieves a specific recipe based on its key.
     *
     * @param recipeKey The key of the recipe to retrieve.
     * @return The recipe corresponding to the given key.
     */
    public Recipe getRecipe(String recipeKey) {
        return recipeBook.get(recipeKey);
    }

}