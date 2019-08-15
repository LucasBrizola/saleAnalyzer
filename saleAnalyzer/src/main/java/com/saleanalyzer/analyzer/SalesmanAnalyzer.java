package com.saleanalyzer.analyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.saleanalyzer.util.DataFileEmptyException;
import com.saleanalyzer.analyzer.Analyzer;
import com.saleanalyzer.analyzer.SaleAnalyzer;
import com.saleanalyzer.analyzer.SalesmanAnalyzer;
import com.saleanalyzer.model.Salesman;
import com.saleanalyzer.service.DataInService;

public class SalesmanAnalyzer implements Analyzer {

    private SaleAnalyzer saleAnalyzer = SaleAnalyzer.getSingleton();
    private String salesmanQuantity;
    private String worseSalesman;
    private List<Salesman> salesmens = new ArrayList<>();
    //created map so you can find a Salesman by his id
    private Map<Salesman, Double> salesmenSalesAmount = new HashMap<>();

    public void setSalesmanQuantity(String salesmanQuantity){
    	this.salesmanQuantity = salesmanQuantity;
    }
    
    public String getSalesmanQuantity(){
    	return salesmanQuantity;
    }
    
    public void setWorseSalesman(String worseSalesman){
    	this.worseSalesman = worseSalesman;
    }
    public String getWorseSalesman() {
    	return worseSalesman;
    }
    
    public List<Salesman> getSalesmens() {
    	return salesmens;
    }

    private SalesmanAnalyzer() {
    }

    private static class StaticHolder{
    	static final SalesmanAnalyzer INSTANCE = new SalesmanAnalyzer();
    }

    public static SalesmanAnalyzer getSingleton(){
    	return StaticHolder.INSTANCE;
    }
    //throws exception if there is no data to read in HOMEPATH/data/in
    @Override
    public void analyze(Set<String> data) {
        if(data.isEmpty()){
            throw new DataFileEmptyException();
        }else{
            salesmanQuantity(data);
            worseSalesman();
        }
    }
    //count all salesman by finding the number 001 on files
    public void salesmanQuantity(Set<String> data) {
        setSalesmanQuantity(String.valueOf(data.stream().filter(salesman -> salesman.contains("001")).count()));
    }
    //clear the map, then add salesman and finds the worse salesman by using the .min
    public void worseSalesman() {
        salesmenSalesAmount.clear();

        addToSalesmanSalesAmount();

        double worseSalesAmount = Collections.min(salesmenSalesAmount.values());

        salesmenSalesAmount.forEach((s, d) -> {
            if(d.equals(worseSalesAmount)){
                setWorseSalesman(s.getName());
            }
        });
    }
    //calculates the amount of income the salesmen did and saves on map to generate an id
    public void addToSalesmanSalesAmount(){

        saleAnalyzer.getSales().forEach(sale -> getSalesmens().forEach(salesman -> {
            if(salesman.getName().equals(sale.getSalesmanName())){
                sale.getItems().forEach(item -> {
                    double amount = getAmountPerSalesman(salesman.getName());
                    amount = amount + (item.getQuantity() * item.getPrice());
                    salesmenSalesAmount.put(salesman,amount);
                });
            }
        }));
    }
    //get salesmen from the files
    public void addToSalesman(Set<String> data) {
        salesmens.clear();

        DataInService dataInService = DataInService.getSingleton();
        data.forEach(line -> {
            dataInService.delimiterAnalyzer(line);
            if (line.contains("001" + dataInService.getDelimiter())) {
                dataInService.readSalesmanFromFile(line);
            }
        });
    }
    //loops through the HashMap to fill the amount of income made by each salesman
    public double getAmountPerSalesman(String salesmanName){
        final double[] amount = {0.0};

        salesmenSalesAmount.forEach((salesman, amountPerSalesman) -> {
            if(salesman.getName().equals(salesmanName)){
                amount[0] += amountPerSalesman;
            }
        });
        return amount[0];
    }
}
