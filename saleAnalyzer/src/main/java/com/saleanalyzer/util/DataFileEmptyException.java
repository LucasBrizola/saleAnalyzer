package com.saleanalyzer.util;

public class DataFileEmptyException extends RuntimeException {
    public DataFileEmptyException(){
        super("The file created at %HOMEPATH%/data/in contains no data to analyze.");
    }
}