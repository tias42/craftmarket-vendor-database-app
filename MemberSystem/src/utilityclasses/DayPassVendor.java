/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilityclasses;

/**
 * Day Pass Vendor object data container for Day Pass Vendor records
 * @author Link Jones
 * @version 1.0
 * @since 2021-11-24
 */
public class DayPassVendor extends Vendor
{
    private String passDate; //the date of the vendor's day pass
    private boolean firstTime; //is this the vendor's first time attending the market?

    /**
     * Default constructor
     */
    public DayPassVendor()
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
     * @param section section ID of associated section
     * @param feePaid is the membership fee paid
     * @param passDate date that the day pass is valid
     * @param firstTime is this the member's first time
     */
    public DayPassVendor(int id, String shopName, String firstName, String lastName, String email, String mobile, int section, boolean feePaid, String passDate, boolean firstTime)
    {
        super(id, shopName, firstName, lastName, email, mobile, section, feePaid);
        this.passDate = passDate;
        this.firstTime = firstTime;
    }
    /**
     * 
     * @return String representing the type of vendor (Day Pass)
     */
    @Override
    public String getType()
    {
        return "DAYP";
    }
    /**
     * 
     * @return pass date
     */
    public String getPassDate()
    {
        return passDate;
    }
    /**
     * 
     * @param passDate 
     */
    public void setPassDate(String passDate)
    {
        this.passDate = passDate;
    }
    /**
     * 
     * @return is this the member's first time
     */
    public boolean isFirstTime()
    {
        return firstTime;
    }
    /**
     * 
     * @param firstTime 
     */
    public void setFirstTime(boolean firstTime)
    {
        this.firstTime = firstTime;
    }
    /**
     * 
     * @return string version of vendor information
     */
    @Override
    public String toString()
    {
        return super.toString() + "DayPassVendor{" + "passDate=" + passDate + ", firstTime=" + firstTime + '}';
    } 
}
