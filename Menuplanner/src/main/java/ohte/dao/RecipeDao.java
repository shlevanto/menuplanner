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
    
    /**
     * Creating a new user
     * @param user String user is used as database name for the databaseId variable
     * @param recipes List of default recipes from the config.properties file
     * @throws SQLException if database or table can not be created.
     */
    public RecipeDao(String user, ArrayList<Recipe> recipes) throws SQLException {
        this.databaseId = user;
        
        connect();
        
        s = db.createStatement();
        
        try {
            s.execute("CREATE TABLE Recipes (id INTEGER PRIMARY KEY, name TEXT UNIQUE, protein TEXT, side TEXT, date TEXT)");
            s.execute("CREATE INDEX idx_name ON Recipes (name)");
        } catch (Exception e) {
            s.close();
            db.close();
            return;
        }
     
        s.close();
        
        // oletusreseptit tietokantaan
        for (Recipe r : recipes) {
            create(r);
        }
        
        db.close();
    }
    
    /**
     * Logging in an existing user.
     * @param user String for uid, opens the existing users database.
     */
    public RecipeDao(String user) {
        this.databaseId = user;
    }
  
    /**
     * 
     * @param recipe User inputted recipe to be added to database
     * @throws SQLException if recipe is in database already
     */
    @Override
    public void create(Recipe recipe) throws SQLException {
        connect();
        
        p = db.prepareStatement("INSERT INTO Recipes(name, protein, side, date) VALUES (?,?,?,datetime(?))");
        p.setString(1, recipe.getName());
        p.setString(2, recipe.getProtein());
        p.setString(3, recipe.getSide());
        p.setString(4, recipe.getDate());
        
        try {
            p.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("Resepti " + recipe.getName() + " on jo tietokannassa.");
        }
                
        p.close();
        db.close();        
    }
    
    /**
     * 
     * @param key Recipe name to be searched
     * @return Returns recipe with name defined by key
     * @throws SQLException if recipe is not found in database and can not be generated
     */
    @Override
    public Recipe read(String key) throws SQLException {
        connect();
        
        Recipe recipe = null;
        
        p = db.prepareStatement("SELECT * FROM Recipes WHERE name = (?)");
        p.setString(1, key);
        
        try {
            r = p.executeQuery();
        } catch (Exception e) {
            
        }
        
        if (r.next()) {
            recipe =  new Recipe(r.getString("name"), r.getString("protein"), r.getString("side"), r.getString("date"));
        } else {
            throw new SQLException("Reseptiä " + key + " ei löydy tietokannasta.");
        }
        
        p.close();
        db.close();
        
        
        return recipe;
    }
    
    /**
     * 
     * @param r Recipe to be updated
     * @return Updated recipes name
     * @throws SQLException if update can not be done in database
     */
    @Override
    public Recipe update(Recipe r) throws SQLException {
        connect();
        
        p = db.prepareStatement("UPDATE Recipes SET name = (?), protein = (?), side = (?), date = (?) WHERE name = (?)");
        p.setString(1, r.getName());
        p.setString(2, r.getProtein());
        p.setString(3, r.getSide());
        p.setString(4, r.getDate());
        p.setString(5, r.getName());
        
        try {
            p.executeUpdate();
        } catch (Exception e) {
            System.out.println("Päivitys epäonnistui. " + e);
        }
        
        p.close();
        db.close();
        
        return read(r.getName());
    }
    
    /**
     * 
     * @return ArrayList of recipes in database sorted by date in ascending order
     * @throws SQLException if database can not be read
     */
    @Override
    public List<Recipe> list() throws SQLException {
        connect();
        p = db.prepareStatement("SELECT * FROM Recipes ORDER BY date");
        
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
    
    /**
     * Deletes recipe from database
     * @param key name of recipe to be deleted
     * @throws SQLException if recipe can not be deleted eg. is not in database
     */
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
    
    /**
     * Database connections
     * @throws SQLException if connection fails
     */
    
    @Override
    public void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
            System.out.println(e);
        }
        
        try {
            db = DriverManager.getConnection("jdbc:sqlite:" + databaseId + ".db");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
        
}
