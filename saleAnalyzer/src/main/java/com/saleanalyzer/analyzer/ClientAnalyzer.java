package com.saleanalyzer.analyzer;

import java.util.Set;

public class ClientAnalyzer implements Analyzer {

    private String clientQuantity;

    public void setClientQuantity(String clientQuantity){
    	this.clientQuantity = clientQuantity;
    }
    
    public String getClientQuantity(){
    	return clientQuantity;
    }

    private ClientAnalyzer() {
    }

    private static class StaticHolder{
    	static final ClientAnalyzer INSTANCE = new ClientAnalyzer();
    }

    public static ClientAnalyzer getSingleton(){
    	return StaticHolder.INSTANCE;
    }

    @Override
    public void analyze(Set<String> data) {
        clientQuantity(data);
    }
    //count all clients by finding the number 002 on files
    private void clientQuantity(Set<String> data) {
        setClientQuantity(String.valueOf(data.stream().filter(client -> client.contains("002ç")).count()));
    }
}
