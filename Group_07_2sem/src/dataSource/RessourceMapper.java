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
    
    public ArrayList<Ressource> getRessourceList(){
        
        ArrayList<Ressource> ressourceList = new ArrayList<>();
        
        String SQLString = "SELECT sum(qty), Rst.Typename, Rst.Ressourcetypeid FROM ressources rs, ressourceTypes rst "
                + "WHERE rs.ressourceTypeID = rst.ressourceTypeID "
                + "GROUP BY Rst.Typename, Rst.Ressourcetypeid "
                + "ORDER BY Rst.Ressourcetypeid";
        PreparedStatement statement = null;
        
        try {
            
            statement = DBConnector.getInstance().getConnection().prepareStatement(SQLString);
            
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                
                ressourceList.add(new Ressource(rs.getInt(3), rs.getString(2), 0, rs.getInt(1)));
                
            }
            
        } catch (Exception e) {
            System.out.println("Fail in ressourceMapper, getRessourceList1 " + e.toString());
        }
        
        
        
        return ressourceList;
    }
    
}
