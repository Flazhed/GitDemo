/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import domain.Customer;
import domain.Order;
import domain.OrderDetail;
import domain.Ressource;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Søren
 */
public class DBFacade {

    private Connection con;
    private OrderMapper om;
    private CustomerMapper cm;
    private RessourceMapper rm;
    private DatabaseQueries dbq;
    //== Singleton start
    private static DBFacade instance;

    private DBFacade() {
        //MAPPERS START
        om = new OrderMapper();
        cm = new CustomerMapper();
        dbq = new DatabaseQueries();
        rm = new RessourceMapper();
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

    public boolean insertOrderDetails(ArrayList<OrderDetail> odl) {
        return om.insertOrderDetails(odl, con);
    }
    public boolean insertCustomer(Customer c){
        return cm.insertCustomer(c, con);
    }
    
    public Customer getCustomer(int customerID){
        return cm.getCustomer(customerID, con);
    }
    
    public ArrayList <Customer> getAllCustomers(){
        return cm.getAllCustomers(con);
    }
    
    public ArrayList <Order> getAllOrders(){
        return om.getAllOrders(con);
    }
    
    public boolean checkRessourceAvailability(int resType, String startDate, String endDate, int requestedQty){
        return dbq.checkRessourceAvailability(resType, startDate, endDate, requestedQty);
    } 
    
    public ArrayList<OrderDetail> createVerifiedOrderDetails(int resTypeID, int requestedQty, Order o){
        return dbq.createVerifiedOrderDetails(resTypeID, requestedQty, o);
    }
    
    public ArrayList<Ressource> getRessourceList(int resTypeID){
        return rm.getRessourceList(resTypeID);
    }
    
     public ArrayList<Ressource> getAllRessources(int resTypeID){
         return rm.getAllRessources(resTypeID);
     }
}
