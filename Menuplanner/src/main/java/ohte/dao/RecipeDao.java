/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohte.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import ohte.domain.Recipe;

/**
 *
 * @author levantsi
 */
public class RecipeDao implements Dao<Recipe, Integer> {
    private Connection db;
    private Statement s;
    private PreparedStatement p;
    private ResultSet r;
    
    public void initialize(String user) throws SQLException {
        db = DriverManager.getConnection("jdbc:sqlite:"+user+".db");
        s = db.createStatement();
        
        try {
            s.execute("CREATE TABLE Recipes (id INTEGER PRIMARY KEY, name TEXT, protein TEXT, side TEXT, priority INT)");
        } catch (Exception E) {}
     
        s.close();
        db.close();
        
    }
  
    @Override
    public void create(Recipe recipe) throws SQLException{
        
        Connection db = DriverManager.getConnection("jdbc:sqlite:users.db");
        Statement s = db.createStatement();
        
        try {
            s.execute("CREATE TABLE Users (id INTEGER PRIMARY KEY, uid TEXT");
        } catch (Exception E) {}
        

        ResultSet r = s.executeQuery("SELECT * FROM Tuotteet");
        while (r.next()) {
            System.out.println(r.getInt("id")+" "+r.getString("nimi")+" "+r.getInt("hinta"));
        }
    }
    
    @Override
    public Recipe read(Integer key) throws SQLException {
        return new Recipe("");
    }
    
    @Override
    public Recipe update(Recipe u) throws SQLException {
        return new Recipe("");
    }
    
    @Override
    public List<Recipe> list() throws SQLException {
        return new ArrayList<>();
    }
    
    @Override
    public void delete(Integer key) throws SQLException {
        System.out.println("");
    }
        
}

    

