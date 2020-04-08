/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ohte.setup.Setup;
import java.util.TreeSet;
import org.junit.Test;
import static org.junit.Assert.*;
import ohte.domain.*;

/**
 *
 * @author levantsi
 */
public class RecipeTest {
    Setup s;
    Recipe r;
    TreeSet<String> proteins;
    TreeSet<String> sides;
    
    public RecipeTest() {
        proteins = new TreeSet<>();
        sides = new TreeSet<>();
        
        try {
            Setup s = new Setup();
            proteins = s.initProteins();
            sides = s.initSides();
        } catch (Exception e) {
            System.out.println("Could not initialize from config.properties. Check config file.");
        }
    }       

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void RecipeNoDateNoPriority1() {
       r = new Recipe("a", proteins.last(), sides.last());
       assertEquals("", r.getDate());
    }
 
    @Test
    public void RecipeNoDateNoPriority2() {
       r = new Recipe("a", proteins.last(), sides.last());
       assertEquals(-1, r.getPriority());
    }
    
    @Test
    public void RecipeWithDateNoPriority1() {
        r = new Recipe("a", proteins.last(), sides.last(), "2020-01-01 12:30:12");
        assertNotEquals("", r.getDate());
    }
    
    @Test
    public void RecipeWithDateNoPriority2() {
        r = new Recipe("a", proteins.last(), sides.last(), "2020-01-01 12:30:12");
        assertEquals(-1, r.getPriority());
    }
    
    @Test
    public void RecipeWithDateAndPriority1() {
        r = new Recipe("a", proteins.last(), sides.last(), "2020-01-01 12:30:12", 1);
        assertNotEquals("", r.getDate());
    }
    
    @Test 
    public void RecipeWithDateAndPriority2() {
        r = new Recipe("a", proteins.last(), sides.last(), "2020-01-01 12:30:12", 1);
        assertEquals(1, r.getPriority());
    }
    
    @Test
    public void gettersAndSetters() {
        r = new Recipe("","","");
        
        r.setName("a");
        assertNotEquals("", r.getName());
        r.setProtein(proteins.last());
        assertNotEquals("", r.getProtein());
        r.setSide(sides.last());
        assertNotEquals("", r.getSide());
        r.setDate("2020-01-01 12:30:12");
        assertNotEquals("", r.getDate());
        r.setPriority(1);
        assertNotEquals(-1, r.getPriority());
        
        
    }
     
}
