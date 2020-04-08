/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohte.domain;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.TreeSet;
import java.sql.*;
import java.util.Arrays;
import java.util.Random;


/**
 *
 * @author levantsi
 */
public class Menu {
    private RecipeService rs;
    private TreeSet<String> proteins;
    private TreeSet<String> sides;
    private ArrayList<Recipe> recipes;
    private HashMap<String, ArrayDeque<Recipe>> recipePool;
    private Recipe[] weeklyMenu;
    private String[] sidesArray;
    private Random r;
    
    public Menu(RecipeService rs, TreeSet<String> proteins, TreeSet<String> sides) throws SQLException {
        this.rs = rs;
        this.recipePool = new HashMap<>();
        this.weeklyMenu = new Recipe[5];
        this.r = new Random();
        
        try {
            this.recipes = rs.list();
        } catch (Exception e) {
            
        }
    }
    
    public void setupRecipePool() {
        
        for (Recipe r : recipes) {
            recipePool.put(r.getSide(), new ArrayDeque<Recipe>());
        }
        
        for (Recipe r : recipes) {
            recipePool.get(r.getSide()).addLast(r);
        }
    }
    
    public void setupArrays() {
        this.sidesArray = new String[recipePool.keySet().size()];
        
        int i = 0;
        
        for (String side : recipePool.keySet()) {
            sidesArray[i] = side;
            i++;
        }
        
        //System.out.println(Arrays.toString(sidesArray));
        
    }
    
    public Recipe[] generate() {
        setupRecipePool();
        setupArrays();
        
        for (int i = 0; i < 5; i++) {
            int j = i;
            
            if (i > sidesArray.length - 1) {
                j = 0;
            }
            
            if (!recipePool.get(sidesArray[j]).isEmpty()) {
                if (j > sidesArray.length) j = 0;
                this.weeklyMenu[i] = recipePool.get(sidesArray[j]).poll();
            } else {
                while (true) {
                    j++;
                    if (j > 5) j = 0;
                    
                    if (!recipePool.get(sidesArray[j]).isEmpty()) {
                        this.weeklyMenu[i] = recipePool.get(sidesArray[j]).poll();
                        break;
                    }
                }
            }
            
        }
        
        updateUsedRecipes();
        
        return weeklyMenu;
    }
    
    public void updateUsedRecipes() {
        for (int i = 0; i < 5; i++) {
            rs.updateDate(weeklyMenu[i]);
        }
    }
}
