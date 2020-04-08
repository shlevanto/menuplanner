/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohte.domain;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author levantsi
 */
public class Setup {
    private Properties properties;
    private String dbId;
    private ArrayList<String> proteins;
    private ArrayList<String> sides;
    
    public Setup() throws Exception {
        this.properties = new Properties();
        this.properties.load(new FileInputStream("config.properties"));
        this.proteins = new ArrayList<>();
        this.sides = new ArrayList<>();
    }
    
    public String initUsersDb() throws Exception {
        return this.properties.getProperty("usersDataBase");
    }
    
    public ArrayList<String> initProteins() {
        String[] p = this.properties.getProperty("proteins").split(",");
        
   
        for (int i = 0; i < p.length; i++) {
            proteins.add(p[i]);
        }
        
        return this.proteins;
    }
    
    public ArrayList<String> initSides() {
        String[] s = this.properties.getProperty("sides").split(",");
        
   
        for (int i = 0; i < s.length; i++) {
            sides.add(s[i]);
        }
        
        return this.sides;
        
    }
    
}
