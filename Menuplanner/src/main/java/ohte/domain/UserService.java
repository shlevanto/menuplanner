/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohte.domain;
import ohte.dao.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Handles User objects using UserDao
 * @author levantsi
 */
public class UserService {
    private UserDao ud;
    private User loggedIn;
    
    /**
     * 
     * @param dbId read from config file
     * @throws SQLException if UserDao can not be created
     */
    public UserService(String dbId) throws SQLException {
        try {
            this.ud = new UserDao(dbId);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        this.loggedIn = null;
    }
    
    /**
     * Logs user in
     * @param u user to be logged in
     */
    public void login(User u) {
        this.loggedIn = u;
    }
    /**
     * Checks if user is in database
     * @param u User to be checked
     * @return boolean true if user is in database, false if not
     */
    public boolean check(User u) {
        try {
            this.loggedIn = ud.read(u.getUid());  
        } catch (Exception e) {
            System.out.println(e);
        }
        
        if (this.loggedIn == null) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Inserts a new user into database
     * @param u User to be inserted
     * @throws SQLException if User can not be inserted
     */
    public void create(User u) throws SQLException {
        try {
            ud.create(u);
            login(u);
        } catch (Exception e) {
            throw new SQLException("Ei voitu lisätä käyttäjää. " + e);
        }
    }
    
    /**
     * Returns list of users in database
     * @return ArrayList of users
     * @throws SQLException if list can not be obtained
     */
    public ArrayList<User> listUsers() throws SQLException {
        ArrayList<User> userList = (ArrayList) ud.list();
       
        return userList;
    }
    
    public User getLoggedIn() {
        return this.loggedIn;
    }
    
    public UserDao getDao() {
        return this.ud;
    } 
            
}
