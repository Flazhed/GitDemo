/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import domain.Controller;

/**
 *
 * @author Uffe
 */
public class ConsoleGUI {
    
    public static void main(String[] args) {
        Controller con = Controller.getInstance();
        System.out.println(con.getOrder(1111).toString());
        System.out.println(con.getOrder(2222).toString());
    }
    
}
