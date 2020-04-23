/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ohte.domain;

import java.util.Random;

/**
 * Handles Recipes. Each recipe has a name, protein and side which are displayed for
 * the user and a date which is used for database purposes.
 *
 * @author levantsi
 */
public class Recipe {
    private String name;
    private String protein;
    private String side;
    private String date;
    private Random r;
    
    /**
     * 
     * @param name The name of the recipe
     * @param protein The main protein of the recipe
     * @param side The side / garnish
     * 
     * Date is created automatically to be old. This ensures new recipes 
     * are used first when making a menu. For new recipes added by the user,
     * set k == 0 in to make it appear in the first menu generated.
     */    
    public Recipe(String name, String protein, String side, int k) {
        this.r = new Random();
        int a = r.nextInt(50) + 10;
        this.name = name;
        this.protein = protein;
        this.side = side;   
        
        if (k == 0) {
            this.date = "1900-01-01 00:00:00";
        } else {
            this.date = "1900-01-01 00:00:" + a; 
        }
    }
    
    /**
     * 
     * @param name The name of the recipe
     * @param protein The main protein of the recipe
     * @param side The side / garnish
     * @param date LocalDate variable, used when updating recipes that are on the menu
     */
    public Recipe(String name, String protein, String side, String date) {
        this.name = name;
        this.protein = protein;
        this.side = side;
        this.date = date; 
    }
    
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setProtein(String protein) {
        this.protein = protein;
    }
    
    public String getProtein() {
        return this.protein;
    }
    
    public void setSide(String side) {
        this.side = side;
    }
    
    public String getSide() {
        return this.side;
    }
    
    public String getDate() {
        return this.date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String toString() {
        return this.name + " (" + this.protein + ", " + this.side + ")";
    }
    

    

    
}
