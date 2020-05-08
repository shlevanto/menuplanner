/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohte.dao;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
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
    private String databaseId;
    
    /**
     * Constructor for UserDao
     * @param dbId read from config file, defines the userdatabase's name
     * @throws SQLException if new table can not be created
     */
    public UserDao(String dbId) throws SQLException {
            
        this.databaseId = dbId;
        
        connect();
        s = db.createStatement();
        
        try {
            s.execute("CREATE TABLE Users (id INTEGER PRIMARY KEY, uid TEXT UNIQUE)");
        } catch (Exception e) {
        }
        
        s.close();
        db.close();
    }
    /**
     * Creates a new user
     * @param user defined by the user in GUI
     * @throws SQLException if username is in the database
     */
    
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
    
    /**
     * Reads and returns a user from the database
     * @param uid User id String inputted by user / GUI
     * @return User object
     * @throws SQLException 
     */
    
    
    @Override
    public User read(String uid) throws SQLException {
        connect();
        p = db.prepareStatement("SELECT uid FROM Users WHERE uid = (?)");
        p.setString(1, uid);
        
        User user = null;
        
        try {
            r = p.executeQuery();
        } catch (Exception e) {
            
        }
        
        if (r.next()) {
           
            user =  new User(uid);
        }
        
        p.close();
        db.close();
        
        return user;
        
    }
    /**
     * Not used
     * @param user
     * @return updated user
     * @throws SQLException 
     */
    @Override
    public User update(User user) throws SQLException {
        
        return user;
    }
    /**
     * Lists users in database
     * @return ArrayList of Users
     * @throws SQLException if database query fails
     */
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
    
    /**
     * Not in use
     * @param key user name
     * @throws SQLException if user can not be removed 
     */
    @Override
    public void delete(String key) throws SQLException {
       
    }
    /**
     * Database connections
     * @throws SQLException if connection fails
     */
    @Override
    public void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
            System.out.println(e);
        }
        
        try {
            db = DriverManager.getConnection("jdbc:sqlite:" + databaseId + ".db");
        } catch (Exception e) {
            System.out.println("CONNECT FAIL" + e);
        }
    }
    
    
        
}

