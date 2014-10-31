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
            System.out.println("Error in OrderMapper " + e);
        } finally {

            try {
                statment.close();
            } catch (SQLException e) {
                System.out.println("Error in OrderMapper, finally " + e);
            }

        }

        return o;
    }

}
