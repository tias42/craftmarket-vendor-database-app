/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import io.DatabaseMethods;
import javax.swing.JFrame;
import utilityclasses.DayPassVendor;
import utilityclasses.PermanentVendor;
import utilityclasses.Section;
import utilityclasses.Vendor;

/**
 * Add Vendor window for the Vendor database application
 * Allows for adding a vendor to the database. Includes data validation of form
 * fields. Uses the VendorFields panel class for form fields.
 * menu
 * @author Link Jones
 * @version 1.0
 * @since 2021-11-24
 */
public class AddVendor extends javax.swing.JFrame
{
    private MainGUI parent;
    /**
     * Creates new form AddMember
     */
    public AddVendor(MainGUI parent)
    {
        this.parent = parent;
        initComponents();
        this.setBounds(100, 100, 550, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        java.awt.GridBagConstraints gridBagConstraints;

        lblTitle = new javax.swing.JLabel();
        dataFields = new ui.VendorFields();
        pnlButtons = new javax.swing.JPanel();
        btnClose = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        lblFeedback = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        lblTitle.setFont(new java.awt.Font("Verdana", 1, 22)); // NOI18N
        lblTitle.setText("Add Vendor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(28, 177, 0, 0);
        getContentPane().add(lblTitle, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 83;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 0, 0);
        getContentPane().add(dataFields, gridBagConstraints);

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCloseActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnClearActionPerformed(evt);
            }
        });

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlButtonsLayout = new javax.swing.GroupLayout(pnlButtons);
        pnlButtons.setLayout(pnlButtonsLayout);
        pnlButtonsLayout.setHorizontalGroup(
            pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlButtonsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        pnlButtonsLayout.setVerticalGroup(
            pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlButtonsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAdd, btnClear, btnClose});

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 38;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 0, 0);
        getContentPane().add(pnlButtons, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 251, 5, 0);
        getContentPane().add(lblFeedback, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Add a new vendor to the database with the supplied data, if the data is
     * valid. Clears the fields if successful, otherwise gives feedback text.
     * @param evt 
     */
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAddActionPerformed
    {//GEN-HEADEREND:event_btnAddActionPerformed
        if (dataFields.validateTextInputs()) //validate data before adding (client-side)
        {
            Vendor newVendor = fetchDataFromTextFields(); //get data and store in vendor object
            //try to add vendor to database
            if (DatabaseMethods.addVendor(newVendor))
            {
                lblFeedback.setText("Vendor added");
                dataFields.clearFields();
            }
            else //failed to add
            {
                lblFeedback.setText("Error adding vendor");
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed
    /**
     * Close this window and show the parent (mainGUI) window
     * @param evt 
     */
    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCloseActionPerformed
    {//GEN-HEADEREND:event_btnCloseActionPerformed
        this.parent.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed
    /**
     * Clear all fields and reset the form
     * @param evt 
     */
    private void btnClearActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnClearActionPerformed
    {//GEN-HEADEREND:event_btnClearActionPerformed
        dataFields.clearFields();
    }//GEN-LAST:event_btnClearActionPerformed
    /**
     * Get the input data from the form fields and store in a Vendor object
     * @return a new Vendor object
     */
    private Vendor fetchDataFromTextFields()
    {
        String firstName = dataFields.getTxtFirstName().getText();
        String lastName = dataFields.getTxtLastName().getText();
        String shopName = dataFields.getTxtShopName().getText();
        String email = dataFields.getTxtEmail().getText();
        String mobile = dataFields.getTxtMobile().getText();
        int sectionID = dataFields.getSelectedSection();
        boolean feePaid = dataFields.getChkFeePaid().isSelected();
        //check what type of vendor
        if (dataFields.getRadPermanent().isSelected()) //permanent vendor
        {
            String joinedDate = dataFields.getTxtDateJoined().getText();
            //id is set to 0 as it will be generated by auto-increment upon insertion
            PermanentVendor newVendor = new PermanentVendor(0, 
                                                            shopName, 
                                                            firstName, 
                                                            lastName, 
                                                            email, 
                                                            mobile, 
                                                            sectionID, 
                                                            feePaid, 
                                                            joinedDate);
            return newVendor;
        }
        else //day pass vendor
        {
            String passDate = dataFields.getTxtPassDate().getText();
            boolean firstTime = dataFields.getChkFirstTime().isSelected();
            //id is set to 0 as it will be generated by auto-increment upon insertion
            DayPassVendor newVendor = new DayPassVendor(0, 
                                                        shopName, 
                                                        firstName, 
                                                        lastName, 
                                                        email, 
                                                        mobile, 
                                                        sectionID, 
                                                        feePaid, 
                                                        passDate, 
                                                        firstTime);
            return newVendor;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClose;
    private ui.VendorFields dataFields;
    private javax.swing.JLabel lblFeedback;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlButtons;
    // End of variables declaration//GEN-END:variables
}