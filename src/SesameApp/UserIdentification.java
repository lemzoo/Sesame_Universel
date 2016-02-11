/**
 * This class contains all information about the share of the SESAME. 
 */
package SesameApp;

import AccesGUI.*;
import HomeGUI.*;
import LinkingGUI.*;
import SharingGUI.*;

import java.util.Date;
import javax.swing.JPasswordField;

/**
 *
 * @author LamineBA
 */
public class UserIdentification implements java.io.Serializable {
    private String nom;
    private String prenom;
    private Date date_naissance;
    private String numero_mobile;
    private String adresse_mail;
    private int numero_voie;
    private String nom_voie;
    private int code_postale;
    private String ville;
    private String pays;
    
    public UserIdentification (){
        nom = "BA";
        prenom = "Lamine";
        date_naissance = new Date(2015, 8, 9);
        numero_mobile = "06 51 58 75 08";
        adresse_mail = "lamine.ba@everygates.com";
        numero_voie = 21;
        nom_voie = "Rue Robert Degert";
        code_postale = 94400;
        ville = "Vitry sur Seine";
        pays = "France";
    }
    
    public UserIdentification(String [] data){
        if (data != null && data.length>=12){
            String lv_nom;
            String lv_prenom;
            Date   lv_date_naissance;
            String lv_numero_mobile;
            String lv_adresse_mail;
            int    lv_numero_voie;
            String lv_nom_voie;
            int    lv_code_postale = 0;
            String lv_ville;
            String lv_pays;

            int day   = 0;
            int month = 0;
            int year  = 0;
            
            // Assignement 
            lv_nom = data[0];
            lv_prenom = data[1];
            
            // Convert Data to the correct format
            try{
                day   = Integer.parseInt(data[2]);
                month = Integer.parseInt(data[3]);
                year  = Integer.parseInt(data[4]);
                lv_numero_voie  = Integer.parseInt(data[7]);
                code_postale   = Integer.parseInt(data[9]);
                System.out.println("Conversion done succesffully");
           }
            catch(NumberFormatException ex){
                System.out.println("Error de conversion de String to int " + ex.getMessage());
                day = 0;
                month = 0;
                year = 0;
                lv_numero_voie = 0;
                code_postale = 0;
            }
            lv_date_naissance = new Date(year, month, day);
            
            lv_numero_mobile = data[5];
            lv_adresse_mail = data[6];
            lv_nom_voie = data[8];
            lv_ville = data[10];
            lv_pays = data[11];

            // Assign the real attribut
            nom = lv_nom;
            prenom = lv_prenom;
            date_naissance = lv_date_naissance;
            numero_mobile = lv_numero_mobile;
            adresse_mail = lv_adresse_mail;
            numero_voie = lv_numero_voie;
            nom_voie = lv_nom_voie;
            code_postale = lv_code_postale;
            ville = lv_ville;
            pays = lv_pays;
            System.out.println("Object UserIdentification is created with correct information");
        }
        else{
            nom = "";
            prenom = "";
            date_naissance = new Date(1900, 0, 1);
            numero_mobile = "";
            adresse_mail = "";
            numero_voie = 0;
            nom_voie = "";
            code_postale = 0;
            ville = "";
            pays = "";
            System.out.println("UserIdentification is created with default value");
        }
    }
    
    @Override
    public String toString(){
        String chaine = "... Nom : " + nom + " ...\n" + 
                        "... Prenom : " + prenom + " ...\n" +
                        "... Date de naissance : " + date_naissance.getDay() + "-" + date_naissance.getMonth() + "-" + date_naissance.getYear() + "...\n" + 
                        "... Numero de telephone : " + numero_mobile + " ...\n" +
                        "... Adresse : " + numero_voie + ", " + nom_voie + " ...\n" +
                        "...           " + ville + ", " + pays + " ...\n"; 
        
        return "... Les informations à propos de l'utilisateur ...\n" + chaine;
    }
    
    /**
     * Methode getUserFirstName()
     * This methode returns the first name of the user who is the 
     * owner of the SESAME. 
     * Returned type : String
     * @return nom
     */
    public String getUserFirstName(){
        return this.nom;
    }
    /**
     * Methode setUserFirstName(String first_name)
     * @param data
     */
    public void setUserFirstName(String data){
        this.nom = data;
    }
    
