/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ohte.domain.User;

/**
 *
 * @author levantsi
 */
public class UserTest {
    User user;
    
    public UserTest() {
        user = new User("Paavo");
    }       

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void getUid() {
        assertEquals("Paavo", user.getUid());
     }
     
}
