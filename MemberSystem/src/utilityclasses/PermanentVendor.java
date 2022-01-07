/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilityclasses;

/**
 * Permanent Vendor object data container for Permanent Vendor records
 * @author Link Jones
 * @version 1.0
 * @since 2021-11-24
 */
public class PermanentVendor extends Vendor
{
    private String joinedDate;
    /**
     * Default constructor
     */
    public PermanentVendor()
    {
        super();
    }
    /**
     * Constructor
     * @param id unique ID  
     * @param shopName shop name
     * @param firstName first name
     * @param lastName last name
     * @param email email
     * @param mobile mobile
     * @param section section ID for associated section
     * @param feePaid is the membership fee paid
     * @param joinedDate date the member joined
     */
    public PermanentVendor(int id, String shopName, String firstName, String lastName, String email, String mobile, int section, boolean feePaid, String joinedDate)
    {
        super(id, shopName, firstName, lastName, email, mobile, section, feePaid);
        this.joinedDate = joinedDate;
    }
    /**
     * 
     * @return String representing the type of vendor (Permanent)
     */
    @Override
    public String getType()
    {
        return "PERM";
    }
    /**
     * 
     * @return date the member joined
     */
    public String getJoinedDate()
    {
        return joinedDate;
    }
    /**
     * 
     * @param joinedDate 
     */
    public void setJoinedDate(String joinedDate)
    {
        this.joinedDate = joinedDate;
    }
    /**
     * 
     * @return string version of vendor information
     */
    @Override
    public String toString()
    {
        return super.toString() + "PermanentVendor{" + "joinedDate=" + joinedDate + '}';
    }   
}
