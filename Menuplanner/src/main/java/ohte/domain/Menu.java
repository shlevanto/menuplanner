/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohte.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import java.sql.*;

/**
 *
 * @author levantsi
 */
public class Menu {
    private RecipeService rs;
    private TreeSet<String> proteins;
    private TreeSet<String> sides;
    private ArrayList<Recipe> recipes;
    private HashMap<String, Recipe> recipePool;
    
    public Menu(RecipeService rs, TreeSet<String> proteins, TreeSet<String> sides) throws SQLException {
        this.rs = rs;
        
        try {
            this.recipes = rs.list();
        } catch (Exception e) {
            
        }
        
        
    }
}
