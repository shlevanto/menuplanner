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
    private int a;
    
    public Recipe(String name) {
        this.name = name;
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
    
}
