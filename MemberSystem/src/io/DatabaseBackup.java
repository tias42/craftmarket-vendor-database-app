/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import utilityclasses.Section;
import utilityclasses.Vendor;

/**
 * Contains methods to back up the craftmarketdb data to a binary file and 
 * restore the db data from the file
 * @author Link Jones
 * @version 1.0
 * @since 2021-11-24
 */
public class DatabaseBackup
{
    private static final String filename = "DatabaseBackup.bin";
    /**
     * Fetches the vendor and section lists from the craftmarketdb and writes
     * them to a binary file
     * @return true if the backup finished without error
     */
    public static boolean writeToFile()
    {
        boolean successful = false;
        //fetch records from database
        ArrayList<Vendor> vendors = DatabaseMethods.getVendorsList();
        ArrayList<Section> sections = DatabaseMethods.getSectionList();
        
        FileOutputStream fos;
        ObjectOutputStream oos;
        
        try
        {
            //write each db table to file as an arraylist
            fos = new FileOutputStream(filename);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(vendors);
            oos.writeObject(sections);
            oos.close();
            successful = true;
            System.out.println("Database backup has been created");
        }
        catch (FileNotFoundException fnfEx)
        {
            System.err.println("File not found");
        }
        catch (NotSerializableException nsEx)
        {
            System.err.println("Not serializable");
        }
        catch (IOException ioEx)
        {
            System.err.println("Error writing to file");
            ioEx.printStackTrace();
        }
        return successful;
    }
    /**
     * Reads the vendor and section lists from a binary file and writes the 
     * records to the craftmarketdb tables
     * @return true if the database was read from file without error
     */
    public static boolean readFromFile()
    {
        boolean successful = false;
        ArrayList<Vendor> vendors = new  ArrayList<Vendor>();
        ArrayList<Section> sections = new ArrayList<Section>();
        FileInputStream fis;
        ObjectInputStream ois;
        //populate lists from file
        try
        {
            //write binary file data to arraylists
            fis = new FileInputStream(filename);
            ois = new ObjectInputStream(fis);
            vendors = (ArrayList<Vendor>) ois.readObject();
            sections = (ArrayList<Section>) ois.readObject();
            //write arraylist data to database
            DatabaseMethods.populateDatabase(vendors, sections);
            successful = true;
        }
        catch (FileNotFoundException fnfEx)
        {
            System.err.println("File not found");
            fnfEx.printStackTrace();
        }
        catch (IOException ioEx)
        {
            System.err.println("Error writing to file");
            ioEx.printStackTrace();
        }
        catch (ClassNotFoundException cnfEx)
        {
            System.err.println("Class not found");
            cnfEx.printStackTrace();
        } 
        return successful;
    }  
}
