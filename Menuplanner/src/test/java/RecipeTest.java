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
    }       

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void RecipeNoDate() {
       r = new Recipe("a", "liha", "pasta");
       assertNotEquals("", r.getDate());
    }
 
        
    @Test
    public void RecipeWithDate() {
        r = new Recipe("a", "liha", "pasta", "2020-01-01 12:30:12");
        assertNotEquals("", r.getDate());
    }
    
}
