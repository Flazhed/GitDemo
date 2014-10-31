/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import java.sql.Connection;

/**
 *
 * @author Søren
 */
public class DBFacade {
    
	  private Connection con;
	  
	  //== Singleton start
	  private static DBFacade instance;
	 
	  private DBFacade() {
		  //MAPPERS START
              
                  //MAPPERS END
            	  con 	= DBConnector.getInstance().getConnection();  
                  
	  }
	  public static DBFacade getInstance()
	  {
		  if(instance == null)
			  instance = new DBFacade();
		  return instance;
	  }
	  //== Singleton end
    
}
