/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import domain.*;
import java.sql.Date;
import java.text.ParseException;

/**
 *
 * @author Uffe
 */
public class ConsoleGUI {

    public static void main(String[] args) throws ParseException {
//        ArrayList<OrderDetail> odl = new ArrayList();
        Controller con = Controller.getInstance();

        con.insertOrder(new Order(0002, 1, 11, true, con.formatStringToDate("30-10-2015"), con.formatStringToDate("30-11-2015") , 3400f));
        
        System.out.println(con.getOrder(0002));      
        
//        Order o = con.getOrder(1111);
//        odl.add(con.createNewOrderDetail(1111, 3, 1, 5));
//        con.insertOrderDetails(odl);
//        System.out.println(con.getOrder(1111));

    }

}
