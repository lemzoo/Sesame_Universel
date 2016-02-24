/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SharingClass;

import SesameApp.*;
import AccesGUI.*;
import HomeGUI.*;
import LinkingGUI.*;
import SharingGUI.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LamineBA
 */
public class SerialPortSharerInstruction extends SerialPortGPIO{
    
    private String id_sesame_sharer = null;
    private String id_sesame_accredited = null;
    private String key_accredited = null;
    
    public SerialPortSharerInstruction(int baudrate){
        super(baudrate);
        
        
    }
    
    /**
     * Methode : analyzeDataReceived => Traitement des données recu
     * @param received_data
     * @throws java.lang.InterruptedException
     */
    @Override
    public void analyzeDataReceived(String received_data) throws InterruptedException{
        super.setLastReceivedData(received_data);
        switch (received_data) {
            
            case BONJOUR:
                Thread.sleep(100);
                System.out.println("|BONJOUR| recu dans la classe herité");
                break;
                        
            case DEMANDE_INFORMATION_SESAME_DOORS:
                Thread.sleep(1000);
                System.out.println("|DEMANDE_INFORMATION_SESAME_DOORS| recu dans la classe herité");
                String [] data_device = arrangeDeviceSharingData();
                super.sendData(data_device);
                break;
                
            case KEY_SHARE_ENREGISTREE_CORRECTEMENT:
                Thread.sleep(50);
                System.out.println("KEY_SHARE_ENREGISTREE_CORRECTEMENT recu");
                boolean flag_end_share = saveAndCloseTheShare();
                System.out.println("Status du partage = " + flag_end_share);
                break;
                        
            case INFORMATION_SESAME_DOORS_ENREGISTRE_CORRECTEMENT:
                Thread.sleep(50);
                System.out.println("INFORMATION_SESAME_DOORS_ENREGISTRE_CORRECTEMENT recu");
                super.sendData(DEMANDE_CONFIRMATION_DE_PARTAGE);
                break;
                
            case BEGIN:
                System.out.println("|BEGIN| dans la classe hérité");
                Thread.sleep(100);
                // reset the buffer
                super.resetBufferReception();
                super.setSavingFlag(true);
                break;
                
            case END:
                Thread.sleep(100);
                System.out.println("|END| dans la classe hérité");
                super.setSavingFlag(false);
                this.checkBufferData();
                break;
                
            case MERCI:
                System.out.println("Information envoyée avec succès et rajout de l'écouteur");
                sendData(FIN_DE_LA_COMMUNICATION);
                break;

            default:
                break;
        }
    }
    
    /**
     * Methode : saveAccreditedInformation() allows you to save all the information about the accredited user
     * in a ser file. 
     * @param data : contains the data received in the serial port
     * @return true if the owner information is correct
     * @throws java.lang.InterruptedException
     */
    private boolean saveAccreditedInformation(String [] data) throws InterruptedException {
        boolean flag = false;

        if (super.isChecksumCorrect(data)){
            OwnerInformation user = new OwnerInformation(data);
            // Make the Serialization before closing the windows
            File file = new File("accredited_information.ser");
            try{
                try (FileOutputStream fileOut = new FileOutputStream(file); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                    out.writeObject(user);
                    System.out.println(user);
                    flag = true;
                    System.out.println("accredited_information.ser file is created correcly");
                }
            }catch(IOException i){
                System.out.println("Exception de Serialisation " + i.getMessage());
                flag = false;
            }
        }
        else{
            System.out.println("The data about the accredited user is not saved");
            flag = false;
        }

        return flag;
    }
    
    
    /**
     * Methode checkBufferData() allows you to verify all the data saved in the buffer
     * @throws java.lang.InterruptedException
     */
    private void checkBufferData() throws InterruptedException{
        System.out.println("<--- BEGIN OF CALLING checkBufferData() methode --->");
        
        // Get the data saved in the buffer
        System.out.println("Contents of the buffer : " + super.getBufferReception());
        String [] data_in = SerialPortGPIO.extractBufferData(super.getBufferReception());
        
        //reset the reception buffer
        super.resetBufferReception();
        
        // Extract the first and last data to check the kind of request
        String first_data = data_in[0];
        String last_data  = data_in[data_in.length - 1];
        
        System.out.println("First = " + first_data);
        System.out.println("Last  = " + last_data);
        
        // Extract only the data about the request
        int size_data = data_in.length -2;
        String [] data_temp = new String[size_data];
        System.arraycopy(data_in, 1, data_temp, 0, size_data);
        for (int i=0; i<data_temp.length; i++){
            //data_temp[i] = data_in[i+1];
            System.out.println("Classe fille : valide data["+i+"] = " + data_temp[i]);
        }
        
        // Call the checkSavedData() methode to identify the kind of request
        boolean flag = checkSavedData(first_data, last_data, data_temp);
        
        System.out.println("<--- END OF CALLING checkBufferData() methode --->");
    }
    
