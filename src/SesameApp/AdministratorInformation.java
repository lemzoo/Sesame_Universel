/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SesameApp;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author LamineBA
 */
public class AdministratorInformation extends javax.swing.JFrame {

    /**
     * Creates new form AdministratorInformation
     */
    public AdministratorInformation() {
        initComponents();
        
        /* Deserializable of the file containing the information of the owner
         * to add the indentifier number and password 
         */          
        OwnerInformation user = null;
        File file = new File("owner_information.ser");
        try (FileInputStream fileIn = new FileInputStream(file); ObjectInputStream in = new ObjectInputStream(fileIn)) {
            user = (OwnerInformation) in.readObject();
            
            nom_jLabel.setText(user.getOwnerFirstName());
            prenom_jLabel.setText(user.getOwnerLastName());
            date_naissance_jLabel.setText(String.valueOf(user.getOwnerBirthdayDate().getDate()) + "/" +
                                          String.valueOf(user.getOwnerBirthdayDate().getMonth()) + "/" +
                                          String.valueOf(user.getOwnerBirthdayDate().getYear()));
            numero_telephone_jLabel.setText(user.getOwnerPhoneNumber());
            email_contact_jLabel.setText(user.getOwnerEmailAddress());
            numero_nom_voie_jLabel.setText(String.valueOf(user.getOwnerStreetNumber()) + " " + user.getOwnerStreetName());
            code_postale_ville_jLabel.setText(String.valueOf(user.getOwnerCodePostale()) + " " + user.getOwnerCity());
            pays_jLabel.setText(user.getOwnerCountry());
        }catch(IOException i)
        {
        }catch(ClassNotFoundException c)
        {
           System.out.println("OwnerInformation class not found");
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nom_jLabel = new javax.swing.JLabel();
        prenom_jLabel = new javax.swing.JLabel();
        date_naissance_jLabel = new javax.swing.JLabel();
        numero_telephone_jLabel = new javax.swing.JLabel();
        email_contact_jLabel = new javax.swing.JLabel();
        numero_nom_voie_jLabel = new javax.swing.JLabel();
        code_postale_ville_jLabel = new javax.swing.JLabel();
        pays_jLabel = new javax.swing.JLabel();
        close_jbutton = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrator Information");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel1.setText("Nom : ");
        jLabel1.setSize(new java.awt.Dimension(45, 20));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel2.setText("Date de naissance : ");
        jLabel2.setPreferredSize(new java.awt.Dimension(103, 20));

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel3.setText("Prenom : ");
        jLabel3.setPreferredSize(new java.awt.Dimension(63, 20));

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel4.setText("Email de contact : ");
        jLabel4.setPreferredSize(new java.awt.Dimension(103, 20));

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel5.setText("Numero de téléphone : ");
        jLabel5.setPreferredSize(new java.awt.Dimension(103, 20));

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel6.setText("Adresse de résidence : ");
        jLabel6.setPreferredSize(new java.awt.Dimension(103, 20));

        nom_jLabel.setFont(new java.awt.Font("Lucida Sans", 1, 13)); // NOI18N
        nom_jLabel.setText("jLabel7");
        nom_jLabel.setPreferredSize(new java.awt.Dimension(50, 20));
        nom_jLabel.setSize(new java.awt.Dimension(45, 20));

        prenom_jLabel.setFont(new java.awt.Font("Lucida Sans", 1, 13)); // NOI18N
        prenom_jLabel.setText("jLabel7");
        prenom_jLabel.setPreferredSize(new java.awt.Dimension(50, 20));
        prenom_jLabel.setSize(new java.awt.Dimension(45, 20));

        date_naissance_jLabel.setFont(new java.awt.Font("Lucida Sans", 1, 13)); // NOI18N
        date_naissance_jLabel.setText("jLabel7");
        date_naissance_jLabel.setPreferredSize(new java.awt.Dimension(50, 20));
        date_naissance_jLabel.setSize(new java.awt.Dimension(45, 20));

        numero_telephone_jLabel.setFont(new java.awt.Font("Lucida Sans", 1, 13)); // NOI18N
        numero_telephone_jLabel.setText("jLabel7");
        numero_telephone_jLabel.setPreferredSize(new java.awt.Dimension(50, 20));
        numero_telephone_jLabel.setSize(new java.awt.Dimension(45, 20));

        email_contact_jLabel.setFont(new java.awt.Font("Lucida Sans", 1, 13)); // NOI18N
        email_contact_jLabel.setText("jLabel7");
        email_contact_jLabel.setPreferredSize(new java.awt.Dimension(50, 20));
        email_contact_jLabel.setSize(new java.awt.Dimension(45, 20));

        numero_nom_voie_jLabel.setFont(new java.awt.Font("Lucida Sans", 1, 13)); // NOI18N
        numero_nom_voie_jLabel.setText("jLabel7");
        numero_nom_voie_jLabel.setPreferredSize(new java.awt.Dimension(50, 20));
        numero_nom_voie_jLabel.setSize(new java.awt.Dimension(45, 20));

        code_postale_ville_jLabel.setFont(new java.awt.Font("Lucida Sans", 1, 13)); // NOI18N
        code_postale_ville_jLabel.setText("jLabel7");
        code_postale_ville_jLabel.setPreferredSize(new java.awt.Dimension(50, 20));
        code_postale_ville_jLabel.setSize(new java.awt.Dimension(45, 20));

        pays_jLabel.setFont(new java.awt.Font("Lucida Sans", 1, 13)); // NOI18N
        pays_jLabel.setText("jLabel7");
        pays_jLabel.setPreferredSize(new java.awt.Dimension(50, 20));
        pays_jLabel.setSize(new java.awt.Dimension(45, 20));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numero_nom_voie_jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(code_postale_ville_jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pays_jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(email_contact_jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(numero_telephone_jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(date_naissance_jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nom_jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(prenom_jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nom_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prenom_jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date_naissance_jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numero_telephone_jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(email_contact_jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numero_nom_voie_jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(code_postale_ville_jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pays_jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        close_jbutton.setText("Close");
        close_jbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close_jbuttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(close_jbutton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(close_jbutton)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(428, 391));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void close_jbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close_jbuttonActionPerformed
        close();
    }//GEN-LAST:event_close_jbuttonActionPerformed

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
            java.util.logging.Logger.getLogger(AdministratorInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministratorInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministratorInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministratorInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministratorInformation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton close_jbutton;
    private javax.swing.JLabel code_postale_ville_jLabel;
    private javax.swing.JLabel date_naissance_jLabel;
    private javax.swing.JLabel email_contact_jLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel nom_jLabel;
    private javax.swing.JLabel numero_nom_voie_jLabel;
    private javax.swing.JLabel numero_telephone_jLabel;
    private javax.swing.JLabel pays_jLabel;
    private javax.swing.JLabel prenom_jLabel;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Methode : close() allow you to close the current windows
     */
    private void close() {
        WindowEvent winClosing = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosing);
    }
}