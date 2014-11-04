/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Søren
 */
public class DBConnector {
    
    
    private static String driver = "oracle.jdbc.driver.OracleDriver";
    private static String URL = "jdbc:oracle:thin:@datdb.cphbusiness.dk:1521:dat";
    private static String id = "cphsl159";			//Insert Your ORACLE id and password
    private static String pw = "cphsl159";
    
    private Connection con;

    //-- Singleton ---- 
    private static DBConnector instance;
    private DBConnector()
    {
        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(URL, id, pw);   // The connection will be released upon program 
		  					      // termination by the garbage collector	
        } catch (Exception e)
        {
            System.out.println("\n*** Remember to insert your Oracle ID and PW in the DBConnector class! ***\n");
            System.out.println("error in DBConnector.getConnection()");
            System.out.println(e);
        }
    }
    public static DBConnector getInstance()
    {
        if (instance == null)
            instance = new DBConnector();
        return instance;
    }
    //------------------
    
    public Connection getConnection()
    {
      return con;
    }
    
}
