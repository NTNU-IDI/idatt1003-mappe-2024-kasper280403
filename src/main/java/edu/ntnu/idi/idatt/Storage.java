package edu.ntnu.idi.idatt;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class Storage {

    private static final HashMap<String, Item> storage = new HashMap<>();

    public Storage() {}

    public void updateStorage(String name, Double quantity, String unit, Double price, String expiration){
        name = name.toLowerCase();
        boolean NewItem =! storage.containsKey(name);

        if (NewItem) {
            // Adds new item to storage
            ExpirationDate date = new ExpirationDate(name, quantity, expiration);
            storage.put(name, new Item(name, quantity, unit, price, date));
        } else {
            // Updates existing item
            Item existingItem = storage.get(name);

            // Converts quantity if necessary
            if (!existingItem.unit.equals(unit)) {
                quantity = unitConverter(quantity, unit, existingItem.unit);
            }

            // Updates quantity
            existingItem.quantity += quantity;

            // Updates price if it's different and not 0
            if (price != 0) {
                existingItem.price = price;
            }

            //Add to date thing
            //existingItem.date =
            existingItem.expiration.update(quantity, expiration);





        }
    }


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

    private int findUnitIndex(String unit, List<String> volume, List<String> weight) {
        if (volume.contains(unit)) {
            return volume.indexOf(unit); // 0 to 6 for volume
        } else if (weight.contains(unit)) {
            return weight.indexOf(unit) + 10; // 10 to 16 for weight
        }
        return -1; // Returns -1 if unit is non existent
    }




    public Item getItem(String key) {
        return storage.get(key.toLowerCase());
    }

    public ArrayList<String> itemList(String key){
        ArrayList<String> v = new ArrayList<>();

        Item item = storage.get(key.toLowerCase());
        v.add(item.getName());
        v.add(String.valueOf(item.getQuantity()));
        v.add(item.getUnit());
        v.add(String.valueOf(item.getPrice()));
        v.add(item.getExpiration());


        return v;
    }

    public ArrayList<ArrayList<String>> storageList(){
        ArrayList<ArrayList<String>> table = new ArrayList<>();
        //int i = 0;
        for(String key : storage.keySet()){
            table.add(itemList(key));
        }

        return table;
    }
}
