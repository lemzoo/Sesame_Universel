//SESAME

package SesameApp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialFactory;
import com.pi4j.io.serial.SerialPortException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * This example code demonstrates how to perform serial communications using the Raspberry Pi.
 * 
 * @author Robert Savage
 */
public class SerialPortGPIO implements ConstantsConfiguration{
    
    private String reception_buffer;
    private String [] linked_data;
    private String [] owner_information;
    private boolean flag_owner_information = false;
    private boolean flag_linking = false;
    private boolean flag_saving = false;
    private int baudrate = 0;
    private Serial serial;
    UARTListener uart_listener = null;
    private String buffer;
    private String identifiant = "";
    private String key = "";

    /**
     * Class : SerialPortGPIO is the class which configure the uart port. 
     * @param baudrate
     */
    public SerialPortGPIO (int baudrate) {
        this.baudrate = baudrate;
        System.out.println("<--Pi4J--> Serial Communication Class ... started.");
        System.out.println(" ... connect using settings: " + this.baudrate + ", N, 8, 1.");
        System.out.println(" ... data received on serial port should be displayed below.");
        System.out.println("<==============================================>");              
        System.out.println("<=============== SESAME STARTED ===============>");      
        System.out.println("<==============================================>"); 
        this.initialize();
        reception_buffer = "raspberry";
    }
    
    /**
     * Methode : initialize() allows you to initialize the uart port. 
     */
    public void initialize() {
        System.out.println("Initialisation du port de communication serie");
        // create an instance of the serial communications class
        serial = SerialFactory.createInstance();
        
        // open the default serial port provided on the GPIO header
        serial.open(Serial.DEFAULT_COM_PORT, this.baudrate);
        serial.flush();

        // create and register the serial data listener
        uart_listener = new UARTListener(this);
        serial.addListener(uart_listener);        
    }

    /**
     * Methode : setDataBufferReception(String data)
     * @param data
     */
    public void setDataBufferReception (String data){
        this.reception_buffer = data;
    }
    
    /**
     * Methode getNewDataReceived allow you to get the contains of the uart reception buffer
     * @return reception_buffer 
     */
    public String getLastReceivedData (){
        return reception_buffer;
    }

    /**
     * Methode getSerial()
     * @return serial port 
     */
    public Serial getSerial(){
        return this.serial;
    }
    
    /**
     * Methode getLinkedFlag()
     * @return flag_linking
     */
    public boolean getLinkedFlag(){
        return this.flag_linking;
    }
    
    /**
     * Methode getSavingFlag()
     * @return flag_saving
     */
    public boolean getSavingFlag(){
        return this.flag_saving;
    }
    
    /**
     * Methode setSavingFlag()
     * @param flag
     */
    public void setSavingFlag(boolean flag){
        this.flag_saving = flag;
    }
    
    /**
     * Methode getFlagOwnerInformation()
     * @return flag_owner_information
     */
    public boolean getFlagOwnerInformation(){
        return this.flag_owner_information;
    }
    
    /**
     * Methode getConnectionData()
     * @return linked_data which contains
     * all the onformation about the new device
     */
    public String[] getLinkedData(){
        return linked_data;
    }
    
    /**
     * Methode setReceivedData() allows you to write the received data in the buffer which you want
     * @param pos : is the position which data will be written
     * @param buffer : is the array string which received data will be written
     * @param data_received : is the data which received by the serial port
     */

    public void setReceivedData(int pos, String [] buffer, String data_received){
        buffer[pos] = data_received;
        System.out.println("Data saved : " + data_received + " pos = " + pos);
    }
    
    /**
     * Methode setBufferData() allows you to write the received data in the buffer which you want
     * @param data_received : is the data which received by the serial port
     */

    public void setBufferData(String data_received){
        buffer = buffer + data_received;
        buffer = buffer.replace("\n", "").replace("\r", "");
    }
    
    /**
     * Methode getConnectingData()
     * @return linked_data which contains
     * all the onformation about the new device
     */
    public String[] getOwnerInformation(){
        return owner_information;
    }
    
