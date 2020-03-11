/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohte.menuplanner;

import javafx.application.Application;

import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author levantsi
 */
public class GraphicUI extends Application{
    public static void main(String[] args) {
        launch(GraphicUI.class);
    }
    
    @Override
    public void start(Stage ikkuna) {
        
        Label pohjoinen = new Label("NORTH");
        Label etela = new Label("SOUTH");
        Label ita = new Label("EAST");
        
        BorderPane asettelu = new BorderPane();
        asettelu.setTop(pohjoinen);
        asettelu.setBottom(etela);
        asettelu.setRight(ita);
        
        Scene nakyma = new Scene(asettelu);
        
        ikkuna.setScene(nakyma);
        ikkuna.show();
        
    }

    
}
