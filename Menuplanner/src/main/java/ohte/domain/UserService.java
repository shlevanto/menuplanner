/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohte.domain;

import ohte.domain.*;
import ohte.dao.*;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author levantsi
 */
public class UserService {
    private UserDao ud;
    private User loggedIn;
    
    public UserService() throws SQLException {
        try {
            this.ud = new UserDao();
        } catch (Exception e) {  
        }
        
        this.loggedIn = null;
    }
    
    public void login(User u) {
        this.loggedIn = u;
    }
    
    public boolean check(User u) {
        try {
            this.loggedIn = ud.read(u.getUid());  
        } catch (Exception e) {
        }
        
        if (this.loggedIn == null) {
            return false;
        }
        
        return true;
    }
    
    public void create(User u) {
        try {
            ud.create(u);
            login(u);
            System.out.println("logattu");
        } catch (Exception e) {
            System.out.println("Ei voitu lisätä käyttäjää." + e);
        }
    }
    
    public void listUsers() throws SQLException {
        ArrayList<User> userList = (ArrayList) ud.list();
        
        for (User u : userList) {
            System.out.println(u.getUid());
        }
    }
    
    public User getLoggedIn() {
        return this.loggedIn;
    }
            
}
