package com.saleanalyzer.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SalesmanTest {

    @SuppressWarnings("deprecation")
	@Test
    public void shouldCreateASalesman(){

        Salesman salesman = new Salesman("02721739085", "Brizola", 3000.0);

        assertNotNull(salesman);
        assertEquals("02983423085", salesman.getCpf());
        assertEquals("Fabio", salesman.getName());
        assertEquals(new Double(3000.0), salesman.getSalary());

    }
}
