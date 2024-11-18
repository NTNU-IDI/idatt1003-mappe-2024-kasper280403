package edu.ntnu.idi.idatt;

import java.util.ArrayList;

/**
 * The ExpirationDate class represents an item with an expiration date and tracks the quantity available.
 * It provides methods to update the quantity, remove the quantity based on oldest expiration date,
 * and check for expired quantities.
 */
public class ExpirationDate {
    String name;
    ArrayList<Double> quantities = new ArrayList<>();
    ArrayList<String> dates = new ArrayList<>();

    /**
     * Constructs an ExpirationDate object with an initial quantity and expiration date.
     *
     * @param name       the name of the item.
     * @param quantity   the initial quantity of the item.
     * @param expiration the expiration date of the initial quantity.
     */
    public ExpirationDate(String name, Double quantity, String expiration){
        this.name = name;
        this.quantities.add(quantity);
        this.dates.add(expiration);
    }

    /**
     * Updates the item with additional quantity and a new expiration date.
     *
     * @param quantity   the quantity to be added.
     * @param expiration the expiration date of the added quantity.
     */
    public void update(Double quantity, String expiration){
        this.quantities.add(quantity);
        this.dates.add(expiration);
    }

    /**
     * Removes a specified quantity from the item based on the oldest expiration date first.
     * If the quantity to be removed is more than the available total quantity, it removes all the quantity.
     *
     * @param quantity      the quantity to be removed. It should be a negative value as it reduces the quantity.
     * @param totalQuantity the total available quantity of the item.
     */
    public void remove(Double quantity, Double totalQuantity){
        if(-quantity>totalQuantity){
            quantity = -totalQuantity;
        }
        double remainingQuantity = -quantity;
        while(remainingQuantity > 0.0) {
            int index = oldestIndex();
            Double freeQuantity = this.quantities.get(index);
            if (remainingQuantity < freeQuantity) {
                this.quantities.set(index, freeQuantity - remainingQuantity);
            } else {
                this.quantities.remove(index);
                this.dates.remove(index);
            }
            remainingQuantity -= freeQuantity;
        }
    }

    /**
     * Finds the index of the item with the oldest (chronologically first) expiration date.
     *
     * @return the index of the item with the oldest expiration date.
     */
    public int oldestIndex(){

        int indexOldest = 0;

        for (int index = 1; index < dates.size(); index++) {

            if(isPastDate(dates.get(index), dates.get(indexOldest))){
                indexOldest = index;
            }

        }

        return indexOldest;

    }

    /**
     * Retrieves the expiration date at the specified index.
     *
     * @param index the index of the expiration date to be retrieved.
     * @return the expiration date at the specified index.
     */
    public String getDate(int index){
        return this.dates.get(index);

    }

    /**
     * Calculates the total quantity expired based on a provided date.
     *
     * @param testDate the date to check against for expired items.
     * @return the total quantity of expired items as of the testDate.
     */
    public Double amountExpired(String testDate){
        Double expiredAmount = 0.0;
        for(int i = 0; i < this.dates.size(); i++){
            if(isPastDate(dates.get(i), testDate)){
                expiredAmount += this.quantities.get(i);
            }
        }
        return expiredAmount;
    }

    /**
     * Checks if the itemDate is before the currenDate.
     *
     * @param itemDate    the date of the item.
     * @param currenDate  the current date to compare against.
     * @return true if itemDate is before currenDate, otherwise false.
     */
    public static boolean isPastDate(String itemDate, String currenDate){

        int currentYear = Integer.parseInt(currenDate.substring(6, 10));
        int currentMonth = Integer.parseInt(currenDate.substring(3, 5));
        int currentDay = Integer.parseInt(currenDate.substring(0, 2));

        int itemYear = Integer.parseInt(itemDate.substring(6, 10));
        int itemMonth = Integer.parseInt(itemDate.substring(3, 5));
        int itemDay = Integer.parseInt(itemDate.substring(0, 2));


        if (itemYear < currentYear) {
            return true;
        } else if (itemYear == currentYear) {
            if (itemMonth < currentMonth) {
                return true;
            } else if (itemMonth == currentMonth) {
                return itemDay <= currentDay;
            }
        }
        return false;
    }


}

