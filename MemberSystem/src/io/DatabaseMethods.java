/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import utilityclasses.*;

/**
 * Contains methods for reading from and writing data to the craftmarketdb
 * database for use with the Vendor Database application.
 * @author Link Jones
 * @version 1.0
 * @since 2021-11-24
 */
public class DatabaseMethods
{
    /**
     * Gets the connection to the database
     * @return a connection object that connects to the craftmarketdb database
     */
    public static Connection getConnection()
    {
        Connection conn = null;
        String username = ConnectionDetails.getUsername();
        String password = ConnectionDetails.getPassword();
        String url = ConnectionDetails.getUrl();
        
        try 
        {
            System.out.println("Attempting to connect to database");
            Class.forName(ConnectionDetails.getDriver()); //load driver
            //instantiate connection with user credentials
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database: " + ConnectionDetails.getDb());
        }
        catch (ClassNotFoundException cnfEx)
        {
            System.err.println("Database driver class not found");
            cnfEx.printStackTrace();
        }
        catch (SQLException sqlEx)
        {
            System.err.println("Error connecting to the database");
            sqlEx.printStackTrace();
        }
        return conn;
    }
    /**
     * Create the Section and Vendor database tables if they do not already exist
     */
    public static void createTables()
    {
        Connection conn = null;
        Statement stmt = null;
        
        try
        {
            conn = getConnection();
            //create Section table
            System.out.println("Attempting to create Section table");
            String sql = "CREATE TABLE IF NOT EXISTS Section (section_id int NOT NULL AUTO_INCREMENT, "
                                                            + "name varchar(50), "
                                                            + "size int, "
                                                            + "area varchar(25), "
                                                            + "PRIMARY KEY (section_id));";
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            System.out.println("Section table already exists or was created successfully");
            //create Vendor table
            System.out.println("Attempting to create Vendor table");
            sql = "CREATE TABLE IF NOT EXISTS Vendor (vendor_id int NOT NULL AUTO_INCREMENT, "
                                                            + "section_id int, first_name varchar(25), "
                                                            + "last_name varchar(25), "
                                                            + "shop_name varchar(50), "
                                                            + "email varchar(255), "
                                                            + "mobile varchar(10), "
                                                            + "fee_paid BIT, "
                                                            + "member_type varchar(4), "
                                                            + "joined_date date, "
                                                            + "pass_date date, "
                                                            + "first_time BIT, "
                                                            + "PRIMARY KEY (vendor_id), "
                                                            + "FOREIGN KEY (section_id) "
                                                            + "REFERENCES Section(section_id));";
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Vendor table already exists or was created successfully");
        }
        catch (SQLException sqlEx)
        {
            System.err.println("Error creating database tables");
        }
        finally
        {
            // close the statement object
            try
            {
                if( stmt != null )
                    stmt.close();
                System.out.println("Statement object closed");
            }
            catch(SQLException se)
            {
                System.err.println("Error:  Statement not closed");
            }
            // close connection to the database
            try
            {
                if( conn != null )
                    conn.close();   
                System.out.println("Connection to db closed");
            }
            catch(SQLException se)
            {
                System.err.println("Error:  Connection to db not closed");
            }
        }
    
    }
    /**
     * Empty the database and populate it with records from the provided lists
     * @param vendorsList list of Vendor records
     * @param sectionList list of Section records
     * @return true if all records from both lists added to the database successfully
     */
    public static boolean populateDatabase(ArrayList<Vendor> vendorsList, ArrayList<Section> sectionList)
    {
        boolean successful = true;
        clearDatabase(); //make sure the db is empty first
        System.out.println("Adding sections to Section table");        
        for (Section s: sectionList)
        { //add each section to the database
            successful &= addSection(s); 
        }
        System.out.println("Adding vendors to Vendor table");          
        for (Vendor v: vendorsList)
        { //add each vendor to the database
            successful &= addVendorWithId(v); // record if adding was successful 
        }
        //print feedback
        if (successful)
            System.out.println("Database populated");
        else
            System.err.println("Error adding one or more records to the database");
        
        return successful;
    }
    /**
     * Delete all records from the database
     */
    public static void clearDatabase()
    {
        Connection conn = null;
        Statement stmt = null;
 
        try
        { 
            conn = getConnection();
            //delete vendors first, as vendor table contains foreign key 
            //reference to section table
            String sql = "DELETE FROM Vendor";
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            //delete section table data
            sql = "DELETE FROM Section";
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            
            System.out.println("Database records cleared");
        }
        catch (SQLException sqlEx)
        {
            System.err.println("Error deleting records");
            sqlEx.printStackTrace();
        }
        finally
        {
            // close the statement object
            try
            {
                if( stmt != null )
                    stmt.close();
                
                System.out.println("Statement object closed");
            }
            catch(SQLException se)
            {
                System.err.println("Error:  Statement not closed");
            }
            
            // close connection to the database
            try
            {
                if( conn != null )
                    conn.close();
                
                System.out.println("Connection to db closed");
            }
            catch(SQLException se)
            {
                System.err.println("Error:  Connection to db not closed");
            }
        }
    }
    
