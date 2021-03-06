/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohte.dao;

import java.sql.*;
import java.util.*;

/**
 * Defines the interface for DAO -classes
 * @author levantsi
 */
public interface Dao<T, K> {
    void create(T object) throws SQLException;
    
    T read(K key) throws SQLException;
    
    T update(T object) throws SQLException;
    
    void delete(K key) throws SQLException;
    
    List<T> list() throws SQLException;
    
    void connect() throws SQLException;
    
   
}
