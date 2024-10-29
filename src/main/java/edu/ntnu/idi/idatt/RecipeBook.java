package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.HashMap;

public class RecipeBook {
    private static final HashMap<String, Recipe> recipeBook = new HashMap<>();

    public RecipeBook() {}

    public void addRecipe(String recipeName, String recipeDescription, ArrayList<String> ingredients, ArrayList<Double> amounts, ArrayList<String> unit) {
        recipeBook.put(recipeName, new Recipe(recipeName, recipeDescription, ingredients, amounts, unit));
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
        return new ArrayList<>(recipeBook.keySet());
    }

    public Recipe getRecipe(String recipeName) {
        return recipeBook.get(recipeName);
    }
}
