package edu.ntnu.idi.idatt.initializer;

import edu.ntnu.idi.idatt.ingredients.Storage;
import edu.ntnu.idi.idatt.recipe.RecipeBook;

import java.util.ArrayList;
import java.util.Arrays;

public class PreMadeData {

    public PreMadeData(){}

    public static void addAll(){
        addItems();
        makeRecipes();
    }

    public static void addItems(){
        Storage storage = new Storage();
        storage.updateStorage("ham", 6.0, "hg", 30.0, "10-11-2024");
        storage.updateStorage("milk", 35.0, "dL", 21.90, "08-10-2024");
        storage.updateStorage("carrot", 8.0, "stk", 2.90, "15-11-2024");
        storage.updateStorage("cheese", 1.04, "kg", 134.40, "20-12-2024");
        storage.updateStorage("butter", 2.0, "kg", 50.00, "10-10-2024");
        storage.updateStorage("apple", 10.0, "stk", 3.50, "18-11-2024");
        storage.updateStorage("banana", 15.0, "stk", 4.00, "05-11-2024");
        storage.updateStorage("juice", 2.5, "L", 25.90, "12-11-2024");
        storage.updateStorage("chicken", 1.0, "kg", 95.00, "03-11-2024");
        storage.updateStorage("pasta", 1.0, "kg", 18.50, "25-12-2024");
        storage.updateStorage("rice", 2.0, "kg", 30.00, "01-01-2025");
        storage.updateStorage("yogurt", 2.0, "stk", 10.90, "15-11-2024");
        storage.updateStorage("tomato", 8.0, "stk", 2.50, "20-10-2024");
        storage.updateStorage("potato", 3.0, "kg", 15.00, "20-11-2024");
        storage.updateStorage("onion", 2.0, "kg", 12.00, "12-11-2024");
        storage.updateStorage("salmon", 0.75, "kg", 110.00, "05-12-2024");
        storage.updateStorage("coffee", 0.5, "kg", 80.00, "30-12-2024");
        storage.updateStorage("tea", 0.3, "kg", 60.00, "15-01-2025");
        storage.updateStorage("bread", 5.0, "stk", 25.00, "28-10-2024");
        storage.updateStorage("eggs", 12.0, "stk", 35.00, "01-11-2024");
    }

    public static void makeRecipes(){
        RecipeBook recipeBook = new RecipeBook();

        recipeBook.addRecipe(
                "Scrambled eggs",
                "Scramble the eggs",
                new ArrayList<>(Arrays.asList("egg", "milk", "cheese", "butter")),
                new ArrayList<>(Arrays.asList(3.0, 2.0, 0.1, 0.05)),
                new ArrayList<>(Arrays.asList("stk", "dL", "kg", "kg")));

        recipeBook.addRecipe(
                "Chicken and rice",
                "Mix it and put it in a bowl or something",
                new ArrayList<>(Arrays.asList("chicken", "rice", "carrot", "onion")),
                new ArrayList<>(Arrays.asList(0.5, 1.0, 2.0, 0.5)),
                new ArrayList<>(Arrays.asList("kg", "kg", "stk", "kg")));

        recipeBook.addRecipe(
                "Smoothie",
                "Not to be confused with milkshake, not a nice experience",
                new ArrayList<>(Arrays.asList("milk", "banana", "yogurt")),
                new ArrayList<>(Arrays.asList(3.0, 2.0, 1.0)),
                new ArrayList<>(Arrays.asList("dL", "stk", "stk")));

        recipeBook.addRecipe(
                "Vegetable soup",
                "Soup is yummy yummy.",
                new ArrayList<>(Arrays.asList("carrot", "potato", "tomato", "onion")),
                new ArrayList<>(Arrays.asList(3.0, 0.5, 2.0, 1.0)),
                new ArrayList<>(Arrays.asList("stk", "kg", "stk", "kg")));


        recipeBook.addRecipe(
                "Avocado Toast",
                "Popular with white girls",
                new ArrayList<>(Arrays.asList("bread", "avocado", "egg", "cheese")),
                new ArrayList<>(Arrays.asList(2.0, 1.0, 1.0, 0.05)),
                new ArrayList<>(Arrays.asList("stk", "stk", "stk", "kg"))
        );

        recipeBook.addRecipe(
                "Spaghetti Bologna",
                "Cook the beef(not in water, but sear). Then add the rest and like boil.",
                new ArrayList<>(Arrays.asList("pasta", "ground beef", "tomato", "onion", "garlic")),
                new ArrayList<>(Arrays.asList(0.2, 0.3, 2.0, 0.1, 0.02)),
                new ArrayList<>(Arrays.asList("kg", "kg", "stk", "kg", "kg"))
        );

        recipeBook.addRecipe(
                "Vegetarian Pizza",
                "Not as good as normal pizza.",
                new ArrayList<>(Arrays.asList("flour", "tomato", "cheese", "bell pepper", "mushroom")),
                new ArrayList<>(Arrays.asList(0.3, 2.0, 0.2, 1.0, 0.1)),
                new ArrayList<>(Arrays.asList("kg", "stk", "kg", "stk", "kg"))
        );

        recipeBook.addRecipe(
                "Pancakes",
                "Everyone knows how to make pancake.",
                new ArrayList<>(Arrays.asList("milk", "egg", "flour", "butter", "sugar")),
                new ArrayList<>(Arrays.asList(3.0, 2.0, 0.2, 0.05, 0.05)),
                new ArrayList<>(Arrays.asList("dL", "stk", "kg", "kg", "kg"))
        );

        recipeBook.addRecipe(
                "Cooked Horse",
                "what???!!!",
                new ArrayList<>(Arrays.asList("horse", "carrot")),
                new ArrayList<>(Arrays.asList(3.0, 30.0)),
                new ArrayList<>(Arrays.asList("kg", "stk"))
        );

        recipeBook.addRecipe(
                "Guacamole",
                "Its good.",
                new ArrayList<>(Arrays.asList("avocado", "tomato", "onion", "lime", "salt")),
                new ArrayList<>(Arrays.asList(2.0, 1.0, 0.1, 1.0, 0.01)),
                new ArrayList<>(Arrays.asList("stk", "stk", "kg", "stk", "kg"))
        );

    }
}
