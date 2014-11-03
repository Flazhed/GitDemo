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
                System.out.println("ERROR in OrderMapper - InsertOrder.Finally " + e);
            }
        }

        System.out.println("RowsInserted: " + rowInserted);

        return rowInserted == 1;
    }

    public boolean insertOrderDetails(OrderDetail od, Connection con) {
        int rowInserted = 0;

        String SQLString = "INSERT INTO Orderdetails "
                + "VALUES (?,?,?,?)";

        PreparedStatement statement = null;

        try {
            statement = con.prepareStatement(SQLString);

            statement.setInt(1, od.getOrderID());
            statement.setInt(2, od.getResourceID());
            statement.setInt(3, rowInserted);
            statement.setInt(4, od.getQty());
            
            
            
        } catch (SQLException e) {
            
        }

    }

    public Order getOrder(int orderID, Connection con) {

        Order o = null;

        String SQLString1 = "SELECT * "
                + "FROM Orders "
                + "WHERE OrderID = ?";

        String SQLString2 = "SELECT od.RessourceID, od.qty "
                + "FROM OrderDetails od "
                + "WHERE od.OrderID = ?";

// String for orderDetails, will be inserted here.
        PreparedStatement statment = null;
        try {
            statment = con.prepareStatement(SQLString1);
            statment.setInt(1, orderID);

            ResultSet rs = statment.executeQuery();

            if (rs.next()) {
                boolean confirmed = (1 == rs.getInt(4));
                o = new Order(orderID, rs.getInt(2), rs.getInt(3), confirmed, rs.getDate(5), rs.getDate(6), rs.getFloat(7));
            }

            statment = con.prepareStatement(SQLString2);
            statment.setInt(1, orderID);

            rs = statment.executeQuery();

            while (rs.next()) {
                o.addDetail(new OrderDetail(orderID, rs.getInt(1), rs.getInt(2)));
            }

        } catch (SQLException e) {
            System.out.println("Error in OrderMapper - getOrder " + e);
        } finally {

            try {
                statment.close();
            } catch (SQLException e) {
                System.out.println("Error in OrderMapper - getOrder.finally " + e);
            }

        }

        return o;
    }

}
