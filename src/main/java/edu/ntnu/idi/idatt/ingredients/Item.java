package edu.ntnu.idi.idatt.ingredients;

/**
 * The Item class represents an item with a name, quantity, unit, price, and expiration date.
 */
public class Item {
    String name;
    Double quantity;
    String unit;
    Double price;
    ExpirationDate expiration;

    /**
     * Constructs a new Item with the specified name, quantity, unit, price, and expiration date.
     *
     * @param name       the name of the item
     * @param quantity   the quantity of the item
     * @param unit       the unit of the item
     * @param price      the price of the item
     * @param expiration the expiration date of the item
     */
    public Item(String name, Double quantity, String unit, Double price, ExpirationDate expiration) {
        this.name = name.toLowerCase();
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.expiration = expiration;
    }


    /**
     * Gets the name of the item.
     *
     * @return the name of the item
     */
    public String getName(){
        return name;
    }

    /**
     * Gets the quantity of the item.
     *
     * @return the quantity of the item
     */
    public Double getQuantity(){
        return quantity;
    }

    /**
     * Gets the unit of the item.
     *
     * @return the unit of the item
     */
    public String getUnit(){
        return unit;
    }

    /**
     * Gets the price of the item.
     *
     * @return the price of the item
     */
    public Double getPrice(){
        return price;
    }

    /**
     * Gets the expiration date of the item.
     * Retrieves the oldest valid expiration date, if available.
     *
     * @return the expiration date of the item, or "No valid dates" if none exist
     */
    public String getExpiration(){
        int index = expiration.oldestIndex();
        if (index >= 0 && index < expiration.dates.size()) {
            return expiration.getDate(index);
        }
        return "No valid dates";
    }
}
