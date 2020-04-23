/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohte.main;

/**
 *
 * @author levantsi
 */
import ohte.ui.*;
import ohte.setup.Setup;
import ohte.dao.*;

import java.sql.*;
import java.util.Scanner;

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
        //graafinen käyttöliittymä
        GraphicUI.main(args);
        
        
        
        //tekstikäyttöliittymä
        /*Scanner scanner = new Scanner(System.in);
        
        try {
            Setup setup = new Setup("config");
            UI ui = new UI(scanner, setup);
            ui.login();
        } catch (Exception e) {
            System.out.println("Problems with config file.");
        }
        */
        
        
       
    }
    
}
