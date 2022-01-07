/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.swing.JFrame;
import ui.MainGUI;

/**
 * A database interface application for the Craft Market Vendor application.
 * Allows viewing, adding, updating and deleting records, backing up
 * and restoring the database from a binary file, and viewing a help document 
 * that contains a user guide for the application.
 * 
 * @author Link Jones
 * @version 1.0
 * @since 2021-11-24
 */
public class Program
{
    /**
     * Opens the main menu window of the application
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        MainGUI app = new MainGUI();
        
        app.setBounds(50, 50, 550, 800);
        app.setLocationRelativeTo(null);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
       // ui.AddMember app = new ui.AddMember();
    } 
}
