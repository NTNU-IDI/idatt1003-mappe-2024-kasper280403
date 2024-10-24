package edu.ntnu.idi.idatt;

import java.util.ArrayList;

public class TestClient {
    public static void main(String[] args) {

        Storage storage = new Storage();

        storage.updateStorage("Milk", 10.0, "L", 21.90, "12-12-2024");

        Item milk = storage.getItem("milk");
        System.out.println(milk.getName());
        System.out.println(milk.getQuantity());
        System.out.println(milk.getExpiration());

        storage.updateStorage("milk", 200.0, "dL", 21.90, "10-12-2024");
        storage.updateStorage("carrot", 2000.0, "stk", 300.90, "00-00-0000");
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

        printTable();
    }

    public static void printTable(){
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
}