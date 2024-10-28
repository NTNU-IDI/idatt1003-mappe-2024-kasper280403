package edu.ntnu.idi.idatt;

import java.util.ArrayList;

public class TestClientExpiration {
    public static void main(String[] args) {
        Storage storage = new Storage();

        storage.updateStorage("pizza", 10.0, "stk", 39.00, "29-10-2024");
        storage.updateStorage("pasta", 5.0, "kg", 10.00, "03-12-2024");
        storage.updateStorage("butter", 200.0, "g", 39.00, "10-10-2024");

        Item pizza = storage.getItem("pizza");
        Item pasta = storage.getItem("pasta");
        Item butter = storage.getItem("butter");

        System.out.println("\nTesting get expiration date before updating\n\n");

        System.out.println("Pizza expiration: " + pizza.getExpiration());
        System.out.println("Pasta expiration: " + pasta.getExpiration());
        System.out.println("Butter expiration: " + butter.getExpiration());

        System.out.println("\nTesting get expiration after updating pizza with 5 new units with an earlier expiration date\n\n");

        storage.updateStorage("pizza", 5.0, "stk", 39.00, "27-10-2024");

        System.out.println("Pizza expiration: " + pizza.getExpiration());
        System.out.println("Pasta expiration: " + pasta.getExpiration());
        System.out.println("Butter expiration: " + butter.getExpiration());

        System.out.println("\nTesting get expiration date before updating\n\n");

        System.out.println("\nTesting get expiredList with date 28-10.2024");
        System.out.println("Should return 200 butter, 5 pizza\n\n");

        ArrayList<ArrayList<String>> expiredList = storage.expiredList("28-10.2024");
        printTableSelection(expiredList);

        System.out.println("\nCurrently everything works 26.oct.2024");
        System.out.println("Will implement a method of deleting all expired items from storage");
        System.out.println("by using the expiredList method, should be pretty simple with some parsing and using current methods");


        System.out.println("\n\nTesting deleteExpiredItems with date 28-10.2024\n");

        System.out.println("Printing out all items in storage:");
        printTableSelection(storage.storageList());
        System.out.println("\nPrinting out expired items in storage:");
        printTableSelection(storage.expiredList("28-10-2024"));
        System.out.println("\n Deleting all expired items from storage\n Printing out expired items list(should be empty:");
        storage.deleteExpiredItems("28-10-2024");
        printTableSelection(storage.expiredList("28-10-2024"));
        System.out.println("\nPrinting out all items in storage:");
        printTableSelection(storage.storageList());


        storage.updateStorage("butter", 2.0, "kg", 50.00, "10-10-2024");
        System.out.println("\n\n\nPrinting out all items in storage, after adding 2 kg butter:");
        printTableSelection(storage.storageList());

    }


    public static void printTableSelection(ArrayList<ArrayList<String>> list){

        // prints out heading
        System.out.printf("%-10s %-10s %-10s %-10s %-15s%n", "Item", "Quantity", "Unit", "Price", "Expiration");
        System.out.println("--------------------------------------------------------------");

        // Prints out data with formatting
        for (ArrayList<String> itemList : list) {
            System.out.printf("%-10s %-10s %-10s %-10s %-15s%n",
                    itemList.get(0), // Item
                    itemList.get(1), // quantity
                    itemList.get(2), // unit
                    itemList.get(3), // price
                    itemList.get(4)  // expiration
            );
        }
    }

    public boolean isValidDateFormat(String date) {
        // Regular expression for format DD.MM.YYYY or similar with any delimiter
        String datePattern = "^\\d{2}[-./]\\d{2}[-./]\\d{4}$";

        // Check if the date matches the pattern and has length 10
        return date != null && date.length() == 10 && date.matches(datePattern);
    }
}
