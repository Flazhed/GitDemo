/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import domain.Controller;

/**
 *
 * @author Søren
 */
public class ConsoleResCheck {
    
    public static void main(String[] args) {
        
        Controller con = Controller.getInstance();
        con.checkRessourceAvailability(111);
        
    }
    
}
