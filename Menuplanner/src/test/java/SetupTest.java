/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.TreeSet;
import static org.junit.Assert.*;

import ohte.domain.*;
import ohte.setup.*;

/**
 *
 * @author levantsi
 */
public class SetupTest {
    Setup s;
    
    public SetupTest() {
        try {
            File testProperties = new File("test.properties");
            FileWriter myWriter = new FileWriter("test.properties");
            myWriter.write("recipes = a,liha,pasta,b,kala,keitto" + "\n");
            myWriter.write("proteins = liha,kala" + "\n");
            myWriter.write("sides = pasta,keitto" + "\n");
            myWriter.close();
            } catch (Exception e) {

            }
        
        try {
            s = new Setup("test");
        } catch (Exception e) {
            
        }
    }
    
    @BeforeClass
    public static void setUpClass() {

    }
    
    @AfterClass
    public static void tearDownClass() {
        File toDel = new File("test.properties");
        try { 
            toDel.delete();
        } catch (Exception e) {
            
        }
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void initProteinsTest() {
        // checks that properties files proteins
        TreeSet<String> list = s.initProteins();
        
        assertEquals(true, list.contains("liha"));
        assertEquals(true, list.contains("kala"));
        assertEquals(false, list.contains("spoo"));
    }
    
    @Test
    public void initSidesTest() {
        // checks that properties files sides is read
        TreeSet<String> list = s.initSides();
        
        assertEquals(true, list.contains("pasta"));
        assertEquals(true, list.contains("keitto"));
        assertEquals(false, list.contains("spoo"));
    }
    
    @Test
    public void initRecipesTest() {
        // checks that properties files recipe is read
        ArrayList<Recipe> list = s.initRecipes();
        
        assertEquals("a", list.get(0).getName());
        assertEquals("liha", list.get(0).getProtein());
        assertEquals("pasta", list.get(0).getSide());
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
