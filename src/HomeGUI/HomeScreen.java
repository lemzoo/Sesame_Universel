/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HomeGUI;

import AccesGUI.*;
import AccreditedClass.*;
import LinkingGUI.*;
import SesameApp.*;
import SharingClass.*;
import SharingGUI.*;
import synchroShare.*;


import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LamineBA
 */
public class HomeScreen extends javax.swing.JFrame {
    /**
     * Creates new form HomeScreen
     */
    private SerialPortSynchronizeInstruction port_synchronize = null;
    
    public HomeScreen() {
        initComponents();
        owner_jLabel.setText("Proprietaire : " + "Lamine BA");
        this.clock();
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
        owner_jLabel = new javax.swing.JLabel();
        date_jLabel = new javax.swing.JLabel();
        clock_jLabel = new javax.swing.JLabel();
        synchronize_btn = new javax.swing.JToggleButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jMenuBar = new javax.swing.JMenuBar();
        sesame_JMenu = new javax.swing.JMenu();
        aProposSesame_jMenuItem = new javax.swing.JMenuItem();
        afficherInfoAdministrateur_jMenuItem = new javax.swing.JMenuItem();
        everygates_jMenuItem = new javax.swing.JMenuItem();
        fonctions_jMenu = new javax.swing.JMenu();
        acceder_jMenuItem = new javax.swing.JMenuItem();
        partager_jMenuItem = new javax.swing.JMenuItem();
        supprimer_jMenuItem = new javax.swing.JMenuItem();
        rattacher_jMenuItem = new javax.swing.JMenuItem();
        systeme_jMenu = new javax.swing.JMenu();
        redemarrer_jMenuItem = new javax.swing.JMenuItem();
        mettreEnVeille_jMenuItem = new javax.swing.JMenuItem();
        arreter_jMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Home");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 2, 10)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sesame est une marque déposée de EveryGates SAS");

        owner_jLabel.setFont(new java.awt.Font("Lucida Grande", 3, 12)); // NOI18N
        owner_jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        owner_jLabel.setText("jLabel2");

        date_jLabel.setFont(new java.awt.Font("Lucida Grande", 3, 12)); // NOI18N
        date_jLabel.setText("jLabel2");
        date_jLabel.setToolTipText("");

        clock_jLabel.setFont(new java.awt.Font("Lucida Grande", 3, 12)); // NOI18N
        clock_jLabel.setText("jLabel2");
        clock_jLabel.setToolTipText("");

