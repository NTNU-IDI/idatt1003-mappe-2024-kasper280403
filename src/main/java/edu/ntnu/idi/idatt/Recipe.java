package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Recipe.
 * It contains details about the recipe name, description, ingredients,
 * their quantities, and units of measurement.
 */
public class Recipe {
    String recipeName;
    String recipeDescription;
    ArrayList<String> ingredientsList;
    ArrayList<Double> amountsList;
    ArrayList<String> unitList;

    /**
     * Constructs a new Recipe object.
     *
     * @param recipeName the name of the recipe.
     * @param recipeDescription a short description of the recipe.
     * @param ingredients the list of ingredients needed for the recipe.
     * @param amounts the corresponding quantities of each ingredient.
     * @param unit the units of measurement for the ingredients.
     */
    public Recipe(String recipeName, String recipeDescription, ArrayList<String> ingredients, ArrayList<Double> amounts, ArrayList<String> unit) {
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.ingredientsList = ingredients;
        this.amountsList = amounts;
        this.unitList = unit;
    }

    /**
     * Checks if the recipe can be made with the current storage.
     *
     * @param printMissing if true, prints the missing ingredients.
     * @param storage the storage object to check ingredient availability.
     * @return true if all ingredients are available in sufficient quantity, false otherwise.
     */
    public boolean isMakeAble(boolean printMissing, Storage storage) {
        ArrayList<ArrayList<String>> missingList = missingList(storage);
        if(missingList.isEmpty()) {
            return true;
        }
        if(printMissing) {
            System.out.println("\nMissing ingredients: ");
            for (ArrayList<String> missing : missingList) {
                System.out.println(missing.get(0) + ": " + missing.get(1) + " " + missing.get(2));
            }
        }
        return false;
    }

    //make function that checks if the required ingredients are in the storage, returns a list of the missing items
    public ArrayList<ArrayList<String>> missingList(Storage storage){
        ArrayList<ArrayList<String>> missingList = new ArrayList<>();
        for (int i = 0; i < ingredientsList.size(); i++) {
            if(isInStorage(i, storage) > 0.0){
                ArrayList<String> temporary = new ArrayList<>();
                temporary.add(ingredientsList.get(i));
                temporary.add(isInStorage(i, storage).toString());
                temporary.add(unitList.get(i));
                missingList.add(temporary);
            }
        }
        return missingList;
    }

    /**
     * Checks if a specific ingredient is available in the storage with the required amount.
     *
     * @param index the index of the ingredient in the recipe's ingredient list.
     * @param storage the storage object to check ingredient availability.
     * @return 0.0 if the ingredient is available in sufficient quantity, otherwise the missing amount.
     */
    public Double isInStorage(int index, Storage storage) {
        String key = ingredientsList.get(index);
        Item item = storage.getItem(key);
        //checks if the item is in the storage
        if(!storage.itemExists(key)){
            return amountsList.get(index);
        }

        //checks if the unit in storage is the same as in recipe
        if(!item.getUnit().equals(unitList.get(index))){
            //if not it tries to convert the recipe to a new amount and update the unit
            Double newAmount = unitConverter(amountsList.get(index), unitList.get(index), item.getUnit());
            if(newAmount != -1.0) {
                amountsList.set(index, newAmount);
                unitList.set(index, item.getUnit());
            }
        }

        //checks if amount in storage is less than the required amount
        if(item.getQuantity() > amountsList.get(index)) {
            return 0.0;
        }
        return amountsList.get(index)-item.getQuantity();
    }

    /**
     * Converts the quantity of an ingredient from one unit to another.
     *
     * @param quantity the quantity to convert.
     * @param unitFrom the unit of the given quantity.
     * @param unitTo the target unit for conversion.
     * @return the converted quantity if conversion is possible, or -1.0 if the units are incompatible.
     */
    private Double unitConverter(Double quantity, String unitFrom, String unitTo) {

        List<String> volume = List.of("mL", "cL", "dL", "L", "daL", "hL", "kL");
        List<String> weight = List.of("mg", "cg", "dg", "g", "dag", "hg", "kg");

        int indexFrom = findUnitIndex(unitFrom, volume, weight);
        int indexTo = findUnitIndex(unitTo, volume, weight);

        // If both units is in the same category (volume or weight)
        if (indexFrom != -1 && indexTo != -1) {
            int categoryFrom = indexFrom / 10; //becomes 0 if it's volume(index 0-6) and 1 if it's weight(index 10-16)
            int categoryTo = indexTo / 10;

            if (categoryFrom == categoryTo) {
                // Converts quantity
                int f = indexFrom % 10; //gives the rest of (0-6 or 10-16) / 10, becomes 0-6, and you get original index of weight
                int t = indexTo % 10;
                return quantity * Math.pow(10, (f - t)); // calculates new quantity with old unit
            }
        }

        // Returns -1 if unit is inconvertible
        return -1.0;
    }

    /**
     * Finds the index of a unit in a predefined list of volume or weight units.
     *
     * @param unit the unit to find.
     * @param volume a list of volume units.
     * @param weight a list of weight units.
     * @return the index of the unit in the corresponding list, or -1 if not found.
     */
    private int findUnitIndex(String unit, List<String> volume, List<String> weight) {
        if (volume.contains(unit)) {
            return volume.indexOf(unit); // 0 to 6 for volume
        } else if (weight.contains(unit)) {
            return weight.indexOf(unit) + 10; // 10 to 16 for weight
        }
        return -1; // Returns -1 if unit is non existent
    }

    /**
     * Prints the list of ingredients required for the recipe along with their quantities and units.
     */
    public void ingredientList(){

        System.out.println("Ingredient list:");
        for (int i = 0; i < ingredientsList.size(); i++) {
            System.out.println(ingredientsList.get(i) + ": " + amountsList.get(i) + " " + unitList.get(i));
        }
    }

    /**
     * Removes the required quantities of each ingredient in the recipe from the storage.
     *
     * @param storage the storage object from which ingredients will be deducted.
     */
    public void deleteFromStorage(Storage storage) {
        for (int i = 0; i < this.ingredientsList.size(); i++) {
            String ingredient = ingredientsList.get(i);
            Double quantity = -amountsList.get(i);
            String unit = unitList.get(i);
            storage.updateStorage(ingredient, quantity, unit, 0.0, "00-00-0000");
            System.out.println("Updated");
        }
    }
}
