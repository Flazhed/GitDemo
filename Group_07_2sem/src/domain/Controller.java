/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dataSource.DBFacade;
import java.sql.Date;

/**
 *
 * @author Søren
 */
public class Controller {

    private static Controller instance;
    private DBFacade dbf;

    private Controller() {

        dbf = DBFacade.getInstance();
    }

    public static Controller getInstance() {

        if (instance == null) {
            instance = new Controller();
        }
        return instance;

    }

    public Order getOrder(int orderID) {

        return dbf.getOrder(orderID);
    }

    public boolean insertOrder(Order o) {

        return dbf.insertOrder(o);
    }

    public Order createNewOrder(int orderID, int cusomerID, int salesID, boolean confirmed,
            Date startDate, Date endDate, float price) {
        Order o = new Order(orderID, cusomerID, salesID, confirmed, startDate, endDate, price);
        return o;
    }

}
