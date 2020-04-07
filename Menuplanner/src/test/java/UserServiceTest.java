/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.File;

import ohte.domain.*;
import ohte.dao.*;

/**
 *
 * @author levantsi
 */
public class UserServiceTest {
    UserService us;
    UserDao ud;
    User u;
    Connection db;
    Statement s;
    
    
    public UserServiceTest() throws SQLException {
        this.us = new UserService("test");
        this.ud = us.getDao();
    }
        
    @Before
    public void setUp() throws SQLException{  
        try {
            db = DriverManager.getConnection("jdbc:sqlite:test.db");
            s = db.createStatement();
            s.execute("DELETE * FROM Users");
        } catch (Exception e) {
        
        s.close();
        db.close();
        }
        
        u = new User("Paavo");
        
    }
    
    @After
    public void tearDown() throws SQLException {
        try {
            db = DriverManager.getConnection("jdbc:sqlite:test.db");
            s = db.createStatement();
            s.execute("DELETE FROM Users");
        } catch (Exception e) {
        
        s.close();
        db.close();
        }
    }
    
    @AfterClass
    public static void removeFiles() {
        File f = new File("test.db");
        f.delete();
    }
    
    
    
    @Test
    public void login() {
        // checks if login updates the loggedIn variable
        us.login(u);
        assertEquals(u, us.getLoggedIn());
    }
    
    @Test
    public void create() {
        //checks that created user ends up in database
        try {
            us.create(u);
        } catch (Exception e) {
            
        }
        assertEquals(true, us.check(u));
    }
    
    @Test
    public void createAndLogin() {
        // checks that the created user is logged in
        User v = new User("Pertti");
        
        try { 
            us.create(v);
        } catch (Exception e) {
            
        }
        
        us.login(v);
        assertEquals(v, us.getLoggedIn());    
    }
    
    @Test
    public void loggedInDB () {
        // check returns false if user is not in database
        u = new User("Perttixzxzxzxzx");
        assertEquals(false, us.check(u));
    }
    
    @Test
    public void check() {
        // checks if logged in user is in the database
        us.check(new User("armama"));
        assertEquals(false, us.check(u));
    }
    
    @Test
    public void listUsersEmpty() {
        int a = 0;
        
        try {
            ArrayList<User> users = us.listUsers();
            a = users.size();
        } catch (Exception e) {            
        }
        
        assertEquals(a,0);
    }
    
    @Test(expected = SQLException.class)
    public void userAlreadyInDB() throws SQLException {
        try {
            us.create(new User("u"));
            us.create(new User("u"));
        } catch (Exception e) {
            throw new SQLException("");
        }
        
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
