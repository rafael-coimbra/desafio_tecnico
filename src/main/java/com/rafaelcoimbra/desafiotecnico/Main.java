package com.rafaelcoimbra.desafiotecnico;

import com.rafaelcoimbra.desafiotecnico.exceptions.PropertiesFileException;
import com.rafaelcoimbra.desafiotecnico.service.DataAnalysisSystem;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws PropertiesFileException, IOException {

        DataAnalysisSystem data = new DataAnalysisSystem();

        while (true) {
            data.fileAnalysis();
        }
    }
}