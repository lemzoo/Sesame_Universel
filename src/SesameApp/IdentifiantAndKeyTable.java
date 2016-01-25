/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SesameApp;

import java.util.ArrayList;

/**
 *
 * @author LamineBA
 */
public class IdentifiantAndKeyTable {
    
    private ArrayList<DeviceLinkedData> table_device_info;
    
    public IdentifiantAndKeyTable (){        
        table_device_info = new ArrayList<>();
    }
    
    /**
     * Methode addDeviceForLink () allow you to add the identifiant and key on the table
     * @param device
     * @param id
     * @param key
     */
    public void addDeviceForLink(DeviceLinkingData device, String id, String key){
        if (device != null && id != null && key != null){
            
            DeviceLinkedData dev = new DeviceLinkedData(device, id, key);
            table_device_info.add(dev);
            
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
     * Methode : toString print all the identifiant and key saved on the table
     * @return 
     */
    @Override
    public String toString(){
        String chaine = "<---------------------------------------------------->" + "\n"+
                        "<---  Table containing the identifiants and keys  --->" + "\n";
        
        int count = 0;
        while (count < table_device_info.size()){
            chaine += "<---------------------------------------------------->" + "\n";
            chaine += "Device Number : " +count+  "\n"; 
            chaine += table_device_info.get(count).getDeviceLinkingInformation().toString() + "\n"; 
            chaine += "Device Id = " + table_device_info.get(count).getDeviceId() + "\n"; 
            chaine += "Device Key = " + table_device_info.get(count).getDeviceKey() + "\n";
            count ++;
        }
        chaine += "<---------------------------------------------------->";
        return chaine;
    }
    
    public static void main (String [] args){
        IdentifiantAndKeyTable id = new IdentifiantAndKeyTable();
        String ide = "SESAME DOORS";
        String key = "AZERTYUIOP";
        String [] data = {"Maison", "Proprietaire", "Rez de Chaussee", "Porte d'entree principale", "Ma residence principale",
                          "13", "Avenue Maximilien Robespierre", "94400", "Vitry sur Seine", "France"};
        DeviceLinkingData dev = new DeviceLinkingData(data);
        id.addDeviceForLink(dev, ide, key);
        
        ide = "SESAME HAND";
        key = "QSDFGHJKLM";
        id.addDeviceForLink(dev, ide, key);
        
        ide = "SESAME CAR";
        key = "WXCVBN";
        id.addDeviceForLink(dev, ide, key);
        
        System.out.println(id);
        
        
    }
}

class DeviceLinkedData {

     private DeviceLinkingData device_info;
     private String device_id;
     private String device_key;

    public DeviceLinkedData(DeviceLinkingData device, String id, String key) {
        this.device_info = device;
        this.device_id   = id;
        this.device_key  = key;
    }

    /**
     * Methode : getDeviceId() allow you to get the information about the linked device
     * @return device_info
     */
    public DeviceLinkingData getDeviceLinkingInformation() {
        return this.device_info;
    }
    /**
     * Methode setDeviceLinkingInformation() allow you to set the information about the device
     * @param device_info 
     */
    public void setDeviceLinkingInformation(DeviceLinkingData device_info) {
        this.device_info = device_info;
    }
    //====>
    
    /**
     * Methode : getDeviceId() allow you to get the Id of the device
     * @return device_id
     */
    public String getDeviceId() {
        return this.device_id;
    }
    /**
     * Methode setDeviceId() allow you to set the id of the device
     * @param id 
     */
    public void setDeviceId(String id) {
        this.device_id = id;
    }
    //====>
    
    /**
     * Methode : getDeviceKey() allow you to get the key of the device
     * @return device_key
     */
    public String getDeviceKey() {
        return this.device_key;
    }
    /**
     * Methode setDeviceKey() allow you to set the key of the device
     * @param key 
     */
    public void setDeviceKey(String key) {
        this.device_key = key;
    }
    //====>
}
