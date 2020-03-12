/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author levantsi
 */
public class KassapaateTest {
    Kassapaate k;
    Maksukortti m;
    
    @Before
    public void setUp() {
         k = new Kassapaate();
         m = new Maksukortti(800);
    }
    
    @Test 
    public void kassaLuotuOikein() {
        assertEquals(100000, k.kassassaRahaa());
        assertEquals(0, k.maukkaitaLounaitaMyyty());
        assertEquals(0, k.edullisiaLounaitaMyyty());
        
    }
    
    @Test
    public void ostaKateisellaEdullinenLounas() {
        assertEquals(60, k.syoEdullisesti(60));
        assertEquals(100000, k.kassassaRahaa());
        assertEquals(0, k.edullisiaLounaitaMyyty());
        
        assertEquals(0, k.syoEdullisesti(240));
        assertEquals(100240, k.kassassaRahaa());
        assertEquals(1, k.edullisiaLounaitaMyyty());
        
        assertEquals(60, k.syoEdullisesti(300));
        assertEquals(100480, k.kassassaRahaa());
        assertEquals(2, k.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void ostaKateisellaMaukasLounas() {
        assertEquals(100, k.syoMaukkaasti(100));
        assertEquals(100000, k.kassassaRahaa());
        assertEquals(0, k.maukkaitaLounaitaMyyty());
        
        assertEquals(0, k.syoMaukkaasti(400));
        assertEquals(100400, k.kassassaRahaa());
        assertEquals(1, k.maukkaitaLounaitaMyyty());
        
        assertEquals(100, k.syoMaukkaasti(500));
        assertEquals(100800, k.kassassaRahaa());
        assertEquals(2, k.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void ostaKortillaEdullinenLounas() {
        assertEquals(true, k.syoEdullisesti(m));
        assertEquals(560, m.saldo());
        assertEquals(1, k.edullisiaLounaitaMyyty());
        assertEquals(100000, k.kassassaRahaa());
        
        k.syoEdullisesti(m);
        assertEquals(100000, k.kassassaRahaa());
        k.syoEdullisesti(m);
        assertEquals(100000, k.kassassaRahaa());
        
        assertEquals(false, k.syoEdullisesti(m));
        assertEquals(80, m.saldo());
        assertEquals(3, k.edullisiaLounaitaMyyty());
        assertEquals(100000, k.kassassaRahaa());
    }
    
    @Test
    public void ostaKortillaMaukasLounas() {
        m.lataaRahaa(20);
        
        assertEquals(true, k.syoMaukkaasti(m));
        assertEquals(420, m.saldo());
        assertEquals(1, k.maukkaitaLounaitaMyyty());
        assertEquals(100000, k.kassassaRahaa());
        
        k.syoMaukkaasti(m);
        assertEquals(100000, k.kassassaRahaa());
        
        assertEquals(false, k.syoMaukkaasti(m));
        assertEquals(20, m.saldo());
        assertEquals(2, k.maukkaitaLounaitaMyyty());
        assertEquals(100000, k.kassassaRahaa());
    }
    
    @Test
    public void lataaRahaaKassalla() {
        
        k.lataaRahaaKortille(m, -1);
        assertEquals(800, m.saldo());
        assertEquals(100000, k.kassassaRahaa());
        
        k.lataaRahaaKortille(m, 500);
        assertEquals(1300, m.saldo());
        assertEquals(100500, k.kassassaRahaa());
    }
    
}
    

