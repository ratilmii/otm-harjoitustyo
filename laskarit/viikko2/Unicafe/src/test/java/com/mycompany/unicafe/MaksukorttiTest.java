package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals(1000, kortti.saldo());
    }
    
    @Test 
    public void lataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(200);
        assertEquals("saldo: 12.0", kortti.toString());
    }
    
    @Test 
    public void rahanOttaminenToimii() {
        kortti.otaRahaa(500);
        assertEquals("saldo: 5.0", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenEiToimi() {
        kortti.otaRahaa(1500);
        assertEquals("saldo: 10.0", kortti.toString());
    }
}
