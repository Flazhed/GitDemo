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

        // date(dato - 1900, month from 00(jan) to 11(dec), days 01-31)
        Date startDate = new Date(2014 - 1900, 11, 24);
        Date endDate = new Date(2015 - 1900, 00, 30);
        

        con.insertOrder(con.createNewOrder(4444, 2, 22, false, startDate, endDate, 123.90f));
        System.out.println(startDate);

        System.out.println(con.getOrder(4444));
        
        
//        Order o = con.getOrder(1111);
//        odl.add(con.createNewOrderDetail(1111, 3, 1, 5));
//        con.insertOrderDetails(odl);
//        System.out.println(con.getOrder(1111));

    }

}
