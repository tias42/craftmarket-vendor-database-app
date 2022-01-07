/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

/**
 * Contains the connection details for the craftmarketdb database
 * @author Link Jones
 * @version 1.0
 * @since 2021-11-24
 */
public class ConnectionDetails
{
     private static final String username = "default";
    private static final String password = "default";
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/"; //localhost could be replaced by pc's IP, and the number is the port number
    
    private static final String db = "craftmarketdb";

    /**
     * 
     * @return the database connection username
     */
    public static String getUsername()
    {
        return username;
    }

    /**
     * 
     * @return the database connection password
     */
    public static String getPassword()
    {
        return password;
    }
    /**
     * 
     * @return the jdbc database connection driver class
     */
    public static String getDriver()
    {
        return driver;
    }
    /**
     * 
     * @return the database connection URL (autoreconnect=true and SSL=false)
     */
    public static String getUrl()
    {
        return url + db + "?autoReconnect=true&useSSL=false";
    }
    /**
     * 
     * @return the database name
     */
    public static String getDb()
    {
        return db;
    }
    
}
