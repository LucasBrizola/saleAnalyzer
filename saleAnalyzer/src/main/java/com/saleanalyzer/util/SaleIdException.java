package com.saleanalyzer.util;

public class SaleIdException extends RuntimeException {
    public SaleIdException(){
        super("Sale ID already registered.");
    }
}
