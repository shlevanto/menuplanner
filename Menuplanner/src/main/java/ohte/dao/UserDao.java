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
        connect();
        s = db.createStatement();
        
        try {
            s.execute("CREATE TABLE Users (id INTEGER PRIMARY KEY, uid TEXT UNIQUE)");
        } catch (Exception e) {
        }
        
        s.close();
        db.close();
    }
  
    @Override
    public void create(User user) throws SQLException {
        connect();
        p = db.prepareStatement("INSERT INTO Users(uid) VALUES (?)");
        p.setString(1, user.getUid());
        
        try {
            p.executeUpdate();
        } catch (Exception e) {
            throw new SQLException("Käyttäjä " + user.getUid() + " on jo tietokannassa.");
        }
          
        p.close();
        db.close();
        
    }
    
    @Override
    public User read(String key) throws SQLException {
        connect();
        p = db.prepareStatement("SELECT uid FROM Users WHERE uid = (?)");
        p.setString(1, key);
        
        User user = null;
        
        try {
            r = p.executeQuery();
        } catch (Exception e) {
            
        }
        
        if (r.next()) {
           
            user =  new User(key);
        }
        
        p.close();
        db.close();
        
        return user;
        
    }
    
    @Override
    public User update(User user) throws SQLException {
        
        return user;
    }
    
    @Override
    public ArrayList<User> list() throws SQLException {
        ArrayList<User> userList = new ArrayList<>();
        connect();
        p = db.prepareStatement("SELECT * FROM Users");
        
        try {
            r = p.executeQuery();
        } catch (Exception e) {
        }      
        
        while (r.next()) {
            userList.add(new User(r.getString("uid")));
        }
        
        p.close();    
        r.close();
        db.close();
                
        return userList;
    }
    
    @Override
    public void delete(String key) throws SQLException {
        connect();
        p = db.prepareStatement("DELETE FROM Users WHERE Uid = (?)");
        p.setString(1, key);
        
        try {
            p.executeUpdate();
        } catch (Exception e) {
        }

        p.close();
        db.close();
    }
    
    @Override
    public void connect() throws SQLException {
        try {
            db = DriverManager.getConnection("jdbc:sqlite:users.db");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
        
}

