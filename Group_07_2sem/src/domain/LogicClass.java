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
    
    
    public void checkRessourceAvailability(int resType, Connection con){
        //QTY, StartDate og endDate skal med som param
        int qty = 5;
        String startDate = "2010-02-01";
        String endDate = "2015-02-01";
        //ArrayList<Integer> orderIDList = new ArrayList<Integer>(); Er ikke brugt PT
        String SQLString1 = "SELECT orderid "
                + "FROM Orders "
                + "WHERE startDate>=? AND endDate<=? AND confirmed=1";
        SQLString1 = "SELECT sum(qty) FROM Orderdetails WHERE OrderID IN (" +
"      SELECT orderID FROM Orders WHERE startDate>=? AND endDate<=? AND confirmed=1)AND RessourceID IN (" +
"              SELECT RessourceID FROM ressources WHERE ressourceTypeID=?)";
        PreparedStatement statement = null;
        
        try {
            statement = con.prepareStatement(SQLString1);
            statement.setString(1, startDate);
            statement.setString(2, endDate);
            statement.setInt(3, resType);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                System.out.println("Qty for restype(" + resType + ") is " + rs.getInt(1) +" between " + startDate + " and " + endDate);
            }
        } catch (Exception e) {
            System.out.println("Fail1 " + e.toString() );
        }
        
        
        
        
        
        
        
        
        
        finally{
            try {
            con.close();    
            } catch (Exception e) {
                System.out.println("Fail2 " + endDate.toString());
            }
        }
        
        
    }
    
}
