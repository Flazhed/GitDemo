/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import domain.Order;
import domain.OrderDetail;
import domain.Ressource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author MS
 */
public class OrderMapper {

    //Single transaction.. To be added a list of transactions. 
    public boolean insertOrder(Order o, Connection con) {

        int rowInserted = 0;

        String SQLString = "INSERT INTO Orders "
                + "VALUES (?,?,?,?,?,?,?)";

        PreparedStatement statement = null;

        try {
            statement = con.prepareStatement(SQLString);
            
            //Debug
            if(domain.Controller.debugMode)
                    System.out.println("DebugMode (OrderMapper.java): " + "InsertOrder Values\n" +
                    "OrderID: " + o.getOrderID() +
                    "\nCustomerID: " + o.getCustomerID() +
                    "\nSalesID: " + o.getSalesID() +
                    "\nVerified: " + o.getBoolToInt() +
                    "\nstartDate: " + o.getStartDate() +
                    "\nendDate: " + o.getEndDate() +
                    "\nPrice: " + o.getPrice());

            statement.setInt(1, o.getOrderID());
            statement.setInt(2, o.getCustomerID());
            statement.setInt(3, o.getSalesID());
            statement.setInt(4, o.getBoolToInt());
            statement.setDate(5, o.getStartDate());
            statement.setDate(6, o.getEndDate());
            statement.setFloat(7, o.getPrice());

            rowInserted = statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERROR in OrderMapper - insertOrder " + e.toString());
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("ERROR in OrderMapper - InsertOrder.Finally.Statement.Close " + e);
            }

        }
        return rowInserted == 1;
    }
    
    public boolean insertOrderDetails(ArrayList<OrderDetail> odl, Connection con) {
        int rowInserted = 0;
        String SQLString = "INSERT INTO Orderdetails "
                + "VALUES (?,?,?,?)";

        PreparedStatement statement = null;
        
        //Debug
        if(domain.Controller.debugMode)
            System.out.println(odl.get(0).getOrderID() + " " + odl.get(0).getResourceTypeID() + " " + odl.get(0).getStorageID() + " " + odl.get(0).getQty());
        
        try {
            statement = con.prepareStatement(SQLString);

            for (int i = 0; i < odl.size(); i++) {

                OrderDetail od = odl.get(i);

                statement.setInt(1, od.getOrderID());
                statement.setInt(2, od.getResourceTypeID());
                statement.setInt(3, od.getStorageID());
                statement.setInt(4, od.getQty());
                rowInserted += statement.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("ERROR in OrderMapper - InsertOrderDetails " + e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("ERROR in OrderMapper - InsertOrderDetails.Finally.Statement.Close " + e);
            }

        }

        return rowInserted == odl.size();
    }

    public Order getOrder(int orderID, Connection con) {

        Order o = null;

        String SQLString1 = "SELECT * "
                + "FROM Orders "
                + "WHERE OrderID = ?";

        String SQLString2 = "SELECT DISTINCT od.RessourceTypeID, od.StorageID, od.qty, rs.qty, rst.typeName "
                + "FROM Ressources rs, OrderDetails od, RessourceTypes rst "
                + "WHERE od.OrderID = ? AND od.RessourceTypeID = rst.RessourceTypeID and Od.Storageid = rs.storageID and od.Ressourcetypeid= Rs.Ressourcetypeid";

// String for orderDetails, will be inserted here.
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(SQLString1);
            statement.setInt(1, orderID);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                boolean confirmed = (1 == rs.getInt(4));
                o = new Order(orderID, rs.getInt(2), rs.getInt(3), confirmed, rs.getDate(5), rs.getDate(6), rs.getFloat(7));
                
                //Debug
                if(domain.Controller.debugMode)
                    System.out.println("DebugMode (OrderMapper.java): " + "GetOrder Values\n" +
                    "OrderID: " + orderID +
                    "\nCustomerID: " + rs.getInt(2) +
                    "\nSalesID: " + rs.getInt(3) +
                    "\nVerified: " + confirmed +
                    "\nstartDate: " + rs.getDate(5) +
                    "\nendDate: " + rs.getDate(6) +
                    "\nPrice: " + rs.getFloat(7));
            }

            statement = con.prepareStatement(SQLString2);
            statement.setInt(1, orderID);

            rs = statement.executeQuery();

            while (rs.next()) {
                
                Ressource res = new Ressource(rs.getInt(1), rs.getString(5), rs.getInt(2), rs.getInt(4));
                OrderDetail od = new OrderDetail(orderID, rs.getInt(1), rs.getInt(2), rs.getInt(3)); 
                od.setRessource(res);
                o.addDetail(od);
            }

        } catch (SQLException e) {
            System.out.println("Error in OrderMapper - getOrder " + e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("ERROR in OrderMapper - getOrder.Finally.Statement.Close " + e);
            }

        }

        return o;
    }
    
    
    public ArrayList <Order> getAllOrders(Connection con){
        
        ArrayList <Order> tempOrders = new ArrayList();
        
                  String SQLString1 = "SELECT * "
                + "FROM Orders ";
          
           PreparedStatement statment = null;
        try {
            statment = con.prepareStatement(SQLString1);
           

            ResultSet rs = statment.executeQuery();

            while (rs.next()) {
                boolean confirmed = (1==rs.getInt(4));
                tempOrders.add(new Order(rs.getInt(1), rs.getInt(2), rs.getInt(3), confirmed, rs.getDate(5), rs.getDate(6), rs.getFloat(7))); 
            }


        } catch (SQLException e) {
            System.out.println("Error in OrderMapper - getAllOrders " + e);
        } finally {

            try {
                statment.close();
            } catch (SQLException e) {
                System.out.println("Error in OrderMapper - getAllOrders.finally " + e);
            }
        
            
        
        
    }
    return tempOrders;
    }
}
