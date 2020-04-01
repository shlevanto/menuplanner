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
import ohte.ui.UI;
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
         // käynnistyykö graafinen käyttöliittymä
        //GraphicUI.main(args);
        
        Scanner scanner = new Scanner(System.in);
        UI ui = new UI(scanner);
        ui.login();
        
       
    }
    
}
