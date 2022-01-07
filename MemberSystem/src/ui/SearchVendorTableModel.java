/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.ArrayList;
import utilityclasses.Vendor;

/**
 * A TableModel for searching for and displaying selected Vendor records.
 * Allows for showing data in a JTable.
 * @author Link Jones
 * @version 1.0
 * @since 2021-11-24
 */

public class SearchVendorTableModel extends VendorTableModel
{
    ArrayList<Vendor> allVendors = new ArrayList<Vendor>(); //master list of all vendors
    //search option to set the string by which the results will be matched
    public enum SearchOption 
    {
        BY_SHOP_NAME,
        BY_FIRST_NAME,
        BY_LAST_NAME
    }
    /**
     * Default constructor
     * Fills the allVendors list
     */
    public SearchVendorTableModel()
    {
        super();
        //copy vendors list into a master list of all vendors
        //vendorsList will be modified to hold search results
        fillAllVendorsList();
    }
    
    /**
     * Constructor which calls searchVendors on initialisation
     * @param searchTerm a string to match
     * @param searchOption a field to match the string with
     */
    public SearchVendorTableModel(String searchTerm, SearchOption searchOption)
    {
        super();
        fillAllVendorsList();
        searchVendors(searchTerm, searchOption);
    }
    /**
     * Fill vendorsList from the database, then fill this data into allVendors list
     */
    public void refreshData()
    {
        fetchData();
        allVendors.clear();
        fillAllVendorsList();
    }
    
    /**
     * Copy the list of vendors from vendorsList into allVendors
     */
    private void fillAllVendorsList()
    {
        System.out.println("Copying vendorsList to AllVendors");
        for (Vendor v: vendorsList)
        {
            allVendors.add(v);
        }
    }
    /**
     * Find all vendors matching the search term for the search option field and
     * add them to vendorsList
     * @param searchTerm a string to match
     * @param searchOption a field to match the string with
     */
    public void searchVendors(String searchTerm, SearchOption searchOption)
    {
        //clear the current search results
        vendorsList.clear();

        switch (searchOption) //check which field to match with the search term
        {
            case BY_SHOP_NAME:
            {
                for (Vendor v : allVendors)
                {
                    if (v.getShopName().compareToIgnoreCase(searchTerm) == 0)
                    {
                        vendorsList.add(v);
                    }
                } 
            }
                break;
            case BY_FIRST_NAME:
            {
                for (Vendor v : allVendors)
                {
                    if (v.getFirstName().compareToIgnoreCase(searchTerm) == 0)
                    {
                        vendorsList.add(v);
                    }
                } 
            }
                break;
            case BY_LAST_NAME:
            {
                for (Vendor v : allVendors)
                {
                    if (v.getLastName().compareToIgnoreCase(searchTerm) == 0)
                    {
                        vendorsList.add(v);
                    }
                } 
            }
                break;
            default: System.err.println("Incorrect search option");
                     break;      
        }
    }
}
