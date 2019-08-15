package com.saleanalyzer.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ItemTest {

    @SuppressWarnings("deprecation")
	@Test
    public void shouldCreateAnItem(){
        Item item = new Item("2", 3, 20.0);

        assertNotNull(item);
        assertEquals("2", item.getId());
        assertEquals(3, item.getQuantity());
        assertEquals(new Double(20.0), item.getPrice());
    }
}
