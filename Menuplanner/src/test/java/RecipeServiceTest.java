/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.File;

import ohte.domain.*;
import ohte.dao.*;

/**
 *
 * @author levantsi
 */
public class RecipeServiceTest {
    RecipeService rs;
    RecipeDao rd;
    Recipe r;
    Connection db;
    Statement s;
    
    
    public RecipeServiceTest() throws SQLException {
        ArrayList<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("a", "liha", "pasta"));
        
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
    
    
    @Test
    public void addAndRead() {
        //checks that created recipe ends up in database
        Recipe r = null;
        
        try {
            rs.add("b", "kala", "keitto");
        } catch (Exception e) {
            
        }
        
        try {
            r = rs.read("b");
        } catch (Exception e) {
            
        }
        
        assertEquals("b", r.getName());
        assertEquals("kala", r.getProtein());
        assertEquals("keitto", r.getSide());
    }
    
    @Test (expected = SQLException.class)
    public void readRecipeNotFound() throws SQLException {
        Recipe r = null;
        
        try {
            r = rs.read("ärbä");
        } catch (SQLException e) {
            // System.out.println(e);
            throw new SQLException("");
        }
    }
    
    @Test
    public void list() {
        
    }
    @Test
    public void delete() {
        
    }
    
    @Test
    public void update() {
        
    }
    
    @Test (expected = SQLException.class)
    public void recipeAlreadyInDB() throws SQLException {
        try {
            rs.add("u", "kana", "keitto");
            rs.add("u", "kana", "keitto");
        } catch (Exception e) {
            throw new SQLException("");
        }
        
    }
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
