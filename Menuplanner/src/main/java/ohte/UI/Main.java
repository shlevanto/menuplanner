/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohte.UI;

/**
 *
 * @author levantsi
 */
import java.sql.*;
import javafx.application.Application;

import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
/**
 *
 * @author levantsi
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello sunshine!");
        
        Connection db = DriverManager.getConnection("jdbc:sqlite:testi.db");
        Statement s = db.createStatement();
        s.execute("CREATE TABLE Tuotteet (id INTEGER PRIMARY KEY, nimi TEXT, hinta INTEGER)");
        s.execute("INSERT INTO Tuotteet (nimi,hinta) VALUES ('retiisi',7)");
        s.execute("INSERT INTO Tuotteet (nimi,hinta) VALUES ('porkkana',5)");
        s.execute("INSERT INTO Tuotteet (nimi,hinta) VALUES ('nauris',4)");
        s.execute("INSERT INTO Tuotteet (nimi,hinta) VALUES ('lanttu',8)");
        s.execute("INSERT INTO Tuotteet (nimi,hinta) VALUES ('selleri',4)");

        ResultSet r = s.executeQuery("SELECT * FROM Tuotteet");
        while (r.next()) {
            System.out.println(r.getInt("id")+" "+r.getString("nimi")+" "+r.getInt("hinta"));
        }
        
        GraphicUI.main(args);
    }
    
}