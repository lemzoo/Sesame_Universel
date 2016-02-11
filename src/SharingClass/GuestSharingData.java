/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * This class contains all information about the owner of the SESAME. 
 */
package SharingClass;

import SesameApp.*;
import AccesGUI.*;
import HomeGUI.*;
import LinkingGUI.*;
import SharingGUI.*;

import java.util.Date;

/**
 *
 * @author LamineBA
 */
public class GuestSharingData implements java.io.Serializable {
    private String id_sesame_guest;
    private String pseudo;
    private String raison;
    private String relation;
    private Date date_debut;
    private Date date_fin;
   
    
    public GuestSharingData (String [] guest_data){
        if (guest_data != null && guest_data.length>=10){
            
            id_sesame_guest = guest_data[0];
            pseudo = guest_data[1];
            raison = guest_data[2];
            relation = guest_data[3];
            
            int day_start=0;
            int month_start = 0;
            int year_start=0;

            int day_end=0;
            int month_end=0;
            int year_end=0;

            try{
                day_start   = Integer.parseInt(guest_data[4]);
                month_start = Integer.parseInt(guest_data[5]);
                year_start  = Integer.parseInt(guest_data[6]);
                
                day_end   = Integer.parseInt(guest_data[7]);
                month_end = Integer.parseInt(guest_data[8]);
                year_end  = Integer.parseInt(guest_data[9]);
                
            }catch(Exception ex){
                System.out.println("La date de début saisie est incorrect : " + ex.getMessage());
            }
            date_debut = new Date(year_start, month_start, day_start);
            date_fin   = new Date(year_end, month_end, day_end);
        }
        else{
            System.out.println("Guest user is not saved");
            id_sesame_guest = "";
            pseudo = "";
            raison = "";
            relation = "";
            date_debut = new Date(2016, 00,01);
            date_fin = new Date(2016, 00,01);
        }
    }
    
    @Override
    public String toString(){
        String chaine = "... Id du Sesame : " + id_sesame_guest + " ...\n" + 
                        "... Pseudo attribué : " + pseudo + " ...\n" +
                        "... Raison du partage : " + raison + " ...\n" +
                        "... Relation avec le destinataire du partage : " + relation + " ...\n" +
                        "... Date de début du partage : " + date_debut.getDay() + "-" + date_debut.getMonth() + "-" + date_debut.getYear() + "...\n" + 
                        "... Date de fin du partage   : " + date_fin.getDay() + "-" + date_fin.getMonth() + "-" + date_fin.getYear() + "...\n";
        return "... Les informations du partage ...\n" + chaine;
    }
    
    /**
     * Methode getGuestSharingData() allow you to get the data about the sharing
     * @return String[] which contains the data
     */
    public String [] getGuestSharingData(){
        String [] data = new String[10];

        // Arrange the data
        data[0] = id_sesame_guest;
        data[1] = pseudo;
        data[2] = raison;
        data[3] = relation;
        
        data[4] = Integer.toString(date_debut.getDate());
        data[5] = Integer.toString(date_debut.getMonth());
        data[6] = Integer.toString(date_debut.getYear());
        
        data[7] = Integer.toString(date_fin.getDate());
        data[8] = Integer.toString(date_fin.getMonth());
        data[9] = Integer.toString(date_fin.getYear());
        // End of the arrange data
        
        return data;
    }
    
    private static final long serialVersionUID = 42L; 
}
