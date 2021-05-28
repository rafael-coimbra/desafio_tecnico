package com.rafaelcoimbra.desafiotecnico.service;

import com.rafaelcoimbra.desafiotecnico.dao.Customer;
import com.rafaelcoimbra.desafiotecnico.dao.Items;
import com.rafaelcoimbra.desafiotecnico.dao.Sales;
import com.rafaelcoimbra.desafiotecnico.dao.Salesman;
import com.rafaelcoimbra.desafiotecnico.exceptions.InvalidFormatDataIdException;
import com.rafaelcoimbra.desafiotecnico.exceptions.PropertiesFileException;
import com.rafaelcoimbra.desafiotecnico.exceptions.RepeatedDataException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Properties;

public class DataAnalysisSystem {

    private final ArrayList<Salesman> salesmanArray = new ArrayList<>();
    private final ArrayList<Sales> salesArray = new ArrayList<>();
    private final ArrayList<Customer> customerArray = new ArrayList<>();
    public final String inDirectory;
    public final String outDirectory;

    public ArrayList<Salesman> getSalesmanArray() {
        return salesmanArray;
    }

    public ArrayList<Sales> getSalesArray() {
        return salesArray;
    }

    public ArrayList<Customer> getCustomerArray() {
        return customerArray;
    }

    private Properties loadConfigurations() throws PropertiesFileException {
        Properties properties = new Properties();
        try (InputStream inputStream = DataAnalysisSystem.class.getClassLoader().getResourceAsStream("directoryPaths.properties")) {
            properties.load(inputStream);
            return properties;
        } catch (Exception e) {
            throw new PropertiesFileException("Could not access properties file.",e);
        }
    }

    public DataAnalysisSystem() throws PropertiesFileException {
        inDirectory = loadConfigurations().getProperty("InDirectory");
        outDirectory = loadConfigurations().getProperty("OutDirectory");
    }

    public void fileAnalysis() throws IOException {

        File directory = new File(inDirectory);
        File[] fileArray = directory.listFiles();

        if (fileArray != null) {
            for (File file:fileArray) {
                if (file.getName().endsWith(".dat")) {
                    String fileName = file.getName().substring(0, file.getName().lastIndexOf(".dat"));
                    salesmanArray.clear();
                    salesArray.clear();
                    customerArray.clear();

                    try (BufferedReader reader = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
                        String line;
                        String itemId;
                        int itemQuantity;
                        double itemPrice;

                        while ((line = reader.readLine()) != null) {

                            String[] data = line.split("ç");

                            if (data.length > 4 && data[3].contains(" ")) {
                                data[3] = data[3].substring(0, data[3].lastIndexOf(" "));
                                line = line.substring(0, line.indexOf(data[3]) + data[3].length());
                                data = line.split("ç");
                            }

                            boolean newItem = true;

                            switch (data[0]) {
                                case "001":
                                    for (Salesman salesman : salesmanArray) {
                                        if (salesman.getCpf().equals(data[1]) && salesman.getName().equals(data[2])) {
                                            newItem = false;
                                            break;
                                        }
                                    }
                                    if (newItem) {
                                        Salesman salesman = new Salesman(data[1], data[2], Double.parseDouble(data[3]));
                                        salesmanArray.add(salesman);
                                    } else {
                                        throw new RepeatedDataException("Salesman data duplicated (same CPF and name): " + line + ", file: " + file.getName());
                                    }
                                    break;
                                case "002":
                                    for (Customer customer : customerArray) {
                                        if (customer.getCnpj().equals(data[1])) {
                                            newItem = false;
                                            break;
                                        }
                                    }
                                    if (newItem) {
                                        Customer customer = new Customer(data[1], data[2], data[3]);
                                        customerArray.add(customer);
                                    } else {
                                        throw new RepeatedDataException("Customer data duplicated (same CNPJ): " + line + ", file: " + file.getName());
                                    }
                                    break;
                                case "003":
                                    data[2] = data[2].substring(1, data[2].length() - 1);
                                    String[] item = data[2].split(",");
                                    ArrayList<Items> listOfItems = new ArrayList<>();
                                    double totalCost = 0;
                                    for (Sales sales : salesArray) {
                                        if (sales.getSaleId().equals(data[1])) {
                                            newItem = false;
                                        }
                                    }
                                    if (newItem) {
                                        for (String s : item) {
                                            String[] itemData = s.split("-");
                                            itemId = itemData[0];
                                            itemQuantity = Integer.parseInt(itemData[1]);
                                            itemPrice = Double.parseDouble(itemData[2]);
                                            totalCost += itemQuantity * itemPrice;
                                            listOfItems.add(new Items(itemId, itemQuantity, itemPrice));
                                        }
                                        for (Salesman salesman : salesmanArray) {
                                            if (data[3].equals(salesman.getName())) {
                                                salesman.setTotalPriceSold(salesman.getTotalPriceSold() + totalCost);
                                            }
                                        }
                                        Sales sales = new Sales(data[1], listOfItems, totalCost, data[3]);
                                        salesArray.add(sales);
                                    } else {
                                        throw new RepeatedDataException("Sales data duplicated (same sale ID): " + line + ", file: " + file.getName());
                                    }
                                    break;
                                default:
                                    throw new InvalidFormatDataIdException("Not a valid format data id (001, 002 or 003) in data '" + line + "', file: " + file.getName());
                            }
                        }
                        reader.close();
                        writingInFile(outDirectory + fileName + ".done.dat", fileContent());
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new FileNotFoundException("Could not found file. Wrong file name and/or directory.");
                    }
                }
            }
        }
    }

    public String fileContent() {
        return "Number of customers: " + numberOfCustomer() +
                "\nNumber of salesman: " + numberOfSalesman() +
                "\nID of most expensive sale: " + mostExpensiveSaleId() +
                "\nWorst salesman: " + worstSalesman();
    }

    public void writingInFile(String fileName,String content) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(content);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int numberOfCustomer() {
        return customerArray.size();
    }

    public int numberOfSalesman() {
        return salesmanArray.size();
    }

    public String mostExpensiveSaleId() {
        if (salesArray.size() > 0) {
            salesArray.sort(Comparator.comparingDouble(Sales::getTotalListCost).reversed());
            return salesArray.get(0).getSaleId() + " ($" + salesArray.get(0).getTotalListCost() +")";
        } else return null;
    }

    public String worstSalesman() {
        if (salesmanArray.size() > 0) {
            salesmanArray.sort(Comparator.comparingDouble(Salesman::getTotalPriceSold));
            return salesmanArray.get(0).getName() + " ($" + salesmanArray.get(0).getTotalPriceSold() +" sold)";
        } else return null;
    }
}