    /**
     * Populate the Section data from the database
     * @return an ArrayList of Section objects containing data from the Section
     * table
     */
    public static ArrayList<Section> getSectionList()
    {
        Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;
        ArrayList<Section> sectionList = new ArrayList<Section>();
        try
        {
            conn = getConnection();
            //get section data
            System.out.println("Loading Section data");
            String sql = "SELECT * FROM Section ORDER BY name"; //sorted alphabetically
            stmt = conn.createStatement();
            result = stmt.executeQuery(sql);
            
            while (result.next())
            { //create section object 
                Section s = new Section(result.getInt("section_id"),
                                            result.getString("name"),
                                            result.getInt("size"),
                                            result.getString("area"));
                sectionList.add(s); //store section in the list
            }   
            System.out.println("Section data loaded");
        }
        catch (SQLException sqlEx)
        {
            System.err.println("Error reading Section list from database");
        }
        finally
        {
            // close the statement object
            try
            {
                if( stmt != null )
                    stmt.close();
                
                System.out.println("Statement object closed");
            }
            catch(SQLException se)
            {
                System.err.println("Error:  Statement not closed");
            }
            
            // close connection to the database
            try
            {
                if( conn != null )
                    conn.close();
                
                System.out.println("Connection to db closed");
            }
            catch(SQLException se)
            {
                System.err.println("Error:  Connection to db not closed");
            }
        }
        return sectionList;
    }
    /**
     * Fetch all vendors from the Vendor table in the database. Vendors contain
     * sectionID information to refer to their section in the sectionList
     * @return an ArrayList of Vendor objects
     */
    public static ArrayList<Vendor> getVendorsList()
    {
        ArrayList<Vendor> vendorsList = new ArrayList<Vendor>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;
        
        try
        {
            conn = getConnection();
            System.out.println("Loading Vendor data");
            //get vendor data
            String sql = "SELECT * FROM Vendor ORDER BY last_name"; //in order of last name
            stmt = conn.createStatement();
            result = stmt.executeQuery(sql);
            //check if vendors are Permanent or Day Pass and create the correct object type
            //create each vendor and add to the vendor list
            while (result.next())
            {            
                if (result.getString("member_type").compareTo("PERM") == 0)
                {
                    PermanentVendor v = new PermanentVendor();
                    v.setId(result.getInt("vendor_id"));
                    v.setSectionID(result.getInt("section_id"));
                    v.setShopName(result.getString("shop_name"));
                    v.setFirstName(result.getString("first_name"));
                    v.setLastName(result.getString("last_name"));
                    v.setEmail(result.getString("email"));
                    v.setMobile(result.getString("mobile"));
                    v.setFeePaid(result.getBoolean("fee_paid"));
                    v.setJoinedDate(result.getDate("joined_date").toString());
                    vendorsList.add(v);
                }
                else 
                {
                    DayPassVendor v = new DayPassVendor();
                    v.setId(result.getInt("vendor_id"));
                    v.setSectionID(result.getInt("section_id"));
                    v.setShopName(result.getString("shop_name"));
                    v.setFirstName(result.getString("first_name"));
                    v.setLastName(result.getString("last_name"));
                    v.setEmail(result.getString("email"));
                    v.setMobile(result.getString("mobile"));
                    v.setFeePaid(result.getBoolean("fee_paid"));
                    v.setPassDate(result.getDate("pass_date").toString());
                    v.setFirstTime(result.getBoolean("first_time"));
                    vendorsList.add(v);
                }    
            }
            System.out.println("Vendor data loaded");
        }
        catch (SQLException sqlEx)
        {
            System.err.println("Error reading Vendor list from database");
        }
        finally
        {
            // close the statement object
            try
            {
                if( stmt != null )
                    stmt.close();
                
                System.out.println("Statement object closed");
            }
            catch(SQLException se)
            {
                System.err.println("Error:  Statement not closed");
            }
            
            // close connection to the database
            try
            {
                if( conn != null )
                    conn.close();
                
                System.out.println("Connection to db closed");
            }
            catch(SQLException se)
            {
                System.err.println("Error:  Connection to db not closed");
            }
        }
        return vendorsList;
    }
    /**
     * Update the details of a vendor in the database
     * Does not check if values are null
     * @param vendor the Vendor to be updated. The vendor's ID must match a vendor
     * in the database, and all other details in the db will be changed to match the object's.
     * @return true if the vendor was updated successfully
     */
    public static boolean UpdateVendor(Vendor vendor)
    {
        Connection conn = null;
        Statement stmt = null;
        boolean successful = false;
        
        try
        {
            conn = getConnection();
            String sql = ""; //instantiate with dummy value, as it is assigned below
            //check what kind of vendor
            if (vendor instanceof PermanentVendor) //Permanent
            {
                PermanentVendor pVendor = (PermanentVendor) vendor;
                sql = "UPDATE Vendor SET section_id= " + pVendor.getSectionID() + 
                        ", first_name = '" + pVendor.getFirstName() + 
                        "', last_name = '" + pVendor.getLastName() + 
                        "', shop_name = '" + pVendor.getShopName() + 
                        "', email = '" + pVendor.getEmail() + 
                        "', mobile = '" + pVendor.getMobile() +
                        "', fee_paid = " + pVendor.isFeePaid() + 
                        ", member_type = 'PERM'" +
                        ", joined_date = '" + pVendor.getJoinedDate() +
                        "' WHERE vendor_id = " + pVendor.getId();
            }
            else //Day Pass
            {
                DayPassVendor dVendor = (DayPassVendor) vendor;
                sql = "UPDATE Vendor SET section_id= " + dVendor.getSectionID() + 
                        ", first_name = '" + dVendor.getFirstName() + 
                        "', last_name = '" + dVendor.getLastName() + 
                        "', shop_name = '" + dVendor.getShopName() + 
                        "', email = '" + dVendor.getEmail() + 
                        "', mobile = '" + dVendor.getMobile() +
                        "', fee_paid = " + dVendor.isFeePaid() + 
                        ", member_type = 'DAYP'" +
                        ", pass_date = '" + dVendor.getPassDate() +
                        "', first_time = " + dVendor.isFirstTime() +
                        " WHERE vendor_id = " + dVendor.getId();
            }
            System.out.println(sql);
            System.out.println("Updating vendor table");
            stmt = conn.createStatement();
            int updatedRows = stmt.executeUpdate(sql);
            //if exactly one vendor was modified, the update succeeded
            if (updatedRows == 1)
            {
                successful = true;
            }
            System.out.println("Vendor updated successfully");
        }
        catch (SQLException sqlEx)
        {
            System.err.println("Error updating vendor");
        }
        finally
        {
            // close the statement object
            try
            {
                if( stmt != null )
                    stmt.close();
                
                System.out.println("Statement object closed");
            }
            catch(SQLException se)
            {
                System.err.println("Error:  Statement not closed");
            }
            
            // close connection to the database
            try
            {
                if( conn != null )
                    conn.close();
                
                System.out.println("Connection to db closed");
            }
            catch(SQLException se)
            {
                System.err.println("Error:  Connection to db not closed");
            }
        }
        
        return successful;
    }
    /**
     * Add a new vendor to the database. Vendor ID is assigned using auto-increment
     * @param vendor a vendor object containing the data for the new record
     * @return true if the vendor was added successfully
     */
    public static boolean addVendor(Vendor vendor)
    {
        boolean successful = false;
        Connection conn = null;
        Statement stmt = null;
        
        try
        {
            conn = getConnection();
            String sql = "";
            //check which kind of vendor
            //and insert into database
            if (vendor instanceof PermanentVendor) //permanent vendor
            {
                sql = "INSERT INTO Vendor (section_id, "
                        + "first_name,"
                        + "last_name,"
                        + "shop_name,"
                        + "email,"
                        + "mobile,"
                        + "fee_paid,"
                        + "member_type,"
                        + "joined_date) VALUES (" 
                        + vendor.getSectionID() + ", '" 
                        + vendor.getFirstName() + "', '"
                        + vendor.getLastName() + "', '"
                        + vendor.getShopName() + "', '"
                        + vendor.getEmail() + "', '"
                        + vendor.getMobile() + "', "
                        + vendor.isFeePaid() + ", '"
                        + vendor.getType() + "', '"
                        + ((PermanentVendor) vendor).getJoinedDate() + "');";
            }
            else //day pass vendor
            {
                sql = "INSERT INTO Vendor (section_id, "
                        + "first_name, "
                        + "last_name, "
                        + "shop_name, "
                        + "email, "
                        + "mobile, "
                        + "fee_paid, "
                        + "member_type, "
                        + "pass_date, "
                        + "first_time) VALUES (" 
                        + vendor.getSectionID() + ", '" 
                        + vendor.getFirstName() + "', '"
                        + vendor.getLastName() + "', '"
                        + vendor.getShopName() + "', '"
                        + vendor.getEmail() + "', '"
                        + vendor.getMobile() + "', "
                        + vendor.isFeePaid() + ", '"
                        + vendor.getType() + "', '"
                        + ((DayPassVendor) vendor).getPassDate() + "', "
                        + ((DayPassVendor) vendor).isFirstTime() + ");";
            }
            System.out.println(sql);
            stmt = conn.createStatement();
            int updatedRows = stmt.executeUpdate(sql);
            //check if exactly one record was inserted
            if (updatedRows == 1)
            {
                successful = true;
            }
            System.out.println("Vendor added successfully");
        }
        catch (SQLException sqlEx)
        {
            System.err.println("Error adding vendor");
            sqlEx.printStackTrace();
        }
        finally
        {
            // close the statement object
            try
            {
                if( stmt != null )
                    stmt.close();
                
                System.out.println("Statement object closed");
            }
            catch(SQLException se)
            {
                System.err.println("Error:  Statement not closed");
            }
            
            // close connection to the database
            try
            {
                if( conn != null )
                    conn.close();
                
                System.out.println("Connection to db closed");
            }
            catch(SQLException se)
            {
                System.err.println("Error:  Connection to db not closed");
            }
        }
        
        return successful;
    }
    /**
     * Add a vendor to the database, specifying vendor ID (instead of using 
     * auto-increment)
     * @param vendor the details of the new vendor to be added
     * @return true if the vendor was successfully added to the database
     */
    public static boolean addVendorWithId(Vendor vendor)
    {
        boolean successful = false;
        Connection conn = null;
        Statement stmt = null;
  
        try
        {
            conn = getConnection();
            String sql = "";
            //check which kind of vendor
            //and add into database
            if (vendor instanceof PermanentVendor) //permanent vendor
            {
                sql = "INSERT INTO Vendor (vendor_id, "
                        + "section_id, "
                        + "first_name,"
                        + "last_name,"
                        + "shop_name,"
                        + "email,"
                        + "mobile,"
                        + "fee_paid,"
                        + "member_type,"
                        + "joined_date) VALUES (" 
                        + vendor.getId() + ", "
                        + vendor.getSectionID() + ", '" 
                        + vendor.getFirstName() + "', '"
                        + vendor.getLastName() + "', '"
                        + vendor.getShopName() + "', '"
                        + vendor.getEmail() + "', '"
                        + vendor.getMobile() + "', "
                        + vendor.isFeePaid() + ", '"
                        + vendor.getType() + "', '"
                        + ((PermanentVendor) vendor).getJoinedDate() + "');";
            }
            else //day pass vendor
            {
                sql = "INSERT INTO Vendor (vendor_id, "
                        + "section_id, "
                        + "first_name, "
                        + "last_name, "
                        + "shop_name, "
                        + "email, "
                        + "mobile, "
                        + "fee_paid, "
                        + "member_type, "
                        + "pass_date, "
                        + "first_time) VALUES (" 
                        + vendor.getId() + ", "
                        + vendor.getSectionID() + ", '" 
                        + vendor.getFirstName() + "', '"
                        + vendor.getLastName() + "', '"
                        + vendor.getShopName() + "', '"
                        + vendor.getEmail() + "', '"
                        + vendor.getMobile() + "', "
                        + vendor.isFeePaid() + ", '"
                        + vendor.getType() + "', '"
                        + ((DayPassVendor) vendor).getPassDate() + "', "
                        + ((DayPassVendor) vendor).isFirstTime() + ");";
            }
            System.out.println(sql);
            stmt = conn.createStatement();
            int updatedRows = stmt.executeUpdate(sql);
            if (updatedRows == 1)
            {
                successful = true;
            }
            System.out.println("Vendor added successfully");
        }
        catch (SQLException sqlEx)
        {
            System.err.println("Error adding vendor");
            sqlEx.printStackTrace();
        }
        finally
        {
            // close the statement object
            try
            {
                if( stmt != null )
                    stmt.close();
                
                System.out.println("Statement object closed");
            }
            catch(SQLException se)
            {
                System.err.println("Error:  Statement not closed");
            }
            
            // close connection to the database
            try
            {
                if( conn != null )
                    conn.close();
                
                System.out.println("Connection to db closed");
            }
            catch(SQLException se)
            {
                System.err.println("Error:  Connection to db not closed");
            }
        }
        
        return successful;
    }
    /**
     * Delete vendor from database
     * @param vendorID the ID of the vendor to delete
     * @return true if the vendor was successfully deleted
     */
    public static boolean deleteVendor(int vendorID)
    {
        boolean successful = false;
        Connection conn = null;
        Statement stmt = null;
  
        try
        {
            conn = getConnection();
            //delete vendor
            String sql = "DELETE FROM Vendor WHERE vendor_id = " + vendorID;
            System.out.println(sql);
            stmt = conn.createStatement();
            //check if exactly one vendor was deleted
            int updatedRows = stmt.executeUpdate(sql);
            if (updatedRows == 1)
            {
                successful = true;
            }
            System.out.println("Vendor added successfully");
        }
        catch (SQLException sqlEx)
        {
            System.err.println("Error adding vendor");
        }
        finally
        {
            // close the statement object
            try
            {
                if( stmt != null )
                    stmt.close();
                
                System.out.println("Statement object closed");
            }
            catch(SQLException se)
            {
                System.err.println("Error:  Statement not closed");
            }
            
            // close connection to the database
            try
            {
                if( conn != null )
                    conn.close();
                
                System.out.println("Connection to db closed");
            }
            catch(SQLException se)
            {
                System.err.println("Error:  Connection to db not closed");
            }
        }
        
        return successful;
    }
    /**
     * Add a section to the database (does not use auto-increment for section ID)
     * @param section a section object containing the data to be added
     * @return true if the section was successfully added
     */
    public static boolean addSection(Section section)
    {
        boolean successful = false;
        Connection conn = null;
        Statement stmt = null;
        try
        {
            conn = getConnection();
            //insert section data
            String sql = "INSERT INTO Section (section_id, "
                        + "name, "
                        + "size, "
                        + "area) VALUES (" 
                        + section.getId()+ ", '" 
                        + section.getName() + "', "
                        + section.getSize() + ", '"
                        + section.getArea() + "');";

            System.out.println(sql);
            stmt = conn.createStatement();
            //check if exactly one section was inserted
            int updatedRows = stmt.executeUpdate(sql);
            if (updatedRows == 1)
            {
                successful = true;
            }
            System.out.println("Section added successfully");
        }
        catch (SQLException sqlEx)
        {
            System.err.println("Error adding section");
            sqlEx.printStackTrace();
        }
        finally
        {
            // close the statement object
            try
            {
                if( stmt != null )
                    stmt.close();
                
                System.out.println("Statement object closed");
            }
            catch(SQLException se)
            {
                System.err.println("Error:  Statement not closed");
            }
            
            // close connection to the database
            try
            {
                if( conn != null )
                    conn.close();
                
                System.out.println("Connection to db closed");
            }
            catch(SQLException se)
            {
                System.err.println("Error:  Connection to db not closed");
            }
        }
        
        return successful;
    }
}
    
