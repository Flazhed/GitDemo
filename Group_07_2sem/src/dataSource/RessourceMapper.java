/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import domain.Ressource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Søren
 */
public class RessourceMapper {
    
    public ArrayList<Ressource> getRessourceList(int resTypeID){
        
        ArrayList<Ressource> ressourceList = new ArrayList<>();
        String SQLString = "";
        PreparedStatement statement = null;
        if(resTypeID==0){
        SQLString = "SELECT sum(qty), Rst.Typename, Rst.Ressourcetypeid FROM ressources rs, ressourceTypes rst "
                + "WHERE rs.ressourceTypeID = rst.ressourceTypeID "
                + "GROUP BY Rst.Typename, Rst.Ressourcetypeid "
                + "ORDER BY Rst.Ressourcetypeid";
        }
        else{
        SQLString = "SELECT sum(qty), Rst.Typename, Rst.Ressourcetypeid FROM ressources rs, ressourceTypes rst "
                + "WHERE rst.ressourceTypeID = ? AND rs.ressourceTypeID = rst.ressourceTypeID "
                + "GROUP BY Rst.Typename, Rst.Ressourcetypeid "
                + "ORDER BY Rst.Ressourcetypeid";
        }
        try {
            
            statement = DBConnector.getInstance().getConnection().prepareStatement(SQLString);
            if(resTypeID!=0){
                statement.setInt(1, resTypeID);
            }
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                
                ressourceList.add(new Ressource(rs.getInt(3), rs.getString(2), 0, rs.getInt(1)));
                
            }
            
        } catch (Exception e) {
            System.out.println("Fail in ressourceMapper, getRessourceList1 " + e.toString());
        }
        
        
        
        return ressourceList;
    }
    
    
    public ArrayList<Ressource> getAllRessources(int restypeID){
        
        ArrayList<Ressource> ressourceList = new ArrayList<>();
        String SQLString = "";
        PreparedStatement statement = null;
        if(restypeID==0){
        SQLString = "SELECT Rs.Ressourcetypeid, Rst.Typename,rs.StorageID, rs.qty   "
                + "FROM ressources rs, ressourceTypes rst WHERE Rst.Ressourcetypeid = Rs.Ressourcetypeid ORDER BY Rs.Ressourcetypeid";
        }
        else
            SQLString = "SELECT Rs.Ressourcetypeid, Rst.Typename,rs.StorageID, rs.qty   "
                + "FROM ressources rs, ressourceTypes rst WHERE Rst.Ressourcetypeid=? AND Rst.Ressourcetypeid = Rs.Ressourcetypeid ORDER BY Rs.Ressourcetypeid";
        
        
        try {
            
            statement = DBConnector.getInstance().getConnection().prepareStatement(SQLString);
            if(restypeID!=0){
                statement.setInt(1, restypeID);
            }
            
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                
                ressourceList.add(new Ressource(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
                
            }
            
        } catch (Exception e) {
            System.out.println("Fail in ressourceMapper, getAllRessource1 " + e.toString());
        }
        
        
        
        return ressourceList;
    }
    
    
    public boolean insertRessource(Ressource r){
        
        int rowsInserted = 0;
        String SQLString ="INSERT INTO ressources "
                + "VALUES(?, ?, ?)";
        PreparedStatement statement = null;
        
        try {
            statement = DBConnector.getInstance().getConnection().prepareStatement(SQLString);
            statement.setInt(1, r.getRessourceTypeID());
            statement.setInt(2, r.getStorageID());
            statement.setInt(3, r.getQty());
            rowsInserted = statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Fail in insertRessource1 " + e.toString());
        }
        finally {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("ERROR in insertRessource2 - insertRessource Finally " + e);
            }
        }
        
        return rowsInserted == 1;
    }
}
