/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.Date;
import java.util.ArrayList;



/**
 *
 * @author Uffe
 */
public class Order {
    private int orderID;
    private float price;
    private Date startDate;
    private Date endDate;
    private boolean comfirmed;
    private int customerID; //skal laves om til Customer Object senere
    private int salesID; // skal laves om til Salesman Object senere
    private ArrayList<OrderDetail> orderDetails;
    
    public Order(int orderID, float price, long startDate, long endDate,
                 boolean confirmed) { // Der mangler stadig Customer og Sales i constructor
        this.orderID = orderID;
        this.price = price;
        this.comfirmed = confirmed;
        this.startDate = new Date(startDate);
        this.endDate = new Date(endDate);
        orderDetails = new ArrayList();
    }
    
    public void addDetail(OrderDetail od){
        orderDetails.add(od);
    }
    
    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", price=" + price + ", startDate=" + startDate + ", endDate=" + endDate + ", comfirmed=" + comfirmed + ", customerID=" + customerID + ", salesID=" + salesID + '}';
    }
    
    
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isComfirmed() {
        return comfirmed;
    }

    public void setComfirmed(boolean comfirmed) {
        this.comfirmed = comfirmed;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getSalesID() {
        return salesID;
    }

    public void setSalesID(int salesID) {
        this.salesID = salesID;
    }
    
    
     
}
