/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohte.ui;


import java.util.ArrayList;
import java.util.Optional;
import java.util.TreeSet;
import javafx.application.Application;

import javafx.geometry.Pos;
import javafx.geometry.Insets;

import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
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
    private TreeSet<String> proteins;
    private TreeSet<String> sides;
 
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
        Button createButton = new Button("Luo uusi käytäjä");
        Label loginError = new Label("");
        
        
        // 1.2 login layout 
        GridPane loginLayout = new GridPane();

        loginLayout.add(instruction, 0, 0);
        loginLayout.add(picker, 0 , 1);
        loginLayout.add(loginButton, 0, 2);
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
            if (picker.getValue() != null) {
                String userToLogIn = (String) picker.getValue();
            
                us.login(new User(userToLogIn));

                this.rs = new RecipeService(us.getLoggedIn(), setup.initRecipes());

                try {
                    mainWindow(window);
                } catch (Exception e) {

                }
            }
        
        });
        
        createButton.setOnAction((event) -> {
            
            try {
                newUser(window);
            } catch (Exception e) {
                
            }
            
            
        });
        
        
        
        // 1.6 login scene
        Scene loginScene = new Scene(loginLayout);
        window.setScene(loginScene);
        window.show();

    }
    
    public void newUser(Stage window) {
        TextField newUser = new TextField();
        Button createButton = new Button("Lisää käyttäjä");
        Label newUserError = new Label();
        Button backButton = new Button("Palaa");
        
        // 1.2 login layout 
        GridPane newUserLayout = new GridPane();

        newUserLayout.add(newUser, 0, 0);
        newUserLayout.add(createButton, 0 , 1);
        newUserLayout.add(newUserError, 0 , 2);
        newUserLayout.add(backButton, 0 , 2);
        
        // 1.3 login layout settings
        newUserLayout.setPrefSize(300, 180);
        newUserLayout.setAlignment(Pos.CENTER);
        newUserLayout.setVgap(10);
        newUserLayout.setHgap(10);
        newUserLayout.setPadding(new Insets(20, 20, 20, 20));
        
        createButton.setOnAction((event) -> {
            String userToCreate = newUser.getText();
            
            try {
                us.create(new User(userToCreate));
            } catch (Exception e) {
                newUserError.setText("Käyttäjä " + userToCreate + " on jo olemassa.");
            }
        
            this.rs = new RecipeService(us.getLoggedIn(), setup.initRecipes());
            
            try {
                mainWindow(window);
            } catch (Exception e) {
                
            }    
            
        });
        
        backButton.setOnAction((event) -> {
            
            try {
                start(window);
            } catch (Exception e) {
                
            }
        });
        
        // 1.6 login scene
        Scene newUserScene = new Scene(newUserLayout);
        window.setScene(newUserScene);
        window.show();
        
    }
    
    public void mainWindow(Stage window) throws Exception {
        this.proteins = this.setup.initProteins();
        this.sides = this.setup.initSides();
            
        
        Label mainText = new Label("Toinen näyttö");
        TextArea display = new TextArea();
        HBox splitScreen = new HBox();
        VBox buttons = new VBox();
        Button list = new Button("Listaa reseptit");
        list.setWrapText(true);
        list.setMinWidth(150);
        Button add = new Button("Lisää resepti");
        add.setWrapText(true);
        add.setMinWidth(150);
        Button del = new Button("Poista resepti");
        del.setWrapText(true);
        del.setMinWidth(150);
        Button generate = new Button("Tee ruokalista");
        generate.setWrapText(true);
        generate.setMinWidth(150);
        
        buttons.setPrefSize(300,360);
        buttons.setPadding(new Insets(20, 20, 20, 20));
        
        
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
        
        // Add new recipe dialog
        
        Dialog<Recipe> addRecipeDialog = new Dialog<>();
        addRecipeDialog.setTitle("Lisää resepti");
        addRecipeDialog.setResizable(true);
        Label nameLabel = new Label("Nimi: ");
        Label proteinLabel = new Label("Pääraaka-aine: ");
        Label sidesLabel = new Label("Lisuke: ");
        TextField newRecipeName = new TextField();
        ComboBox newRecipeProtein = new ComboBox();
        ComboBox newRecipeSide = new ComboBox();
        Button checkRecipeName = new Button("Tarkista");
        Label addRecipeError = new Label();
        ButtonType addNewRecipe = new ButtonType("Lisää resepti", ButtonData.OK_DONE);
        
        
        for (String p : proteins) {
            newRecipeProtein.getItems().add(p);
        }
        
        for (String s : sides) {
            newRecipeSide.getItems().add(s);
        }
        
        GridPane newRecipeGrid = new GridPane();
        newRecipeGrid.add(sidesLabel,1,1);
        newRecipeGrid.add(newRecipeSide,2,1);
 
        newRecipeGrid.add(proteinLabel,1,2);
        newRecipeGrid.add(newRecipeProtein,2,2);
        
        newRecipeGrid.add(nameLabel,1,3);
        newRecipeGrid.add(newRecipeName,2,3);
        newRecipeGrid.add(checkRecipeName,3,3);
        
        
        
        newRecipeGrid.add(addRecipeError,2,4);
        
        addRecipeDialog.getDialogPane().setContent(newRecipeGrid);
        addRecipeDialog.getDialogPane().setMinSize(2,2);

        
                   
        
        checkRecipeName.setOnAction((event) -> {
            addRecipeError.setText("");
            boolean cannotAdd = true;
        
            try {
                Recipe check = rs.read(newRecipeName.getText());
                if (check != null) {
                    addRecipeError.setText("Resepti " + newRecipeName.getText() + " on jo tietokannassa.");
                } else {
                    addRecipeDialog.getDialogPane().setContent(newRecipeGrid);
        
                }
            } catch (Exception e) {
               if (newRecipeProtein.getValue() != null && newRecipeSide.getValue() != null && !newRecipeName.getText().equals("")) {
                   addRecipeDialog.getDialogPane().getButtonTypes().add(addNewRecipe);
               }
                
            }
            
        });
        
        addRecipeDialog.setResultConverter(new Callback<ButtonType, Recipe>() {
        @Override
        public Recipe call(ButtonType b) {   
            
            if (b == addNewRecipe) {

                return new Recipe(newRecipeName.getText(), 
                        (String) newRecipeProtein.getValue(),
                        (String) newRecipeSide.getValue(),0);            
                }

                return null;
            }
        });
        

        // button actions
        
        list.setOnAction((event) -> {
            display.clear();
            ArrayList<Recipe> recipes = new ArrayList<>();
            
            try {
                recipes = rs.list();
            } catch (Exception e) {
                
            }
            
            for (Recipe r : recipes) {
                display.appendText(r.getName() + "\n");
            }
        });
        
        add.setOnAction((event) -> {
           Optional<Recipe> r = addRecipeDialog.showAndWait();
           
           if (r.isPresent()) {
               Recipe a = r.get();
               try {
                   rs.add(a.getName(), a.getProtein(), a.getSide(), 0);
               } catch (Exception e) {
                   
               }
           }
           
           
        });
        
        generate.setOnAction((event) -> {
            display.clear();
            
            
            Recipe[] weekly = new Recipe[5];
            String[] days = {"MAANANTAI", "TIISTAI", "KESKIVIIKKO", "TORSTAI", "PERJANTAI"};
            
            try {
                Menu m = new Menu(rs, proteins, sides);
                weekly = m.generate();
                m.updateUsedRecipes();
            } catch (Exception e) {
                System.out.println(e);
            }
            
            for (int i = 0; i < weekly.length; i++) {
                display.appendText(days[i] + "\n");
                display.appendText(weekly[i] + "\n");
            }
            
            
            
            
        });
        
        
        
        
        
        
                
        Scene mainScene = new Scene(mainLayout);
        
        window.setScene(mainScene);
        window.setTitle("Menuplanner - " + us.getLoggedIn().getUid());
        window.show();

    }
    
    public static void addRecipe() {
        
    }
    
    public static void main(String[] args) {
        
        launch(GraphicUI.class);
   
    }

    
}
