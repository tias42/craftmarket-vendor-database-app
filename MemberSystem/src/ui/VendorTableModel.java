/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import io.DatabaseMethods;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;
import utilityclasses.Section;
import utilityclasses.Vendor;

/**
 * A TableModel for displaying Vendor data in a JTable.
 * @author Link Jones
 * @version 1.0
 * @since 2021-11-24
 */

public class VendorTableModel extends AbstractTableModel
{
    protected ArrayList<Vendor> vendorsList = new ArrayList<Vendor>();
    protected ArrayList<Section> sectionList = new ArrayList<Section>();
    protected final String[] columnNames = {"ID", "Shop name", "First name", "Last name", "Email", "Mobile", "Section", "Fee paid"};
    /**
     * Constructor. Fills the table model with data
     */
    public VendorTableModel()
    {
        fetchData();
    }
    /**
     * Get vendor and section data arraylists from the database
     */
    public void fetchData()
    {
        vendorsList = DatabaseMethods.getVendorsList();
        sectionList = DatabaseMethods.getSectionList();
    }
    /**
     * 
     * @return the number of rows in the table
     */
    @Override
    public int getRowCount()
    {
        return vendorsList.size();
    }
    /**
     * 
     * @return the number of columns in the table
     */
    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }
    /**
     * Get the value at (row, column)
     * @param row the row index
     * @param column the column index
     * @return the data field for the given column, for the vendor at the given row
     */
    @Override
    public Object getValueAt(int row, int column)
    {
        Vendor v = vendorsList.get(row);
        switch(column)
        {
            case 0: return v.getId();
            case 1: return v.getShopName();
            case 2: return v.getFirstName();
            case 3: return v.getLastName();
            case 4: return v.getEmail();
            case 5: return v.getMobile();
            //get the vendor's section ID and find that section's name
            case 6: return getSectionName(v.getSectionID()); 
            //return Yes if fee is paid and No otherwise
            case 7: return v.isFeePaid() ? "Yes" : "No"; 
        }
        return null;
    }
    /**
     * 
     * @param col the column index
     * @return the name of the given column
     */
    @Override
    public String getColumnName(int col)
    {
        return columnNames[col];
    }
    /**
     * 
     * @param row the row index
     * @return a Vendor object corresponding to the selected row
     */
    public Vendor getRow(int row)
    {
        Vendor v = vendorsList.get(row);
        return v;
    }
    /**
     * Return the name of the section with the given ID in sectionList
     * @param sectionID the unique ID of a Section object
     * @return a name string from the matching Section object
     */
    public String getSectionName(int sectionID)
    {
        for (Section s: sectionList)
        {
            if (s.getId() == sectionID)
            {
                return s.getName();
            }
        }
        return ""; 
    }
    
    /**
     * Allows sorting of non-string fields
     * Source: https://www.codejava.net/java-se/swing/6-techniques-for-sorting-jtable-you-should-know
     * @param columnIndex the table column index
     * @return The class of the selected column
     */
    //Not used as sorting was not implemented
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (vendorsList.isEmpty()) {
            return Object.class;
        }
        return getValueAt(0, columnIndex).getClass();
    }
}
