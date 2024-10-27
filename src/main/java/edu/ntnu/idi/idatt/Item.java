package edu.ntnu.idi.idatt;

public class Item {
    String name;
    Double quantity;
    String unit;
    Double price;
    ExpirationDate expiration;

    public Item(String name, Double quantity, String unit, Double price, ExpirationDate expiration) {
        this.name = name.toLowerCase();
        this.quantity = quantity;
        this.unit = unit;
        this.price = price;
        this.expiration = expiration;
    }

    public String getName(){
        return name;
    }
    public Double getQuantity(){
        return quantity;
    }
    public String getUnit(){
        return unit;
    }
    public Double getPrice(){
        return price;
    }
    public String getExpiration(){
        int index = expiration.oldestIndex();
        if (index >= 0 && index < expiration.dates.size()) {
            return expiration.getDate(index);
        }
        return "No valid dates";
    }
}