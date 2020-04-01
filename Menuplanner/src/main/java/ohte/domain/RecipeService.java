/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohte.domain;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    public void list() throws SQLException {
        ArrayList<Recipe> recipeList = (ArrayList) rd.list();
        
        for (Recipe r : recipeList) {
            System.out.println(r.getName() + " (" + r.getProtein() + ", " + r.getSide() + ")");
        }
    }
}
