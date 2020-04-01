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
public class Recipe implements Comparable<Recipe> {
    private String name;
    private String protein;
    private String side;
    private int priority;
    
    public Recipe(String name, String protein, String side) {
        this.name = name;
        this.protein = protein;
        this.side = side;
        this.priority = 0; 
    }
    
    public Recipe(String name, String protein, String side, int priority) {
        this.name = name;
        this.protein = protein;
        this.side = side;
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
    
    public int getPriority() {
        return this.priority;
    }
    
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    @Override 
    public int compareTo(Recipe other) {
        if (this.priority > other.getPriority()) {
            return 1;
        } else if (this.priority == other.getPriority()) {
            return 0;
        }
        return -1;
    }
    
}
