/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohte.setup;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.TreeSet;
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
    
    public Setup() throws Exception {
        this.properties = new Properties();
        this.properties.load(new FileInputStream("config.properties"));
        this.proteins = new TreeSet<>();
        this.sides = new TreeSet<>();
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
    
}
