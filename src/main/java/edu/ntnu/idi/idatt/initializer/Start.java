package edu.ntnu.idi.idatt.initializer;
import edu.ntnu.idi.idatt.ingredients.Storage;
import edu.ntnu.idi.idatt.recipe.RecipeBook;
import java.util.Scanner;
import static edu.ntnu.idi.idatt.controller.validators.TerminalClient.*;

public class Start {
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
}