    /**
     * Methode sendData allow you to send data via the uart
     * @param data_to_send
     * @throws java.lang.InterruptedException
     */
    public void sendData (String data_to_send) throws InterruptedException{
        try {
            // write a simple string to the serial transmit buffer
            serial.writeln(data_to_send);
            System.out.println("Data sent : " + data_to_send);
            Thread.sleep(200);
        }
        catch(IllegalStateException ex){
        }
        catch(SerialPortException ex) {
            System.out.println(" ==>> SERIAL SETUP FAILED : " + ex.getMessage());
        }	
    }
    
    /**
     * Methode : sendData() allows you to send all the data about the Owner of the SESAME
     * @param data
     * @throws java.lang.InterruptedException
     */
    public void sendData(String [] data) throws InterruptedException{
        String [] data_to_send = formatData(data);
        int i=0;
        while (i<data_to_send.length){
            String [] data_temp = sampleDataToSend(data_to_send[i]);
            for(int j=0;j<data_temp.length;j++){
                System.out.println("Data sent[" + j + "] = " + data_temp[j]);
                sendData(data_temp[j]);
            }
            i++;
        }        
    }
    /**
     * Methode : formatData() allows you to arrange data to the correct format for sending
     * @param data : is the data arranged on the String Array
     * @return data_out : data arranged on the correct format for sending
     */
    public String [] formatData(String [] data) {
        String [] buffer_temp = new String[100];
        String [] data_out;
        // Send the first char to prevent the receiver "B" = Begin
        buffer_temp[0] = BEGIN;
        int k = 0;
        // Put the linking information 
        for (int i=0; i<data.length; i=i+1){
            k=2*i+1;
            buffer_temp[k] = String.format("%02d", data[i].length());
            buffer_temp[k+1] = data[i];
        }
        // Send the last char to end the transmission : "E" = End
        buffer_temp[k+2] = END;
        // Count the size of the String array
        int size=0;
        int count=0;
        while(buffer_temp[count]!= null){
            count ++;
        }
        size = count;
        // Make a table which size is the size of the data available on the buffer data_temp
        data_out = new String[size];
        
        // Copy the data on the buffer which will returned by the medthode
        System.arraycopy(buffer_temp, 0, data_out, 0, size);
        
        return data_out;
    }

    /**
     * Methode : sampleDataToSend allow you to sample the data that will sent by the uart
     * @param data_to_send
     * @return String[] containing all the sampled data
     */
    public static String [] sampleDataToSend(String data_to_send){
        String [] data_sampled_temp = new String[10];
        String [] data_sampled_out;
        
        boolean flag_data = false; 
        String data_to_send_temp = "";
        
        int size_original = data_to_send.length();
        char[] charArray = data_to_send.toCharArray();
        int size_rest = 0; 
        int number_char_to_delete = 10;
        int count = 0;
        
        if (size_original >10){
            System.out.println("Size of the buffer is superior to 10");
            flag_data = true;
            
            while(flag_data){
               
                if (size_original - number_char_to_delete>0){
                    size_rest = number_char_to_delete;
                }
                else{
                    size_rest = size_original;
                }
                data_to_send_temp = "";
                // Extract the character
                for (int i=0; i<size_rest; i++){
                    data_to_send_temp = data_to_send_temp + String.valueOf(charArray[i]); 
                }
                //System.out.println("Data extracted = " + data_to_send_temp);
                data_sampled_temp[count] = data_to_send_temp;
                count ++;
                        
                // delete the character that is extracted
                StringBuilder sb = new StringBuilder();
                sb.append(data_to_send);
                //System.out.println("StringBuilder = " + sb.toString());
                //System.out.println("Buffer before = " + data_to_send);
                sb.delete(0, size_rest);
                data_to_send = sb.toString();
                //System.out.println("Buffer after = " + data_to_send);
                charArray = data_to_send.toCharArray();

                if (size_original == size_rest){
                    flag_data = false;
                }
                else{
                    size_original = data_to_send.length();
                    flag_data = true;
                }
            }
        }
        else{
            count = 0;
            //System.out.println("Size of the buffer is not superior to 10");
            data_sampled_temp[0] = data_to_send;
        }
        // Extract the valid data on the data_sampled_temp
        int count_data = 0;
        while(data_sampled_temp[count_data]!= null){
            count_data ++;
        }
        data_sampled_out = new String[count_data];
        for(int i=0;i<count_data;i++){
            data_sampled_out[i] = data_sampled_temp[i];
            System.out.println("Data extraction methode["+i+"] = " + data_sampled_out[i]);
        }
        
        return data_sampled_out; 
    }
    
