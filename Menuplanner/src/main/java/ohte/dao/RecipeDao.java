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
        
        connect();
        
        s = db.createStatement();
        
        try {
            s.execute("CREATE TABLE Recipes (id INTEGER PRIMARY KEY, name TEXT UNIQUE, protein TEXT, side TEXT, date TEXT)");
            s.execute("CREATE INDEX idx_name ON Recipes (name)");
        } catch (Exception e) {
        
        }
     
        s.close();
        db.close();
    }
  
    @Override
    public void create(Recipe recipe) throws SQLException {
        connect();
        
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
        connect();
        
        Recipe recipe = null;
        
        p = db.prepareStatement("SELECT * FROM Recipes WHERE name = (?)");
        p.setString(1, key);
        
        try {
            r = p.executeQuery();
        } catch (Exception e) {
            throw new SQLException("Reseptiä " + key + " ei löydy tietokannasta.");
        }
        
        if (r.next()) {
            recipe =  new Recipe(r.getString("name"), r.getString("protein"), r.getString("side"), r.getString("date"));
        }
        
        p.close();
        db.close();
        
        
        return recipe;
    }
    
    @Override
    public Recipe update(Recipe u) throws SQLException {
        connect();
        
        p = db.prepareStatement("UPDATE Recipes SET name = (?), protein = (?), side = (?), date = (?) WHERE name = (?)");
        p.setString(1, u.getDate());
        p.setString(2, u.getName());
        p.setString(3, u.getProtein());
        p.setString(4, u.getDate());
        p.setString(5, u.getName());
        
        try {
            p.executeUpdate();
        } catch (Exception e) {
            System.out.println("Päivitys epäonnistui. " + e);
        }
        
        p.close();
        db.close();
        
        return read(u.getName());
    }
    
    @Override
    public List<Recipe> list() throws SQLException {
        connect();
        p = db.prepareStatement("SELECT * FROM Recipes");
        
        ArrayList<Recipe> recipeList = new ArrayList<>();
        
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
        connect();
        p = db.prepareStatement("DELETE FROM Recipes WHERE name = (?)");
        p.setString(1, key);

        
        try {
            p.executeUpdate();
        } catch (Exception e) {
            System.out.println("Reseptiä ei voitu poistaa tietokannasta.");
        }
        
        p.close();
        db.close();
    }
    
    @Override
    public void connect() throws SQLException {
        try {
            db = DriverManager.getConnection("jdbc:sqlite:" + databaseId + ".db");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
        
}
