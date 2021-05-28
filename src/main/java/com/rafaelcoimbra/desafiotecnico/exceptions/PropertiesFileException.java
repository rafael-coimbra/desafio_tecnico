package com.rafaelcoimbra.desafiotecnico.exceptions;

public class PropertiesFileException extends Exception {

    public PropertiesFileException(String message, Exception e) {
        super(message,e);
    }
}
