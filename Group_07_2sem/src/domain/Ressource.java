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
    
    
    private int ressourceTypeID;
    private String ressourceTypeName;
    private int storageID;
    private int qty;

    public Ressource(int ressourceTypeID, String ressourceTypeName, int storageID, int qty) {
        
        this.ressourceTypeID = ressourceTypeID;
        this.ressourceTypeName = ressourceTypeName;
        this.storageID = storageID;
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Ressource{" + "ressourceTypeID=" + ressourceTypeID + ", ressourceTypeName=" + ressourceTypeName + ", storageID=" + storageID + ", qty=" + qty + '}';
    }



    public int getRessourceTypeID() {
        return ressourceTypeID;
    }

    public String getRessourceTypeName() {
        return ressourceTypeName;
    }

    public int getStorageID() {
        return storageID;
    }

    public int getQty() {
        return qty;
    }
    
    
    
    
    
    
}
