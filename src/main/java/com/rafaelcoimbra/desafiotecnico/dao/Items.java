package com.rafaelcoimbra.desafiotecnico.dao;

public class Items {

    private String itemId;
    private int itemQuantity;
    private double itemPrice;
    private double cost;

    public Items(String itemId, int itemQuantity, double itemPrice) {
        this.itemId = itemId;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
        this.cost = itemQuantity*itemPrice;
    }

    public String getItemId() {
        return itemId;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public double getCost() {
        return cost;
    }
}
