/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import domain.Customer;
import domain.Order;
import domain.OrderDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Uffe
 */
public class CustomerMapper {
    //Single transaction.. To be added a list of transactions. 
    public boolean insertCustomer(Customer c, Connection con) {

        int rowInserted = 0;
        String SQLString = "INSERT INTO Customers "
                + "VALUES (?,?,?,?,null,?)";

        PreparedStatement statement = null;

        try {
            statement = con.prepareStatement(SQLString);

            statement.setInt(1, c.getCustomerID());
            statement.setString(2, c.getFullName());
            statement.setString(3, c.getCompanyName());
            statement.setString(4, c.getAddress());
//            statement.setInt(5, c.getZipCode()); ZipCodes er FK i SQL, men ingen zipCode tabel eksistere.
            statement.setInt(5, c.getPhoneNumber());

            rowInserted = statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERROR in CustomerMapper - insertCustomer " + e.toString());
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("ERROR in CustomerMapper - insertCustomer.Finally " + e);
            }
        }

        System.out.println("RowsInserted: " + rowInserted);

        return rowInserted == 1;
    }


    public Customer getCustomer(int customerID, Connection con) {

        Customer c = null;

        String SQLString1 = "SELECT * "
                + "FROM Customers "
                + "WHERE CustomerID = ?";


        PreparedStatement statment = null;
        try {
            statment = con.prepareStatement(SQLString1);
            statment.setInt(1, customerID);

            ResultSet rs = statment.executeQuery();

            if (rs.next()) {
                c = new Customer(customerID, rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
            }


        } catch (SQLException e) {
            System.out.println("Error in CustomerMapper - getCustomer " + e);
        } finally {

            try {
                statment.close();
            } catch (SQLException e) {
                System.out.println("Error in CustomerMapper - getCustomer.finally " + e);
            }

        }

        return c;
    }
    
    public ArrayList<Customer> getAllCustomers(Connection con){
        
        ArrayList <Customer> tempCustomers = new ArrayList();
        
          String SQLString1 = "SELECT * "
                + "FROM Customers ";
          
           PreparedStatement statment = null;
        try {
            statment = con.prepareStatement(SQLString1);
           

            ResultSet rs = statment.executeQuery();

            while (rs.next()) {
                tempCustomers.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6))); 
            }


        } catch (SQLException e) {
            System.out.println("Error in CustomerMapper - getAllCustomer " + e);
        } finally {

            try {
                statment.close();
            } catch (SQLException e) {
                System.out.println("Error in CustomerMapper - getAllCustomer.finally " + e);
            }
        
                
        }

          
        
        return tempCustomers;
    }
}
