package edu.ntnu.idi.idatt;
import java.util.ArrayList;

public class TestClient {
    public static void main(String[] args) {

        Storage storage = new Storage();

        storage.updateStorage("Milk", 10.0, "L", 21.90, "08-12-2024");

        Item milk = storage.getItem("milk");
        System.out.println(milk.getName());
        System.out.println(milk.getQuantity());
        System.out.println(milk.getExpiration());

        storage.updateStorage("milk", 200.0, "dL", 21.90, "06-08-2024");
        storage.updateStorage("carrot", 4.0, "stk", 300.90, "00-00-0000");
        storage.updateStorage("cheese", 1.04, "kg", 134.40, "20-01-2025");

        Item carrot = storage.getItem("carrot");

        System.out.println(milk.getName());
        System.out.println(milk.getQuantity());
        System.out.println(milk.getExpiration());

        System.out.println(carrot.getName());
        System.out.println(carrot.getQuantity());
        System.out.println(carrot.getUnit());


        System.out.println(storage.getItem("cheese").getQuantity());

        ArrayList<String> cheeseList = storage.itemList("cheese");
        for(String i : cheeseList){
            System.out.print(i+"--");
        }
        System.out.println("\n");

        System.out.println("\n!!!---Testing printTable() method\n\n");

        printFullTable();

        System.out.println("\n!!!---Testing searchItem() method\n\n");

        searchItem("ham");

        searchItem("milk");

        System.out.println("\n!!!---Testing expiration\n\n");


        storage.updateStorage("ham", 10.0, "hg", 30.0, "30-08-2024");
        storage.updateStorage("ham", 6.0, "hg", 30.0, "10-08-2024");

        searchItem("ham");

        System.out.println("\n");

        storage.updateStorage("ham", -13.0, "hg", 30.0, "99-99-9999");

        searchItem("ham");


        System.out.println("\n!!!---Testing sum method\n\n");
        double sum = storage.sumOfStorage();
        System.out.println("The sum of all the items in the storage is: "+ sum);

        System.out.println("\n!!!---Testing finding expired items\n\n");

        ArrayList<ArrayList<String>> expiredList = storage.expiredList("15-08-2024");
        System.out.println("These items are expired:");
        printTableSelection(expiredList);



    }

    public static void printFullTable(){
        Storage storage = new Storage();
        ArrayList<ArrayList<String>> storageList = storage.storageList();

        // prints out heading
        System.out.printf("%-10s %-10s %-10s %-10s %-15s%n", "Item", "Quantity", "Unit", "Price", "Expiration");
        System.out.println("--------------------------------------------------------------");

        // Prints out data with formatting
        for (ArrayList<String> itemList : storageList) {
            System.out.printf("%-10s %-10s %-10s %-10s %-15s%n",
                    itemList.get(0), // Item
                    itemList.get(1), // quantity
                    itemList.get(2), // unit
                    itemList.get(3), // price
                    itemList.get(4)  // expiration
            );
        }
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