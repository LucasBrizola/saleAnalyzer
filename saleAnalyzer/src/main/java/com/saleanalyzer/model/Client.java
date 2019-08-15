package com.saleanalyzer.model;

public class Client {

	private String cnpj;
	private String name;
	private String bussinessArea;

	public String getCnpj() {
		return cnpj;
	}

	public String getName() {
		return name;
	}

	public String getBussinessArea() {
		return bussinessArea;
	}

	public Client(String cnpj, String name, String bussinessArea) {
		super();
		this.cnpj = cnpj;
		this.name = name;
		this.bussinessArea = bussinessArea;
	}
	
	@Override
    public String toString() {
        return "Client{" +
                "cnpj='" + cnpj + '\'' +
                ", name='" + name + '\'' +
                ", bussinessArea='" + bussinessArea + '\'' +
                '}';
    }
	
	
}