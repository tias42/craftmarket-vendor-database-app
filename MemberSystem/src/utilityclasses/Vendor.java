/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilityclasses;

import java.io.Serializable;

/**
 * Vendor object data container for fetching and storing data from the Vendor
 * table in craftmarketdb.
 * 
 * @author Link Jones
 * @version 1.0
 * @since 2021-11-24
 */
public abstract class Vendor implements Serializable
{
    private int id;
    private String shopName;
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private int sectionID;
    private boolean feePaid;

    /**
     * Default constructor
     */
    public Vendor()
    {
    }
    /**
     * Constructor for Vendor object
     * @param id unique ID
     * @param shopName shop name
     * @param firstName first name
     * @param lastName last name
     * @param email email
     * @param mobile mobile
     * @param sectionID section ID for associated section
     * @param feePaid is the membership fee paid
     */
    public Vendor(int id, String shopName, String firstName, String lastName, String email, String mobile, int sectionID, boolean feePaid)
    {
        this.id = id;
        this.shopName = shopName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.sectionID = sectionID;
        this.feePaid = feePaid;
    }

    /**
     * The type of vendor for recording in the database table
     * Shows implementation of abstract methods in child classes
     * @return a string code of the vendor type
     */
    public abstract String getType();
    /**
     * 
     * @return vendor ID
     */
    public int getId()
    {
        return id;
    }
    /**
     * Set vendor ID
     * @param id Vendor ID
     */
    public void setId(int id)
    {
        this.id = id;
    }
    /**
     * 
     * @return shop name
     */
    public String getShopName()
    {
        return shopName;
    }
    /**
     * 
     * @param shopName 
     */
    public void setShopName(String shopName)
    {
        this.shopName = shopName;
    }
    /**
     * 
     * @return first name
     */
    public String getFirstName()
    {
        return firstName;
    }
    /**
     * 
     * @param firstName
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    /**
     * 
     * @return last name
     */
    public String getLastName()
    {
        return lastName;
    }
    /**
     * 
     * @param lastName 
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    /**
     * 
     * @return email
     */
    public String getEmail()
    {
        return email;
    }
    /**
     * 
     * @param email 
     */
    public void setEmail(String email)
    {
        this.email = email;
    }
    /**
     * 
     * @return mobile
     */
    public String getMobile()
    {
        return mobile;
    }
    /**
     * 
     * @param mobile 
     */
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }
    /**
     * Set section ID for the related Section
     * @return section ID
     */
    public int getSectionID()
    {
        return sectionID;
    }
    /**
     * 
     * @param sectionID section ID for the related Section
     */
    public void setSectionID(int sectionID)
    {
        this.sectionID = sectionID;
    }
    /**
     * 
     * @return true if fee is paid
     */
    public boolean isFeePaid()
    {
        return feePaid;
    }
    /** 
     * 
     * @param feePaid 
     */
    public void setFeePaid(boolean feePaid)
    {
        this.feePaid = feePaid;
    }
    /**
     * 
     * @return String version of Vendor object
     */
    @Override
    public String toString()
    {
        return "Vendor{" + "id=" + id + ", shopName=" + shopName + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", mobile=" + mobile + ", section=" + sectionID + ", feePaid=" + feePaid + '}';
    }
}
