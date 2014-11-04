/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import domain.Order;
import domain.OrderDetail;
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
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("ERROR in OrderMapper - InsertOrder.Finally.Connection.Close " + e);
            }
        }
        return rowInserted == 1;
    }
    
    public boolean insertOrderDetails(ArrayList<OrderDetail> odl, Connection con) {
        int rowInserted = 0;
        String SQLString = "INSERT INTO Orderdetails "
                + "VALUES (?,?,?,?)";

        PreparedStatement statement = null;
        System.out.println(odl.get(0).getOrderID() + " " + odl.get(0).getResourceID() + " " + odl.get(0).getStorageID() + " " + odl.get(0).getQty());
        try {
            statement = con.prepareStatement(SQLString);

            for (int i = 0; i < odl.size(); i++) {

                OrderDetail od = odl.get(i);

                statement.setInt(1, od.getOrderID());
                statement.setInt(2, od.getResourceID());
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
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("ERROR in OrderMapper - InsertOrderDetails.Finally.Connection.Close " + e);
            }
        }

        return rowInserted == odl.size();
    }

    public Order getOrder(int orderID, Connection con) {

        Order o = null;

        String SQLString1 = "SELECT * "
                + "FROM Orders "
                + "WHERE OrderID = ?";

        String SQLString2 = "SELECT od.RessourceID, od.StorageID, od.qty "
                + "FROM OrderDetails od "
                + "WHERE od.OrderID = ?";

// String for orderDetails, will be inserted here.
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(SQLString1);
            statement.setInt(1, orderID);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                boolean confirmed = (1 == rs.getInt(4));
                o = new Order(orderID, rs.getInt(2), rs.getInt(3), confirmed, rs.getDate(5), rs.getDate(6), rs.getFloat(7));
            }

            statement = con.prepareStatement(SQLString2);
            statement.setInt(1, orderID);

            rs = statement.executeQuery();

            while (rs.next()) {
                o.addDetail(new OrderDetail(orderID, rs.getInt(1), rs.getInt(2), rs.getInt(3)));
            }

        } catch (SQLException e) {
            System.out.println("Error in OrderMapper - getOrder " + e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("ERROR in OrderMapper - InsertOrder.Finally.Statement.Close " + e);
            }
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("ERROR in OrderMapper - InsertOrder.Finally.Connection.Close " + e);
            }
        }

        return o;
    }

}
