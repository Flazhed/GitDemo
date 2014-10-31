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
    private int zipCode;
    private int phoneNumber;
    private String fullName;
    private String companyName;
    private String address;
    
    
    public Customer(int customerID, int zipCode, int phoneNumber, 
                    String fullname, String companyName, String address){
        this.customerID = customerID;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.fullName = fullname;
        this.companyName = companyName;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerID=" + customerID + ", zipCode=" + zipCode + ", phoneNumber=" + phoneNumber + ", fullName=" + fullName + ", companyName=" + companyName + ", address=" + address + '}';
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
