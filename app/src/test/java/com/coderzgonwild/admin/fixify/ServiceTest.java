package com.coderzgonwild.admin.fixify;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceTest {
    Service service = new Service("Plumbing", 120.0);
    @Test
    public void testgetName() {
        String expected = "Plumbing";
        assertEquals(service.getName(), expected);
    }

    @Test
    public void testgetRate() {
        Double expected = 120.0;
        assertEquals(service.getRate(), expected);
    }

    @Test
    public void testtoString() {
        String expected = service.getName()+Double.toString(120.0);
        assertEquals(service.toString(), expected);
    }
}