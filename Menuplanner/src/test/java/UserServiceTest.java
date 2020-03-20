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
    }
        
    @Before
    public void setUp() {  
        u = new User("Paavo");
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void loginTest() {
        us.login(u);
        assertEquals("Paavo", us.getLoggedIn().getUid());
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
