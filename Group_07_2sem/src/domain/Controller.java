/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dataSource.DBFacade;

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

}
