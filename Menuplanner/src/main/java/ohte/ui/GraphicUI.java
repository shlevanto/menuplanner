/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohte.ui;


import java.util.ArrayList;
import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.geometry.Insets;

import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import ohte.domain.*;
import ohte.setup.Setup;

/**
 *
 * @author levantsi
 */
public class GraphicUI extends Application {
    private UserService us;
    private RecipeService rs;
    private Setup setup;
 
    public void init() throws Exception {
        try {
            this.setup = new Setup("config");
        } catch (Exception e) {
        }
        
        try {
            this.us = new UserService(this.setup.initUsersDb());
        } catch (Exception e) {           
        }
    }
    
    
    @Override
    public void start(Stage window) throws Exception {
        
        // 1. login window
        
        // 1.1 login components
        window.setTitle("Menuplanner");
        Label instruction = new Label("Valitse käyttäjä:");
        ComboBox picker = new ComboBox();
        Button loginButton = new Button("Kirjaudu");
        TextField newUser = new TextField("<uusi käyttäjä>");
        Button createButton = new Button("Luo uusi käytäjä");
        Label loginError = new Label("");
        
        
        // 1.2 login layout 
        GridPane loginLayout = new GridPane();

        loginLayout.add(instruction, 0, 0);
        loginLayout.add(picker, 0 , 1);
        loginLayout.add(loginButton, 0, 2);
        loginLayout.add(newUser, 0, 3);
        loginLayout.add(createButton, 0, 4);
        
        // 1.3 login layout settings
        loginLayout.setPrefSize(300, 180);
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setVgap(10);
        loginLayout.setHgap(10);
        loginLayout.setPadding(new Insets(20, 20, 20, 20));
        
        // 1.4 get user list to ComboBox
        ArrayList<User> userList = us.listUsers();
        
        for (User u : userList) {
            picker.getItems().add(u.getUid());
        }
        
        // 1.5 button events
        loginButton.setOnAction((event) -> {
            String userToLogIn = (String) picker.getValue();
            us.login(new User(userToLogIn));
            
            this.rs = new RecipeService(us.getLoggedIn(), setup.initRecipes());
            this.setup.initProteins();
            this.setup.initSides();
            this.setup.initRecipes();
            
            try {
                mainWindow(window);
            } catch (Exception e) {
                
            }
        
        });
        
        createButton.setOnAction((event) -> {
            String userToCreate = newUser.getText();
            
            try {
                us.create(new User(userToCreate));
            } catch (Exception e) {
                loginError.setText("Käyttäjä " + userToCreate + " on jo olemassa.");
            }
        
            this.rs = new RecipeService(us.getLoggedIn(), setup.initRecipes());
            this.setup.initProteins();
            this.setup.initSides();
            this.setup.initRecipes();
            
            try {
                mainWindow(window);
            } catch (Exception e) {
                
            }
            
            
        });
        
        // 1.6 login scene
        Scene loginScene = new Scene(loginLayout);
        window.setScene(loginScene);
        window.show();

    }
    
    public void mainWindow(Stage window) throws Exception {
        Label mainText = new Label("Toinen näyttö");
        TextArea display = new TextArea();
        HBox splitScreen = new HBox();
        VBox buttons = new VBox();
        Button list = new Button("Listaa reseptit");
        Button add = new Button("Lisää resepti");
        Button del = new Button("Poista resepti");
        Button generate = new Button("Muodosta ruokalista");
                
        buttons.getChildren().add(list);
        buttons.getChildren().add(add);
        buttons.getChildren().add(del);
        buttons.getChildren().add(generate);
        
        splitScreen.getChildren().add(buttons);
        splitScreen.getChildren().add(display);
        
        
        StackPane mainLayout = new StackPane();
        mainLayout.setPrefSize(600, 360);
        mainLayout.getChildren().add(splitScreen);
        mainLayout.setAlignment(Pos.CENTER);

        Scene mainScene = new Scene(mainLayout);
        
        window.setScene(mainScene);
        window.setTitle("Menuplanner - " + us.getLoggedIn().getUid());
        window.show();

    }
    
    public static void main(String[] args) {
        
        launch(GraphicUI.class);
   
    }

    
}
