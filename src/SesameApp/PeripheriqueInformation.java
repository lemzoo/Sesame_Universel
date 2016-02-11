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
public class PeripheriqueInformation implements java.io.Serializable{

    private String type_bien; // Maison, Appartement, Entreprise, Magasin, Entrepot, Parking
    private String type_appartenance; // Proprietaire, Locataire, Administrateur, Superviseur.
    private String emplacement; // Sous-sol, Rez de chaussée, 1er etage, 2em etage, 3em etage.
    private String type_porte; // Entree principale, Porte garage, Entree zone confidentiele
    private String commentaire_porte; // Porte de ma résidence principale
    private int numero_voie;
    private String nom_voie;
    private int code_postale;
    private String ville;
    private String pays;

    public PeripheriqueInformation(){
        type_bien = "";
        type_appartenance = "";
        emplacement = "";
        type_porte = "";
        commentaire_porte = "";
        numero_voie = 0;
        nom_voie = "";
        code_postale = 0;
        ville = "";
        pays = "";
        System.out.println("Creation de la classe : PeripheriqueInformation");
    }
    
    /**
     * Methode setTypeBien (String)
     * @param : type_bien
     */
    public void setTypeBien(String type_bien){
        this.type_bien = type_bien;
    }
    
    /**
     * Methode setTypeAppartenance (String)
     * @param : type_appartenance
     */
    public void setTypeAppartenance(String type_appartenance){
        this.type_appartenance = type_appartenance;
    }
    
    /**
     * Methode setEmplacement (String)
     * @param : emplacement
     */
    public void setEmplacement(String emplacement){
        this.emplacement = emplacement;
    }
    
    /**
     * Methode setTypePorte (String)
     * @param : type_porte
     */
    public void setTypePorte(String type_porte){
        this.type_porte = type_porte;
    }
    
    /**
     * Methode setCommentaire (String)
     * @param : commentaire
     */
    public void setCommentaire(String commentaire){
        this.commentaire_porte = commentaire;
    }
    
    /**
     * Methode setNumeroRue (int)
     * @param : numero_rue
     */
    public void setNumeroRue(int numero_rue){
        this.numero_voie = numero_rue;
    }
    
    /**
     * Methode setNomRue (String)
     * @param : nom_rue
     */
    public void setNomRue(String nom_rue){
        this.nom_voie = nom_rue;
    }
    
    /**
     * Methode setCodePostale (int)
     * @param : code_postale
     */
    public void setCodePostale(int code_postale){
        this.code_postale = code_postale;
    }
    
    /**
     * Methode setVille (String)
     * @param : ville
     */
    public void setVille(String ville){
        this.ville = ville;
    }
    
    /**
     * Methode setPays (String)
     * @param : pays
     */
    public void setPays(String pays){
        this.pays = pays;
    }
    
    /**
     * Methode getTypeBien ()
     * @return : type_bien
     */
    public String getTypeBien(){
        return type_bien;
    }
    
    /**
     * Methode getTypeAppartenance ()
     * @return : type_appartenance
     */
    public String getTypeAppartenance(){
        return type_appartenance;
    }
    
    /**
     * Methode getEmplacement ()
     * @return : emplacement
     */
    public String getEmplacement(){
        return emplacement;
    }
    
    /**
     * Methode getTypePorte ()
     * @return : type_porte
     */
    public String getTypePorte(){
        return type_porte;
    }
    
    /**
     * Methode getCommentaire ()
     * @return : commentaire
     */
    public String getCommentaire(){
        return commentaire_porte;
    }
    
    /**
     * Methode getNumeroRue ()
     * @return : numero_rue
     */
    public int getNumeroRue(){
        return numero_voie;
    }
    
    /**
     * Methode getNomRue ()
     * @return : nom_rue
     */
    public String getNomRue(){
        return nom_voie;
    }
    
    /**
     * Methode getCodePostale ()
     * @return : code_postale
     */
    public int getCodePostale(){
        return code_postale;
    }
    
    /**
     * Methode getVille ()
     * @return : ville
     */
    public String getVille(){
        return ville;
    }
    
    /**
     * Methode getPays ()
     * @return : pays
     */
    public String getPays(){
        return pays;
    }
    
    public boolean isPeripheriqueInformationCorrect(){
        boolean status = (  !type_bien.isEmpty() &&
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
            System.out.println("Peripherique Information is correct");
        else
            System.out.println("Peripherique Information is not correct");
        
        return status;
    }
    
    private static final long serialVersionUID = 42L; 
}
