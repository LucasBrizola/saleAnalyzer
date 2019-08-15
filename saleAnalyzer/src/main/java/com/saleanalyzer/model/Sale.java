package com.saleanalyzer.model;

import java.util.List;

import com.saleanalyzer.model.Item;


public class Sale implements Comparable<Sale>{
	  private String saleId;
	    private List<Item> items;
	    private String salesmanName;

	    public Sale(String saleId, List<Item> items, String salesmanName) {
	        this.saleId = saleId;
	        this.items = items;
	        this.salesmanName = salesmanName;
	    }

	    public String getSaleId() {
	        return saleId;
	    }

	    public List<Item> getItems() {
	        return items;
	    }

	    public String getSalesmanName() {
	        return salesmanName;
	    }
	    
	    //be able to show as String
	    @Override
	    public String toString() {
	        return "Sale{" +
	                "saleId=" + saleId +
	                ", items=" + items +
	                ", salesmanName='" + salesmanName + '\'' +
	                '}';
	    }

	    //function from Comparable to use as trees ordering
	    @Override
	    public int compareTo(Sale sale) {
	        return this.salesmanName.compareTo(sale.getSalesmanName());
	    }
}
