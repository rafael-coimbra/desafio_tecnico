package com.rafaelcoimbra.desafiotecnico.dao;

public class Customer {

    private String formatId;
    private String cnpj;
    private String name;
    private String businessArea;

    public Customer(String cnpj, String name, String businessArea) {
        this.formatId = "002";
        this.cnpj = cnpj;
        this.name = name;
        this.businessArea = businessArea;
    }

    public String getFormatId() {
        return formatId;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getName() {
        return name;
    }

    public String getBusinessArea() {
        return businessArea;
    }
}
