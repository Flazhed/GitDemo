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
    private int customerID; //skal laves om til Customer Object senere
    private int salesID; // skal laves om til Salesman Object senere
    private boolean comfirmed;
    private Date startDate;
    private Date endDate;
    private float price;
    private ArrayList<OrderDetail> orderDetails;

    
    
    // OBS! Customer og Salesman er ikke Objecter, men Integer. (skal laves om)
    public Order(int orderID, int customerID, int salesID, boolean comfirmed, 
                 Date startDate, Date endDate, float price) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.salesID = salesID;
        this.comfirmed = comfirmed;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        orderDetails = new ArrayList();
    }
    
    
    public int getBoolToInt(){
        
        if(comfirmed){
            return 1;
        }
        else return 0;
        
    }
    
    public void addDetail(OrderDetail od){
        orderDetails.add(od);
    }
    
    @Override
    public String toString() {
        String str   = "orderID: " + orderID+ ", customerID: " + customerID + ", salesID: "
                     + salesID  + ", price: " + price + ", comfirmed: " + comfirmed
                     + ", startDate: " + startDate + ", endDate: " + endDate
                     + "\nOrder Details:\n";
        
        for (OrderDetail orderDetail : orderDetails) {
            str = str + orderDetail+"\n";
        }
        
        return str;
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
