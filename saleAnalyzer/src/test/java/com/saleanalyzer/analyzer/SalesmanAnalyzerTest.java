package com.saleanalyzer.analyzer;

import org.junit.Test;
import java.util.Set;
import java.util.TreeSet;
import static org.junit.Assert.assertEquals;

public class SalesmanAnalyzerTest {


    @Test
    public void shouldAnalyzeSalesmanQuantity(){

        Set<String> dataList = new TreeSet<>();
        dataList.add("001ç1234567891234çPedroç50000");
        dataList.add("001ç3245678865434çPauloç40000.99");

        SalesmanAnalyzer salesmanAnalyzer = SalesmanAnalyzer.getSingleton();
        salesmanAnalyzer.salesmanQuantity(dataList);

        assertEquals("2", salesmanAnalyzer.getSalesmanQuantity());
    }

    @Test
    public void shouldAnalyzeWorseSalesman(){
        Set<String> dataList = new TreeSet<>();
        dataList.add("001ç1234567891234çPedroç50000");
        dataList.add("001ç3245678865434çPauloç40000.99");
        dataList.add("003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo");
        dataList.add("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro");

        SaleAnalyzer saleAnalyzer = SaleAnalyzer.getSingleton();
        saleAnalyzer.addToSales(dataList);

        SalesmanAnalyzer salesmanAnalyzer = SalesmanAnalyzer.getSingleton();
        salesmanAnalyzer.addToSalesman(dataList);
        salesmanAnalyzer.worseSalesman();

        assertEquals("Paulo", salesmanAnalyzer.getWorseSalesman());
    }
}
