/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import dataSource.DBConnector;
import domain.Order;
import domain.OrderDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Søren
 */
public class DatabaseQueries {

    public boolean checkRessourceAvailability(int resType, String startDate, String endDate, int requestedQty) {
        //CONNECTION SKAL IKKE MED I PARAM, HENT FRA GETINSTANCE. SLET KALD HELE VEJEN GENNEM STRUKTUREN
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
            statement = DBConnector.getInstance().getConnection().prepareStatement(SQLString1);
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
            statement = DBConnector.getInstance().getConnection().prepareStatement(SQLString1);
            statement.setInt(1, resType);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                totalStorageQty += rs.getInt(1);

            }
        } catch (Exception e) {
            System.out.println("Fail2 " + e.toString());
        } finally {
            try {
                
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

    public ArrayList<OrderDetail> createVerifiedOrderDetails(int resTypeID, int requestedQty, Order o) {

        //Hvis der er nok på lager 1:
        ArrayList<OrderDetail> odl = new ArrayList<>();
        String startDate = o.formatDateToString(o.getStartDate());
        String endDate = o.formatDateToString(o.getEndDate());
        int storage1QtyInUse = 0; //OPTAGET qty for perioden
        int storage2QtyInUse = 0; //OPTAGET qty for perioden
        int storage1QtyTotal = 0;
        int storage2QtyTotal = 0;
        int ressourceID1 = 0;
        int ressourceID2 = 0;
        int storageID1 = 1;
        int storageID2 = 2;
        String SQLString1 = "SELECT sum(qty), storageID, ressourceID FROM Orderdetails WHERE OrderID IN ("
                + "      SELECT orderID FROM Orders WHERE ("
                + "      (startDate>=? AND endDate<=?) or"
                + "      (endDate>=? AND endDate>=?) OR"
                + "      (startDate>=?) OR"
                + "      (startDate<=? AND endDate>=? AND startDate<=? AND endDate>=? )"
                + "      ) "
                + "      AND confirmed=1)"
                + "      AND RessourceID IN (SELECT RessourceID FROM ressources WHERE ressourceTypeID=?) GROUP BY StorageID, ressourceID ORDER BY STORAGEID ASC";
        //SELECTER qty for en given RESOURCETYPEID i en GIVEN tidsperiode, to datoer, hvor order = confirmed 
        PreparedStatement statement = null;

        try {
            statement = DBConnector.getInstance().getConnection().prepareStatement(SQLString1);
            statement.setString(1, startDate);
            statement.setString(2, endDate);
            statement.setString(3, startDate);
            statement.setString(4, endDate);
            statement.setString(5, endDate);
            statement.setString(6, startDate);
            statement.setString(7, startDate);
            statement.setString(8, endDate);
            statement.setString(9, endDate);
            statement.setInt(10, resTypeID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                if (rs.getInt(2) == 1) {//HVIS lagerID=1, sæt variables for storage1, ellers storage2
                    storage1QtyInUse = rs.getInt(1);
                    ressourceID1 = rs.getInt(3);
                } else {
                    storage2QtyInUse = rs.getInt(1);
                    ressourceID2 = rs.getInt(3);
                }
            }

        } catch (Exception e) {
            System.out.println("Fail1 " + e.toString());
        }
        System.out.println("Storage1qtyinuse: " + storage1QtyInUse + " resID1: " + ressourceID1);
        System.out.println("Storage2qtyinuse: " + storage2QtyInUse + " resID2: " + ressourceID2);

        //Antallet af ressourcer i brug for hvert lager er fundet. Nu skal der findes max antal ressourcer.
        SQLString1 = "SELECT QTY, StorageID, RessourceID FROM Ressources Where ressourceTypeID=? GROUP BY StorageID, ressourceID, QTY ORDER BY STORAGEID ASC";
        try {
            statement = DBConnector.getInstance().getConnection().prepareStatement(SQLString1);
            statement.setInt(1, resTypeID);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
                
                if(rs.getInt(2)==1){
                    storage1QtyTotal = rs.getInt(1);
                    ressourceID1 = rs.getInt(3);
                }
                else{
                    storage2QtyTotal = rs.getInt(1);
                    ressourceID2 = rs.getInt(3);
                }
                
            }
            
        } catch (Exception e) {
            System.out.println("Fail3 " + e.toString());
        }
        System.out.println("totalqty1:" + storage1QtyTotal);
        System.out.println("totalqty2:" + storage2QtyTotal);
        
        int availableStorage1 = storage1QtyTotal-storage1QtyInUse; //ledige = total-inuse
        int availableStorage2 = storage2QtyTotal-storage2QtyInUse; //ledige = total-inuse
        if(availableStorage1>=requestedQty){
            odl.add(new OrderDetail(o.getOrderID(), ressourceID1, storageID1, requestedQty));
        }
        else{
            odl.add(new OrderDetail(o.getOrderID(), ressourceID1, storageID1, availableStorage1));
            odl.add(new OrderDetail(o.getOrderID(), ressourceID2, storageID2, requestedQty-availableStorage1));
            
            
        }
        //Opret orderdetail og add til liste
        //Hvis ikke
        //opret orderdetail1 og fyld ud
        //Opret orderdetil2 og smid resten ind
        System.out.println("Size på ODL:" + odl.size());
        return odl;
    }

}