    /**
     * Methode : openUartPort()
     * @return status of the port 
     */
    public boolean openUartPort (){
        boolean port_status = false;
        if (this.serial.isOpen()){
            System.out.println("Le port est déjà ouvert");
            port_status = true;
        }
        else{
            try {
                // Re-open the UART port
                serial.open(Serial.DEFAULT_COM_PORT, this.baudrate);
            }
            catch(IllegalStateException ex){
            }
            if (this.serial.isOpen()){
                System.out.println("Le port est déjà ouvert");
                port_status = true;
            }
            else{
                System.out.println("Impossible d'ouvrir le port");
                port_status = false;
            }
        }
        return port_status;
    }
    
    /**
     * Methode : closeUartPort()
     */
    public void closeUartPort (){
        if (this.serial.isOpen()){
            this.serial.shutdown();
            System.out.println("Fermeture immédiat du port UART");
        }
        else{
            System.out.println("Le port UART est déjà fermé");
        }
    }
    
    /**
     * Methode : waitUartPort()
     * @param time_to_wait : is the time wich the Uart will wait
     * @throws java.lang.InterruptedException
     */
    public void waitUartPort (int time_to_wait) throws InterruptedException{
        synchronized(this){
            try {
                this.wait(time_to_wait);
            } catch(InterruptedException e) {
            }
        }
        synchronized(this) {
            this.notify();
        }
    } 
    
    /**
     * Methode : analyzeDataReceived => Traitement des données recu
     * @throws java.lang.InterruptedException
     */
    public void analyzeDataReceived() throws InterruptedException{
        //System.out.println("Data analyzeDataReceived = " + reception_buffer);
        switch (reception_buffer) {
            case BONJOUR:
                System.out.println("|BONJOUR| repondu de la part du SESAME DOORS");
                //System.out.println("Envoie de |ENREGISTREMENT_PROPRIETAIRE_AUTORISEE|");
                break;
                
            case ENREGISTREMENT_PROPRIETAIRE_AUTORISEE:
                System.out.println("|ENREGISTREMENT_PROPRIETAIRE_AUTORISEE| repondu de la part du SESAME DOORS");
                //System.out.println("Envoie de |ENREGISTREMENT_PROPRIETAIRE_AUTORISEE|");                
                break;
            
            case PROPRITAIRE_ENREGISTREE_CORRECTEMENT:
                System.out.println("|PROPRITAIRE_ENREGISTREE_CORRECTEMENT| repondu par le SESAME DOORS");
                //System.out.println("Envoie de la demande de rattachement");
                break;

            case RATTACHEMENT_AUTORISEE_PERIPHERIQUE:
                System.out.println("|RATTACHEMENT_AUTORISEE_PERIPHERIQUE| repondu par le SESAME DOORS");
                break;
                
            case BEGIN:
                Thread.sleep(100);
                System.out.println("Reception de BEGIN");
                flag_saving = true;
                // reset the buffer
                buffer = "";
                break;
                
            case END:
                Thread.sleep(100);
                System.out.println("Reception de END");
                flag_saving = false;
                this.checkBufferData();
                break;

            default:
                //System.out.println("Trame recu non identifiée dans la sequence de fonctionnement");
                break;
        }
    }

