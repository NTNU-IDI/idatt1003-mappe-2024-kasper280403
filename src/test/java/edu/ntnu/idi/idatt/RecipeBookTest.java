package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.ingredients.Storage;
import edu.ntnu.idi.idatt.recipe.Recipe;
import edu.ntnu.idi.idatt.recipe.RecipeBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class RecipeBookTest {
    RecipeBook recipeBook;
    Storage storage;

    @BeforeEach
    void setUp() {
        recipeBook = new RecipeBook();
        storage = new Storage();

        storage.updateStorage("egg", 10.0, "stk", 1.0, "20-12-2024");
        storage.updateStorage("milk", 10.0, "dL", 0.5, "20-12-2024");
        storage.updateStorage("cheese", 2.0, "kg", 10.0, "20-12-2024");
    }

    @Test
    void testAddRecipe() {
        String name = "Scrambled eggs";
        String description = "Scramble the eggs";
        ArrayList<String> ingredients = new ArrayList<>(Arrays.asList("egg", "milk", "cheese"));
        ArrayList<Double> amounts = new ArrayList<>(Arrays.asList(3.0, 2.0, 0.1));
        ArrayList<String> units = new ArrayList<>(Arrays.asList("stk", "dL", "kg"));
        
        recipeBook.addRecipe(name, description, ingredients, amounts, units);
        
        String key = name.replaceAll(" ", "").toLowerCase();
        assertNotNull(recipeBook.getRecipe(key));
    }

    @Test
    void testMakeAbleList() {
        String name = "Scrambled eggs";
        String description = "Scramble the eggs";
        ArrayList<String> ingredients = new ArrayList<>(Arrays.asList("egg", "milk"));
        ArrayList<Double> amounts = new ArrayList<>(Arrays.asList(2.0, 1.0));
        ArrayList<String> units = new ArrayList<>(Arrays.asList("stk", "dL"));
        recipeBook.addRecipe(name, description, ingredients, amounts, units);
        
        ArrayList<String> makeAbleRecipes = recipeBook.makeAbleList(storage);
        assertTrue(makeAbleRecipes.contains(name));
    }

    @Test
    void testGetRecipeNames() {
        String name1 = "Scrambled eggs";
        String description1 = "Scramble the eggs";
        ArrayList<String> ingredients1 = new ArrayList<>(Arrays.asList("egg", "milk"));
        ArrayList<Double> amounts1 = new ArrayList<>(Arrays.asList(2.0, 1.0));
        ArrayList<String> units1 = new ArrayList<>(Arrays.asList("stk", "dL"));
        recipeBook.addRecipe(name1, description1, ingredients1, amounts1, units1);

        String name2 = "Cheese sandwich";
        String description2 = "Make a cheese sandwich";
        ArrayList<String> ingredients2 = new ArrayList<>(Arrays.asList("cheese", "bread"));
        ArrayList<Double> amounts2 = new ArrayList<>(Arrays.asList(0.1, 2.0));
        ArrayList<String> units2 = new ArrayList<>(Arrays.asList("kg", "stk"));
        recipeBook.addRecipe(name2, description2, ingredients2, amounts2, units2);
        
        ArrayList<String> names = recipeBook.getRecipeNames();
        assertTrue(names.contains(name1));
        assertTrue(names.contains(name2));
    }

    @Test
    void testGetRecipeKeys() {
        String name1 = "Scrambled eggs";
        String description1 = "Scramble the eggs";
        ArrayList<String> ingredients1 = new ArrayList<>(Arrays.asList("egg", "milk"));
        ArrayList<Double> amounts1 = new ArrayList<>(Arrays.asList(2.0, 1.0));
        ArrayList<String> units1 = new ArrayList<>(Arrays.asList("stk", "dL"));
        recipeBook.addRecipe(name1, description1, ingredients1, amounts1, units1);

        String name2 = "Cheese sandwich";
        String description2 = "Make a cheese sandwich";
        ArrayList<String> ingredients2 = new ArrayList<>(Arrays.asList("cheese", "bread"));
        ArrayList<Double> amounts2 = new ArrayList<>(Arrays.asList(0.1, 2.0));
        ArrayList<String> units2 = new ArrayList<>(Arrays.asList("kg", "stk"));
        recipeBook.addRecipe(name2, description2, ingredients2, amounts2, units2);
        
        ArrayList<String> keys = recipeBook.getRecipeKeys();
        assertTrue(keys.contains(name1.replaceAll(" ", "").toLowerCase()));
        assertTrue(keys.contains(name2.replaceAll(" ", "").toLowerCase()));
    }

    @Test
    void testGetRecipe() {
        String name = "Scrambled eggs";
        String description = "Scramble the eggs";
        ArrayList<String> ingredients = new ArrayList<>(Arrays.asList("egg", "milk"));
        ArrayList<Double> amounts = new ArrayList<>(Arrays.asList(2.0, 1.0));
        ArrayList<String> units = new ArrayList<>(Arrays.asList("stk", "dL"));
        recipeBook.addRecipe(name, description, ingredients, amounts, units);
        
        String key = name.replaceAll(" ", "").toLowerCase();
        Recipe recipe = recipeBook.getRecipe(key);
        
        assertNotNull(recipe);
        assertEquals(name, recipe.recipeName);
        assertEquals(description, recipe.recipeDescription);
        //assertEquals(ingredients, recipe.ingredientsList);
        //assertEquals(amounts, recipe.amountsList);
        //assertEquals(units, recipe.unitList);
    }
}