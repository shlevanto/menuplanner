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
        
        //components
        window.setTitle("Menuplanner");
        Label instruction = new Label("Valitse käyttäjä:");
        ComboBox picker = new ComboBox();
        Button loginButton = new Button("Kirjaudu");
        Button createButton = new Button("Luo uusi käytäjä");
        Label loginError = new Label("");
        
        
        //layout 
        GridPane loginLayout = new GridPane();

        loginLayout.add(instruction, 0, 0);
        loginLayout.add(picker, 0 , 1);
        loginLayout.add(loginButton, 0, 2);
        loginLayout.add(createButton, 0, 4);
        
        //layout settings
        loginLayout.setPrefSize(300, 180);
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setVgap(10);
        loginLayout.setHgap(10);
        loginLayout.setPadding(new Insets(20, 20, 20, 20));
        
        //get user list to ComboBox
        ArrayList<User> userList = us.listUsers();
        
        for (User u : userList) {
            picker.getItems().add(u.getUid());
        }
        
        //button events
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
        
        
        
        //login scene
        Scene loginScene = new Scene(loginLayout);
        window.setScene(loginScene);
        window.show();

    }
    
    public void newUser(Stage window) {
        //components
        TextField newUser = new TextField();
        Button createButton = new Button("Lisää käyttäjä");
        Label newUserError = new Label();
        Button backButton = new Button("Palaa");
        
        //layout 
        GridPane newUserLayout = new GridPane();

        newUserLayout.add(newUser, 0, 0);
        newUserLayout.add(createButton, 0 , 1);
        newUserLayout.add(newUserError, 0 , 2);
        newUserLayout.add(backButton, 0 , 2);
        
        //layout settings
        newUserLayout.setPrefSize(300, 180);
        newUserLayout.setAlignment(Pos.CENTER);
        newUserLayout.setVgap(10);
        newUserLayout.setHgap(10);
        newUserLayout.setPadding(new Insets(20, 20, 20, 20));
        
        // button actions
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
        
        // scene
        Scene newUserScene = new Scene(newUserLayout);
        window.setScene(newUserScene);
        window.show();
        
    }
    
    public void mainWindow(Stage window) throws Exception {
        // init proteins and sides
        this.proteins = this.setup.initProteins();
        this.sides = this.setup.initSides();
            
        // components
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
        
        // button pane settings and layout
        buttons.setPrefSize(300,360);
        buttons.setPadding(new Insets(20, 20, 20, 20));
        
        
        buttons.getChildren().add(list);
        buttons.getChildren().add(add);
        buttons.getChildren().add(del);
        buttons.getChildren().add(generate);
        
        splitScreen.getChildren().add(buttons);
        splitScreen.getChildren().add(display);
        
        // layout settings
        StackPane mainLayout = new StackPane();
        mainLayout.setPrefSize(600, 360);
        mainLayout.getChildren().add(splitScreen);
        mainLayout.setAlignment(Pos.CENTER);
        
        // remove recipe dialog
        Dialog<String> delRecipeDialog = new Dialog<>();
        delRecipeDialog.setTitle("Poista resepti");
        delRecipeDialog.setResizable(true);
        Label delNameLabel = new Label("Valitse resepti: ");
        ComboBox delRecipesList = new ComboBox();
        ButtonType delRecipe = new ButtonType("Poista resepti", ButtonData.OK_DONE);
        ButtonType delRecipeBack = new ButtonType("Palaa", ButtonData.CANCEL_CLOSE);
        
        ArrayList<Recipe> recipes = new ArrayList<>();
            
            try {
                recipes = rs.list();
            } catch (Exception e) {
                
            }
            
        for (Recipe r : recipes) {
            delRecipesList.getItems().add(r.getName());
        }
        
        
        GridPane delRecipeGrid = new GridPane();
        delRecipeGrid.add(delNameLabel,1,1);
        delRecipeGrid.add(delRecipesList,2,1);
      
        delRecipeDialog.getDialogPane().setContent(delRecipeGrid);
        delRecipeDialog.getDialogPane().setMinSize(2,2);
        delRecipeDialog.getDialogPane().getButtonTypes().add(delRecipeBack);
        delRecipeDialog.getDialogPane().getButtonTypes().add(delRecipe);
        
        delRecipeDialog.setResultConverter(new Callback<ButtonType, String>() {
        @Override
        public String call(ButtonType b) {   
            
            if (b == delRecipe) {

                return (String) delRecipesList.getValue();           
                }

                return null;
            }
        });
        

        // button actions
        
        list.setOnAction((event) -> {
            display.clear();
            ArrayList<Recipe> recipes2 = new ArrayList<>();
            
            try {
                recipes2 = rs.list();
            } catch (Exception e) {
                
            }
            
            for (Recipe r : recipes2) {
                display.appendText(r.getName() + "\n");
            }
        });
        
        add.setOnAction((event) -> {
           
           // opens in a new window 
           display.clear();
           Stage addRecipeWindow = new Stage();
           addRecipe(addRecipeWindow);
           
        });
        
        del.setOnAction((event) -> {
           display.clear();
            
           Optional<String> rDel = delRecipeDialog.showAndWait();
           
           if (rDel.isPresent()) {
               try {
                   String sDel = rDel.get();
                   rs.remove(sDel);
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
        
        // scene
        Scene mainScene = new Scene(mainLayout);
        
        window.setScene(mainScene);
        window.setTitle("Menuplanner - " + us.getLoggedIn().getUid());
        window.show();

    }
    
    public void addRecipe(Stage window) {
        // components
        Label nameLabel = new Label("Nimi: ");
        Label proteinLabel = new Label("Pääraaka-aine: ");
        Label sidesLabel = new Label("Lisuke: ");
        TextField addRecipeName = new TextField();
        ComboBox addRecipeProtein = new ComboBox();
        ComboBox addRecipeSide = new ComboBox();
        Button checkRecipeName = new Button("Tarkista");
        Label addRecipeError = new Label();
        Button addRecipeBack = new Button("Palaa");
        Button addRecipeSave = new Button("Lisää resepti");
        
        // load combobox contents        
        for (String p : proteins) {
            addRecipeProtein.getItems().add(p);
        }
        
        for (String s : sides) {
            addRecipeSide.getItems().add(s);
        }
        
        // layout
        GridPane addRecipeLayout = new GridPane();
        addRecipeLayout.add(proteinLabel,1,1);
        addRecipeLayout.add(addRecipeProtein,2,1);
 
        addRecipeLayout.add(sidesLabel,1,2);
        addRecipeLayout.add(addRecipeSide,2,2);
        
        addRecipeLayout.add(nameLabel,1,3);
        addRecipeLayout.add(addRecipeName,2,3);
        
        addRecipeLayout.add(addRecipeBack,1,5);
        addRecipeLayout.add(checkRecipeName,2,5);
        
        addRecipeLayout.add(addRecipeError,2,4);
        
        addRecipeError.setWrapText(true);
        
        // layout preferences
        addRecipeLayout.setPrefSize(420, 180);
        addRecipeLayout.setAlignment(Pos.CENTER);
        addRecipeLayout.setVgap(10);
        addRecipeLayout.setHgap(10);
        addRecipeLayout.setPadding(new Insets(20, 20, 20, 20));
        
        // button actions
        addRecipeBack.setOnAction((event) -> {
            window.close();
        });
        
        checkRecipeName.setOnAction((event) -> {
            addRecipeError.setText("");
            boolean cannotAdd = true;
        
            try {
                Recipe check = rs.read(addRecipeName.getText());
                if (check != null) {
                    addRecipeError.setText("Resepti " + addRecipeName.getText() + " on jo tietokannassa.");
                } else {
                    addRecipeLayout.add(addRecipeSave,3,5);
        
                }
            } catch (Exception e) {
               if (addRecipeProtein.getValue() != null && addRecipeSide.getValue() != null && !addRecipeName.getText().equals("")) {
                   addRecipeLayout.add(addRecipeSave,3,5);
               }
                
            }
            
        });
        
        addRecipeSave.setOnAction((event) -> {
            try {
                rs.add(addRecipeName.getText(), 
                        (String) addRecipeProtein.getValue(),
                        (String) addRecipeSide.getValue(), 0);
            } catch (Exception e) {
                
            }
            
            try {
                window.close();
            } catch (Exception e) {
                
            }
        });
        
        
        Scene addRecipeScene = new Scene(addRecipeLayout);
        window.setScene(addRecipeScene);
        window.setTitle("Lisää resepti - " + us.getLoggedIn().getUid());
        window.show();
        
    }
    
    public static void main(String[] args) {
        
        launch(GraphicUI.class);
   
    }

    
}
