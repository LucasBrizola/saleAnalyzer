package com.saleanalyzer.service;

import com.saleanalyzer.util.DataRequirements;
import com.saleanalyzer.analyzer.ClientAnalyzer;
import com.saleanalyzer.analyzer.SaleAnalyzer;
import com.saleanalyzer.analyzer.SalesmanAnalyzer;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class DataOutService {

    private DataOutService() {
    }

    private static class StaticHolder{ static final DataOutService INSTANCE = new DataOutService();}

    static DataOutService getSingleton(){ return StaticHolder.INSTANCE; }

    public void writeOutFile() throws IOException {
        DataInService dataInService = DataInService.getSingleton();
        SalesmanAnalyzer salesmanAnalyzer = SalesmanAnalyzer.getSingleton();
        ClientAnalyzer clientAnalyzer = ClientAnalyzer.getSingleton();
        SaleAnalyzer saleAnalyzer = SaleAnalyzer.getSingleton();

        dataInService.getDataList().clear();
        saleAnalyzer.getSales().clear();

        dataInService.readFile();

        dataToLists(dataInService, salesmanAnalyzer, saleAnalyzer);

        salesmanAnalyzer.analyze(dataInService.getDataList());
        saleAnalyzer.analyze(dataInService.getDataList());
        clientAnalyzer.analyze(dataInService.getDataList());

        try(FileWriter fileWriter = new FileWriter(dataInService.getHomepath() + "/data/out/data.done.dat")){
            fileWriter.write(DataRequirements.CLIENTS.toString() + ": " + clientAnalyzer.getClientQuantity() + "\n" +
                    DataRequirements.SALESMEN.toString() + ": " + salesmanAnalyzer.getSalesmanQuantity() + "\n" +
                    DataRequirements.EXPANSIVE_SALE_ID + ": " + saleAnalyzer.getExpansiveSaleId() + "\n" +
                    DataRequirements.WORSE_SALESMAN.toString() + ": " + salesmanAnalyzer.getWorseSalesman());
        }
    }

    private void dataToLists(DataInService dataInService, SalesmanAnalyzer salesmanAnalyzer, SaleAnalyzer saleAnalyzer) {
    	saleAnalyzer.addToSales(dataInService.getDataList());
        salesmanAnalyzer.addToSalesman(dataInService.getDataList());
    }

}
