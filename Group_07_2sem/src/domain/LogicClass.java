/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
        
        String SQLString1 = "SELECT orderid "
                + "FROM Orders "
                + "WHERE startDate>=? AND endDate<=? AND confirmed=1";
        PreparedStatement statement = null;
        
        try {
            statement = con.prepareStatement(SQLString1);
            statement.setString(1, startDate);
            statement.setString(2, endDate);
            ResultSet rs = statement.executeQuery();
        } catch (Exception e) {
        }
        
        
    }
    
}
