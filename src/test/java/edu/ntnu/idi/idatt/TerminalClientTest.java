package edu.ntnu.idi.idatt;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TerminalClientTest {
    Storage storage = new Storage();

    @Test
    void testingUpdateStorage() {
        String name = "milk";
        Double quantity = 12.0;
        String unit = "dL";
        Double price = 12.0;
        String expiration = "20-12-2024";

        storage.updateStorage(name, quantity, unit, price, expiration);

        assertEquals(name, storage.getItem(name).getName());
        assertEquals(quantity, storage.getItem(name).getQuantity());
        assertEquals(unit, storage.getItem(name).getUnit());
        assertEquals(price, storage.getItem(name).getPrice());
        assertEquals(expiration, storage.getItem(name).getExpiration());
    }

    @Test
    void testingAddRecipe() {
        RecipeBook recipeBook = new RecipeBook();
        String name = "Scrambled eggs";
        String description = "Scramble the eggs";
        ArrayList<String> ingredients = new ArrayList<>(Arrays.asList("egg", "milk", "cheese", "butter"));
        ArrayList<Double> amounts = new ArrayList<>(Arrays.asList(3.0, 2.0, 0.1, 0.05));
        ArrayList<String> units = new ArrayList<>(Arrays.asList("stk", "dL", "kg", "kg"));
        recipeBook.addRecipe(name, description, ingredients, amounts, units);
        String key = name.replaceAll(" ", "");
        key = key.toLowerCase();

        assertEquals(name, recipeBook.getRecipe(key).recipeName);
        assertEquals(description, recipeBook.getRecipe(key).recipeDescription);
        assertEquals(ingredients, recipeBook.getRecipe(key).ingredientsList);
        assertEquals(amounts, recipeBook.getRecipe(key).amountsList);
        assertEquals(units, recipeBook.getRecipe(key).unitList);
    }

    @Test
    void testingItemExists() {
        storage.updateStorage("ham", 35.0, "dL", 21.90, "08-10-2024");

        Boolean exist = storage.itemExists("caviar");
        assertEquals(false, exist);
        Boolean exist2 = storage.itemExists("ham");
        assertEquals(true, exist2);
    }

}
