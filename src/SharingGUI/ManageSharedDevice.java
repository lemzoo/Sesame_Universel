/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SharingGUI;

import AccesGUI.*;
import HomeGUI.*;
import LinkingGUI.*;
import SesameApp.*;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author LamineBA
 */
public class ManageSharedDevice extends JFrame implements ListSelectionListener {

    /**
     * Creates new form ManageAcces
     */
    private String selected_item = "";
    
    public ManageSharedDevice() {
        initComponents();
        
        // init the list
        fillOutTheChoiceList();
        nouveau_partage_jbutton.setEnabled(true);
        info_jbutton.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpanel_titre = new javax.swing.JPanel();
        titre_fenetre = new javax.swing.JLabel();
        separator_jpanel = new javax.swing.JPanel();
        info_jbutton = new javax.swing.JButton();
        nouveau_partage_jbutton = new javax.swing.JButton();
        liste_accreditee = new java.awt.List();
        separator_jpanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestion du Partage des Accès");

        titre_fenetre.setFont(new java.awt.Font("Monaco", 1, 14)); // NOI18N
        titre_fenetre.setText("Liste des utilisateurs disposant des accès");

        javax.swing.GroupLayout jpanel_titreLayout = new javax.swing.GroupLayout(jpanel_titre);
        jpanel_titre.setLayout(jpanel_titreLayout);
        jpanel_titreLayout.setHorizontalGroup(
            jpanel_titreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel_titreLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(titre_fenetre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(29, 29, 29))
        );
        jpanel_titreLayout.setVerticalGroup(
            jpanel_titreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel_titreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titre_fenetre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        separator_jpanel.setBackground(new java.awt.Color(0, 153, 255));

        javax.swing.GroupLayout separator_jpanelLayout = new javax.swing.GroupLayout(separator_jpanel);
        separator_jpanel.setLayout(separator_jpanelLayout);
        separator_jpanelLayout.setHorizontalGroup(
            separator_jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        separator_jpanelLayout.setVerticalGroup(
            separator_jpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        info_jbutton.setBackground(new java.awt.Color(255, 153, 153));
        info_jbutton.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        info_jbutton.setText("Plus d'info sur l'utilisateur");
        info_jbutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                obtenirInfoBtnMouseReleased(evt);
            }
        });

        nouveau_partage_jbutton.setBackground(new java.awt.Color(153, 255, 51));
        nouveau_partage_jbutton.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        nouveau_partage_jbutton.setText("Nouveau partage");
        nouveau_partage_jbutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                effectuerPartageBtnMouseReleased(evt);
            }
        });

        liste_accreditee.setBackground(new java.awt.Color(153, 153, 255));
        liste_accreditee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listeAccrediteeMouseClicked(evt);
            }
        });

        separator_jpanel1.setBackground(new java.awt.Color(0, 153, 255));

        javax.swing.GroupLayout separator_jpanel1Layout = new javax.swing.GroupLayout(separator_jpanel1);
        separator_jpanel1.setLayout(separator_jpanel1Layout);
        separator_jpanel1Layout.setHorizontalGroup(
            separator_jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        separator_jpanel1Layout.setVerticalGroup(
            separator_jpanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpanel_titre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(info_jbutton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nouveau_partage_jbutton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(separator_jpanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(separator_jpanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(liste_accreditee, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(4, 4, 4)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpanel_titre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separator_jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(liste_accreditee, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separator_jpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(info_jbutton)
                    .addComponent(nouveau_partage_jbutton))
                .addGap(15, 15, 15))
        );

        //liste_peripherique.addListSelectionListener(this);

        setSize(new java.awt.Dimension(407, 526));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void listeAccrediteeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listeAccrediteeMouseClicked
        // TODO add your handling code here:
        selected_item = liste_accreditee.getSelectedItem();
        System.out.println("Vous avez selectionné l'utilisateur : " + selected_item);
        info_jbutton.setEnabled(true);
    }//GEN-LAST:event_listeAccrediteeMouseClicked

    private void effectuerPartageBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_effectuerPartageBtnMouseReleased
        // Call the classe DeviceAvailableForSharing()        
        this.close();
        DeviceAvailableForSharing dev = new DeviceAvailableForSharing ();
        dev.setVisible(true);
    }//GEN-LAST:event_effectuerPartageBtnMouseReleased

    private void obtenirInfoBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_obtenirInfoBtnMouseReleased
        // TODO add your handling code here:
        InfoAboutSelectedUser info = new InfoAboutSelectedUser ();
        info.setVisible(true);
    }//GEN-LAST:event_obtenirInfoBtnMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManageSharedDevice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageSharedDevice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageSharedDevice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageSharedDevice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ManageSharedDevice().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton info_jbutton;
    private javax.swing.JPanel jpanel_titre;
    private java.awt.List liste_accreditee;
    private javax.swing.JButton nouveau_partage_jbutton;
    private javax.swing.JPanel separator_jpanel;
    private javax.swing.JPanel separator_jpanel1;
    private javax.swing.JLabel titre_fenetre;
    // End of variables declaration//GEN-END:variables
    private String [] device_list = {"DEVICE 01", "DEVICE 02", "DEVICE XYZ", "DEVICE ACD",
                                     "SESAME DOORS", "SESAME", "EveryGates", "Vitry sur Seine", "Paris", "France"};
    
    /**
     * Methode : fillOutTheChoiceList() allows you to fill out the choice list for the user
     * This methode will search all the device linked with this SESAME. 
     */
    public void fillOutTheChoiceList(){
        System.out.println("<--- BEGIN : fillOutTheChoiceList() --->");
        
        String user_name = "";
        
        // Make the deserialization of the table file which is the database of the device
        OwnerInformation user = null;
        boolean flag_extraction =  false;
        //File file = new File("owner_information.ser");
        File file = new File("/home/pi/Desktop/sharing/owner_information.ser");
        try (FileInputStream fileIn = new FileInputStream(file); ObjectInputStream in = new ObjectInputStream(fileIn)) {
            user = (OwnerInformation) in.readObject();

            flag_extraction = true;

        }catch(IOException i){
            flag_extraction = false;
            System.out.println("IOException : " + i.getMessage());
        }catch(ClassNotFoundException c){
           System.out.println("OwnerInformation class not found");
           flag_extraction =false;
        }
        System.out.println("Extraction Flag = " + flag_extraction);
        
        // Check if the deserialization is done succesffully
        if (flag_extraction){
            user_name = user.getOwnerFirstName() + " " + user.getOwnerLastName();
            System.out.println("Nom et Prenom = " + user_name);
            if (user_name != null){
                // Fit the device array list 
                liste_accreditee.add(user_name);
            }
            else{
                for (int i=0; i<device_list.length; i++){
                    liste_accreditee.add(device_list[i], i);
                }
            }
        }
        else{
            for (int i=0; i<device_list.length; i++){
                liste_accreditee.add(device_list[i], i);
            }
        }
        System.out.println("<--- END : fillOutTheChoiceList() --->");
        //liste_peripherique.addListSelectionListener(this);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        
    }
    
    /**
     * Methode : close() allow you to close the current windows
     */
    private void close(){
        WindowEvent winClosing = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosing);
    }
}