    /**
     * Methode extractBufferData() allows you to extract all the data saved in the serial buffer
     * @param string_buffer is the input of the methode which contains the received data
     * @return data_out[] is the data arranged on the array String
     */
    public static String [] extractBufferData(String string_buffer){
        System.out.println("<--- BEGIN OF THE extractBufferData() methode --->");
        
        String [] data_in = new String[100];
        String [] data_out;
        
        //Check the data saved in the buffer
        int buffer_size = string_buffer.length();
        System.out.println("Size of the buffer : " + buffer_size);
        System.out.println("Buffer = " + string_buffer);
       
        
        // Put the buffer contents in the char Array
        char[] charArray = string_buffer.toCharArray();
        String temp="";
        
        // Extract the first data which contains 7 charactere
        int count = 0;
        int size = 0;

        // Extract the first size of the first frame
        temp = String.valueOf(charArray[count]) + String.valueOf(charArray[count+1]);
        count = count + 2;
        System.out.println("Premier Temp = " + temp);
        int size_charactere = 0;
        try{
            size_charactere = Integer.parseInt(temp);
            System.out.println("Cast du premier caractere = " + size_charactere);
        }catch(NumberFormatException ex){
            size_charactere = 0;
            System.out.println("Error while trying to convert String to Integer. The data is : " + temp);
        }
        
        boolean flag_extraction = true;
        while ((count <= buffer_size-1) && flag_extraction){
            temp = "";
            for (int j=count; j<(count + size_charactere); j++){
                char char_ = charArray[j];
                temp = temp + String.valueOf(char_);
                //System.out.println("Temp = " + temp);
            }
            data_in[size] = temp;
            System.out.println("Data extracted["+size+"] = " + data_in[size]);
            size ++;
            
            // Check if you have got the end of the buffer to stop extracting
            if (buffer_size - (count+size_charactere) <=1){
                flag_extraction = false;
            }
            else{
                temp = String.valueOf(charArray[count + size_charactere]) + String.valueOf(charArray[count + size_charactere+1]);                
                
                System.out.println("Size futur data = " + temp);
                count = count + size_charactere + 2;
                try{
                    size_charactere = Integer.parseInt(temp);
                }catch(NumberFormatException ex){
                    size_charactere = 0;
                    System.out.println("Error while trying to convert String to Integer " + ex.getMessage());
                }
            }
            
        }
        
        int count_data = 0;
        while(data_in[count_data]!= null){
            count_data ++;
        }
        data_out = new String[count_data];
        for(int i=0;i<count_data;i++){
            data_out[i] = data_in[i];
            System.out.println("Data extraction methode["+i+"] = " + data_out[i]);
        }
        System.out.println("<--- END OF THE extractBufferData() methode --->");
        return data_out; 
    }
    
    /**
     * Methode : checkSavedData : Traitement des données recu et sauvegarder dans le buffer de reception
     * @param first_data
     * @param last_data
     * @param data
     * @return flag : if the data is saved correctly
     * @throws java.lang.InterruptedException
     */
    public boolean checkSavedData(String first_data, String last_data, String [] data) throws InterruptedException{
        System.out.println("<--- BEGIN OF THE checkSavedData() methode -->");
        boolean flag = false;
        
        if ((first_data != null && first_data.equals(DEBUT_ENVOIE_CONFIRMATION_RATTACHEMENT)) && 
            (last_data  != null  && last_data.equals(FIN_ENVOIE_CONFIRMATION_RATTACHEMENT))){
            System.out.println("First if");
            if(data != null){
                
                System.out.println("Before calling checkLinkingConfirmation()");
                flag = checkLinkingConfirmation(data);
                System.out.println("After calling checkLinkingConfirmation()");
                
                System.out.println("CheckSavedData() is called = " + flag);
                if(flag){
                    System.out.println("La confirmation de rattachement est bien recue");
                }
                else{
                    System.out.println("La confirmation de rattachement n'est pas recue");
                }
            }
            else{
                System.out.println("The data is not saved because it contains some invalid data");
                flag = false;
            }   
        }
        else{
            flag = false;
            System.out.println("First Else ");
        }
        System.out.println("<--- END OF THE checkSavedData() methode --->");
        return flag; 
    }
    
    
    /**
     * Methode checkBufferData() allows you to verify all the data saved in the buffer
     * @throws java.lang.InterruptedException
     */
    public void checkBufferData() throws InterruptedException{
        System.out.println("<--- BEGIN OF CALLING checkBufferData() methode --->");
        
        // Get the data saved in the buffer
        String [] data_in = SerialPortGPIO.extractBufferData(buffer);
        
        // Extract the first and last data to check the kind of request
        String first_data = data_in[0];
        String last_data  = data_in[data_in.length - 1];
        
        System.out.println("First = " + first_data);
        System.out.println("Last = " + last_data);
        
        // Extract only the data about the request
        String [] data_temp = new String[data_in.length -2];
        for (int i=0; i<data_temp.length; i++){
            data_temp[i] = data_in[i+1];
            System.out.println("Only the Data["+i+"] = " + data_temp[i]);
        }
        
        // Call the checkSavedData() methode to identify the kind of request
        boolean flag = checkSavedData(first_data, last_data, data_temp);
        
        System.out.println("<--- END OF CALLING checkBufferData() methode --->");
    }
    
