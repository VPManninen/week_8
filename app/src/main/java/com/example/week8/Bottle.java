package com.example.week8;

public class Bottle {
    private String name;
    private String manufacturer;
    private double total_energy;
    private double price;
    private double size;
    public Bottle(int puteli){
        switch (puteli) {
            case(0):
                name = "Pepsi Max";
                manufacturer = "Pepsi";
                total_energy = 0.6;
                price = 1.80;
                size = 0.5;
                break;
            case(1):
                name = "Pepsi Max";
                manufacturer = "Pepsi";
                total_energy = 1.8;
                price = 2.20;
                size = 1.5;
                break;
            case(2):
                name = "Coca-Cola Zero";
                manufacturer = "Coca-Cola";
                total_energy = 0.5;
                price = 2;
                size = 0.5;
                break;
            case(3):
                name = "Coca-Cola Zero";
                manufacturer = "Coca-Cola";
                total_energy = 1.5;
                price = 2.5;
                size = 1.5;
                break;
            case(4):
                name = "Fanta Zero";
                manufacturer = "Fanta";
                total_energy = 0.5;
                price = 1.95;
                size = 0.5;
                break;
            default:
                name = "Pepsi Max";
                manufacturer = "Pepsi";
                total_energy = 0.6;
                price = 1.80;
                size = 0.5;
                break;
        }
    }
    public String getName(){
        return this.name;
    }
    public String getManufacturer(){
        return this.manufacturer;
    }
    public double getEnergy(){
        return this.total_energy;
    }
    public double getPrice() {
        return this.price;
    }
    public double getSize() {
        return this.size;
    }

}
