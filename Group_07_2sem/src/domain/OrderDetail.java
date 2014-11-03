/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Uffe
 */
public class OrderDetail {
    //Tilføj storage ID og fix toString
    private int orderID;
    private int resourceID;
    private int storageID;
    private int qty;

    public OrderDetail(int orderID, int resourceID, int storageID, int qty) {
        this.orderID = orderID;
        this.resourceID = resourceID;
        this.storageID = storageID;
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "OrderID: " + orderID + ", resourceID: " + resourceID + ", StorageID: " + storageID + ", qty: " + qty + '}';
    }
    
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    
    
    
}
