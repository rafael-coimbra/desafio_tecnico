package com.rafaelcoimbra.desafiotecnico.dao;

import java.util.ArrayList;

public class Sales {

    private String formatId;
    private String saleId;
    private ArrayList<Items> itemsList;
    private double totalListCost;
    private String name;

    public Sales(String saleId, ArrayList<Items> itemsList, double totalListCost, String name) {
        this.formatId = "003";
        this.saleId = saleId;
        this.itemsList = itemsList;
        this.totalListCost = totalListCost;
        this.name = name;
    }

    public String getFormatId() {
        return formatId;
    }

    public String getSaleId() {
        return saleId;
    }

    public ArrayList<Items> getItemsList() {
        return itemsList;
    }

    public double getTotalListCost() {
        return totalListCost;
    }

    public String getName() {
        return name;
    }
}
