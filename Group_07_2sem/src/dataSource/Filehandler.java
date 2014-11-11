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

/**
 *
 * @author abettbl
 */
public class Filehandler {

    
    public void writeToFile(Order order){
        
        
        BufferedWriter writer = null;
        try {
            
            //laver fil ud fra nuværende tidspunkt
            String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            File logFile = new File(timeLog + ".txt");

        
            if(domain.Controller.debugMode)
                System.out.println("DebugMode (Filehandler.java): "+ "Writing to file" +
                        logFile.getCanonicalPath() + logFile);

            writer = new BufferedWriter(new FileWriter(logFile));
        
            //Sidehoved til dokumentet
            writer.write("OrderID:" + order.getOrderID());
            writer.newLine();
            writer.write("Antal\tResID\tLagerId\tnavn");
            writer.newLine();
            
            for (OrderDetail od : order.getOrderDetails()) {
                    if(domain.Controller.debugMode)
                        System.out.println("DebugMode (Filehandler.java): "+ od.getQty() + "\t" + od.getResourceTypeID() + "\t" +
                            od.getRessource().getRessourceTypeName() + "\t" + od.getStorageID());
                    
                    writer.write(od.getQty() + "\t" + od.getResourceTypeID() + "\t" + 
                            od.getStorageID() + "\t" + od.getRessource().getRessourceTypeName());
                    writer.newLine();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // flusher bufferen og lukker file stream
                writer.close();
            } catch (Exception e) {
            }
        }
    }
    
}
