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
        try {
            this.us = new UserService();
        } catch (Exception e) {
            
            
        }
    }
    
    public void login() {
        while (true) {
            System.out.println("[1] Listaa käyttäjät");
            System.out.println("[2] Kirjaudu");
            System.out.println("[x] Poistu sovelluksesta");
        
            System.out.print("> ");
        
            String prompt = scanner.nextLine();
        
            if (prompt.equals("x")) {
                break;
            }
            
            if (prompt.equals("1")) {
                try {
                    us.listUsers();
                } catch (Exception e) {
                    System.out.println(e);}
                continue;
            }
            
            if (prompt.equals("2")) {
                System.out.println("Syötä käyttäjätunnus: ");
                System.out.print("> ");
                
                prompt = scanner.nextLine();
                
                User u = new User(prompt);

                if (!us.check(u)) {
                    System.out.println("Käyttäjää ei löydy tietokannasta, luodaanko uusi käyttäjä? (k/e)");
                    System.out.print("> ");

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
                
            }
        }
}