    /**
     * Methode : checkLinkingConfirmation() allows you to check the confirmation sent by the Device to the Sesame
     * in a ser file. 
     * @param data
     * @return true if the owner information is correct
     * @throws java.lang.InterruptedException
     */
    public boolean checkLinkingConfirmation(String [] data) throws InterruptedException {
        boolean flag = false;
        String id_got_from_device = "";
        int formule; 
        CreateAccesKey all_key = null;
        File file = new File("all_key_saved.ser");

        if (data != null && data.length >0){
            
            id_got_from_device = data[0];
            formule = id_got_from_device.length()*128; 
            
            int formule_recu=0; 
            try{
                formule_recu = Integer.parseInt(data[1]);
            }catch(NumberFormatException ex){
                formule_recu = 0;
            }
            
            if (formule == formule_recu){
                System.out.println("CRC Correct");
                
                // Save the identifiant in the temporary variable
                this.identifiant = id_got_from_device;
                
                // Get a valued key from a table which containing all keys. 
                
                
                // Deserialization 
                try (FileInputStream fileIn = new FileInputStream(file); ObjectInputStream in = new ObjectInputStream(fileIn)) {
                    all_key = (CreateAccesKey) in.readObject();
                    key = all_key.getOneValideKey();
                    System.out.println("Identifiant = " + identifiant);
                    System.out.println("Key Gotten = " + key);
                    flag = true;
                    
                    // Make the Serialization before closing the windows
                    try{
                        try (FileOutputStream fileOut = new FileOutputStream(file); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                            out.writeObject(all_key);
                            System.out.println("all_key_saved.ser file is created correcly");
                            
                            // Arrange the data about the key
                            int key_size = key.length();
                            int checksum = 0;
                            String [] key_data = new String[4];
                            checksum  = (key_size*128) + ((int)(key_size/2))*64 + ((int)(key_size/4))*32; 
                            checksum += ((int)(key_size/8))*16 + ((int)(key_size/16))*8 + ((int)(key_size/32))*4;
                            checksum += ((int)(key_size/64))*2;
                            
                            key_data[0] = DEBUT_ENVOIE_KEY_LINK;
                            key_data[1] = key;
                            key_data[2] = Integer.toString(checksum);
                            key_data[3] = FIN_ENVOIE_KEY_LINK;
                            
                            // Envoie du tab
                            this.sendData(key_data);
                            
                        }
                    }catch(IOException i){
                        System.out.println("Exception de Serialisation : " + i.getMessage());
                    }

                }catch(IOException i){
                    flag = false;
                }catch(ClassNotFoundException c){
                   System.out.println("CreateAccesKey class not found : " + c.getMessage());
                   flag =false;
                }
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

        return flag;
    }
}