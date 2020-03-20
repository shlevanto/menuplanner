/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohte.domain;

/**
 *
 * @author levantsi
 */
public class User {
    private String uid;
    
    public User(String uid) {
        this.uid = uid;
    }
    
    public String getUid() {
        return this.uid;
    }
    
    // pitää kirjoittaa compareTo -metodi?
}
