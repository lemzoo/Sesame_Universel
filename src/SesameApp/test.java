/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SesameApp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import static java.lang.System.out;
import javax.swing.JOptionPane;
import static sun.misc.MessageUtils.out;

/**
 *
 * @author LamineBA
 */
public class test extends javax.swing.JFrame {

    /**
     * Creates new form test
     */
    private PeripheriqueInformation periph = null;
    public test() {
        initComponents();
        periph = new PeripheriqueInformation();
        //test1 t = new test1();
        //JOptionPane.showMessageDialog(null, "Les champs ci-dessous sont vides\n" + "notification" + "Cliquez sur OK et saisissez un mot de passe puis confirmation", "Mot de Passe Vide", JOptionPane.ERROR_MESSAGE);
        //JOptionPane.INPUT_VALUE_PROPERTY();
        //JOptionPane.showInputDialog(null, this, "title", HEIGHT, "icon", "selectionValues", DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        popupMenu1 = new java.awt.PopupMenu();
        popupMenu2 = new java.awt.PopupMenu();
        jpanel_titre = new javax.swing.JPanel();
        titre_fenetre = new javax.swing.JLabel();
        separator_jpanel = new javax.swing.JPanel();
        jpanel_bouton = new javax.swing.JPanel();
        diagnostiquer = new javax.swing.JButton();
        ouvrir = new javax.swing.JButton();
        fermer = new javax.swing.JButton();
        jpanel_status = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        popupMenu1.setLabel("popupMenu1");

        popupMenu2.setLabel("popupMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titre_fenetre.setFont(new java.awt.Font("Monaco", 1, 18)); // NOI18N
        titre_fenetre.setText("Gestion des accès");

        javax.swing.GroupLayout jpanel_titreLayout = new javax.swing.GroupLayout(jpanel_titre);
        jpanel_titre.setLayout(jpanel_titreLayout);
        jpanel_titreLayout.setHorizontalGroup(
            jpanel_titreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel_titreLayout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(titre_fenetre)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpanel_titreLayout.setVerticalGroup(
            jpanel_titreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel_titreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titre_fenetre)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        diagnostiquer.setText("DIAGNOSTIQUER");

        ouvrir.setText("OUVRIR");

        fermer.setText("FERMER");

        javax.swing.GroupLayout jpanel_boutonLayout = new javax.swing.GroupLayout(jpanel_bouton);
        jpanel_bouton.setLayout(jpanel_boutonLayout);
        jpanel_boutonLayout.setHorizontalGroup(
            jpanel_boutonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel_boutonLayout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addGroup(jpanel_boutonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(diagnostiquer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ouvrir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fermer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpanel_boutonLayout.setVerticalGroup(
            jpanel_boutonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel_boutonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(diagnostiquer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ouvrir)
                .addGap(18, 18, 18)
                .addComponent(fermer)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel1.setText("Status sur les accès");

        javax.swing.GroupLayout jpanel_statusLayout = new javax.swing.GroupLayout(jpanel_status);
        jpanel_status.setLayout(jpanel_statusLayout);
        jpanel_statusLayout.setHorizontalGroup(
            jpanel_statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel_statusLayout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addComponent(jLabel1)
                .addContainerGap(210, Short.MAX_VALUE))
        );
        jpanel_statusLayout.setVerticalGroup(
            jpanel_statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanel_statusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpanel_titre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(separator_jpanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpanel_bouton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpanel_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jpanel_titre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separator_jpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jpanel_bouton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpanel_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new test().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton diagnostiquer;
    private javax.swing.JButton fermer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPanel jpanel_bouton;
    private javax.swing.JPanel jpanel_status;
    private javax.swing.JPanel jpanel_titre;
    private javax.swing.JButton ouvrir;
    private java.awt.PopupMenu popupMenu1;
    private java.awt.PopupMenu popupMenu2;
    private javax.swing.JPanel separator_jpanel;
    private javax.swing.JLabel titre_fenetre;
    // End of variables declaration//GEN-END:variables
}
