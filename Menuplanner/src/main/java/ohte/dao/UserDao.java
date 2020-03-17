/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohte.dao;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import ohte.domain.User;

/**
 *
 * @author levantsi
 */
public class UserDao implements Dao<User, Integer> {
    private Connection db;
    private Statement s;
    private ResultSet r;
    
  
    
    public void create(User user) throws SQLException{
        
        Connection db = DriverManager.getConnection("jdbc:sqlite:users.db");
    
        Statement s = db.createStatement();
        
        try {
            s.execute("CREATE TABLE Users (id INTEGER PRIMARY KEY, uid TEXT");
        } catch (Exception E) {}
        /*s.execute("INSERT INTO Tuotteet (nimi,hinta) VALUES ('retiisi',7)");
        s.execute("INSERT INTO Tuotteet (nimi,hinta) VALUES ('porkkana',5)");
        s.execute("INSERT INTO Tuotteet (nimi,hinta) VALUES ('nauris',4)");
        s.execute("INSERT INTO Tuotteet (nimi,hinta) VALUES ('lanttu',8)");
        s.execute("INSERT INTO Tuotteet (nimi,hinta) VALUES ('selleri',4)");

        ResultSet r = s.executeQuery("SELECT * FROM Tuotteet");
        while (r.next()) {
            System.out.println(r.getInt("id")+" "+r.getString("nimi")+" "+r.getInt("hinta"));
        }*/
    }
        public User read(int key) throws SQLException {
            return new User("");
        }
        @Override
        public User update(User u) throws SQLException {
            return new User("");
        }
        @Override
        public List<User> list() throws SQLException {
            return new ArrayList<>();
        }
        public void del(int o) throws SQLException {
            System.out.println("");
        }
        
}

