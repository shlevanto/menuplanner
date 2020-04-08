/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ohte.domain;

/**
 *
 * @author levantsi
 */

public class Recipe {
    private String name;
    private String protein;
    private String side;
    private String date;
    public int priority;
        
    public Recipe(String name, String protein, String side) {
        this.name = name;
        this.protein = protein;
        this.side = side;   
        this.date = ""; 
        this.priority = -1;
    }
    
    public Recipe(String name, String protein, String side, String date) {
        this.name = name;
        this.protein = protein;
        this.side = side;
        this.date = date; 
        this.priority = -1;
    }
    
    public Recipe(String name, String protein, String side, String date, int priority) {
        this.name = name;
        this.protein = protein;
        this.side = side;
        this.date = date; 
        this.priority = priority;
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
    
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    public int getPriority() {
        return this.priority;
    }
    

    

    
}
