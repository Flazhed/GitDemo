/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dataSource.DatabaseQueries;
import dataSource.DBConnector;
import dataSource.DBFacade;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

    public Date formatStringToDate(String inputDate) {
        //SKAL DENNE LIGGE I CONTROL????
        java.sql.Date sqlStartDate = null;

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        try {
            java.util.Date date = sdf1.parse(inputDate);
            sqlStartDate = new java.sql.Date(date.getTime());
        } catch (Exception e) {
            System.out.println("ERROR in formatStringToDate " + e);
        }

        return sqlStartDate;
    }

    public Order getOrder(int orderID) {

        return dbf.getOrder(orderID);
    }

    public boolean insertOrder(Order o) {

        return dbf.insertOrder(o);
    }
    public boolean insertCustomer(Customer c){
        
        return dbf.insertCustomer(c);
    }
    public Customer getCustomer(int customerID){
        return dbf.getCustomer(customerID);
    }
    public boolean insertOrderDetails(ArrayList<OrderDetail> odl) {
        return dbf.insertOrderDetails(odl);

    }

    public Order createNewOrder(int orderID, int cusomerID, int salesID, boolean confirmed,
            Date startDate, Date endDate, float price) {
        Order o = new Order(orderID, cusomerID, salesID, confirmed, startDate, endDate, price);
        return o;
    }

    public OrderDetail createNewOrderDetail(int orderID, int ressourceID, int storageID, int qty) {
        //Ved fremtidig version skal Order O ikke med. aktiv order ligger i controller
        OrderDetail od = (new OrderDetail(orderID, ressourceID, storageID, qty));
        return od;
    }
    
    public boolean checkRessourceAvailability(int resType, String startDate, String endDate, int requestedQty){
        //ALT SKAL VÆRE RESTYPEID ikke RESTYPE
        
        return dbf.checkRessourceAvailability(resType, startDate, endDate, requestedQty);
        
    }
    
    public ArrayList<OrderDetail> createVerifiedOrderDetails(int resTypeID, int requestedQty, Order o){
        
        return dbf.createVerifiedOrderDetails(resTypeID, requestedQty, o);
    }
    
    
//    
//    public boolean verifyRessourceBooking(int resTypeID, Order o, int requestedQty){
//        
//        return checkRessourceAvailability(resTypeID, o.formatDateToString(o.getStartDate()), o.formatDateToString(o.getEndDate()), requestedQty);
//        
//        
//    }
    
    
    public ArrayList <Customer> getAllCustomers(){
        
        return dbf.getAllCustomers();
        
    }
    
    public ArrayList <Order> getAllOrders (){
        return dbf.getAllOrders();
    }

}
