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

import java.util.Arrays;

/**
 * Class : AssociatedKeyAndDevice allow you to save identifiant and key 
 * @author LamineBA
 */
public class AssociatedKeyAndDevice implements java.io.Serializable{
    
    private String identifiant_device = "";
    private String key_device         = "";
    
    public AssociatedKeyAndDevice (String identifiant, String key){
        if (identifiant != null || key != null){
            identifiant_device = identifiant;
            key_device         = key;
            System.out.println("The device and the key are associated succesfully");
        }
        else{
            identifiant_device = "";
            key_device         = "";
            System.out.println("The device and the key are note associated. You have some invalide information : device identifiant or key");
        }
    }
    
    @Override
    public String toString(){
        String chaine = "<---------------------------------------------------->" + "\n"+
                        "<--- Information about Device and Key association --->" + "\n"+
                        "Identifiant = " + identifiant_device + "\n" +
                        "Key         = " + key_device + "\n" +
                        "<---------------------------------------------------->";
        return chaine;
    }
    
    public String [][] getIdentifiantAndKey(){
        String [][] id_key = new String[1][2];
        id_key[0][0] = identifiant_device;
        id_key[0][1] = key_device;
        System.out.println("Identifiant = " + id_key[0][0] + " Key = " + id_key[0][1]);
        return id_key;
    }
    
    private static final long serialVersionUID = 42L;
    
    public static void main (String [] args){
        AssociatedKeyAndDevice ass = new AssociatedKeyAndDevice ("SESAME DOORS", "ABCDERGHIJK12EDSA12343RFD");
        System.out.println(ass);
        
        String [][] key = ass.getIdentifiantAndKey();
    }
}
