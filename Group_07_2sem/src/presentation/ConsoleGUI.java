/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import domain.*;
import java.util.ArrayList;

/**
 *
 * @author Uffe
 */
public class ConsoleGUI {
    
    public static void main(String[] args) {
        ArrayList<OrderDetail> odl = new ArrayList<OrderDetail>();
        Controller con = Controller.getInstance();
        System.out.println(con.getOrder(1111).toString());
        Order o = con.getOrder(1111);
        odl.add(con.createNewOrderDetail(1111, 3, 1, 5));
        con.insertOrderDetails(odl);
        System.out.println(con.getOrder(1111).toString());
      
    }
    
}
