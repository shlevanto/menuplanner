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
    
    public UserServiceTest() throws SQLException {
        this.us = new UserService();
        this.ud = new UserDao();
    }
        
    @Before
    public void setUp() {  
        u = new User("Paavo");
    }
    
    @After
    public void tearDown() throws SQLException {
        ud.delete("Paavo");
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
        us.create(u);
        assertEquals(true, us.check(u));
    }
    
    @Test
    public void createAndLogin() {
        // checks that the created user is logged in
        us.create(u);
         assertEquals(u, us.getLoggedIn());    
    }
    
    @Test
    public void loggedInDB () {
        // can't check null user
        u = new User("Perttixzxzxzxzx");
        assertEquals(false, us.check(u));
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
