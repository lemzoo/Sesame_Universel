/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SharingGUI;

import AccesGUI.*;
import AccreditedClass.*;
import HomeGUI.*;
import LinkingGUI.*;
import SesameApp.*;
import SharingClass.*;

import com.pi4j.io.serial.SerialPortException;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe "ReceiverSummaryInformation" summarize the information about the receiver
 * @author LamineBA
 */
public class ReceiverSummaryInformation extends javax.swing.JFrame implements ConstantsConfiguration{

    private GuestSharingData guest = null;
    private SerialPortSharerInstruction port = null;
    
    public ReceiverSummaryInformation() {
        initComponents();
        
        /* Deserializable of the file containing the information 
           of the data which allow you to identify the receiver of this share */ 
        boolean flag_extraction = false;
        File file = new File("guest_sharing_information.ser");
        try (FileInputStream fileIn = new FileInputStream(file); ObjectInputStream in = new ObjectInputStream(fileIn)) {
            guest = (GuestSharingData) in.readObject();
            flag_extraction = true;
        }catch(IOException i){}
        catch(ClassNotFoundException c){
           System.out.println("PeripheriqueInformation class not found");
        }
        if (flag_extraction){
            String [] data = guest.getGuestSharingData();
            accredite_jlabel.setText(data[0]);
            pseudo_jlabel.setText(data[1]);
            raison_partage_jtextfiel.setText(data[2]);
            relation_jlabel.setText(data[3]);
            
            int day_start = Integer.parseInt(data[4]) + 1;
            int month_start = Integer.parseInt(data[5]) + 1;
            int year_start = Integer.parseInt(data[6]);
            date_debut_partage_jlabel.setText(day_start + "/" + month_start + "/" + year_start);
            
            int day_end = Integer.parseInt(data[7]) + 1;
            int month_end = Integer.parseInt(data[8]) + 1;
            int year_end = Integer.parseInt(data[9]);
            date_fin_partage_jlabel.setText(day_end + "/" + month_end + "/" + year_end);   
        }
        else{
            
        }
        try {
            port = new SerialPortSharerInstruction(115200);
        }
        catch(SerialPortException ex) {
            System.out.println(" ==>> SERIAL SETUP FAILED : " + ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lancer_procedure = new javax.swing.JToggleButton();
        corriger_info = new javax.swing.JToggleButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        raison_partage_jtextfiel = new javax.swing.JTextField();
        pseudo_jlabel = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        relation_jlabel = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        date_debut_partage_jlabel = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        date_fin_partage_jlabel = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        accredite_jlabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Recapitulatif des informations du partage");
        setResizable(false);

        lancer_procedure.setBackground(new java.awt.Color(153, 255, 153));
        lancer_procedure.setText("Lancer la Procedure");
        lancer_procedure.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lancer_procedureMouseReleased(evt);
            }
        });