    /**
     * Methode : checkSavedData : Traitement des données recu et sauvegarder dans le buffer de reception
     * @param first_data
     * @param last_data
     * @param data
     * @return flag : if the data is saved correctly
     * @throws java.lang.InterruptedException
     */
    private boolean checkSavedData(String first_data, String last_data, String [] data) throws InterruptedException{
        System.out.println("<--- BEGIN OF THE checkSavedData() methode --->");
        boolean flag = false;
        
        if ((first_data != null && first_data.equals(DEBUT_ENVOIE_INFORMATION_UTILISATEUR_ACCREDITE)) && 
            (last_data  != null  && last_data.equals(FIN_ENVOIE_INFORMATION_UTILISATEUR_ACCREDITE))){
            System.out.println("First if");
            if(data != null){
                
                System.out.println("Before calling saveAccreditedInformation()");
                flag = saveAccreditedInformation(data);
                System.out.println("After calling saveAccreditedInformation()");
                
                if(flag){
                    System.out.println("The data is saved correctly");
                    Thread.sleep(500);
                    this.sendData(INFORMATION_ACCREDITE_ENREGISTRE_CORRECTEMENT);
                }
                else{
                    System.out.println("The data is not saved because it contains some invalid data");
                    Thread.sleep(500);
                    this.sendData(INFORMATION_ACCREDITE_DONNEES_ERONNEES);
                }
            }
            else{
                System.out.println("The data is not saved because it contains some invalid data");
                Thread.sleep(500);
                this.sendData(INFORMATION_ACCREDITE_DONNEES_ERONNEES);
                System.out.println("Le buffer est invalide");
                flag = false;
            }   
        }
        if ((first_data != null && first_data.equals(DEBUT_ENVOIE_CONFIRMATION_PARTAGE)) && 
            (last_data  != null  && last_data.equals(FIN_ENVOIE_CONFIRMATION_PARTAGE))){
            System.out.println("First if");
            if(data != null){
                
                System.out.println("Before calling checkSharingConfirmation()");
                flag = checkSharingConfirmation(data);
                System.out.println("After calling checkSharingConfirmation()");
                
                System.out.println("CheckSavedData() is called = " + flag);
                
                if(flag){
                    System.out.println("La confirmation de partage est bien recue");
                }
                else{
                    System.out.println("La confirmation de partage n'est pas recue");
                }

            }
            else{
                System.out.println("The data is not saved because it contains some invalid data");
                flag = false;
            }   
        }
        // ===>
        else{
            flag = false;
            System.out.println("First Else ");
        }
        
        System.out.println("<--- END OF THE checkSavedData() methode -->");
        return flag; 
    }
    
    /**
     * Methode : arrangeDeviceSharingData() arrange the necessary data for sharing
     * @return [] string which contains all the data 
     */
    private String [] arrangeDeviceSharingData(){
        
        String [] data_device = null; 
        String [] arranged_data = null;
        boolean extract_device = false;
        
        /* Deserializable of the file containing the information of the owner
         * to add the indentifier number and password 
         */          
        DeviceLinkingData device = null;
        File file = new File("device_to_share.ser");
        try (FileInputStream fileIn = new FileInputStream(file); ObjectInputStream in = new ObjectInputStream(fileIn)) {
            device = (DeviceLinkingData) in.readObject();
            System.out.println("Extraction de " + file.getName());
            System.out.println(device);
            data_device = device.getDeviceDataWithId();
            extract_device = true;
            
        }catch(IOException io){
            System.out.println("Exception de IO : " + io.getMessage());
            extract_device = false;
        }catch(ClassNotFoundException c){
           System.err.println("DeviceLinkingData class not found");
           extract_device = false;
        }
        
        if (extract_device){
            int formule = 0; 
            int number_element = data_device.length;
            int size_arranged = number_element+3;
            int checksum = 0;
            arranged_data = new String[size_arranged];
            
            // Send the first frame to inform the device all the data received after taht frame is to save
            arranged_data[0]  = DEBUT_ENVOIE_INFORMATION_SESAME_DOORS;

            // Put the owner information without the ID of its Sesame
            System.arraycopy(data_device, 0, arranged_data, 1, number_element);
            
            // Create the checksum
            for (int j=0; j<number_element; j++){
                formule = formule + (number_element-j)*data_device[j].length();
            }
            checksum = formule - number_element*128;
            arranged_data[number_element+1] = Integer.toString(checksum);

            // Send the last frame to inform the device that is the last data
            arranged_data[number_element+2] = FIN_ENVOIE_INFORMATION_SESAME_DOORS;
        }
        else{
            arranged_data = new String[1];
            arranged_data[0] = "NOK";
        }
        return arranged_data;
    }
    
