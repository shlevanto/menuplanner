/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohte.UI;

import java.util.Scanner;
import ohte.domain.*;

/**
 *
 * @author levantsi
 */
public class UI {
    private Scanner scanner;
    private UserService us;
    
    public UI (Scanner scanner) {
        this.scanner = scanner;
        this.us = new UserService();
    }
    
    public void login() {
        while (true) {
            System.out.println("Syötä käyttäjätunnus: ");
        
            System.out.print("> ");
        
            String prompt = scanner.nextLine();
        
            if (scanner.equals("")) {
                break;
            }
            
            User u = new User(prompt);
                
            if (!us.login(u)) {
                System.out.println("Käyttäjää ei löydy tietokannasta, luodaanko uusi käyttäjä? (k/e)");
                System.out.print("> ");
                
                prompt = scanner.nextLine();
                
                if (prompt.equals("k")) {
                    us.create(u);
                    us.login(u);
                    System.out.println("Luotu uusi käyttäjä " + u.getUid());
                } else {
                    continue;
                }
                  
                
            } else {
                us.login(u);
            }
            
            start();
            break;
           
        }
    }
    
        public void start() {
            
            while (true) {
                System.out.print("Kirjautuneena " + us.getLoggedInUid() + "\n");
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
                
            }
        }
}
