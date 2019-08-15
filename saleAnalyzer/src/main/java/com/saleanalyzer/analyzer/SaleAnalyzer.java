package com.saleanalyzer.analyzer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.saleanalyzer.analyzer.SalesmanAnalyzer;
import com.saleanalyzer.service.DataInService;
import com.saleanalyzer.model.Sale;

public class SaleAnalyzer implements Analyzer{

	//using treeSet to not have sales duplicates
	private Set<Sale> sales = new TreeSet<>();
	//using hashMap to save prices and serialize them so they can be found by id
    private Map<Sale, Double> totalItemsPricePerSale = new HashMap<>();
    private Double expansiveSalePrice;
    private String expansiveSaleId;

    public String getExpansiveSaleId(){
    	return expansiveSaleId;
    }
    
    public void setExpansiveSaleId(String expansiveSaleId){
    	this.expansiveSaleId = expansiveSaleId;
    }
    
    public Set<Sale> getSales(){
    	return sales;
    }

    private SaleAnalyzer() {
    }

    @Override
    public void analyze(Set<String> data) {
        mostExpansiveSaleId();
    }
    
    //get the id from the highest sale and saves as the ExpansiveSaleId
    private void mostExpansiveSaleId() {
        itemsPriceAmountPerSale();

        totalItemsPricePerSale.forEach((sale, saleAmount) -> {
            if(saleAmount.equals(expansiveSalePrice)){
                setExpansiveSaleId(sale.getSaleId());
            }
        });
    }
    //go through the items from sales list (making a stream), get items price and multiply
	//for the quantity, then sum all theses prices and saves in a map to hash them
    private void itemsPriceAmountPerSale(){
        //cleans the map first
    	totalItemsPricePerSale.clear();
        sales.forEach(sale -> {
            double itemsPriceAmountOnSale = sale.getItems().stream().mapToDouble(item ->
                    (item.getPrice() * item.getQuantity())).sum();
            totalItemsPricePerSale.put(sale, itemsPriceAmountOnSale);
        });
        getExpansiveSalePrice();
    }
    
    //using .max to get highest sale price 
    private void getExpansiveSalePrice() {
        expansiveSalePrice = Collections.max(totalItemsPricePerSale.values());
    }

    private static class StaticHolder{ static final SaleAnalyzer INSTANCE = new SaleAnalyzer();}
    //use the singleton design pattern to get only one instance of the SaleAnalyzer
    public static SaleAnalyzer getSingleton(){ return StaticHolder.INSTANCE; }
    //clear the sales tree, then get the data from the files using the dataInService, 
    public void addToSales(Set<String> data) {
        SalesmanAnalyzer salesmanAnalyzer = SalesmanAnalyzer.getSingleton();
        salesmanAnalyzer.getSalesmens().clear();
        sales.clear();

        DataInService dataInService = DataInService.getSingleton();
        data.forEach(line -> {
            dataInService.delimiterAnalyzer(line);
            if (line.contains("003" + dataInService.getDelimiter())) {
                dataInService.readSalesFromFile(line);
            }
        });
    }
}
