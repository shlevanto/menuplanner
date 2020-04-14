/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohte.setup;

import ohte.domain.Recipe;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author levantsi
 */
public class Setup {
    private Properties properties;
    private String dbId;
    private TreeSet<String> proteins;
    private TreeSet<String> sides;
    private ArrayList<Recipe> recipes;
    
    public Setup(String c) throws Exception {
        this.properties = new Properties();
        FileInputStream f = new FileInputStream(c + ".properties");
        this.properties.load(new InputStreamReader(f, Charset.forName("UTF-8")));
        this.proteins = new TreeSet<>();
        this.sides = new TreeSet<>();
        this.recipes = new ArrayList<>();
    }
    
    public String initUsersDb() throws Exception {
        return this.properties.getProperty("usersDataBase");
    }
    
    public TreeSet<String> initProteins() {
        String[] p = this.properties.getProperty("proteins").split(",");
        
   
        for (int i = 0; i < p.length; i++) {
            proteins.add(p[i]);
        }
        
        return this.proteins;
    }
    
    public TreeSet<String> initSides() {
        String[] s = this.properties.getProperty("sides").split(",");
        
   
        for (int i = 0; i < s.length; i++) {
            sides.add(s[i]);
        }
        
        return this.sides;
        
    }
    
    public ArrayList<Recipe> initRecipes() {
        String[] r = this.properties.getProperty("recipes").split(",");

        for (int i = 0; i < r.length; i += 3) {
            recipes.add(new Recipe(r[i], r[i + 1], r[i + 2]));
        }

        return this.recipes;
    }
    
}
