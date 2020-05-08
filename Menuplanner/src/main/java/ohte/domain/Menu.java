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
import java.util.Collections;
import java.util.Random;


/**
 * Generates a weekly menu from recipes in database and
 * updates the dates of the recipes used in order to ensure rotation of weekly
 * menu options.
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
    /**
     * 
     * @param rs RecipeService object in use
     * @param proteins List of proteins in use for the weekly items according to config file.
     * @param sides List of sides / garnishes in use for the weekly items according to config file.
     * @throws SQLException The method gets the current recipe list from the database.
     */
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
    
    /**
     * Sets up recipe pool for the weekly menu.
     * Uses ArrayDeque for a heap structure based on the date for recipe in database
     * ensuring that recipes are rotated.
     */
    public void setupRecipePool() {
        
        for (Recipe r : recipes) {
            recipePool.put(r.getSide(), new ArrayDeque<Recipe>());
        }
        
        for (Recipe r : recipes) {
            recipePool.get(r.getSide()).addLast(r);
        }
    }
    
    /**
     * Sets up the arrays used by the generate method.
     */
    public void setupArrays() {
        ArrayList<String> sidesShuffler = new ArrayList<>();
        
        for (String side : recipePool.keySet()) {
            sidesShuffler.add(side);
        }
        
        Collections.shuffle(sidesShuffler);
        
        this.sidesArray = new String[recipePool.keySet().size()];
        
        int i = 0;
        
        for (String side: sidesShuffler) {
            this.sidesArray[i] = sidesShuffler.get(i);
            i++;
        }
        
    }
    
    /**
     * Generates the weekly menu by polling recipes from the recipePool and
     * ensures that adjacent days have different sides/garnishes.
     * 
     * @return Array of 5 recipes, weekly menu for work days. 
     */
    public Recipe[] generate() {
        setupRecipePool();
        setupArrays();
        
        for (int i = 0; i < 5; i++) {
            int j = i;
            
            if (i > sidesArray.length - 1) {
                j = 0;
            }
            
            if (!recipePool.get(sidesArray[j]).isEmpty()) {
                this.weeklyMenu[i] = recipePool.get(sidesArray[j]).poll();
            } else {
                while (true) {
                    j++;
                    if (j > 5) {
                        j = 0;
                    }
                    if (!recipePool.get(sidesArray[j]).isEmpty()) {
                        this.weeklyMenu[i] = recipePool.get(sidesArray[j]).poll();
                        break;
                    }
                }
            } 
        }
        
        return weeklyMenu;
    }
    
    /**
     * Takes the recipes on the weekly menu array and updates their date to
     * LocalTime in the database ie. pushes them to the back so menu rotation is
     * achieved.
     */
    public void updateUsedRecipes() {
        try {
            for (int i = 0; i < 5; i++) {
                rs.updateDate(weeklyMenu[i]);
            }
        } catch (Exception e) {
            
        }
    }
}
