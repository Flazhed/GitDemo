/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import domain.Ressource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Søren
 */
public class RessourceMapper {
    
    public ArrayList<Ressource> getRessourceList(ArrayList<Integer> rIDList, ArrayList<Integer> sIDList){
        
        ArrayList<Ressource> ressourceList = new ArrayList<>();
        
        String SQLString = "SELECT * FROM Ressources";
        PreparedStatement statement = null;
        
        try {
            
            statement = DBConnector.getInstance().getConnection().prepareStatement(SQLString);
            
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                
                
                
            }
            
        } catch (Exception e) {
        }
        
        
        
        return null;
    }
    
}
