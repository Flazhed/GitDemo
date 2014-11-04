/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Søren
 */
public class LogicClass {

    public boolean checkRessourceAvailability(int resType, String startDate, String endDate, int requestedQty, Connection con) {

        int bookedQty = 0;
        int totalStorageQty = 0;
        //startDate = "2014-01-13";
        //endDate = "2014-01-17";
        //ArrayList<Integer> orderIDList = new ArrayList<Integer>(); Er ikke brugt PT

        String SQLString1 = "SELECT sum(qty) FROM Orderdetails WHERE OrderID IN ("
                + "      SELECT orderID FROM Orders WHERE ("
                + "      (startDate>=? AND endDate<=?) or"
                + "      (endDate>=? AND endDate>=?) OR"
                + "      (startDate>=?) OR"
                + "      (startDate<=? AND endDate>=? AND startDate<=? AND endDate>=? )"
                + "      ) "
                + "      AND confirmed=1)"
                + "      AND RessourceID IN (SELECT RessourceID FROM ressources WHERE ressourceTypeID=?)";
        //SELECTER qty for en given RESOURCETYPEID i en GIVEN tidsperiode, to datoer, hvor order = confirmed 
        PreparedStatement statement = null;

        try {
            statement = con.prepareStatement(SQLString1);
            statement.setString(1, startDate);
            statement.setString(2, endDate);
            statement.setString(3, startDate);
            statement.setString(4, endDate);
            statement.setString(5, endDate);
            statement.setString(6, startDate);
            statement.setString(7, startDate);
            statement.setString(8, endDate);
            statement.setString(9, endDate);
            statement.setInt(10, resType);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                bookedQty += rs.getInt(1);
                System.out.println("Qty for restype(" + resType + ") is " + rs.getInt(1) + " between " + startDate + " and " + endDate);
            }
        } catch (Exception e) {
            System.out.println("Fail1 " + e.toString());
        }

       //Nu findes det totale ledige amount. ved at finde alle oprettede ressources, med det ønskede resTypeID
        //Ledig-QTY = total-QTY-used-QTY
        SQLString1 = "SELECT sum(qty) FROM Ressources WHERE ressourceTypeID=?";
        try {
            statement = con.prepareStatement(SQLString1);
            statement.setInt(1, resType);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                totalStorageQty += rs.getInt(1);
                
            }
        } catch (Exception e) {
            System.out.println("Fail2 " + e.toString());
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.out.println("Fail3 " + e.toString());
            }
        }
        System.out.println("Qty for restype(" + resType + ") is " + totalStorageQty + " on all storages");
        if (requestedQty <= (totalStorageQty - bookedQty)) {
            System.out.println("Der er nok");
            return true;
        } else {
            System.out.println("Der er ikke nok");
            return false;
        }

    }

}