        corriger_info.setBackground(new java.awt.Color(255, 153, 153));
        corriger_info.setText("Corriger les infos");
        corriger_info.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                corriger_infoMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(corriger_info)
                .addGap(60, 60, 60)
                .addComponent(lancer_procedure)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lancer_procedure)
                    .addComponent(corriger_info))
                .addContainerGap())
        );

        jLabel13.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel13.setText("Pseudo attribué : ");

        jLabel17.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel17.setText("Raison du partage");

        raison_partage_jtextfiel.setBackground(new java.awt.Color(204, 255, 204));
        raison_partage_jtextfiel.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        raison_partage_jtextfiel.setText("Raison du partage");

        pseudo_jlabel.setBackground(new java.awt.Color(204, 255, 204));
        pseudo_jlabel.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        pseudo_jlabel.setText("Pseudo");

        jLabel14.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel14.setText("Relation avec l'accrédité : ");

        relation_jlabel.setBackground(new java.awt.Color(204, 255, 204));
        relation_jlabel.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        relation_jlabel.setText("Relation");

        jLabel15.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel15.setText("Date de début du partage : ");

        date_debut_partage_jlabel.setBackground(new java.awt.Color(204, 255, 204));
        date_debut_partage_jlabel.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        date_debut_partage_jlabel.setText("01/01/2015");

        jLabel16.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel16.setText("Date de fin du partage      : ");

        date_fin_partage_jlabel.setBackground(new java.awt.Color(204, 255, 204));
        date_fin_partage_jlabel.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        date_fin_partage_jlabel.setText("01/01/2016");

        jLabel18.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel18.setText("Identifiant de l'accrédité : ");

        accredite_jlabel.setBackground(new java.awt.Color(204, 255, 204));
        accredite_jlabel.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        accredite_jlabel.setText("ID_Sesame_Guest");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(8, 8, 8)
                        .addComponent(date_fin_partage_jlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(date_debut_partage_jlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(relation_jlabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel17)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pseudo_jlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(accredite_jlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(raison_partage_jtextfiel, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(accredite_jlabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(pseudo_jlabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(raison_partage_jtextfiel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(relation_jlabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(date_debut_partage_jlabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(date_fin_partage_jlabel))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(51, 0, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel2.setText("Recapitulatif des informations de l'accrédité");
        jLabel2.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(51, 0, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(394, 470));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lancer_procedureMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lancer_procedureMouseReleased
        // 1. Instanciation de la communication uart
        // 2. Envoie de la trame demande de rattachement d'un Sesame; Periphérique verifie s'il est rattaché ou pas
        // 3. Peripherique renvoie une autorisation de rattachement
        // 4. Sesame envoie le fichier d'information du peripherique et son identifiant
        
        // Diseable the acces to the acces
        lancer_procedure.setEnabled(false);
        corriger_info.setEnabled(false);
        
        // Call the methode which contains the instruction of the sharing
        shareAccesInstruction();
        
        lancer_procedure.setEnabled(true);
        corriger_info.setEnabled(true);
    }//GEN-LAST:event_lancer_procedureMouseReleased

    private void corriger_infoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_corriger_infoMouseReleased
        // TODO add your handling code here:
        close();
        port.closeUartPort();
        IdentifyTheReceiver id_accredited = new IdentifyTheReceiver();
        id_accredited.setVisible(true);
    }//GEN-LAST:event_corriger_infoMouseReleased

    
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
            java.util.logging.Logger.getLogger(ReceiverSummaryInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReceiverSummaryInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReceiverSummaryInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReceiverSummaryInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ReceiverSummaryInformation().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel accredite_jlabel;
    private javax.swing.JToggleButton corriger_info;
    private javax.swing.JLabel date_debut_partage_jlabel;
    private javax.swing.JLabel date_fin_partage_jlabel;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JToggleButton lancer_procedure;
    private javax.swing.JLabel pseudo_jlabel;
    private javax.swing.JTextField raison_partage_jtextfiel;
    private javax.swing.JLabel relation_jlabel;
    // End of variables declaration//GEN-END:variables

    private static final long serialVersionUID = 42L;
    
    /*
     * Methode : close() allow you to close the current windows
     */
    private void close(){
        // Close the uart port before closing the windows
        port.closeUartPort();
        
        WindowEvent winClosing = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosing);
    }
    
    /**
     * Methode : arrangeSharerData() arrange the necessary data for sharing
     * @return [] string which contains all the data 
     */
    private String [] arrangeSharerData(){
        
        String [] data_sharer = null; 
        String [] arranged_data = null;
        boolean extract_sharer = false;
        
        /* Deserializable of the file containing the information of the owner
         * to add the indentifier number and password 
         */          
        OwnerInformation sharer = null;
        File file = new File("owner_information.ser");
        try (FileInputStream fileIn = new FileInputStream(file); ObjectInputStream in = new ObjectInputStream(fileIn)) {
            sharer = (OwnerInformation) in.readObject();
            //System.out.println("Extraction de OwnerInformation");
            data_sharer = sharer.getOwnerInformationForSharing();
            System.out.println(sharer);
            extract_sharer = true;
            
        }catch(IOException io){
            System.out.println("Exception de IO : " + io.getMessage());
            extract_sharer = false;
        }catch(ClassNotFoundException c){
           System.err.println("OwnerInformation class not found");
           extract_sharer = false;
        }
        
        if (extract_sharer){
            int formule = 0; 
            int number_element = data_sharer.length;
            int size_arranged = number_element+3;
            int checksum = 0;
            arranged_data = new String[size_arranged];
            
            // Send the first frame to inform the device all the data received after taht frame is to save
            arranged_data[0]  = DEBUT_ENVOIE_INFORMATION_UTILISATEUR_PARTAGEANT;

            // Put the owner information without the ID of its Sesame
            System.arraycopy(data_sharer, 0, arranged_data, 1, number_element);
            
            // Create the checksum
            for (int j=0; j<number_element; j++){
                formule = formule + (number_element-j)*data_sharer[j].length();
            }
            checksum = formule - number_element*128;
            arranged_data[number_element+1] = Integer.toString(checksum);

            // Send the last frame to inform the device that is the last data
            arranged_data[number_element+2] = FIN_ENVOIE_INFORMATION_UTILISATEUR_PARTAGEANT;
        }
        else{
            arranged_data = new String[1];
            arranged_data[0] = "NOK";
        }
        return arranged_data;
    }
    
    
    /**
     * Methode : shareAccesInstruction() allows you to send all the instruction about the share of the acces. 
     * @param args 
     */
    public void shareAccesInstruction(){
        boolean status_sharing_owner  = false;
        boolean status_sharing_guest  = false;
        
        boolean status_sharing_device = false;
        boolean status_linking_device = false;
        
        boolean status_share_config = false;
        
        boolean flag = true;
        int tempo = 0;
        int seconde = 1000;

        // Send "BONJOUR"        
        try {
            port.sendData(BONJOUR);
        }catch(InterruptedException e){
            flag = false;
        }
        
        // Check the answer of the device in the while loop
        while (flag){
            if (tempo>seconde){
                flag=false;
                System.out.println("Tempo d'attente de la réponse du périphérique a expiré");
                tempo = 0;
            }
            else{
                flag = (port.getLastReceivedData()!=null && !port.getLastReceivedData().equals(BONJOUR));
                if (!flag){
                    System.out.println("|BONJOUR| sur SerialPortForSharingAcces");
                }
                tempo = tempo + 100;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {}
            }
        }
        
        // Wait 10 secondes
        tempo = 0;
        while (tempo<5*seconde){
            tempo = tempo + seconde;
            try {
                Thread.sleep(seconde);
            } catch (InterruptedException ex) {}
        }
        // Reset the tempo && the flag
        tempo = 0; 
        flag = true;
        
        // Send ID of the Sesame guest
        String id_sesame_guest = "RPi_GUEST";
        try {
            port.sendData(id_sesame_guest);
        }catch(InterruptedException e){
            flag = false;
        }
        
        // Check if the ID of the Sesame is the same as the old ID saved
        while (flag){
            if (tempo>seconde){
                flag=false;
                System.out.println("Tempo d'attente de la réponse du périphérique a expiré");
                tempo = 0;
            }
            else{
                flag = (port.getLastReceivedData()!=null && !port.getLastReceivedData().equals(id_sesame_guest));
                if (!flag){
                    System.out.println(id_sesame_guest + " is the id of the Sesame guest");
                }
                tempo = tempo + 100;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {}
            }
        }
        tempo = 0;
        flag = true;
        
        // Send the frame DEMANDE_PARTAGE_ACCES
        try {
            port.sendData(DEMANDE_PARTAGE_ACCES);
        }catch(InterruptedException e){
            flag = false;
        }
        
        // Check the received data if the Sesame authorize the share or not
        while (flag){
            if (tempo>seconde){
                flag=false;
                System.out.println("Tempo d'attente de la réponse du périphérique a expiré");
                tempo = 0;
            }
            else{
                flag = (port.getLastReceivedData()!=null && !port.getLastReceivedData().equals(PARTAGE_ACCES_AUTORISEE));
                if (!flag){
                    System.out.println("PARTAGE_ACCES_AUTORISEE is the received");
                }
                tempo = tempo + 100;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {}
            }
        }
        
        // The expected answer is received by the SESAME 
        if (!flag){
            System.out.println("Partage d'accès autorisé par le Sesame destinataire");
            String [] data_to_send = arrangeSharerData();
            flag = true;
            tempo = 0;
            try {
                // Arrange the owner information and Send it
                port.sendData(data_to_send);
            } catch (InterruptedException ex){}
        }
        
        // Check if the Receiver of the share has saved the Sharer Information => status_sharing_owner
        while (flag){
            if (tempo>7000){
                flag=false;
                tempo = 0;
                System.out.println("Tempo d'attente de la réponse du périphérique a expiré");
            }
            else{
                flag = (port.getLastReceivedData()!=null && !port.getLastReceivedData().equals(INFORMATION_PARTAGEUR_ENREGISTRE_CORRECTEMENT));
                if (!flag){
                    System.out.println("|PARTAGEUR_ENREGISTRE_CORRECTEMENT| par le Sesame destinataire");
                    status_sharing_owner = true;
                }
                else{
                    tempo =  tempo + 100;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {}
                }
            }
        }
        
        // Ask the receiver of the share to send its owner information => send => DEMANDE_INFORMATION_IDENTIFICATION_ACCREDITEE
        try {
            port.sendData(DEMANDE_INFORMATION_IDENTIFICATION_ACCREDITEE);
        }catch(InterruptedException e){
            flag = false;
        }
        
        
        // Check if the Receiver of the share has saved the Sharer Information => status_sharing_owner
        /*while (flag){
            if (tempo>7000){
                flag=false;
                tempo = 0;
                System.out.println("Tempo d'attente de la réponse du périphérique a expiré");
            }
            else{
                flag = (port.getLastReceivedData()!=null && !port.getLastReceivedData().equals(INFORMATION_SESAME_DOORS_ENREGISTRE_CORRECTEMENT));
                if (!flag){
                    System.out.println("|INFORMATION_SESAME_DOORS_ENREGISTRE_CORRECTEMENT| par le Sesame destinataire");
                    status_sharing_device = true;
                }
                else{
                    tempo =  tempo + 100;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {}
                }
            }
        }*/
        /*// After 10 secondes, check if the accredited user is created
        boolean flag_accredited = false;
        try{
            // create new files
            File accredited_user = new File("accredited_information.ser");
         
            // tests if the file exists
            flag_accredited = accredited_user.exists();         
        }catch(Exception e){
            // if any error occurs
            e.printStackTrace();
        }
        
        // Wait 10 secondes
        tempo = 0;
        while (tempo<7*seconde){
            tempo = tempo + seconde;
            try {
                //System.out.println("Attente tempo : " + tempo + " secondes");
                Thread.sleep(seconde);
            } catch (InterruptedException ex){}
        }
        
        System.out.println("Status of creating Accredited User = " + flag_accredited);
        
        System.out.println("Envoie des données du périphérique");*/
        
        if (status_sharing_owner && status_sharing_device){
            System.out.println("Enregistrement de l'administrateur et de l'accrédité se sont effectués avec succes");
            try {
                port.sendData(DEMANDE_CONFIRMATION_DE_PARTAGE);                
            }catch(InterruptedException e){
                System.out.println("Impossible to the send the following message : DEMANDE_CONFIRMATION_DE_PARTAGE");
            }
        }
        else{
            System.out.println("Enregistrement de l'administrateur et de l'accrédité ne sont pas effectués correctement");   
        }
        
        System.out.println("status du flag = " + (status_sharing_owner && status_sharing_device));
        // Passer une autre etape du processus
    }
    
}