/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Søren
 */
public class Ressource {
    
    private int ressourceID;
    private int ressourceTypeID;
    private String ressourceType;
    private int storageID;
    private int qty;

    public Ressource(int ressourceID, int ressourceTypeID, String ressourceType, int storageID, int qty) {
        this.ressourceID = ressourceID;
        this.ressourceTypeID = ressourceTypeID;
        this.ressourceType = ressourceType;
        this.storageID = storageID;
        this.qty = qty;
    }

    public int getRessourceID() {
        return ressourceID;
    }

    public int getRessourceTypeID() {
        return ressourceTypeID;
    }

    public String getRessourceType() {
        return ressourceType;
    }

    public int getStorageID() {
        return storageID;
    }

    public int getQty() {
        return qty;
    }
    
    
    
    
    
    
}
