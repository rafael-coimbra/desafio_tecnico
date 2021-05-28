package com.rafaelcoimbra.desafiotecnico.dao;

public class Salesman {

    private String formatId;
    private String cpf;
    private String name;
    private double salary;
    private double totalPriceSold;

    public Salesman(String cpf, String name, double salary) {
        this.formatId = "001";
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
        this.totalPriceSold = 0;
    }

    public Salesman(String name, double totalPriceSold) {
        this.name = name;
        this.totalPriceSold = totalPriceSold;
    }

    public String getFormatId() {
        return formatId;
    }

    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public double getTotalPriceSold() {
        return totalPriceSold;
    }

    public void setTotalPriceSold(double totalPriceSold) {
        this.totalPriceSold = totalPriceSold;
    }
}
