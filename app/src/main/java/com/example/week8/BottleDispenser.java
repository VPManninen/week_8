package com.example.week8;

import java.util.ArrayList;

public class BottleDispenser {
    private static BottleDispenser dispenser = new BottleDispenser();
    private int bottles;
    private ArrayList<Bottle> bottle_array;
    private double money;

    private BottleDispenser() {
        bottles = 5;
        money = 0;
        bottle_array = new ArrayList();
        for(int i = 0;i<bottles;i++) {
            bottle_array.add(new Bottle(i));
        }
    }

    public static BottleDispenser getInstance() {
        return dispenser;
    }

    public void addMoney(double amount) {
        money += amount;
        System.out.println("DONE");
    }

    public double buyBottle(String name, double size) {
        double retVal = -1;
        for (int i = 0; i < bottles; i++) {
            if ((bottle_array.get(i).getName().equals(name)) && (bottle_array.get(i).getSize() == size)) {
                if (money >= bottle_array.get(i).getPrice()) {
                    // System.out.println("KACHUNK! " + bottle_array.get(i).getName() + " came out of the dispenser!");
                    bottles -= 1;
                    money -= bottle_array.get(i).getPrice();
                    retVal = bottle_array.get(i).getPrice();
                    bottle_array.remove(i);
                } else {
                    retVal = 0;
                }
            }
        }
        return retVal;
    }

    public void returnMoney() {
        money = 0;
    }

    public ArrayList<String> getBottles() {
        ArrayList<String> names = new ArrayList();
        for (int i = 0; i < bottles; i++) {
            if (names.contains(bottle_array.get(i).getName()) == false) {
                names.add(bottle_array.get(i).getName());
            }
        }
        return names;
    }

    public ArrayList<Double> getSizes() {
        ArrayList<Double> sizes = new ArrayList();
        for (int i = 0; i < bottles; i++) {
            if (sizes.contains(bottle_array.get(i).getSize()) == false) {
                sizes.add(bottle_array.get(i).getSize());
            }
        }
        return sizes;
    }

    public double getMoney() {
        return this.money;
    }
}
