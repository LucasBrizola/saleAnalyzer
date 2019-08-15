package com.saleanalyzer.analyzer;

import org.junit.Test;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class ClientAnalyzerTest {

    @Test
    public void shouldAnalyzeClientQuantity(){

        Set<String> dataList = new TreeSet<>();
        dataList.add("002ç2345675434544345çJose da SilvaçRural");
        dataList.add("002ç2345675433444345çEduardoPereiraçRural");

        ClientAnalyzer clientAnalyzer = ClientAnalyzer.getSingleton();
        clientAnalyzer.analyze(dataList);

        assertEquals("2", clientAnalyzer.getClientQuantity());
    }
}
