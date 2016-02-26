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

import java.util.ArrayList;

/**
 *
 * @author LamineBA
 */
public class IdentifiantAndKeyTable implements java.io.Serializable {
    
    private ArrayList<DeviceLinkedData> table_device_info;
    
    public IdentifiantAndKeyTable (){        
        table_device_info = new ArrayList<>();
    }
    
    /**
     * Methode addDeviceForLink () allow you to add the identifiant and key on the table
     * @param device
     */
    public void addDeviceForLink(DeviceLinkedData device){
        OwnerInformation user;
        DeviceLinkingData dev;
        String id;
        String key;
        
        if (device != null){
            user = (OwnerInformation)device.getOwnerInformation();
            dev = (DeviceLinkingData)(device.getDeviceLinkingInformation());
            id = (String)(device.getDeviceId());
            key = (String)(device.getDeviceKey());
            
            DeviceLinkedData devi = new DeviceLinkedData(user, dev, id, key);
            table_device_info.add(devi);
            
        }
        else{
            System.out.println("Nothing to add");
        }
    }
    
    public String [] getAllLinkedDeviceId(){
        
        String [] all_device_id_temp = new String[100];
        String [] all_device_id_out;
        
        for (int i=0; i<table_device_info.size(); i++){
            all_device_id_temp[i] = table_device_info.get(i).getDeviceId();
        }
        
        int count = 0;
        while(all_device_id_temp[count]!= null){
            count ++;
        }
        
        all_device_id_out = new String[count];
        System.arraycopy(all_device_id_temp, 0, all_device_id_out, 0, count);
        
        return all_device_id_out;
    }
    
    /**
     * Methode : getCorrespondingKey() allow you to get the corresponding to the identifiant passed on the argument
     * @param device_id : is the id of the device that you want to get the key
     * @return key if the device id passed is available on the table
     */
    public String getCorrespondingKey(String device_id){
        String key = "";
        
        for (int i=0; i<table_device_info.size(); i++){
            if (table_device_info.get(i).getDeviceId().equals(device_id)){
                key = table_device_info.get(i).getDeviceKey();
                System.out.println("The key corresponding on this device = '" + device_id + "' is = '" + key + "'");
                i = table_device_info.size();
            }
            else{
                key = null;
            }
        }
        return key;
    }
    
    /**
     * Methode : getCorrespondingKey() allow you to get the corresponding to the identifiant passed on the argument
     * @param user : is the user object that you want to get the key
     * @return key if the device id passed is available on the table
     */
    public String getCorrespondingKey(OwnerInformation user){
        String key = "";
        if (user != null){
            for (int i=0; i<table_device_info.size(); i++){
                OwnerInformation user_temp = table_device_info.get(i).getOwnerInformation();
                if (compareOwnerInformation(user_temp, user)){
                    key = table_device_info.get(i).getDeviceKey();
                    System.out.println("The corresponding key to this User = '" + user.getOwnerFirstName() + " " + user.getOwnerLastName() + " is = '" + key + "'");
                    i = table_device_info.size();
                }
                else{
                    System.err.println("L'utilisateur en question n'existe pas dans la table id_key");
                    key = null;
                }
            }
        }
        else{
            System.err.println("L'argument de OwnerInformation est null");
            key = null;
        }
        return key;
    }
    
    /**
     * Methode : getCorrespondingDevice() allow you to get the corresponding device
     * @param device_id : is the id of the device that you want to get the key
     * @return device if the device id passed is available on the table
     */
    public DeviceLinkingData getCorrespondingDevice(String device_id){
        DeviceLinkingData device = null;
        for (int i=0; i<table_device_info.size(); i++){
            if (table_device_info.get(i).getDeviceId().equals(device_id)){
                device = table_device_info.get(i).getDeviceLinkingInformation();
                i = table_device_info.size();
            }
            else{
                device = null;
            }
        }
        return device;
    }
    
    
    /**
     * Methode : toString print all the identifiant and key saved on the table
     * @return 
     */
    @Override
    public String toString(){
        String chaine = "<---------------------------------------------------->" + "\n"+
                        "<---  Table containing the identifiants and keys  --->" + "\n";
        int count = 0;
        
        if (table_device_info.isEmpty()){
            chaine += "Il n'y a aucun périphérique rattaché" + "\n";
        }
        else{
            while (count < table_device_info.size()){
                String device_number = String.format("%02d", (count +1));
                chaine += "<---------------------------------------------------->" + "\n";
                chaine += "Device Number : " + device_number +  "\n"; 
                chaine += table_device_info.get(count).getOwnerInformation().toString() + "\n";
                chaine += table_device_info.get(count).getDeviceLinkingInformation().toString() + "\n"; 
                chaine += "Device Id = " + table_device_info.get(count).getDeviceId() + "\n"; 
                chaine += "Device Key = " + table_device_info.get(count).getDeviceKey() + "\n";
                count ++;
            }
        }
        chaine += "<---------------------------------------------------->";
        return chaine;
    }
    
