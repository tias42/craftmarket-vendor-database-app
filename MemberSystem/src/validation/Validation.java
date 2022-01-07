/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Data validation methods for use with the Vendor Fields form
 * 
 * @author Link Jones
 * @version 1.0
 * @since 2021-11-24
 */
public class Validation
{
    /**
     * Checks if the string is between 1 and 25 characters long
     * @param input the string to validate
     * @return true if the input is valid and false otherwise
     */
    public static boolean validName(String input)
    {
        return (input.length() !=0 && input.length() <= 25);
    }
     /**
     * Checks if the string is between 1 and 50 characters long
     * @param input the string to validate
     * @return true if the input is valid and false otherwise
     */
    public static boolean validShopName(String input)
    {
        return (input.length() !=0 && input.length() <= 50);
    }
    /**
     * Checks if the mobile number is a string of 10 digits only
     * @param input the string to validate
     * @return true if the input is valid and false otherwise
     */
    public static boolean validMobileNumber(String input)
    {
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
    /**
     * Checks if the email address is formatted like _@_._ with _ matching at least one character
     * @param input the string to validate
     * @return true if the input is valid and false otherwise
     */
    public static boolean validEmailAddress(String input)
    {
        //regex source: https://stackoverflow.com/questions/1710505/asp-net-email-validator-regex
        Pattern pattern = Pattern.compile("\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
    /**
     * Checks if the date is in the format yyyy-mm-dd where mm is between 01 and 12
     * and dd is between 01 and 31 (inclusive)
     * @param input the string to validate
     * @return true if the input is valid and false otherwise
     */
    public static boolean validDate(String input)
    {
        Pattern pattern = Pattern.compile("^(\\d{4}-((0[1-9])|(1[012]))-((0[1-9])|([12][0-9])|(3[01])))$");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
}