    /**
     * Methode : checkSharingConfirmation() allows you to check the confirmation sent by the Device to the Sesame
     * in a ser file. 
     * @param data
     * @return true if the owner information is correct
     * @throws java.lang.InterruptedException
     */
    private boolean checkSharingConfirmation(String [] data) throws InterruptedException {
        boolean flag = false;
        boolean flag_got_key = false;
        boolean flag_end = false;
        
        String id_got_from_sesame = "";
        int formule; 
        CreateAccesKey all_key = null;

        if (data != null && data.length >0){
            id_got_from_sesame = data[0];
            formule = id_got_from_sesame.length()*128; 
            
            int formule_recu=0; 
            try{
                formule_recu = Integer.parseInt(data[1]);
            }catch(NumberFormatException ex){
                formule_recu = 0;
            }
            
            if (formule == formule_recu){
                System.out.println("CRC Correct");
                // Save the identifiant in the temporary variable
                id_sesame_accredited = id_got_from_sesame;
                flag = true;
            }
            else{
                System.out.println("CRC inCorrect");
                flag = false;
            }
        }
        else{
            System.out.println("The data about the confirmation between DEVICE and SESAME is not valide");
            flag = false;
        }

        // Wait 10 secondes
        System.out.println("Wait for 5 secondes before sending the key to the device");
        int tempo = 0;
        int seconde = 1000;
        while (tempo<5*seconde){

            System.out.println("Tempo wait before sending the key = " + tempo);
            tempo += seconde;
            Thread.sleep(seconde);
        }
        
        
        File file = new File("all_key_saved.ser");
        // Check if the received id is correct and then send the key
        if (flag){
            // Get a valued key from a table which containing all keys. 
            // Deserialization 
            try (FileInputStream fileIn = new FileInputStream(file); ObjectInputStream in = new ObjectInputStream(fileIn)) {
                all_key = (CreateAccesKey) in.readObject();
                key_accredited = all_key.getOneValideKey();
                System.out.println("Identifiant Sesame Accredité     = " + id_sesame_accredited);
                System.out.println("Clé attribué au Sesame Accredité = " + key_accredited);
                
                flag_got_key = true;

            }catch(IOException i){
                flag_got_key = false;
            }catch(ClassNotFoundException c){
                flag_got_key = false;
               System.out.println("CreateAccesKey class not found : " + c.getMessage());
            }
        }
        else{
            flag_got_key = false;
            System.out.println("The received id is not correct ");
        }

        if (flag_got_key){
            // Make the Serialization before closing the windows
            try (FileOutputStream fileOut = new FileOutputStream(file); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(all_key);
                System.out.println("all_key_saved.ser file is created correctly");

                // Arrange the data about the key and calculate the checksum value
                int key_size = key_accredited.length();
                int checksum = 0;
                String [] key_data = new String[4];
                checksum  = (key_size*128) + (key_size/2)*64 + (key_size/4)*32; 
                checksum += (key_size/8)*16 + (key_size/16)*8 + (key_size/32)*4;
                checksum += (key_size/64)*2;

                // Arrange the key data in a tab for sending
                key_data[0] = DEBUT_ENVOIE_KEY_SHARE;
                key_data[1] = key_accredited;
                key_data[2] = Integer.toString(checksum);
                key_data[3] = FIN_ENVOIE_KEY_SHARE;


                // Send the Key tab to the device
                this.sendData(key_data);
                flag_end = true; 
                
            }catch(IOException i){
                System.out.println("Exception de Serialisation : " + i.getMessage());
            }
        }
        else{
            flag_end = false;
        }
        return flag && flag_got_key && flag_end;
    }
    
