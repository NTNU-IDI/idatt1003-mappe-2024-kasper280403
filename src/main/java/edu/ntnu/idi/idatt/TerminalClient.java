package edu.ntnu.idi.idatt;
import java.util.ArrayList;
import java.util.Scanner;

public class TerminalClient {
    public static void main(String[] args) {
        Storage storage = new Storage();

        System.out.println("\n---||| Welcome to the food storage program |||---\n");
        Scanner scanner = new Scanner(System.in);

        boolean keepGoing = true;
        while (keepGoing) {

            String menu = """
            \nDo you want to:
                A) Add or remove an item (update)
                B) Search for an item (search)
                C) Print out list of all items (listAll)
                D) Print out list of expired items (listExpired)
                E) Quit (quit)
            Please enter your choice:\s""";

            System.out.print(menu);
            String keyword = scanner.nextLine();

            switch (keyword.toLowerCase()) {
                case "update" -> updateStorageInput(storage);
                case "search" -> {
                    System.out.println("Write name of product you want to find: ");
                    String name = scanner.nextLine();
                    searchItem(name, storage);
                }
                case "listall" -> {
                    System.out.println("\nList of all items in storage: ");
                    printTableSelection(storage.storageList());
                }
                case "listexpired" -> {
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
                case "quit" -> {
                    scanner.close();
                    keepGoing = false;
                    System.out.println("Exiting program.");}

                default -> System.out.println("Invalid choice, please try again.");
            }
        }

    }


    public static void updateStorageInput(Storage storage){
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter item name (use generic names for better recipe suggestions): ");
        String name = scanner.nextLine();

        System.out.print("Enter item quantity (format: 00.00): ");
        String quantityInput = scanner.nextLine();
        while(!isValidDoubleFormat(quantityInput)) {
            System.out.print("Enter valid quantity format (format: 00.00): ");
            quantityInput = scanner.nextLine();
        }
        double quantity = Double.parseDouble(quantityInput);

        System.out.print("Enter item unit (if possible use g, hg, kg/cL, dL, L): ");
        String unit = scanner.nextLine();

        System.out.print("Enter item price (format: 00.00): ");
        String priceInput = scanner.nextLine();
        while(!isValidDoubleFormat(priceInput)) {
            System.out.print("Enter valid price format (format: 00.00): ");
            priceInput = scanner.nextLine();
        }
        double price = Double.parseDouble(priceInput);


        System.out.print("Enter item expiration date (format: dd/mm/yyyy): ");
        String expiration = scanner.nextLine();
        while(!isValidDateFormat(expiration)){
            System.out.print("Enter valid date format (format: dd/mm/yyyy): ");
            expiration = scanner.nextLine();
        }

        storage.updateStorage(name, quantity, unit, price, expiration);
        System.out.println("Item was updated successfully.");

    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean isValidDateFormat(String date) {
        // Regular expression for format DD.MM.YYYY or similar with any delimiter
        String datePattern = "^\\d{2}[-./]\\d{2}[-./]\\d{4}$";

        // Check if the date matches the pattern and has length 10
        return date != null && date.length() == 10 && date.matches(datePattern);
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean isValidDoubleFormat(String input) {
        String doublePattern = "^\\d*\\.?\\d*$";
        if (input.isEmpty()){
            return false;
        }
        return input.matches(doublePattern);
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
            sum += Double.parseDouble(itemList.get(1)) * Double.parseDouble(itemList.get(3));
        }

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





}
