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
    public void saldoAlussaOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void lataaSaldoaOikein1() {
        kortti.lataaRahaa(100);
        assertEquals("saldo: 11.0", kortti.toString());
    }
    
    @Test
    public void lataaSaldoaOikein2() {
        kortti.lataaRahaa(10);
        assertEquals("saldo: 10.10", kortti.toString());
    }
    
    @Test
    public void SaldoNostaKunTarpeeksiRahaa() {
        kortti.otaRahaa(550);
        assertEquals("saldo: 4.50", kortti.toString());
    }
    
    @Test
    public void palauteNostaKunTarpeeksiRahaa() {
        assertTrue(kortti.otaRahaa(550));
    }
    
    @Test
    public void saldoNostaKunEiTarpeeksiRahaa() {
        kortti.otaRahaa(5500);
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void palauteNostaKunEiTarpeeksiRahaa() {
        assertFalse(kortti.otaRahaa(5500));
    }
    
    @Test
    public void saldoTesti() {
        assertEquals(1000, kortti.saldo());
    }
}
