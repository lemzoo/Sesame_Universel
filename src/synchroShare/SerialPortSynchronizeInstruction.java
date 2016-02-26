/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package synchroShare;

import SesameApp.*;
import java.io.*;

/**
 * Class : SerialPortSynchronizeInstruction() make the synchronization after sharing acces to an accredited user. 
 * @author LamineBA
 */
public class SerialPortSynchronizeInstruction extends SerialPortGPIO{
    
    private OwnerInformation accredited_user = null;
    private String id_sesame_accredited = null;
    private String key_accredited = null;
    
    public SerialPortSynchronizeInstruction(int baudrate){
        
        super(baudrate);
        boolean extract_accredited = false;
        boolean extract_id_key_table = false;
        
        //File file = new File("/home/pi/Desktop/synchro/accredited_information.ser");
        File file = new File("accredited_information.ser");
        try (FileInputStream fileIn = new FileInputStream(file); ObjectInputStream in = new ObjectInputStream(fileIn)) {
            accredited_user = (OwnerInformation) in.readObject();
            System.out.println("Extraction de : '" + file.getName() + "'");
            extract_accredited = true;
            
        }catch(IOException io){
            extract_accredited = false;
            System.out.println("Exception de IO : " + io.getMessage());
        }catch(ClassNotFoundException c){
           extract_accredited = false;
           System.err.println("OwnerInformation class not found : " + c.getMessage());
        }
        
        if (extract_accredited)
            System.out.println("Accredited user : \n " + accredited_user);
        
        IdentifiantAndKeyTable id_key_table = null;
        //file = new File("/home/pi/Desktop/synchro/identifiant_and_key_table.ser");
        file = new File("identifiant_and_key_table.ser");
        try (FileInputStream fileIn = new FileInputStream(file); ObjectInputStream in = new ObjectInputStream(fileIn)) {
            id_key_table = (IdentifiantAndKeyTable) in.readObject();
            System.out.println("Extraction de : '" + file.getName() + "'");
            extract_id_key_table = true;
            
        }catch(IOException io){
            extract_id_key_table = false;
            System.out.println("Exception de IO : " + io.getMessage());
        }catch(ClassNotFoundException c){
           extract_id_key_table = false;
           System.err.println("IdentifiantAndKeyTable class not found : " + c.getMessage());
        }
        
        if (extract_id_key_table)
            System.out.println("IdentifiantAndKeyTable : \n" + id_key_table);
        
        
        if (extract_accredited && extract_id_key_table){
            System.out.println("GOT THE KEY CORRESPONDING TO THE USER");
            key_accredited = id_key_table.getCorrespondingKey(accredited_user);
            System.out.println("key_accredited = " + key_accredited);
            
            try {
                Thread.sleep(500);
                super.sendData(BONJOUR);
            } catch (InterruptedException ex) {}
        }
        else{
            System.err.println("IMPOSSIBLE TO GET THE KEY");
        }
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
                System.out.println("|BONJOUR| recu dans la classe herité et envoie de 'DEMANDE_SYNCHRONIZATION_APRES_PARTAGE_ACCES'");
                super.sendData(DEMANDE_SYNCHRONIZATION_APRES_PARTAGE_ACCES);
                break;
                        
            case DEMANDE_SYNCHRONIZATION_APRES_PARTAGE_ACCES_AUTORISEE:
                Thread.sleep(100);
                System.out.println("|DEMANDE_SYNCHRONIZATION_APRES_PARTAGE_ACCES_AUTORISEE| recu dans la classe herité");
                String [] data_accredited = arrangeAccreditedData();
                super.sendData(data_accredited);
                break;
                
            case INFORMATION_ACCREDITEE_ENREGISTRE_CORRECTEMENT:
                Thread.sleep(100);
                System.out.println("INFORMATION_ACCREDITEE_ENREGISTRE_CORRECTEMENT recu");
                System.out.println("Envoie d'une demande de reception de la cle d'acces");
                super.sendData(PREPARATION_ENREGISTREMENT_CLE_ACCES_ACCREDITEE);
                break;
                        
            case SESAME_PRET_ENREGISTRE_CLE_ACCES:
                Thread.sleep(100);
                System.out.println("SESAME_PRET_ENREGISTRE_CLE_ACCES recu");
                String [] data_key_accredited = arrangeAccreditedKey();
                super.sendData(data_key_accredited);
                break;
                
            case CLE_ACCES_ACCREDITEE_ENREGISTREE_CORRECTEMENT:
                Thread.sleep(100);
                System.out.println("CLE_ACCES_ACCREDITEE_ENREGISTREE_CORRECTEMENT recu");
                this.closeUartPort();
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
                super.checkBufferData();
                break;
                
            case MERCI:
                System.out.println("Information envoyée avec succès et rajout de l'écouteur");
                sendData(FIN_DE_LA_COMMUNICATION);
                break;

            default:
                break;
        }
    }
    
    
    private boolean synchronizeProcedure(){
        boolean status_synchro = false;
        
        // 1. send "BONJOUR
        // 2. send DEMANDE_SYNCHRONIZATION_APRES_PARTAGE_ACCES
        // 3. check DEMANDE_SYNCHRONIZATION_AUTORISEE
        // 4. send OwnerInformation
        // 5. check the Answer "OwnerInformation enregistree correctement"
        // 6. send the key
        // 7. close the process
        
        return status_synchro;
    }
    
    /** 
     * Methode : arrangeAccreditedData() arrange the data about the accredited user
     * @return [] String which contains a package of data ready to send. 
     */
    public String[] arrangeAccreditedData (){
        String [] accredited_data = accredited_user.getOwnerInformationForSharing();
        
        int size_data_owner = accredited_data.length;
        String [] data_out = new String[size_data_owner + 3];
        int formule = 0;
        
        // Send the first frame to inform the device all the data received after taht frame is to save
        data_out[0]  = DEBUT_ENVOIE_INFORMATION_ACCREDITEE;

        // Put the linking information 
        System.arraycopy(accredited_data, 0, data_out, 1, size_data_owner);

        // Create the checksum
         
        for (int j=0; j<size_data_owner; j++){
            formule = formule + (size_data_owner-j)*accredited_data[j].length();
        }
        int checksum = formule - size_data_owner*128;
        data_out[size_data_owner + 1] = Integer.toString(checksum);

        // Send the last frame to inform the device that is the last data
        data_out[size_data_owner + 2] = FIN_ENVOIE_INFORMATION_ACCREDITEE;
        
        
        return data_out;
    }
    
    /** 
     * Methode : arrangeAccreditedKey() arrange the key affected to the accredited user during the sharing process
     * @return [] String which contains a package of data ready to send. 
     */
    public String[] arrangeAccreditedKey (){
        // Arrange the data about the key and calculate the checksum value
        int key_size = key_accredited.length();
        int checksum = 0;
        String [] key_data = new String[4];
        checksum  = (key_size*128) + (key_size/2)*64 + (key_size/4)*32; 
        checksum += (key_size/8)*16 + (key_size/16)*8 + (key_size/32)*4;
        checksum += (key_size/64)*2;

        // Arrange the key data in a tab for sending
        key_data[0] = DEBUT_ENVOIE_CLE_ACCES_ACCREDITEE;
        key_data[1] = key_accredited;
        key_data[2] = Integer.toString(checksum);
        key_data[3] = FIN_ENVOIE_CLE_ACCES_ACCREDITEE;
        
        return key_data;
    }
    
    
    public static void main(String [] args){
        SerialPortSynchronizeInstruction test = new SerialPortSynchronizeInstruction(115200);
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
