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
 *
 * @author levantsi
 */
public class RecipeService {
    private RecipeDao rd;
    
    public RecipeService(User u) {
        try {
            this.rd = new RecipeDao(u.getUid());
        } catch (Exception e) {
            
        }
    }
    
    public void add(String name, String protein, String side) {
        try {
            rd.create(new Recipe(name, protein, side));
        } catch (Exception e) {
            System.out.println("Ei voitu lisätä reseptiä.");
        }   
    }
    
    public Recipe read(String name) {
        Recipe recipe = null;
        
        try {
            recipe = rd.read(name);
        } catch (Exception e) {
            System.out.println("Ei voitu hakea reseptiä " + name);
        }
        
        return recipe;
    }
    
    public ArrayList<Recipe> list() throws SQLException {
        ArrayList<Recipe> recipeList = (ArrayList) rd.list();
        
        
        return recipeList;
    }
    
    public void remove(String name) {
        try {
            rd.delete(name);
        } catch (Exception e) {
        }
        
    }
    
    public void updateDate(Recipe recipe) {
        Recipe returnedRecipe = null;
        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String updatedDate = now.format(format);
        recipe.setDate(updatedDate);
        
        
        try {
            returnedRecipe = rd.update(recipe);
        } catch (Exception e) {
            System.out.println("Ei voitu päivittää päivämäärää reseptille " + recipe.getName());
            System.out.println(e);
        }
    }
}
