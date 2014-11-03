/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import domain.Order;
import java.sql.Connection;

/**
 *
 * @author Søren
 */
public class DBFacade {

    private Connection con;
    private OrderMapper om;
    //== Singleton start
    private static DBFacade instance;

    private DBFacade() {
        //MAPPERS START
        om = new OrderMapper();
        //MAPPERS END
        con = DBConnector.getInstance().getConnection();

    }

    public static DBFacade getInstance() {
        if (instance == null) {
            instance = new DBFacade();
        }
        return instance;
    }
    //== Singleton end

    public Order getOrder(int orderID) {
        return om.getOrder(orderID, con);
    }

    public boolean insertOrder(Order o) {
        return om.insertOrder(o, con);
    }

}
