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
 * @author ramirami
 */
public class KassapaateTest {
    
    Kassapaate unicafeExactum;
    Maksukortti kortti;
    
    @Before
    public void setup() {
        unicafeExactum = new Kassapaate();
    }
    
    @Test
    public void luotuKassapaateOlemassa(){
        assertTrue(unicafeExactum!=null);
    }
    
    @Test
    public void KassassaRahaaAlussaOikein() {
        assertEquals(100000, unicafeExactum.kassassaRahaa());
    }
    
    @Test
    public void myytyjaAnnoksiaAlussa() {
        assertEquals(0, unicafeExactum.edullisiaLounaitaMyyty());
        assertEquals(0, unicafeExactum.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullinenKateismaksuOnnistui() {
        int maksu = 300;
        int vaihtoraha = unicafeExactum.syoEdullisesti(maksu);
        assertEquals(100240, unicafeExactum.kassassaRahaa());
        assertEquals(60, vaihtoraha);
        assertEquals(1, unicafeExactum.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullinenKateismaksuEpaonnistui() {
        int maksu = 200;
        int vaihtoraha = unicafeExactum.syoEdullisesti(maksu);
        assertEquals(100000, unicafeExactum.kassassaRahaa());
        assertEquals(200, vaihtoraha);
        assertEquals(0, unicafeExactum.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukasKateismaksuOnnistui() {
        int maksu = 450;
        int vaihtoraha = unicafeExactum.syoMaukkaasti(maksu);
        assertEquals(100400, unicafeExactum.kassassaRahaa());
        assertEquals(50, vaihtoraha);
        assertEquals(1, unicafeExactum.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukasKateismaksuEpaonnistui() {
        int maksu = 200;
        int vaihtoraha = unicafeExactum.syoMaukkaasti(maksu);
        assertEquals(100000, unicafeExactum.kassassaRahaa());
        assertEquals(200, vaihtoraha);
        assertEquals(0, unicafeExactum.maukkaitaLounaitaMyyty());
    }
    
    @Test 
    public void edullinenKorttimaksuOnnistui() {
        kortti = new Maksukortti(500);
        unicafeExactum.syoEdullisesti(kortti);
        assertEquals(1, unicafeExactum.edullisiaLounaitaMyyty());
        assertEquals(260, kortti.saldo());
    }
    
    @Test 
    public void edullinenKorttimaksuEpaonnistui() {
        kortti = new Maksukortti(100);
        unicafeExactum.syoEdullisesti(kortti);
        assertEquals(0, unicafeExactum.edullisiaLounaitaMyyty());
        assertEquals(100, kortti.saldo());
    }
    
    @Test 
    public void maukasKorttimaksuOnnistui() {
        kortti = new Maksukortti(500);
        unicafeExactum.syoMaukkaasti(kortti);
        assertEquals(1, unicafeExactum.maukkaitaLounaitaMyyty());
        assertEquals(100, kortti.saldo());
    }
    
    @Test 
    public void maukasKorttimaksuEpaonnistui() {
        kortti = new Maksukortti(100);
        unicafeExactum.syoMaukkaasti(kortti);
        assertEquals(0, unicafeExactum.edullisiaLounaitaMyyty());
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void kortilleRahanLataaminenOnnistui() {
        kortti = new Maksukortti(0);
        unicafeExactum.lataaRahaaKortille(kortti, 500);
        assertEquals(500, kortti.saldo());
        assertEquals(100500, unicafeExactum.kassassaRahaa());
    }
    
    @Test
    public void kortilleRahanLataaminenEpaonnistui() {
        kortti = new Maksukortti(0);
        unicafeExactum.lataaRahaaKortille(kortti, -50);
        assertEquals(0, kortti.saldo());
        assertEquals(100000, unicafeExactum.kassassaRahaa());
    }
}