    /**
     * Methode getUserLastName()
     * This methode returns the last name of the user who is the 
     * owner of the SESAME. 
     * Returned type : String
     * @return prenom
     */
    public String getUserLastName(){
        return this.prenom;
    }
    /**
     * Methode setUserLastName(String last_name)
     * @param last_name 
     */
    public void setUserLastName(String last_name){
        this.prenom = last_name;
    }
    
    /**
     * Methode getUserBirthdayDate()
     * This methode returns the birthday date of the user who is the 
     * owner of the SESAME. 
     * Returned type : Date
     * @return date_naissance
     */
    public Date getUserBirthdayDate(){
        return this.date_naissance;
    }
    /**
     * Methode getUserBirthdayDate(Data date)
     * @param date 
     */
    public void setUserBirthdayDate(Date date){
        this.date_naissance = date;
    }
    
    /**
     * Methode getUserPhoneNumber()
     * This methode returns the phone number of the user who is the 
     * owner of the SESAME. 
     * Returned type : String
     * @return numero_mobile
     */
    public String getUserPhoneNumber(){
        return this.numero_mobile;
    }
    /**
     * Methode setUserPhoneNumber(String phone_number)
     * @param phone_number 
     */
    public void setUserPhoneNumber(String phone_number){
        this.numero_mobile = phone_number;
    }
    
    /**
     * Methode getUserEmailAddress()
     * This methode returns the phone number of the user who is the 
     * owner of the SESAME. 
     * Returned type : String
     * @return adresse_mail
     */
    public String getUserEmailAddress(){
        return this.adresse_mail;
    }
    /**
     * Methode setUserEmailAddress(String email_address)
     * @param email_address 
     */
    public void setUserEmailAddress(String email_address){
        this.adresse_mail = email_address;
    }
    
    /**
     * Methode getUserStreetNumber()
     * This methode returns the street number of the home address of the user who is the 
     * owner of the SESAME. 
     * Returned type : int
     * @return numero_voie
     */
    public int getUserStreetNumber(){
        return this.numero_voie;
    }
    /**
     * Methode setUserStreetNumber(int street_number)
     * @param street_number 
     */
    public void setUserStreetNumber(int street_number){
        this.numero_voie = street_number;
    }
    
    /**
     * Methode getUserStreetName()
     * This methode returns the street name of the home address of the user who is the 
     * owner of the SESAME. 
     * Returned type : String
     * @return nom_voie
     */
    public String getUserStreetName(){
        return this.nom_voie;
    }
    /**
     * Methode setUserStreetName(String street_name)
     * @param street_name 
     */
    public void setUserStreetName(String street_name){
        this.nom_voie = street_name;
    }
    
    /**
     * Methode getUserCodePostale()
     * This methode returns the Code Postale of the home address of the user who is the 
     * owner of the SESAME. 
     * Returned type : int
     * @return code_postale
     */
    public int getUserCodePostale(){
        return this.code_postale;
    }
    /**
     * Methode setUserCodePostale(int postale_code)
     * @param postale_code 
     */
    public void setUserCodePostale(int postale_code){
        this.code_postale = postale_code;
    }
    /**
     * Methode getUserCity()
     * This methode returns the city of home address of the user who is the 
     * owner of the SESAME. 
     * Returned type : String
     * @return ville
     */
    public String getUserCity(){
        return this.ville;
    }
    /**
     * Methode setUserCity(String city)
     * @param city 
     */
    public void setUserCity(String city){
        this.ville = city;
    }
    
    /**
     * Methode getUserCountry()
     * This methode returns the country of the home address of the user who is the 
     * owner of the SESAME. 
     * Returned type : String
     * @return pays
     */
    public String getUserCountry(){
        return this.pays;
    }
    /**
     * Methode setUserCountry(String country)
     * @param country 
     */
    public void setUserCountry(String country){
        this.pays = country;
    }
    
    /**
     * Methode getUserInformationForSharing() allow you to get the data about the owner of the Sesame
     * @return String[] which contains the data
     */
    public String [] getUserInformation(){
        String [] data = new String[12];
        
        // Arrange the data
        data[0] = nom;
        data[1] = prenom;
        data[2] = Integer.toString(date_naissance.getDate());
        data[3] = Integer.toString(date_naissance.getMonth());
        data[4] = Integer.toString(date_naissance.getYear());
        data[5] = numero_mobile;
        data[6] = adresse_mail;
        data[7] = Integer.toString(numero_voie);
        data[8] = nom_voie;
        data[9] = Integer.toString(code_postale);
        data[10] = ville;
        data[11] = pays;
        // End of the arrange data
        
        return data;
    }
    private static final long serialVersionUID = 42L; 
}
