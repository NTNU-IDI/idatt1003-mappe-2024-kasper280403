package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.HashMap;

public class RecipeBook {
    private static final HashMap<String, Recipe> recipeBook = new HashMap<>();

    public RecipeBook() {}

    public void addRecipe(String recipeName, String recipeDescription, ArrayList<String> ingredients, ArrayList<Double> amounts, ArrayList<String> unit) {
        String key = recipeName.toLowerCase();
        key = key.replaceAll(" ", "");
        recipeBook.put(key, new Recipe(recipeName, recipeDescription, ingredients, amounts, unit));
    }

    public ArrayList<String> makeAbleList(Storage storage) {
        ArrayList<String> makeAble = new ArrayList<>();
        for(String key : recipeBook.keySet()) {
            if(recipeBook.get(key).isMakeAble(false, storage)){
                makeAble.add(recipeBook.get(key).recipeName);
            }
        }
        return makeAble;
    }

    public ArrayList<String> getRecipeNames() {
        ArrayList<String> recipeNames = new ArrayList<>();
        for(String key: recipeBook.keySet()){
            Recipe recipe = recipeBook.get(key);
            recipeNames.add(recipe.recipeName);
        }

        return recipeNames;
    }

    public ArrayList<String> getRecipeKeys() {
        return new ArrayList<>(recipeBook.keySet());
    }

    public Recipe getRecipe(String recipeKey) {
        return recipeBook.get(recipeKey);
    }

}