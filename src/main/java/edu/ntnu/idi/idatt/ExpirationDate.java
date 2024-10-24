package edu.ntnu.idi.idatt;

import java.util.ArrayList;

public class ExpirationDate {
    String name;
    ArrayList<Double> quantity = new ArrayList<>();
    ArrayList<String> date = new ArrayList<>();

    public ExpirationDate(String name, Double quantity, String expiration){
        this.name = name;
        this.quantity.add(quantity);
        this.date.add(expiration);
    }

    public void update(Double quantity, String expiration){
        this.quantity.add(quantity);
        this.date.add(expiration);
    }

    public void remove(Double quantity){
        Double remainingQuantity = quantity;
        while(remainingQuantity > 0) {
            int index = oldestIndex();
            Double freeQuantity = this.quantity.get(index);
            if (remainingQuantity < freeQuantity) {
                this.quantity.set(index, freeQuantity - remainingQuantity);
            } else {
                this.quantity.remove(index);
                this.date.remove(index);
            }
            remainingQuantity -= freeQuantity;
        }
    }


    public int oldestIndex(){

        String temp = this.date.getFirst().substring(6, 10);
        int year = Integer.parseInt(temp);
        temp = this.date.getFirst().substring(3, 5);
        int month = Integer.parseInt(temp);
        temp = this.date.getFirst().substring(0, 2);
        int day = Integer.parseInt(temp);

        int indexOldest = 0;

        for (int index = 1; index < date.size(); index++) {
            temp = this.date.get(index).substring(6, 10);
            int yearNext = Integer.parseInt(temp);
            temp = this.date.get(index).substring(3, 5);
            int monthNext = Integer.parseInt(temp);
            temp = this.date.get(index).substring(0, 2);
            int dayNext = Integer.parseInt(temp);

            if(year >= yearNext){
                if(month >= monthNext){
                    if(day >= dayNext){
                        indexOldest += 1;
                        year = yearNext;
                        month = monthNext;
                        day = dayNext;
                    }
                }
            }
        }

        return indexOldest;
    }

    public String getDate(int Index){
        return this.date.get(Index);
    }
}
