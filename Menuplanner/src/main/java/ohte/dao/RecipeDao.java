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
public class RecipeDao implements Dao<Recipe, String> {
    private Connection db;
    private Statement s;
    private PreparedStatement p;
    private ResultSet r;
    private String databaseId;
    
    public RecipeDao(String user) throws SQLException {
        this.databaseId = user;
        
        db = DriverManager.getConnection("jdbc:sqlite:" + databaseId + ".db");
        s = db.createStatement();
        
        try {
            s.execute("CREATE TABLE Recipes (id INTEGER PRIMARY KEY, name TEXT UNIQUE, protein TEXT, side TEXT, date TEXT)");
        } catch (Exception e) {
        
        }
     
        s.close();
        db.close();
        
    }
  
    @Override
    public void create(Recipe recipe) throws SQLException {
        
        Connection db = DriverManager.getConnection("jdbc:sqlite:" + databaseId + ".db");
        p = db.prepareStatement("INSERT INTO Recipes(name, protein, side, date) VALUES (?,?,?,datetime('now','localtime'))");
        p.setString(1, recipe.getName());
        p.setString(2, recipe.getProtein());
        p.setString(3, recipe.getSide());
        
        try {
            p.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("Resepti " + recipe.getName() + " on jo tietokannassa.");
        }
                
        p.close();
        db.close();
    }
    
    @Override
    public Recipe read(String key) throws SQLException {
        return new Recipe("","","");
    }
    
    @Override
    public Recipe update(Recipe u) throws SQLException {
        return new Recipe("","","");
    }
    
    @Override
    public List<Recipe> list() throws SQLException {
        ArrayList<Recipe> recipeList = new ArrayList<>();
        
        Connection db = DriverManager.getConnection("jdbc:sqlite:" + databaseId + ".db");
        p = db.prepareStatement("SELECT * FROM Recipes");
        
        try {
            r = p.executeQuery();
        } catch (Exception e) {
        }      
        
        while (r.next()) {
            recipeList.add(new Recipe(r.getString("name"), r.getString("protein"), r.getString("side"), r.getString("date")));
        }
        
        p.close();
        r.close();
        db.close();
        
        
        return recipeList;
    }
    
    @Override
    public void delete(String key) throws SQLException {
        Connection db = DriverManager.getConnection("jdbc:sqlite:" + databaseId + ".db");
        p = db.prepareStatement("SELECT (id) FROM Recipes WHERE name = (?)");
        p.setString(1, key);
        
        try {
            r = p.executeQuery();
        } catch (Exception e) {
        }      
        
        if (!r.next()) {
            throw new SQLException ("Reseptiä " + key + "ei löydy tietokannasta.");
        } else {
            p = db.prepareStatement("DELETE FROM Recipes WHERE id = (?)");
            p.setInt(1, r.getInt("id"));
        }
        
        try {
            p.executeUpdate();
        } catch (Exception e) {
            
        }
        
        p.close();
        r.close();
        db.close();
        
        
        
    }
        
}

    

