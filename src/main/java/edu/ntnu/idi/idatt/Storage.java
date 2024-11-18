package edu.ntnu.idi.idatt;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


/**
 * Storage class that manages items in a storage with functionality to update, retrieve,
 * convert, and organize items based on various parameters like names, quantities, units, prices,
 * and expiration dates.
 */
public class Storage {
    //hashmap that contains the item, under its name
    private static final HashMap<String, Item> storage = new HashMap<>();

    //empty constructor, used for calling the storage hashmap
    public Storage() {}

    /**
     * Updates the storage with the specified item's details.
     *
     * @param name The name of the item
     * @param quantity The quantity of the item
     * @param unit The unit of measurement for the item
     * @param price The price of the item
     * @param expiration The expiration date of the item
     */
    public void updateStorage(String name, Double quantity, String unit, Double price, String expiration){

        //putting all names to lowercase to avoid making two items of for example "Milk" and "milk"
        name = name.toLowerCase();
        boolean NewItem =! storage.containsKey(name);

        if (NewItem) {
            // runs if the item name is not already a key in the storage hashmap
            // Adds the new item to storage
            ExpirationDate date = new ExpirationDate(name, quantity, expiration);
            storage.put(name, new Item(name, quantity, unit, price, date));
        } else {
            // runs if the item already is in the hashmap

            //gets the item already in storage that will be updated
            Item existingItem = storage.get(name);

            // the unit used earlier when storing the item might not be the same
            // so the quantity is updated in that case, along with the price
            // if the unit is not convertible the old unit is used
            if (!existingItem.unit.equals(unit)) {
                List<Double> convertedUnits = unitConverter(quantity, unit, existingItem.unit, price);
                if(!convertedUnits.equals(List.of(-1.0, -1.0))) {
                    quantity = convertedUnits.getFirst();
                    price = convertedUnits.getLast();
                }
            }


            if(quantity>0) {
                // if the quantity is positive the item amount is increased, and the expiration date does
                // not need any extra attention, and it is simply added in the ExpirationDate class
                existingItem.expiration.update(quantity, expiration);
            } else {
                // if the quantity is negative or 0 the expiration date remove() will be run to remove the
                // elements with the earliest expiration date first
                existingItem.expiration.remove(quantity, existingItem.quantity);
            }


            // Updates quantity
            if((existingItem.quantity+quantity)<0.0){
                existingItem.quantity = 0.0;
            } else{
                existingItem.quantity += quantity;
            }


            // Updates price if the new price is not 0
            if (price != 0.0) {
                existingItem.price = price;
            }

        }
    }

    /**
     * Converts the unit of the given quantity and price.
     *
     * @param quantity The quantity to be converted
     * @param unitFrom The unit to convert from
     * @param unitTo The unit to convert to
     * @param price The price associated with the quantity
     * @return A List containing converted values
     */
    private List<Double> unitConverter(Double quantity, String unitFrom, String unitTo, Double price) {

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
                Double q = quantity * Math.pow(10, (f - t)); // calculates new quantity with old unit
                Double p = price * Math.pow(10, (t - f));
                return List.of(q, p);
            }
        }

        // Returns -1 if unit is inconvertible
        return List.of(-1.0, -1.0);
    }

    /**
     * Finds the index of the unit in the given lists.
     *
     * @param unit The unit to find
     * @param volume The list of volume units
     * @param weight The list of weight units
     * @return The index of the unit
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
     * Retrieves an item from the storage.
     *
     * @param key The key name of the item
     * @return The item corresponding to the key
     */
    public Item getItem(String key) {
        return storage.get(key.toLowerCase());
    }

    /**
     * Retrieves a list of item names.
     *
     * @param key The key to look up items
     * @return An ArrayList of item names
     */
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

    /**
     * Retrieves a list of expired items.
     *
     * @param key The key to look up items
     * @param amount The amount to check expiry against
     * @return An ArrayList of expired items
     */
    public ArrayList<String> itemListExpired(String key, Double amount){
        ArrayList<String> v = new ArrayList<>();

        Item item = storage.get(key.toLowerCase());
        v.add(item.getName());
        v.add(amount.toString());
        v.add(item.getUnit());
        v.add(String.valueOf(item.getPrice()));
        v.add(item.getExpiration());


        return v;
    }

    /**
     * Retrieves the entire list of items in storage.
     *
     * @return An ArrayList of ArrayLists containing storage information
     */
    public ArrayList<ArrayList<String>> storageList(){
        ArrayList<ArrayList<String>> table = new ArrayList<>();
        for(String key : storage.keySet()){
            if (storage.get(key).quantity != 0.0) {
                table.add(itemList(key));
            }
        }

        return table;
    }

    /**
     * Checks if an item exists in the storage.
     *
     * @param key The key name of the item
     * @return true if the item exists, otherwise false
     */
    public boolean itemExists(String key){
        return storage.containsKey(key);
    }

    /**
     * Retrieves a list of expired items based on the current date.
     *
     * @param currentDate The current date to check expiry against
     * @return An ArrayList of ArrayLists containing expired items
     */
    public ArrayList<ArrayList<String>> expiredList(String currentDate){
        ArrayList<Item> expiredItem = new ArrayList<>();
        ArrayList<Double> quantity = new ArrayList<>();
        ArrayList<ArrayList<String>> expired = new ArrayList<>();
        for (String key : storage.keySet()) {
            Item item = storage.get(key);
            Double amount = item.expiration.amountExpired(currentDate);
            if(amount>0) {
                expiredItem.add(item);
                quantity.add(amount);
            }
        }
        int i = 0;
        for(Item item : expiredItem){
            expired.add(itemListExpired(item.getName(), quantity.get(i)));
            i++;
        }
        return expired;
    }

    /**
     * Deletes expired items based on the current date.
     *
     * @param currentDate The current date to check expiry against
     */
    public void deleteExpiredItems(String currentDate){
        ArrayList<ArrayList<String>> expiredList = expiredList(currentDate);
        for (ArrayList<String> expired : expiredList) {
            String name = expired.get(0);
            Double quantity = -Double.parseDouble(expired.get(1));
            String unit = expired.get(2);
            Double price = Double.parseDouble(expired.get(3));
            String expiration = "00-00-0000";
            updateStorage(name, quantity, unit, price, expiration);
        }
    }
}
