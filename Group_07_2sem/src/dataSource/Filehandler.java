/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

// Importer order
import domain.Order;
import domain.OrderDetail;
import domain.Ressource;

/**
 *
 * @author abettbl
 */
public class Filehandler {

    
    public void writeToFile(Order order){
        
        
        BufferedWriter writer = null;
        try {
            
            //Laver midlertidig fil
            String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            File logFile = new File(timeLog);

        
            // This will output the full path where the file will be written to...
            //System.out.println(logFile.getCanonicalPath());

            writer = new BufferedWriter(new FileWriter(logFile));
        
            
            //Sidehoved til dokumentet
            writer.write("OrderID:" + order.getOrderID());
            writer.write("Antal \t RessourceID \t Navn \t LagerID");
            
            //ResID, restypeID, String restype, StorageID, maxqty
            
            // metode (List resID, list StorID) return list res objects
            
            
            
            for (OrderDetail od : order.getOrderDetails()) {
                
                    writer.write(od.getQty() + "\t" + od.getResourceID() + "\t" +
                            od.getRessource.getTypename + "\t" + od.getStorageID());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }
    }
    
}
