/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohte.ui;

import ohte.setup.Setup;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.TreeSet;
import ohte.domain.*;

/**
 *
 * @author levantsi
 */
public class UI {
    private Scanner scanner;
    private UserService us;
    private RecipeService rs;
    private Setup setup;
    private TreeSet<String> proteins;
    private TreeSet<String> sides;
    
    public UI(Scanner scanner, Setup setup) {
        
        this.setup = setup;
        this.scanner = scanner;
        
        try {
            this.us = new UserService(this.setup.initUsersDb());
        } catch (Exception e) {           
        }
        
        try {
            this.proteins = this.setup.initProteins();
            this.sides = this.setup.initSides();
        } catch (Exception e) {
            
        }
    }
    
    public void login() {
        while (true) {
            System.out.println("[1] Listaa käyttäjät");
            System.out.println("[2] Kirjaudu");
            System.out.println("[x] Poistu sovelluksesta");
        
            
            String prompt = scanner.nextLine();
        
            if (prompt.equals("x")) {
                break;
            }
            
            if (prompt.equals("1")) {
                ArrayList<User> users = new ArrayList<>();
                
                try {
                    users = us.listUsers();
                } catch (Exception e) {
                    System.out.println(e);
                }
                
                for (User user : users) {
                    System.out.println(user.getUid());
                }
                continue;
            }
            
            if (prompt.equals("2")) {
                System.out.println("Syötä käyttäjätunnus: ");
                
                prompt = scanner.nextLine();
                
                User u = new User(prompt);

                if (!us.check(u)) {
                    System.out.println("Käyttäjää ei löydy tietokannasta, luodaanko uusi käyttäjä? (k/e)");
            
                    prompt = scanner.nextLine();

                    if (prompt.equals("k")) {
                        try {
                            us.create(u);
                        } catch (Exception e) {
                            
                        }
                        System.out.println("Luotu uusi käyttäjä " + u.getUid());
                    } else {
                        continue;
                    }


                } else {
                    us.login(u);
                }
            }
        
            this.rs = new RecipeService(us.getLoggedIn());
        
            start();
            break;
           
        }
    }
    
    
    public void start() {
        
        while (true) {
            System.out.print("Kirjautuneena " + us.getLoggedIn().getUid() + "\n");
            System.out.println("------------");
            System.out.println("Valitse tominnallisuus: ");
            System.out.println("[1] listaa reseptit");
            System.out.println("[2] lisää resepti");
            //System.out.println("[3] muokkaa reseptiä");
            System.out.println("[4] poista resepti");
            System.out.println("[5] muodosta ruokalista");
            System.out.println("[x] lopeta");
            System.out.print("> ");

            String prompt = scanner.nextLine();

            if (prompt.equals("x")) {
                break;
            }
            
            if (prompt.equals(("1"))) {
                try {
                    ArrayList<Recipe> list = rs.list();
                    
                    for (Recipe r : list) {
                        System.out.println(r.toString());
                    }
                    System.out.println("");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            
            if (prompt.equals("2")) {
                System.out.println("Anna reseptin nimi:");
                
                
                String name = scanner.nextLine();
                
                String protein;
                
                while (true) {
                    System.out.println("Mikä on reseptin pääraaka-aine? " + this.proteins);
                    
               
                    protein = scanner.nextLine();
                   
                    if (this.proteins.contains(protein)) {
                        break;
                    }
                }
             
                
                String side;
                
                while (true) {
                    System.out.println("Mikä on reseptin lisuke? " + this.sides);
                   
                
                    side = scanner.nextLine();
                   
                    if (this.sides.contains(side)) {
                        break;
                    }
                }
                
                rs.add(name, protein, side);
            }
            
            if (prompt.equals("4")) {
                System.out.println("Minkä reseptin haluat poistaa?");
                try {
                    rs.list();
                    System.out.println("");
                } catch (Exception e) {
                    System.out.println(e);
                }
                
                String removal = scanner.nextLine();
                
                rs.remove(removal);
            }
            
            if (prompt.equals("5")) {
                Recipe[] tuloste = new Recipe[5];
                
                try {
                    Menu m = new Menu(rs, proteins, sides);
                    tuloste = m.generate();
                } catch (Exception e) {
                    
                }
                
                for (int i = 0; i < 5; i++) {
                    System.out.println(tuloste[i].toString());
                }
                
                
            }

        }
    }
}
