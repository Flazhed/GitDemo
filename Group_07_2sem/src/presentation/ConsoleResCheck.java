/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import domain.Controller;
import domain.LogicClass;
import domain.Order;
import domain.OrderDetail;
import java.util.ArrayList;

/**
 *
 * @author Søren
 */
public class ConsoleResCheck {
    
    public static void main(String[] args) {
        
        Controller con = Controller.getInstance();
        //con.checkRessourceAvailability(111, "2014-01-13", "2014-01-17", 20);
        Order o = new Order(9, 4, 33, true, con.formatStringToDate("13-01-2014"), con.formatStringToDate("17-01-2014"), 1001.1001f);
        LogicClass lc = new LogicClass();
        int resTypeID = 333;
        int reqAmount = 2;
        if(con.checkRessourceAvailability(resTypeID, "2014-01-13", "2014-01-17", reqAmount)){
        ArrayList<OrderDetail> odl = lc.createVerifiedOrderDetails(resTypeID, reqAmount, o);
        for (OrderDetail odl1 : odl) {
            System.out.println(odl1.toString());
            con.insertOrderDetails(odl);
        }
        }
        else System.out.println("Not enough ressources in storage");
    }
    
}
