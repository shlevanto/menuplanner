/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohte.ui;

import java.util.Scanner;
import ohte.domain.*;

/**
 *
 * @author levantsi
 */
public class UI {
    private Scanner scanner;
    private UserService us;
    private RecipeService rs;
    
    public UI(Scanner scanner) {
        this.scanner = scanner;
        try {
            this.us = new UserService("users");
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
                try {
                    us.listUsers();
                } catch (Exception e) {
                    System.out.println(e);
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
                        us.create(u);
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
            System.out.println("[3] muokkaa reseptiä");
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
                    rs.list();
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
                    System.out.println("Mikä on reseptin pääraaka-aine? (kasvis, kala, kana, liha):");
                    
               
                    protein = scanner.nextLine();
                   
                    if (protein.equals("kasvis") || protein.equals("kala") || protein.equals("kana") || protein.equals("liha")) {
                        break;
                    }
                }
                
                
                String side;
                
                while (true) {
                    System.out.println("Mikä on reseptin lisuke? (pasta, riisi, peruna, keitto, muu):");
                   
                
                    side = scanner.nextLine();
                   
                    if (side.equals("pasta") || side.equals("riisi") || side.equals("peruna") || side.equals("keitto") || side.equals("muu")) {
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
                System.out.println("Mikä resepti päivitetään?");
                String name = scanner.nextLine();
                
                Recipe paivitettava = rs.read(name);
                
                rs.updateDate(paivitettava);
            }

        }
    }
}
