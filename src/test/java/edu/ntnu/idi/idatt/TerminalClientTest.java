package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.ingredients.Storage;
import edu.ntnu.idi.idatt.recipe.RecipeBook;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TerminalClientTest {
    Storage storageTemp = new Storage();

    @Test
    void testingUpdateStorage() {
        String name = "milk";
        Double quantity = 0.0;
        String unit = "dL";
        Double price = 12.0;
        String expiration = "20-12-2024";

        storageTemp.updateStorage(name, quantity, unit, price, expiration);

        assertEquals(name, storageTemp.getItem(name).getName());
        assertEquals(52.0, storageTemp.getItem(name).getQuantity());
        assertEquals(unit, storageTemp.getItem(name).getUnit());
        assertEquals(price, storageTemp.getItem(name).getPrice());
        assertEquals(expiration, storageTemp.getItem(name).getExpiration());
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
        //assertEquals(ingredients, recipeBook.getRecipe(key).ingredientsList);
        //assertEquals(amounts, recipeBook.getRecipe(key).amountsList);
        //assertEquals(units, recipeBook.getRecipe(key).unitList);
    }

    @Test
    void testingItemExists() {
        storageTemp.updateStorage("ham", 35.0, "dL", 21.90, "08-10-2024");

        Boolean exist = storageTemp.itemExists("caviar");
        assertEquals(false, exist);
        Boolean exist2 = storageTemp.itemExists("ham");
        assertEquals(true, exist2);
    }

    @Test
    void testingListAll(){
        ArrayList<ArrayList<String>> list = storageTemp.storageList();

        assertEquals(list.get(1).getFirst(), "bread");
        assertEquals(list.get(0).get(0), "yogurt");
        assertEquals(list.get(0).get(3), "20.0");
        assertEquals(list.get(1).get(1), "10.0");
        assertEquals(list.get(1).get(2), "kg");

    }



}
