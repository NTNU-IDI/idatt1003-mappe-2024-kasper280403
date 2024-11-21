package edu.ntnu.idi.idatt;

import edu.ntnu.idi.idatt.ingredients.Storage;
import edu.ntnu.idi.idatt.recipe.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest {
    Recipe recipe;
    Storage storage;

    @BeforeEach
    void setUp() {
        storage = new Storage();
        storage.updateStorage("egg", 10.0, "stk", 3.0, "20-12-2024");
        storage.updateStorage("milk", 10.0, "dL", 1.0, "20-12-2024");
        storage.updateStorage("cheese", 2.0, "kg", 10.0, "20-12-2024");

        String name = "Scrambled eggs";
        String description = "Scramble the eggs";
        ArrayList<String> ingredients = new ArrayList<>(Arrays.asList("egg", "milk", "cheese"));
        ArrayList<Double> amounts = new ArrayList<>(Arrays.asList(3.0, 2.0, 0.1));
        ArrayList<String> units = new ArrayList<>(Arrays.asList("stk", "dL", "kg"));
        
        recipe = new Recipe(name, description, ingredients, amounts, units);
    }

    @Test
    void testIsMakeAble() {
        assertTrue(recipe.isMakeAble(false, storage));
        
        storage.updateStorage("egg", -10.0, "stk", 0.5, "00-00-0000");
        assertTrue(recipe.isMakeAble(false, storage));
    }
    

    @Test
    void testIsInStorage() {
        //int index = recipe.ingredientsList.indexOf("egg");
        //assertEquals(0.0, recipe.isInStorage(index, storage));

        storage.updateStorage("egg", 1.0, "stk", 1.0, "20-12-2024");
        //assertEquals(0.0, recipe.isInStorage(index, storage));
    }



    @Test
    void testDeleteFromStorage() {
        recipe.deleteFromStorage(storage);

        assertTrue(storage.itemExists("egg"));
        assertEquals(18.0, storage.getItem("egg").getQuantity());

        assertTrue(storage.itemExists("milk"));
        
        assertTrue(storage.itemExists("cheese"));
        assertEquals(9.9, storage.getItem("cheese").getQuantity());
    }
}