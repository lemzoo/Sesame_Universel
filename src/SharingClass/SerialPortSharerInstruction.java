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
    
    public SerialPortSharerInstruction(int baudrate){
        super(baudrate);
        
        OwnerInformation user = new OwnerInformation();
        System.out.println(user);
        // Make the Serialization for OwnerInformation 
        File file = new File("owner_information.ser");
        try(FileOutputStream fileOut = new FileOutputStream(file); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(user);
        }catch(IOException i){
            System.out.println("Exception for OwnerInformation");
        }
        // End of the Serialization for OwnerInformation

        int count = 0;
        while(count<5){
            try {
                super.sendData(BONJOUR);
                System.out.println("Count = " + count);
                count += 1;
                Thread.sleep(1000);
            } catch (InterruptedException ex) {}
        }
        /*try {
            super.sendData(BEGIN);
            Thread.sleep(1000);
            super.sendData(END);
            Thread.sleep(1000);
        } catch (InterruptedException ex) {}*/
        
        System.out.println("<--- DEBUT DE shareAccesInstruction() --->");
        shareAccesInstruction();
        System.out.println("<--- DEBUT DE shareAccesInstruction() --->");
    }
    
    /**
     * Methode : analyzeDataReceived => Traitement des données recu
     * @throws java.lang.InterruptedException
     */
    @Override
    public void analyzeDataReceived() throws InterruptedException{

        switch (super.getLastReceivedData()) {
            case BONJOUR:
            Thread.sleep(100);
                System.out.println("|BONJOUR| recu dans la classe herité");
                break;
                
            case BEGIN:
                System.out.println("Reception de |BEGIN| dans la classe hérité");
                Thread.sleep(100);
                // reset the buffer
                super.setBufferReception("");
                super.setSavingFlag(true);
                break;
                
            case END:
                Thread.sleep(100);
                System.out.println("Reception de |END| dans la classe hérité");
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
     * Methode : shareAccesInstruction() allows you to send all the instruction about the share of the acces. 
     * @param args 
     */
    public void shareAccesInstruction(){
        boolean status_sharing_owner  = false;
        boolean status_sharing_guest  = false;
        
        boolean status_sharing_device = false;
        boolean status_linking_device = false;
        
        boolean status_share_config = false;
        
        boolean flag = true;
        int tempo = 0;
        int seconde = 1000;

        // Send "BONJOUR"        
        try {
            super.sendData(BONJOUR);
        }catch(InterruptedException e){
            flag = false;
        }
        
        // Check the answer of the device in the while loop
        while (flag){
            if (tempo>seconde){
                flag=false;
                System.out.println("Tempo d'attente de la réponse du périphérique a expiré");
                tempo = 0;
            }
            else{
                flag = (super.getLastReceivedData()!=null && !super.getLastReceivedData().equals(BONJOUR));
                if (!flag){
                    System.out.println("|BONJOUR| sur SerialPortForSharingAcces");
                }
                tempo = tempo + 100;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {}
            }
        }
        
        // Wait 10 secondes
        tempo = 0;
        while (tempo<5*seconde){
            tempo = tempo + seconde;
            try {
                Thread.sleep(seconde);
            } catch (InterruptedException ex) {}
        }
        // Reset the tempo && the flag
        tempo = 0; 
        flag = true;
        
        // Send ID of the Sesame guest
        String id_sesame_guest = "RPi_GUEST";
        try {
            super.sendData(id_sesame_guest);
        }catch(InterruptedException e){
            flag = false;
        }
        
        // Check if the ID of the Sesame is the same as the old ID saved
        while (flag){
            if (tempo>seconde){
                flag=false;
                System.out.println("Tempo d'attente de la réponse du périphérique a expiré");
                tempo = 0;
            }
            else{
                flag = (super.getLastReceivedData()!=null && !super.getLastReceivedData().equals(id_sesame_guest));
                if (!flag){
                    System.out.println(id_sesame_guest + " is the id of the Sesame guest");
                }
                tempo = tempo + 100;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {}
            }
        }
        tempo = 0;
        flag = true;
        
        // Send the frame DEMANDE_PARTAGE_ACCES
        try {
            super.sendData(DEMANDE_PARTAGE_ACCES);
        }catch(InterruptedException e){
            flag = false;
        }
        
        // Check the received data if the Sesame authorize the share or not
        while (flag){
            if (tempo>seconde){
                flag=false;
                System.out.println("Tempo d'attente de la réponse du périphérique a expiré");
                tempo = 0;
            }
            else{
                flag = (super.getLastReceivedData()!=null && !super.getLastReceivedData().equals(PARTAGE_ACCES_AUTORISEE));
                if (!flag){
                    System.out.println("PARTAGE_ACCES_AUTORISEE is the received");
                }
                tempo = tempo + 100;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {}
            }
        }
        
        // The expected answer is received by the SESAME 
        if (!flag){
            System.out.println("Partage d'accès autorisé par le Sesame destinataire");
            String [] data_to_send = arrangeSharerData();
            flag = true;
            tempo = 0;
            try {
                // Arrange the owner information and Send it
                super.sendData(data_to_send);
            } catch (InterruptedException ex) {}
        }
        
        // Check if the Receiver of the share has saved the Sharer Information => status_sharing_owner
        while (flag){
            if (tempo>7000){
                flag=false;
                tempo = 0;
                System.out.println("Tempo d'attente de la réponse du périphérique a expiré");
            }
            else{
                flag = (super.getLastReceivedData()!=null && !super.getLastReceivedData().equals(INFORMATION_PARTAGEUR_ENREGISTRE_CORRECTEMENT));
                if (!flag){
                    System.out.println("|PARTAGEUR_ENREGISTRE_CORRECTEMENT| par le Sesame destinataire");
                    status_sharing_owner = true;
                }
                else{
                    tempo =  tempo + 100;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {}
                }
            }
        }
        
        // Ask the receiver of the share to send its owner information => send => DEMANDE_INFORMATION_IDENTIFICATION_ACCREDITEE
        try {
            super.sendData(DEMANDE_INFORMATION_IDENTIFICATION_ACCREDITEE);
        }catch(InterruptedException e){
            flag = false;
        }
    }
    
    private String [] arrangeSharerData(){
        
        String [] data_sharer = null; 
        String [] arranged_data = null;
        boolean extract_sharer = false;
        
        /* Deserializable of the file containing the information of the owner
         * to add the indentifier number and password 
         */          
        OwnerInformation sharer = null;
        File file = new File("owner_information.ser");
        try (FileInputStream fileIn = new FileInputStream(file); ObjectInputStream in = new ObjectInputStream(fileIn)) {
            sharer = (OwnerInformation) in.readObject();
            //System.out.println("Extraction de OwnerInformation");
            data_sharer = sharer.getOwnerInformationForSharing();
            System.out.println(sharer);
            extract_sharer = true;
            
        }catch(IOException io){
            System.out.println("Exception de IO : " + io.getMessage());
            extract_sharer = false;
        }catch(ClassNotFoundException c){
           System.err.println("OwnerInformation class not found");
           extract_sharer = false;
        }
        
        if (extract_sharer){
            int formule = 0; 
            int number_element = data_sharer.length;
            int size_arranged = number_element+3;
            int checksum = 0;
            arranged_data = new String[size_arranged];
            
            // Send the first frame to inform the device all the data received after taht frame is to save
            arranged_data[0]  = DEBUT_ENVOIE_INFORMATION_UTILISATEUR_PARTAGEANT;

            // Put the owner information without the ID of its Sesame
            System.arraycopy(data_sharer, 0, arranged_data, 1, number_element);
            
            // Put the Sesame Sharer ID on the variable "id_sesame_sharer"
            id_sesame_sharer = data_sharer[number_element-1];
            System.out.println("ID Of the sesame partageur extracted : " + id_sesame_sharer);
            
            // Create the checksum
            for (int j=0; j<number_element; j++){
                formule = formule + (number_element-j)*data_sharer[j].length();
            }
            checksum = formule - number_element*128;
            arranged_data[number_element+1] = Integer.toString(checksum);

            // Send the last frame to inform the device that is the last data
            arranged_data[number_element+2] = FIN_ENVOIE_INFORMATION_UTILISATEUR_PARTAGEANT;
        }
        else{
            arranged_data = new String[1];
            arranged_data[0] = "NOK";
        }
        return arranged_data;
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
            UserIdentification user = new UserIdentification(data);
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
        String [] data_in = SerialPortGPIO.extractBufferData(super.getBufferReception());
        
        
        // Extract the first and last data to check the kind of request
        String first_data = data_in[0];
        String last_data  = data_in[data_in.length - 1];
        
        System.out.println("First = " + first_data);
        System.out.println("Last  = " + last_data);
        
        // Extract only the data about the request
        int size_data = data_in.length -2;
        String [] data_temp = new String[data_in.length -2];
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
        // ===>
        else{
            flag = false;
            System.out.println("First Else ");
        }
        
        System.out.println("<--- END OF THE checkSavedData() methode -->");
        return flag; 
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
