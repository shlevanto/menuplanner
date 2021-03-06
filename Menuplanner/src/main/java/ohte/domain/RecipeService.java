/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohte.domain;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import ohte.dao.*;

/**
 * Handles Recipe objects by using RecipeDao
 * @author levantsi
 */
public class RecipeService {
    private RecipeDao rd;
    
    /**
     * For creating a new user with default recipes in the database.
     * @param u A recipe database is created for each user.
     * @param recipes The Recipes defined in the config file.
     */
    public RecipeService(User u, ArrayList<Recipe> recipes) {
        try {
            this.rd = new RecipeDao(u.getUid(), recipes);
        } catch (Exception e) {
            
        }
    }
    /**
     * When logging in an excisting user.
     * @param u User uid is used as database name.
     */
    public RecipeService(User u) {
        try {
            this.rd = new RecipeDao(u.getUid());
        } catch (Exception e) {
            
        }
    }
    /**
     * Adds a new recipe to the users recipe database.
     * @param name Input given by user.
     * @param protein Chosen by user from a set of options.
     * @param side Chosen by user from a set of options.
     * @param k A new recipe can be created with a set or randomized timestamp. k = 0 sets the timestamp at 1900-01-01 00:00:00
     * 
     * @throws SQLException if recipe can not be added
     */
    public void add(String name, String protein, String side, int k) throws SQLException {
        try {
            rd.create(new Recipe(name, protein, side, k));
        } catch (Exception e) {
            throw new SQLException("Ei voitu lisätä reseptiä.");
        }   
    }
    /**
     * Reads and returns a recipe from the database.
     * @param name Input by user.
     * @return Returns a Recipe object
     * @throws SQLException 
     */
    public Recipe read(String name) throws SQLException {
        Recipe recipe = null;
        
        try {
            recipe = rd.read(name);
        } catch (Exception e) {
            throw new SQLException("Ei voitu hakea reseptiä " + name);
        }
        
        return recipe;
    }
    
    /**
     * Gets a list of all recipes in the database
     * @return ArrayList<Recipe> all recipes currently in the database.
     * @throws SQLException if list is not available
     */
    public ArrayList<Recipe> list() throws SQLException {
        ArrayList<Recipe> recipeList = (ArrayList) rd.list();
        
        
        return recipeList;
    }
    
    /**
     * Removes a recipe from the database.
     * @param name Input by user
     */
    public void remove(String name) {
        try {
            rd.delete(name);
        } catch (Exception e) {
        }
        
    }
    
    /**
     * Updates the date of a Recipe to current LocalTime.
     * Recipes that end up on weekly menu get updated timestamps to ensure recipe rotation in Menu.
     * @param recipe Recipes are read from the weeklymenu array generated by Menu class.
     * @throws SQLException if recipe is not updated
     */
    public void updateDate(Recipe recipe) throws SQLException {
        Recipe returnedRecipe = null;
        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String updatedDate = now.format(format);
        recipe.setDate(updatedDate);
        
        
        try {
            returnedRecipe = rd.update(recipe);
        } catch (Exception e) {
            throw new SQLException("Ei voitu päivittää päivämäärää reseptille " + recipe.getName());
            
        }
    }
    
    public RecipeDao getDao() {
        return this.rd;
    }
}
