package edu.ntnu.idi.idatt;

import java.util.ArrayList;


public class ExpirationDate {
    String name;
    ArrayList<Double> quantities = new ArrayList<>();
    ArrayList<String> dates = new ArrayList<>();


    public ExpirationDate(String name, Double quantity, String expiration){
        this.name = name;
        this.quantities.add(quantity);
        this.dates.add(expiration);
    }

    public void update(Double quantity, String expiration){
        this.quantities.add(quantity);
        this.dates.add(expiration);
    }

    public void remove(Double quantity){
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


    //returns the item with the chronologically first expiration date, will not work BC.
    public int oldestIndex(){

        int indexOldest = 0;

        for (int index = 1; index < dates.size(); index++) {

            if(isPastDate(dates.get(index), dates.get(indexOldest))){
                indexOldest = index;
            }

        }

        return indexOldest;

    }



    public String getDate(int Index){
        return this.dates.get(Index);

    }

    public Double amountExpired(String testDate){
        Double expiredAmount = 0.0;
        for(int i = 0; i < this.dates.size(); i++){
            if(isPastDate(dates.get(i), testDate)){
                expiredAmount += this.quantities.get(i);
            }
        }
        return expiredAmount;
    }

    //returns true if itemDate is before currenDate
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