    /**
     * Methode : compareOwnerInformation() allow you to compare if two OwnerInformation are equal or not
     * @param first_object
     * @param second_object
     * @return true if the objects are equal. if not; it returns false
     */
    private boolean compareOwnerInformation(OwnerInformation first_object , OwnerInformation second_object){
        boolean compare_flag = false;
        boolean check_arg = false;
        boolean check_object_nature = false;
        
        // Check if the object is not null
        if (first_object != null && second_object != null)
            check_arg = true;
        else
            check_arg = false;
        
        // Check if the object is instance of OwnerInformation
        if (first_object instanceof OwnerInformation && second_object instanceof OwnerInformation)
            check_object_nature = true;
        else
            check_object_nature = false;
        
        // Compare the attribut of the two object
        if (check_arg && check_object_nature){
            int i_compare_birthday = first_object.getOwnerBirthdayDate().compareTo(second_object.getOwnerBirthdayDate());
            boolean b_compare_birthday = i_compare_birthday == 0;
            
            boolean b_compare_other = false;              
            b_compare_other = first_object.getOwnerFirstName().equals(second_object.getOwnerFirstName())  &&
                              first_object.getOwnerLastName().equals(second_object.getOwnerLastName())      &&
                              first_object.getOwnerPhoneNumber().equals(second_object.getOwnerPhoneNumber()) && 
                              first_object.getOwnerEmailAddress().equals(second_object.getOwnerEmailAddress()) &&
                              first_object.getOwnerStreetNumber() == second_object.getOwnerStreetNumber()     &&
                              first_object.getOwnerStreetName().equals(second_object.getOwnerStreetName())   &&
                              first_object.getOwnerCodePostale() == second_object.getOwnerCodePostale()     &&
                              first_object.getOwnerCity().equals(second_object.getOwnerCity())             &&
                              first_object.getOwnerCountry().equals(second_object.getOwnerCountry())      &&
                              first_object.getOwnerIdentifiant().equals(second_object.getOwnerIdentifiant());
                
            compare_flag = b_compare_birthday && b_compare_other;
        }
        else
            compare_flag =  false;
        
        return compare_flag;
    }
    
    
    private static final long serialVersionUID = 42L; 
    
    public static void main (String [] args){
        IdentifiantAndKeyTable id = new IdentifiantAndKeyTable();
        String ide = "SESAME DOORS";
        String key = "AZERTYUIOP";
        String [] data = {"Maison", "Proprietaire", "Rez de Chaussee", "Porte d'entree principale", "Ma residence principale",
                          "13", "Avenue Maximilien Robespierre", "94400", "Vitry sur Seine", "France"};
        DeviceLinkingData dev = new DeviceLinkingData(data);
        //System.out.println("DeviceLinkingData : \n " + dev);
        
        String data_owner [] = new String[12]; 
        data_owner[0]  = "BA";
        data_owner[1]  = "Lamine";
        data_owner[2]  = "10";
        data_owner[3]  = "10";
        data_owner[4]  = "1990";
        data_owner[5]  = "06 51 58 75 08";
        data_owner[6]  = "lamine.ba@everygates.com";
        data_owner[7]  = "13";
        data_owner[8]  = "Avenue Maximilien Robespierre";
        data_owner[9]  = "94400";
        data_owner[10] = "Vitry sur Seine";
        data_owner[11] = "France";
        OwnerInformation owner = new OwnerInformation(data_owner);
        //System.out.println(owner);
        
        
        DeviceLinkedData device_linked = new DeviceLinkedData(owner, dev, ide, key);
        id.addDeviceForLink(device_linked);
        //System.out.println("IdentifiantAndKeyTable : \n" + id);
        
        
        ide = "SESAME HAND";
        key = "QSDFGHJKLM";
        
        data_owner[0]  = "TAGU";
        data_owner[1]  = "Flaubert";
        data_owner[2]  = "10";
        data_owner[3]  = "10";
        data_owner[4]  = "1990";
        data_owner[5]  = "06 51 58 75 08";
        data_owner[6]  = "flaubert.tagu@everygates.com";
        data_owner[7]  = "13";
        data_owner[8]  = "Avenue Maximilien Robespierre";
        data_owner[9]  = "92220";
        data_owner[10] = "Bagneux";
        data_owner[11] = "France";
        OwnerInformation owner1 = new OwnerInformation(data_owner);
        device_linked = new DeviceLinkedData(owner1, dev, ide, key);
        id.addDeviceForLink(device_linked);
        System.out.println("IdentifiantAndKeyTable : \n" + id);

        System.out.println("GOT THE CORRESPONDING KEY ");
        String key_temp = id.getCorrespondingKey(owner);
        System.out.println("KEY = " +  key_temp);
        
        //System.out.println(id);   
    }
}