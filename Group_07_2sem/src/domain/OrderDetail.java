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
    private int orderID;
    private int resourceID;
    private int qty;

    public OrderDetail(int orderID, int resourceID, int qty) {
        this.orderID = orderID;
        this.resourceID = resourceID;
        this.qty = qty;
    }
    
    
    
}
