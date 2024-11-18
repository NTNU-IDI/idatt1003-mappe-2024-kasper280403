package edu.ntnu.idi.idatt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TerminalClient {
    public static void main(String[] args) {
        Storage storage = new Storage();
        RecipeBook recipeBook = new RecipeBook();

        //To test the program with pre-made items and recipes uncomment the next line:
        PreMadeData.addAll();

        System.out.println("\n---||| Welcome to the food storage program |||---\n");
        Scanner scanner = new Scanner(System.in);

        boolean keepGoing = true;
        while (keepGoing) {

            String menu = """
            \nDo you want to:
                A) Add or remove an item (update)                   F) Add recipe (newRecipe)
                B) Search for an item (search)                      G) Get recipe names (recipeNames)
                C) Print out list of all items (listAll)            H) Get recipes that are able to be made
                D) Print out expired items (listExpired)               with the current items in storage (makeAble)
                E) Quit (quit)                                      I) Get recipe instructions and ingredient list (make)
            Please enter your choice:\s""";

            System.out.print(menu);
            String keyword = scanner.nextLine();

            switch (keyword.toLowerCase()) {
                case "update","a","1"  -> updateStorageInput(storage);
                case "search","b","2" -> {
                    System.out.print("Write name of product you want to find: ");
                    String name = scanner.nextLine();
                    searchItem(name, storage);
                }
                case "listall","c","3" -> {
                    System.out.println("\nList of all items in storage: ");
                    printTableSelection(storage.storageList());
                }
                case "listexpired","d","4" -> {
                    System.out.print("Write today's date (format dd-mm-yyyy): ");
                    String date = scanner.nextLine();
                    while (!isValidDateFormat(date)) {
                        System.out.print("Enter valid date format (format: dd/mm/yyyy): ");
                        date = scanner.nextLine();
                    }
                    printTableSelection(storage.expiredList(date));
                    System.out.print("Do you want to remove these items from the storage (yes/no)?: ");
                    String choice = scanner.nextLine();
                    if (choice.equalsIgnoreCase("yes")) {
                        storage.deleteExpiredItems(date);
                    }
                }
                case "quit","e","5" -> {
                    scanner.close();
                    keepGoing = false;
                    System.out.println("Exiting program.");
                }
                case "newrecipe","f","6" -> AddRecipe(recipeBook);

                case "recipenames","g","7" -> getRecipeNames(recipeBook, storage);

                case "makeable","h","8" -> getMakeAbleRecipes(recipeBook, storage);

                case "make", "i", "9" -> makeRecipe(recipeBook, storage);

                default -> System.out.println("Invalid choice, please try again.");
            }
        }

    }

    public static void updateStorageInput(Storage storage){
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter item name (use generic names for better recipe suggestions): ");
        String name = scanner.nextLine();

        System.out.print("Enter item quantity, negative to remove (format: (-)00.00): ");
        String quantityInput = scanner.nextLine();
        while(!isValidDoubleFormat(quantityInput)) {
            System.out.print("Enter valid quantity format (format: (-)00.00): ");
            quantityInput = scanner.nextLine();
        }
        double quantity = Double.parseDouble(quantityInput);

        System.out.print("Enter item unit (if possible use g, hg, kg/cL, dL, L): ");
        String unit = scanner.nextLine();

        if(quantity < 0.0){
            storage.updateStorage(name, quantity, unit, 0.0, "00-00-0000");
            System.out.println("Item was removed successfully.");
            return;
        }

        System.out.print("Enter item price per unit, to keep current price write 0 (format: 00.00): ");
        String priceInput = scanner.nextLine();
        while(!isValidDoubleFormat(priceInput)) {
            System.out.print("Enter valid price format (format: 00.00): ");
            priceInput = scanner.nextLine();
        }
        double price = Double.parseDouble(priceInput);

        if(quantity == 0.0){
            storage.updateStorage(name, quantity, unit, price, "00-00-0000");
            System.out.println("Item was updated successfully.");
            return;
        }


        System.out.print("Enter item expiration date (format: dd/mm/yyyy): ");
        String expiration = scanner.nextLine();
        while(!isValidDateFormat(expiration)){
            System.out.print("Enter valid date format (format: dd/mm/yyyy): ");
            expiration = scanner.nextLine();
        }

        storage.updateStorage(name, quantity, unit, price, expiration);
        System.out.println("Item was added successfully.");

    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean isValidDateFormat(String date) {
        // Regular expression for format DD.MM.YYYY or similar with any delimiter
        String datePattern = "^\\d{2}[-./,]\\d{2}[-./,]\\d{4}$";

        // Check if the date matches the pattern and has length 10
        return date != null && date.length() == 10 && date.matches(datePattern);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean isValidDoubleFormat(String input) {
        String doublePattern = "^-?\\d*\\.?\\d*$";
        if (input.isEmpty()){
            return false;
        }
        return input.matches(doublePattern);
    }

    public static void printTableSelection(ArrayList<ArrayList<String>> list){

        //sorts items by name
        list.sort(Comparator.comparing(ArrayList::getFirst));

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
            sum += Double.parseDouble(itemList.get(1)) * Double.parseDouble(itemList.get(3));
        }

        sum =  Math.round(sum*1000)/1000.0;

        System.out.printf("%-10s %-10s %-10s %-10s %-15s%n", "Sum", "", "", sum, "");
    }

    public static void searchItem(String key, Storage storage) {
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

    public static void AddRecipe(RecipeBook recipeBook) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter recipe name: ");
        String name = scanner.nextLine().toLowerCase();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        int i = 1;
        String input;
        String pattern = "^(\\w+)[-./,]\\s*(\\d*\\.?\\d*)[-./,]\\s*(\\w+)$";
        Pattern regex = Pattern.compile(pattern);
        ArrayList<String> ingredients = new ArrayList<>();
        ArrayList<Double> amounts = new ArrayList<>();
        ArrayList<String> units = new ArrayList<>();

        System.out.println("Enter the ingredients 1 by 1 (format: 'ingredient name', '00.00', 'unit'), leave blank when finished");
        do{
            System.out.print("Ingredient "+i+": ");
            input = scanner.nextLine();
            Matcher matcher = regex.matcher(input);
            if(matcher.matches() && !input.isEmpty()){
                i++;
                ingredients.add(matcher.group(1));
                amounts.add(Double.parseDouble(matcher.group(2)));
                units.add(matcher.group(3));

            } else if(!matcher.matches() && !input.isEmpty()){
                System.out.println("Invalid format!");
            }

        }while(!input.isEmpty());

        recipeBook.addRecipe(name, description, ingredients, amounts, units);
        System.out.println("Recipe added successfully.");

    }

    public static void getMakeAbleRecipes(RecipeBook recipeBook, Storage storage) {
        ArrayList<String> recipes = recipeBook.makeAbleList(storage);
        System.out.println("\nThese recipes can be made with the items in storage: ");
        for (String recipe : recipes) {
            System.out.println(recipe);
        }
    }

    public static void getRecipeNames(RecipeBook recipeBook, Storage storage) {
        System.out.println("\n--The recipe book contains these recipes:");
        ArrayList<String> recipeKeys = recipeBook.getRecipeKeys();
        for (String key : recipeKeys) {
            System.out.print(recipeBook.getRecipe(key).recipeName);
            if(recipeBook.getRecipe(key).isMakeAble(false, storage)) {
                System.out.print(" (required ingredients is in storage)");
            }
            System.out.println();
        }
    }

    public static void makeRecipe(RecipeBook recipeBook, Storage storage) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter recipe name: ");
        ArrayList<String> names = recipeBook.getRecipeKeys();
        String name = scanner.nextLine().toLowerCase();
        name = name.replace(" ", "");
        while(!names.contains(name)){
            System.out.println("Recipe not found, enter valid recipe name: ");
            name = scanner.nextLine().toLowerCase();
        }
        Recipe recipe = recipeBook.getRecipe(name);
        System.out.println("\n--"+recipe.recipeName);
        recipe.ingredientList();
        System.out.println("\nInstructions:\n"+recipe.recipeDescription);
        if(!recipe.isMakeAble(true, storage)) {
            System.out.println("Cannot make recipe");
            return;
        }

        System.out.println("Storage contains the required ingredients to make recipe!");



        System.out.print("\nDo you want to remove the ingredients from storage (yes/no): ");
        String input = scanner.nextLine();
        if(input.equalsIgnoreCase("yes")){
            recipe.deleteFromStorage(storage);
            System.out.println("Ingredients removed successfully.");
        }


    }


}
