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
public class UserDao implements Dao<User, String> {
    private Connection db;
    private Statement s;
    private PreparedStatement p;
    private ResultSet r;
    
    public UserDao() throws SQLException {
        db = DriverManager.getConnection("jdbc:sqlite:users.db");
        s = db.createStatement();
        
        try {
            s.execute("CREATE TABLE Users (id INTEGER PRIMARY KEY, uid TEXT)");
        } catch (Exception E) {}
     
        s.close();
        db.close();
        
    }
  
    @Override
    public void create(User user) throws SQLException{
        
        Connection db = DriverManager.getConnection("jdbc:sqlite:users.db");
        p = db.prepareStatement("INSERT INTO Users(uid) VALUES (?)");
        p.setString(1, user.getUid());
        
        try {
           p.executeUpdate();
        } catch (Exception e) {}
                
        p.close();
        db.close();
    }
    
    @Override
    public User read(String key) throws SQLException {
        Connection db = DriverManager.getConnection("jdbc:sqlite:users.db");
        p = db.prepareStatement("SELECT uid FROM Users WHERE uid = (?)");
        p.setString(1, key);
        
        try {
            r = p.executeQuery();
        } catch (Exception e) {}
        
        if (r.next()) {
            return new User(key);
        }
        
        return null;
        
    }
    
    @Override
    public User update(User u) throws SQLException {
        return new User("");
    }
    
    @Override
    public List<User> list() throws SQLException {
        return new ArrayList<>();
    }
    @Override
    public void delete(String key) throws SQLException {
        System.out.println("");
    }
        
}

