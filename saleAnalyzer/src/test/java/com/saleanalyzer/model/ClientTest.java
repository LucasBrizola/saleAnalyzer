package com.saleanalyzer.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ClientTest {

    @Test
    public void shouldCreateAClient(){
        Client client = new Client("68040310000173", "Lucas", "Developer");

        assertNotNull(client);
        assertEquals("68040310000173", client.getCnpj());
        assertEquals("Lucas", client.getName());
        assertEquals("Developer", client.getBussinessArea());
    }
}
