/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SesameApp;

import AccesGUI.*;
import HomeGUI.*;
import LinkingGUI.*;
import SharingGUI.*;

/**
 *
 * @author LamineBA
 */
public class DeviceLinkingData implements java.io.Serializable{
    private String type_de_bien; 
    private String type_appartenance; 
    private String emplacement; 
    private String type_porte; 
    private String commentaire_porte; 
    private int numero_voie;
    private String nom_voie;
    private int code_postale;
    private String ville;
    private String pays;
    private String device_identifiant="";
    
    public DeviceLinkingData (){
        type_de_bien = ""; 
        type_appartenance = ""; 
        emplacement = ""; 
        type_porte = ""; 
        commentaire_porte = ""; 
        numero_voie = 0;
        nom_voie = "";
        code_postale = 0;
        ville = "";
        pays = "";
        device_identifiant = "";
    }
    
    public DeviceLinkingData(String [] data){
        if (data != null && data.length >= 11){
            
            type_de_bien = data[0]; 
            type_appartenance = data[1]; 
            emplacement = data[2]; 
            type_porte = data[3]; 
            commentaire_porte = data[4];       
            nom_voie = data[6];
            ville = data[8];
            pays = data[9];  
            device_identifiant = data[10];
            
            try{
                numero_voie = Integer.parseInt(data[5]);
                code_postale = Integer.parseInt(data[7]);                
            }catch(NumberFormatException ex){
                numero_voie = 0;
                code_postale = 0;
            }
        }
    }
    
    @Override
    public String toString(){
        String chaine =   "<<============================================>>" + "\n"
                        + "... Device Information for the Identification ..." + "\n"
                        + "<<============================================>>" + "\n"
                        + "... TYPE DE BIEN : "+type_de_bien + " ..." + "\n" 
                        + "... TYPE D'APPARTENANCE : " + type_appartenance + " ..." + "\n"
                        + "... EMPLACEMENT : " + emplacement + " ..." + "\n"
                        + "... TYPE DE PORTE : " + type_porte + " ..." + "\n"
                        + "... COMMENTAIRE : " + commentaire_porte + " ..." + "\n"
                        + "... ADRESSE : " + numero_voie + " " + nom_voie + "..." + "\n"
                        + ".............. " + code_postale + " " + ville + " ..." + "\n" 
                        + ".............. " + pays + " ..." + "\n"
                        + ".............................................." + "\n"
                        + "... IDENTIFIANT DU PERIPHERIQUE : " + device_identifiant + " ..." + "\n"
                        + "<<============================================>>";
        return chaine; 
    }
    
    /**
     * Methode makeDeviceIdentifiant () allows you to make the identifiant of the device
     * @return identifiant made through the parameters of the device 
     */
    public static String makeDeviceIdentifiant(){
        String identifiant = "DEV_01";
        /* Les parametres : 
            - type de bien
            - type d'appartenance
            - emplacement
            - type de porte
            - commentaire
            - Adresse :  
                - Numero + nom du rue 
                - code postale + ville + pays
        */
        return identifiant;
    }
    
    /**
     * Methode : getDeviceIdentifiant() allow you to get the id of the device
     * @return device_identifiant is the variable which contains the identifiant of the device
     */
    public String getDeviceIdentifiant(){
        return this.device_identifiant;
    }
    
    /**
     * Methode : setDeviceIdentifiant() allow you to set the id of the device
     * @param identifiant : is the identifiant that will write on the device
     */
    public void setDeviceIdentifiant(String identifiant){
        device_identifiant =  identifiant;
    }
    
    /**
     * Methode : getTypeDeBien() allow you to get "TYPE DE BIEN"
     * @return type_de_bien
     */
    public String getTypeDeBien(){
        return this.type_de_bien;
    }
    
    /**
     * Methode : getTypeAppartenance() allow you to get "TYPE D'APPARTENANCE"
     * @return type_appartenance
     */
    public String getTypeAppartenance(){
        return this.type_appartenance;
    }
    
    /**
     * Methode : getEmplacement() allow you to get "EMPLACEMENT"
     * @return emplacement
     */
    public String getEmplacement(){
        return this.emplacement;
    }
    
    /**
     * Methode : getTypeDePorte() allow you to get "TYPE DE PORTE"
     * @return type_porte
     */
    public String getTypeDePorte(){
        return type_porte;
    }
    
    /**
     * Methode : getCommentaire() allow you to get "COMMENTAIRE"
     * @return type_porte
     */
    public String getCommentaire(){
        return commentaire_porte;
    }
    
    /**
     * Methode : getNumeroVoie() allow you to get "NUMERO DE LA VOIE"
     * @return numero_voie
     */
    public String getNumeroRue(){
        return Integer.toString(this.numero_voie);
    }
    
    /**
     * Methode : getNomVoie() allow you to get "NOM DE LA VOIE"
     * @return nom_voie
     */
    public String getNomVoie(){
        return this.nom_voie;
    }
    
    /**
     * Methode : getCodePostale() allow you to get "CODE POSTALE"
     * @return code_postale
     */
    public String getCodePostale(){
        return Integer.toString(this.code_postale);
    }
    
    /**
     * Methode : getVille() allow you to get "VILLE"
     * @return ville
     */
    public String getVille(){
        return this.ville;
    }
    
    /**
     * Methode : getPays() allow you to get "pays"
     * @return pays
     */
    public String getPays(){
        return this.pays;
    }
    
    /**
     * Methode getDeviceLinkingData() allow you to get arranged data which is ready for sending
     * @return [] String containing all attribut of the class
     */
    public String [] getDeviceLinkingData(){
        String [] data = new String[11];
        
        data[0] = type_de_bien;
        data[1] = type_appartenance;
        data[2] = emplacement;
        data[3] = type_porte;
        data[4] = commentaire_porte;
        data[5] = Integer.toString(numero_voie);
        data[6] = nom_voie;
        data[7] = Integer.toString(code_postale);
        data[8] = ville;
        data[9] = pays;
        data[10] = device_identifiant;
        
        return data;
    }
    
    /**
     * Methode isDeviceDataCorrect() verify the data about the device. 
     * @return true if the data is valid or else if not
     */
    public boolean isDeviceDataCorrect(){
        boolean status = (  !type_de_bien.isEmpty() &&
                            !type_appartenance.isEmpty() &&
                            !emplacement.isEmpty() &&
                            !type_porte.isEmpty() &&
                            !commentaire_porte.isEmpty() &&
                            (numero_voie > 0) &&
                            !nom_voie.isEmpty() &&
                            (code_postale > 0) &&
                            !ville.isEmpty() && 
                            !pays.isEmpty()
                          );
        if (status)
            System.out.println("Device Identification data is correct");
        else
            System.out.println("Device Identification data is not correct");
        
        return status;
    }
    
    private static final long serialVersionUID = 42L; 

    public static void main(String[] args) {
        String [] data = {"Maison", "Proprietaire", "Rez de Chaussee", "Porte d'entree principale", "Ma residence principale",
                          "13", "Avenue Maximilien Robespierre", "94400", "Vitry sur Seine", "France"};
        DeviceLinkingData dev = new DeviceLinkingData(data);
        System.out.println(dev);
    }
    
}

