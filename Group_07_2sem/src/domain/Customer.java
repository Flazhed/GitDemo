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
public class Customer {
    private int customerID;
    private String fullName;
    private String companyName;
    private String address;
    private int zipCode;
    private int phoneNumber;
    
    

    public Customer(int customerID, String fullName, String companyName, 
                    String address, int zipCode, int phoneNumber) {
       
        this.customerID = customerID;
        this.fullName = fullName;
        this.companyName = companyName;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
    }
    
    
    
    

    @Override
    public String toString() {
        return "Kunde ID: " + customerID +", Fulde Navn: " + fullName + ", Firma Navn: " + companyName + "\nPost Nr. " + zipCode + ", Telefon Nummer: " + phoneNumber +  "Addresse: "  + address + "\n";
    }
    
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