    /**
     * Methode : saveAndCloseTheShare() allow you to finish the share procedure between the SESAME and SESAME
     * @return true if the linking procedure is succesfully ran or false if else where
     * @throws java.lang.InterruptedException
     */
    private boolean saveAndCloseTheShare() throws InterruptedException{
        System.out.println("<--- BEGIN : saveAndCloseTheShare --->");
        
        boolean flag_extract_accredited = false;
        boolean flag_extract_device_sharing = false; 
        
        boolean flag_creating = false;
        boolean flag_adding_table = false;
        
        boolean flag_end = false;
        
        OwnerInformation accredited = null;
        DeviceLinkingData device_sharing = null;
        DeviceLinkedData device_shared = null;
        
        IdentifiantAndKeyTable table_temp = null; 
        
        String new_identifiant = id_sesame_accredited;
        String new_key = key_accredited;
        
        // Make the Deserialization of accredited_information.ser
        File file = new File("accredited_information.ser");
        try (FileInputStream fileIn = new FileInputStream(file); ObjectInputStream in = new ObjectInputStream(fileIn)) {
            accredited = (OwnerInformation) in.readObject();
            flag_extract_accredited = true;             
        }catch(IOException i){
            flag_extract_accredited = false;
            System.out.println("IOException : " + i.getMessage());
        }catch(ClassNotFoundException c){
            flag_extract_accredited = false;
            System.out.println("OwnerInformation class not found");
        }
        

        // Make the Deserialization of device_sharing.ser
        file = new File("device_to_share.ser");
        try (FileInputStream fileIn = new FileInputStream(file); ObjectInputStream in = new ObjectInputStream(fileIn)) {
            device_sharing = (DeviceLinkingData) in.readObject();
            flag_extract_device_sharing = true;             
        }catch(IOException i){
            flag_extract_device_sharing = false;
            System.out.println("IOException : " + i.getMessage());
        }catch(ClassNotFoundException c){
            flag_extract_device_sharing = false;
            System.out.println("DeviceLinkingData class not found");
        }

        // Check if the two files are extracted correctly, then continue the process
        if (flag_extract_accredited && flag_extract_device_sharing){
            // Create a new object containing device_info,id and key
            device_shared = new DeviceLinkedData(accredited, device_sharing, new_identifiant, new_key);
            flag_creating = true;
        }
        else{
            flag_creating = false;
        }
        
        // if the object is created correctly then continue
        if (flag_creating){
            // Make the deserialization of the table file which is the database of the device
            file = new File("identifiant_and_key_table.ser");
            try (FileInputStream fileIn = new FileInputStream(file); ObjectInputStream in = new ObjectInputStream(fileIn)) {
                table_temp = (IdentifiantAndKeyTable) in.readObject();

                // Add the device link information on the table
                table_temp.addDeviceForLink(device_shared);
                System.out.println("Content of the table after");
                System.out.println(table_temp);

                flag_adding_table = true;

            }catch(IOException i){
                flag_adding_table = false;
                System.out.println("IOException : " + i.getMessage());
            }catch(ClassNotFoundException c){
                flag_adding_table =false;
                System.out.println("DeviceLinkingData class not found");
            }
        }
        else{
            // Nothing
            flag_adding_table = false;
        }
        
        // if the deserialization of the linking file is done succesfully and the table id and key also done succesfully
        if (flag_creating && flag_adding_table){
            // Make the serialization of the table id and key file to save the new linked device
            // Make the serialization to save the new added device on the table
            file = new File("identifiant_and_key_table.ser");
            try(FileOutputStream fileOut = new FileOutputStream(file); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(table_temp);
                flag_end = true;
                // End of the Serialization for IdentifiantAndKeyTable
                System.out.println("Serialized of the new IdentifiantAndKeyTable is saved in identifiant_and_key_table.ser");

            }catch(IOException io){
                flag_end = false;
                System.out.println("Exception for IdentifiantAndKeyTable : " + io.getMessage());
                System.out.println("Serialized of the new IdentifiantAndKeyTable is not done : check error");
            }
        }
        
        System.out.println("<--- END   : saveAndCloseTheShare --->");
        
        return flag_end;
    }
    
    
    public static void main(String [] args){
        SerialPortSharerInstruction test = new SerialPortSharerInstruction(115200);
        int count = 0;
        while (true){
            if (count<5){
                System.out.println("Count SESAME DOORS = " + count);
            }
            count ++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {}
        }
    }
}
