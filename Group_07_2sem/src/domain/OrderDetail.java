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
    private int resourceTypeID;
    private int storageID;
    private int qty;

    public OrderDetail(int orderID, int resourceTypeID, int storageID, int qty) {
        this.orderID = orderID;
        this.resourceTypeID = resourceTypeID;
        this.storageID = storageID;
        this.qty = qty;
    }

    public int getStorageID() {
        return storageID;
    }

    public void setStorageID(int storageID) {
        this.storageID = storageID;
    }

    @Override
    public String toString() {
        return "RessourceID: " + resourceTypeID +", Antal: " + qty;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getResourceTypeID() {
        return resourceTypeID;
    }

    public void setResourceTypeID(int resourceTypeID) {
        this.resourceTypeID = resourceTypeID;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

}
