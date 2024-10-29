package edu.ntnu.idi.idatt;
import java.util.ArrayList;

public class TestClient {
    public static void main(String[] args) {
        Storage storage = new Storage();
        RecipeBook recipeBook = new RecipeBook();

        PreMadeData.addAll();

        ArrayList<String> recipeNames = recipeBook.getRecipeNames();
        for (String recipeName : recipeNames) {
            System.out.println(recipeName);
        }


    }



    public static void printTableSelection(ArrayList<ArrayList<String>> list){


        // prints out heading
        System.out.printf("%-10s %-10s %-10s %-10s %-15s%n", "Item", "Quantity", "Unit", "Price", "Expiration");
        System.out.println("--------------------------------------------------------------");

        double sum = 0;

        // Prints out data with formatting
        for (ArrayList<String> itemList : list) {
            System.out.printf("%-10s %-10s %-10s %-10s %-15s%n",
                    itemList.get(0), // Item
                    itemList.get(1), // quantity
                    itemList.get(2), // unit
                    itemList.get(3), // price
                    itemList.get(4)  // expiration
            );
            sum += Double.parseDouble(itemList.get(3));
        }

        System.out.printf("%-10s %-10s %-10s %-10s %-15s%n", "Sum", "", "", sum, "");
    }

    public static void searchItem(String key) {
        Storage storage = new Storage();
        if (storage.itemExists(key)) {
            Item itemList = storage.getItem(key);
            System.out.println("Item found:");
            System.out.printf("%-10s %-10s %-10s %-10s %-15s%n", "Item", "Quantity", "Unit", "Price", "Expiration");
            System.out.println("--------------------------------------------------------------");

            System.out.printf("%-10s %-10s %-10s %-10s %-15s%n",
                    itemList.getName(), // Item
                    itemList.getQuantity(), // quantity
                    itemList.getUnit(), // unit
                    itemList.getPrice(), // price
                    itemList.getExpiration()  // expiration
            );
        } else {
            System.out.println("Item not found");
        }
    }








}