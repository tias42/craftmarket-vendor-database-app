/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilityclasses;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Section object data container for fetching and storing data from the Section
 * table in craftmarketdb.
 * Currently section size and area are not used in the application, but may be 
 * in future.
 * @author Link Jones
 * @version 1.0
 * @since 2021-11-24
 */
public class Section implements Serializable //serializable for writing to binary files
{
    private int id;
    private String name;
    private int size;
    private String area;
    
    /**
     * Default constructor
     */
    public Section()
    {
    }

    /**
     * Constructor for Section object
     * 
     * @param id unique ID
     * @param name section name
     * @param size size of the section
     * @param area name of the area the section will be located in
     */
    public Section(int id, String name, int size, String area)
    {
        this.id = id;
        this.name = name;
        this.size = size;
        this.area = area;
    }
    /**
     * 
     * @return section ID
     */
    public int getId()
    {
        return id;
    }
    /**
     * 
     * @param id 
     */
    public void setId(int id)
    {
        this.id = id;
    }
    /**
     * 
     * @return name
     */
    public String getName()
    {
        return name;
    }
    /**
     * 
     * @param name 
     */
    public void setName(String name)
    {
        this.name = name;
    }
    /**
     * 
     * @return size
     */
    public int getSize()
    {
        return size;
    }
    /**
     * 
     * @param size 
     */
    public void setSize(int size)
    {
        this.size = size;
    }
    /**
     * 
     * @return area
     */
    public String getArea()
    {
        return area;
    }
    /**
     * 
     * @param area 
     */
    public void setArea(String area)
    {
        this.area = area;
    }
    /**
     * 
     * @return the section name
     */
    @Override
    public String toString()
    {
        return this.getName();
    }
}