        synchronize_btn.setText("Synchronize");
        synchronize_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                synchronizeMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(clock_jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addComponent(date_jLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(70, 70, 70))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(owner_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(synchronize_btn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(date_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(clock_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
                .addComponent(synchronize_btn)
                .addGap(18, 18, 18)
                .addComponent(owner_jLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1))
        );

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel2.setText("Bienvenu dans le Sesame Administrateur");
        jLabel2.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(44, 44, 44))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
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
            .addGap(0, 8, Short.MAX_VALUE)
        );

        jMenuBar.setToolTipText("");
        jMenuBar.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jMenuBar.setName(""); // NOI18N

        sesame_JMenu.setText("Sesame");
        sesame_JMenu.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N

        aProposSesame_jMenuItem.setText("A Propos du Sesame");
        sesame_JMenu.add(aProposSesame_jMenuItem);

        afficherInfoAdministrateur_jMenuItem.setText("Afficher les infos Administrateur");
        afficherInfoAdministrateur_jMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                afficherInfoAdministrateurMouseReleased(evt);
            }
        });
        sesame_JMenu.add(afficherInfoAdministrateur_jMenuItem);

        everygates_jMenuItem.setText("EveryGates");
        sesame_JMenu.add(everygates_jMenuItem);

        jMenuBar.add(sesame_JMenu);

        fonctions_jMenu.setText("Fonctions");
        fonctions_jMenu.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N

        acceder_jMenuItem.setText("Acceder");
        acceder_jMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                accederMouseReleased(evt);
            }
        });
        fonctions_jMenu.add(acceder_jMenuItem);

        partager_jMenuItem.setText("Partager les accès");
        partager_jMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                partagerMouseReleased(evt);
            }
        });
        fonctions_jMenu.add(partager_jMenuItem);

        supprimer_jMenuItem.setText("Supprimer les accès");
        fonctions_jMenu.add(supprimer_jMenuItem);

        rattacher_jMenuItem.setText("Rattacher un Periphérique");
        rattacher_jMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rattacherMouseReleased(evt);
            }
        });
        fonctions_jMenu.add(rattacher_jMenuItem);

        jMenuBar.add(fonctions_jMenu);

        systeme_jMenu.setText("Systeme");
        systeme_jMenu.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N

        redemarrer_jMenuItem.setText("Redemarrer");
        redemarrer_jMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                redemarrerMouseReleased(evt);
            }
        });
        systeme_jMenu.add(redemarrer_jMenuItem);

        mettreEnVeille_jMenuItem.setText("Mettre en veille");
        mettreEnVeille_jMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                mettreEnVeilleMouseReleased(evt);
            }
        });
        systeme_jMenu.add(mettreEnVeille_jMenuItem);

        arreter_jMenuItem.setText("Arreter");
        arreter_jMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                arreterMouseReleased(evt);
            }
        });
        systeme_jMenu.add(arreter_jMenuItem);

        jMenuBar.add(systeme_jMenu);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(400, 568));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Methode : redemarrerMouseReleased()
     * @param evt 
     */
    private void redemarrerMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_redemarrer_jMenuItemMouseReleased
        close();
        try {
            Thread.sleep(2);
        } catch (InterruptedException ex) {
            Logger.getLogger(HomeScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        Authentification auth = new Authentification();
        auth.setVisible(true);
    }//GEN-LAST:event_redemarrer_jMenuItemMouseReleased
    /**
     * Methode : mettreEnVeilleMouseReleased()
     * @param evt 
     */
    private void mettreEnVeilleMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mettreEnVeille_jMenuItemMouseReleased
        //this.EXIT_ON_CLOSE;
    }//GEN-LAST:event_mettreEnVeille_jMenuItemMouseReleased

    private void arreterMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_arreter_jMenuItemMouseReleased
        close();
    }//GEN-LAST:event_arreter_jMenuItemMouseReleased
    
    /**
     * Methode afficherInfoAdministrateurMouseReleased 
     * this methode allows you to print all the information about the owner
     * @param evt 
     */
    private void afficherInfoAdministrateurMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_afficherInfoAdministrateur_jMenuItemMouseReleased
        AdministratorInformation admin = new AdministratorInformation();
        admin.setVisible(true);
        
    }//GEN-LAST:event_afficherInfoAdministrateur_jMenuItemMouseReleased

    /**
     * Methode partager_jMenuItemMouseReleased 
     * this methode allows you to share the acces of your gate with 
     * others Sesame User by your Sesame.
     * @param evt 
     */
    private void partagerMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_partagerMouseReleased
        // Partager les accès
        ManageSharedDevice share = new ManageSharedDevice();
        share.setVisible(true);
    }//GEN-LAST:event_partagerMouseReleased

    /**
     * Methode rattacher_jMenuItemMouseReleased 
     * this methode allows you to synchronize the Sesame with the device wich manages
     * the gate of the owner of the Sesame
     * @param evt 
     */
    private void rattacherMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rattacherMouseReleased
        // TODO add your handling code here:
        // Rattacher un periphérique  
        Peripherique periph = new Peripherique ();
        periph.setVisible(true);
    }//GEN-LAST:event_rattacherMouseReleased

    /**
     * Methode accederMouseReleased 
     * this methode allows you to acces to your gate by the Sesame
     * @param evt 
     */
    private void accederMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accederMouseReleased
        // TODO add your handling code here:
        // Acceder     
        ManageAcces manage_acces = new ManageAcces ();
        manage_acces.setVisible(true);
    }//GEN-LAST:event_accederMouseReleased

    private void synchronizeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_synchronizeMouseReleased
        // TODO add your handling code here:
        System.out.println("Lancement de la procedure de synchronisation");
        port_synchronize = new SerialPortSynchronizeInstruction(9600);
    }//GEN-LAST:event_synchronizeMouseReleased
   
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
            java.util.logging.Logger.getLogger(HomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aProposSesame_jMenuItem;
    private javax.swing.JMenuItem acceder_jMenuItem;
    private javax.swing.JMenuItem afficherInfoAdministrateur_jMenuItem;
    private javax.swing.JMenuItem arreter_jMenuItem;
    private javax.swing.JLabel clock_jLabel;
    private javax.swing.JLabel date_jLabel;
    private javax.swing.JMenuItem everygates_jMenuItem;
    private javax.swing.JMenu fonctions_jMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JMenuItem mettreEnVeille_jMenuItem;
    private javax.swing.JLabel owner_jLabel;
    private javax.swing.JMenuItem partager_jMenuItem;
    private javax.swing.JMenuItem rattacher_jMenuItem;
    private javax.swing.JMenuItem redemarrer_jMenuItem;
    private javax.swing.JMenu sesame_JMenu;
    private javax.swing.JMenuItem supprimer_jMenuItem;
    private javax.swing.JToggleButton synchronize_btn;
    private javax.swing.JMenu systeme_jMenu;
    // End of variables declaration//GEN-END:variables

    /**
     * Methode : close() allow you to close the current windows
     */
    private void close() {
        port_synchronize.closeUartPort();
        
        WindowEvent winClosing = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosing);
    }
    
    /**
     * Methode : clock allow you to get the current time and date
     * This methode show you the current date and times
     */
    public void clock (){
        Thread clock = new Thread(){
            @Override
            public void run(){
                try {
                    while (true){
                        Calendar cal = new GregorianCalendar();        
                        int day = cal.get(Calendar.DAY_OF_MONTH);
                        int month = cal.get(Calendar.MONTH)+1;
                        int year = cal.get(Calendar.YEAR);

                        int hour = cal.get(Calendar.HOUR);
                        int minute = cal.get(Calendar.MINUTE);
                        int seconde = cal.get(Calendar.SECOND);
                                               
                        date_jLabel.setText("Date du jour : " + String.format("%02d", day) + "/" + String.format("%02d", month) + "/" + String.format("%04d", year));
                        clock_jLabel.setText("Heure Locale : " + String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", seconde));
                        sleep(1000);    
                    }
                } catch (InterruptedException ex) {}
            }
        };
        clock.start();
    }
}