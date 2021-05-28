package com.rafaelcoimbra.desafiotecnico;

import com.rafaelcoimbra.desafiotecnico.dao.Customer;
import com.rafaelcoimbra.desafiotecnico.dao.Items;
import com.rafaelcoimbra.desafiotecnico.dao.Sales;
import com.rafaelcoimbra.desafiotecnico.dao.Salesman;
import com.rafaelcoimbra.desafiotecnico.exceptions.PropertiesFileException;
import com.rafaelcoimbra.desafiotecnico.service.DataAnalysisSystem;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void testPropertiesContent() throws PropertiesFileException {
        DataAnalysisSystem data = new DataAnalysisSystem();

        assertEquals("/home/ilegra/data/in/",data.inDirectory);
        assertEquals("/home/ilegra/data/out/",data.outDirectory);
    }

    @Test
    public void testFileContent() throws PropertiesFileException {
        DataAnalysisSystem data = new DataAnalysisSystem();

        Customer customer = new Customer("123456789","Rafael","Software");
        Salesman salesman = new Salesman("1234","Pedro",5000);
        Salesman salesman1 = new Salesman("12374","Marta",7500);

        ArrayList<Items> itemsArray = new ArrayList<>();

        Sales sales = new Sales("05",itemsArray,10000,"Pedro");
        Sales sales1 = new Sales("20",itemsArray,50000,"Marta");

        data.getCustomerArray().add(customer);
        data.getSalesmanArray().add(salesman);
        data.getSalesmanArray().add(salesman1);
        data.getSalesArray().add(sales);
        data.getSalesArray().add(sales1);

        assertEquals("Number of customers: 1\n" +
                "Number of salesman: 2\n" +
                "ID of most expensive sale: 20 ($50000.0)\n" +
                "Worst salesman: Pedro ($0.0 sold)",data.fileContent());
    }
}
