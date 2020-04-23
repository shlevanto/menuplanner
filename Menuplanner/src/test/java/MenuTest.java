/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TreeSet;
import ohte.dao.RecipeDao;
import ohte.domain.Menu;
import ohte.domain.Recipe;
import ohte.domain.RecipeService;
import ohte.setup.*;
import ohte.domain.User;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author levantsi
 */
public class MenuTest {
    RecipeService rs;
    RecipeDao rd;
    Recipe r;
    ArrayList<Recipe> recipes;
    Connection db;
    Statement s;
    Setup setup;
    
    
    public MenuTest() throws SQLException {
        try {
            setup = new Setup("config");
        } catch (Exception e) {
            
        }
        
        recipes = setup.initRecipes();
        
        this.rs = new RecipeService(new User("testiTepponen"), recipes);
        this.rd = rs.getDao();
    }
        
    @Before
    public void setUp() throws SQLException{  
    }
    
    @After
    public void tearDown() throws SQLException {
        try {
            db = DriverManager.getConnection("jdbc:sqlite:testiTepponen.db");
            s = db.createStatement();
            s.execute("DELETE FROM Recipes");
        } catch (Exception e) {
        
        s.close();
        db.close();
        }
    }
    
    @AfterClass
    public static void removeFiles() {
        File f = new File("testiTepponen.db");
        f.delete();
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void menuWorks() throws SQLException {
    // menu gets generated and no two adjacent days have the same side
        TreeSet<String> p = setup.initProteins();
        TreeSet<String> s = setup.initSides();
        
        Menu m = new Menu(rs, p, s);
        
        Recipe[] weekly = m.generate();
        
        // a recipe for each day
        for (int i = 0; i < weekly.length; i++) {
            assertNotNull(weekly[i]);
        }
        
        // no adjacent days has same sidedish
        for (int i = 0; i < weekly.length -1; i++) {
            assertNotEquals(weekly[i].getSide(), weekly[i+1].getSide());
        }
        
    
    }
}
