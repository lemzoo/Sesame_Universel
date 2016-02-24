//SESAME SHARER

package SesameApp;

import AccesGUI.*;
import HomeGUI.*;
import LinkingGUI.*;
import SharingGUI.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.pi4j.io.serial.*;
import java.io.*;

/**
 * This example code demonstrates how to perform serial communications using the Raspberry Pi.
 * 
 * @author Lamine BA by editing Robert Savage source code for the Serial Port Communication
 */
public class SerialPortGPIO implements ConstantsConfiguration{
    
    private String reception_buffer;
    private boolean flag_saving = false;
    private int baudrate = 0;
    private Serial serial;
    UARTListener uart_listener = null;
    private StringBuffer buffer;
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
        //reception_buffer = "raspberry";
    }
    
    /**
     * Methode : initialize() allows you to initialize the uart port. 
     */
    private void initialize() {
        System.out.println("Initialisation du port de communication serie");
        // create an instance of the serial communications class
        serial = SerialFactory.createInstance();
        
        // open the default serial port provided on the GPIO header
        serial.open(Serial.DEFAULT_COM_PORT, this.baudrate);

        // create and register the serial data listener
        uart_listener = new UARTListener(this);
        serial.addListener(uart_listener);  
        
        buffer = new StringBuffer("");
    }

    /**
     * Methode : setDataBufferReception(String data)
     * @param data
     */
    public void setLastReceivedData (String data){
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
     * Methode : setBufferReception(String data)
     * @param data
     */
    public void setBufferReception (String data){
        int len = buffer.length();
        buffer.insert(len, data);
        
        //this.buffer = data;
    }
    
    /**
     * Methode : resetBufferReception(String data)
     */
    public void resetBufferReception (){
        int len = buffer.length();
        buffer.delete(0, len);        
    }
    
    /**
     * Methode getBufferReception allow you to get the contains of the uart buffer
     * @return buffer 
     */
    public String getBufferReception (){
        //return buffer;
        return buffer.toString();
    }

    /**
     * Methode getSerial()
     * @return serial port 
     */
    public Serial getSerial(){
        return this.serial;
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
     * Methode setBufferData() allows you to write the received data in the buffer which you want
     * @param data_received : is the data which received by the serial port
     */

    public void setBufferData(String data_received){
        buffer.append(data_received);
    }
    
    
    /**
     * Methode sendData allow you to send data via the uart
     * @param data_to_send
     * @throws java.lang.InterruptedException
     */
    public void sendData (String data_to_send) throws InterruptedException{
        try {
            // Call methode sampleDataToSend() to arrange the data to send by package of 10 char maxi.
            String [] data_temp = sampleDataToSend(data_to_send);
            
            for(int j=0;j<data_temp.length;j++){
                Thread.sleep(200);
                System.out.println("Data sent = [" + data_temp[j] + "]");
                serial.writeln(data_temp[j]);
            }
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
            sendData(data_to_send[i]);
            i++;
        }        
    }
    /**
     * Methode : formatData() allows you to arrange data to the correct format for sending
     * @param data : is the data arranged on the String Array
     * @return data_out : data arranged on the correct format for sending
     */
    private String [] formatData(String [] data) {
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
    private static String [] sampleDataToSend(String data_to_send){
        String data_to_send_in = data_to_send;
        String [] data_sampled_temp = new String[20];
        String [] data_sampled_out;
        
        boolean flag_data = false; 
        String data_to_send_temp = "";
        
        int size_original = data_to_send_in.length();
        char[] charArray = data_to_send_in.toCharArray();
        //System.out.println("Char = " + Arrays.toString(charArray));
        int size_rest = 0; 
        int number_char_to_delete = 10;
        int count = 0;
        
        if (size_original >10){
            //System.out.println("Size of the buffer is superior to 10");
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
                    data_to_send_temp += String.valueOf(charArray[i]); 
                }
                //System.out.println("Data extracted = " + data_to_send_temp);
                data_sampled_temp[count] = data_to_send_temp;
                count ++;
                        
                // delete the character that is extracted
                StringBuilder sb = new StringBuilder();
                sb.append(data_to_send_in);
                //System.out.println("StringBuilder = " + sb.toString());
                //System.out.println("Buffer before = " + data_to_send);
                sb.delete(0, size_rest);
                data_to_send_in = sb.toString();
                //System.out.println("Buffer after = " + data_to_send);
                charArray = data_to_send_in.toCharArray();

                if (size_original == size_rest){
                    flag_data = false;
                }
                else{
                    size_original = data_to_send_in.length();
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
            //System.out.println("Data extraction methode["+i+"] = " + data_sampled_out[i]);
        }
        return data_sampled_out; 
    }
    
    /**
     * Methode extractBufferData() allows you to extract all the data saved in the serial buffer
     * @param string_buffer is the input of the methode which contains the received data
     * @return data_out[] is the data arranged on the array String
     */
    public static String [] extractBufferData(String string_buffer){
        System.out.println("<--- BEGIN OF THE extractBufferData() methode --->");
        
        String str_arg_clean = string_buffer.replace("\n", "").replace("\r", "");
        
        StringBuffer str_arg = new StringBuffer(str_arg_clean);
        
        String [] data_in = new String[100];
        String [] data_out;
        
        System.out.println("Buffer = " + str_arg);
        
        boolean flag_buffer_content = str_arg.length() > 0;
        
        //Check the data saved in the buffer
        int buffer_size = 0;       
        
        // Put the buffer contents in the char Array
        char[] charArray = null;
        StringBuffer temp = new StringBuffer("");
        int len_temp = 0;
        String str_default = null;
        
        if (flag_buffer_content){
            buffer_size = str_arg.length(); 
            str_default = str_arg.toString();
            charArray = str_default.toCharArray();
        }
        else{
            System.out.println("Le buffer est vide");
            str_default = "02AB02CD06EFGHIJ";
            buffer_size = str_default.length();
            charArray = str_default.toCharArray();
        }
        
        // Extract the first data which contains 7 charactere
        int count = 0;
        int size = 0;

        // Extract the first size of the first frame
        temp.insert(0, String.valueOf(charArray[count]));
        len_temp = temp.length();
        temp.insert(len_temp, String.valueOf(charArray[count+1])); //temp = String.valueOf(charArray[count]) + String.valueOf(charArray[count+1]);
        count = count + 2;
        
        int size_charactere = 0;
        
        try{
            size_charactere = Integer.parseInt(temp.toString());
        }catch(NumberFormatException ex){
            size_charactere = 0;
            System.out.println("Error while trying to convert String to Integer. The data is : " + temp);
        }
        
        boolean flag_extraction = true;
        while ((count <= buffer_size-1) && flag_extraction){
            len_temp = temp.length();
            temp.delete(0, len_temp);
            //temp = "";
            
            for (int j=count; j<(count + size_charactere); j++){
                char char_ = charArray[j];
                len_temp = temp.length();
                temp.insert(len_temp, String.valueOf(char_));
                //temp = temp + String.valueOf(char_);
            }
            data_in[size] = temp.toString();
            size ++;
            
            // Check if you have got the end of the buffer to stop extracting
            if (buffer_size - (count+size_charactere) <=1){
                flag_extraction = false;
            }
            else{
                len_temp = temp.length();
                temp.delete(0, len_temp);
                
                temp.insert(0, String.valueOf(charArray[count + size_charactere]));
                len_temp = temp.length();
                temp.insert(len_temp, String.valueOf(charArray[count + size_charactere+1]));
                //temp = String.valueOf(charArray[count + size_charactere]) + String.valueOf(charArray[count + size_charactere+1]);                
                
                count = count + size_charactere + 2;
                try{
                    size_charactere = Integer.parseInt(temp.toString());
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
        System.arraycopy(data_in, 0, data_out, 0, count_data);
        
        System.out.println("<--- END OF THE extractBufferData() methode --->");
        
        return data_out; 
    }
    
    /**
     * Methode : openUartPort() allow you to open the uart port if this one is closed 
     */
    public void openUartPort (){
        if (this.serial.isOpen()){
            System.out.println("Le port est déjà ouvert");
            serial.addListener(uart_listener);
        }
        else{
            try {
                // Re-open the UART port
                serial.open(Serial.DEFAULT_COM_PORT, this.baudrate);
                serial.addListener(uart_listener);
            }
            catch(IllegalStateException ex){
                System.err.println("Impossible to re-open the uart port");
            }
            
            if (this.serial.isOpen()){
                System.out.println("Le port est déjà ouvert");
            }
            else{
                System.err.println("Impossible d'ouvrir le port");
            }
        }
    }
    
    /**
     * Methode : closeUartPort() allow you to close the uart port is this one is already open
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
     * @param received_data
     * @throws java.lang.InterruptedException
     */
    public void analyzeDataReceived(String received_data) throws InterruptedException{
        reception_buffer = received_data;
        switch (received_data) {
            case BONJOUR:
            Thread.sleep(50);
                System.out.println("|BONJOUR| repondu de la part du SESAME DOORS");
                break;
                
            case BEGIN:
                Thread.sleep(50);
                System.out.println("Reception de BEGIN");
                // reset the buffer
                resetBufferReception();
                flag_saving = true;
                break;
                
            case END:
                Thread.sleep(50);
                System.out.println("Reception de END");
                flag_saving = false;
                this.checkBufferData();
                break;
                
            case KEY_LINK_ENREGISTREE_CORRECTEMENT:
                Thread.sleep(50);
                System.out.println("KEY_LINK_ENREGISTREE_CORRECTEMENT recu");
                boolean flag_end_link = saveAndCloseTheLink();
                System.out.println("Status du rattachement = " + flag_end_link);
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
     * Methode : checkSavedData : Traitement des données recu et sauvegarder dans le buffer de reception
     * @param first_data
     * @param last_data
     * @param data
     * @return flag : if the data is saved correctly
     * @throws java.lang.InterruptedException
     */
    private boolean checkSavedData(String first_data, String last_data, String [] data) throws InterruptedException{
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
     * Methode checkReceivedData()
     * @param data : is the array String which contains the received data by the serial port
     * @return status=true if the owner information is received correctly. 
     */
    private boolean checkReceivedData (String [] data){
        boolean status = true;
        if (data.length>0){
            for (int i=0; i<data.length; i++) {
                status = status && data[i] != null && !data[i].isEmpty();
            }
        } 
        else{
            status = false;
        }
        
        return status;
    }

    /**
     * Methode isChecksumCorrect()
     * @param data
     * @return status=true if the checksum is correct. 
     * @throws java.lang.InterruptedException 
     */
    public boolean isChecksumCorrect (String [] data) throws InterruptedException{
        boolean status = false;
        int formule = 0;
        int CRC = 0;
                
        if (checkReceivedData(data) && data.length>0){
            int number_element = data.length-1;
            System.out.println("Taille de data = " + number_element);
            for (int i=0; i<number_element; i++){
                formule = formule + (number_element -i)*data[i].length();
            }
            
            int checksum = formule - number_element*128;
            System.out.println("Resultat du checksum = " + checksum);
            
            try{
                CRC = Integer.parseInt(data[number_element]);
                System.out.println("Checksum conversion to integer done successfully");
            }
            catch(NumberFormatException ex){
                System.out.println("Le checksum recu n'est un pas un entier" + ex.getMessage());
                CRC = 0;
            }
            
            if ((checksum - CRC)==0){
                System.out.println("CRC Correct");
                status = true;
            }
            else{
                System.out.println("CRC wrong");
                status = false;
            }
        }
        else{
            status = false;
        }
        return status;
    }

    
    /**
     * Methode checkBufferData() allows you to verify all the data saved in the buffer
     * @throws java.lang.InterruptedException
     */
    private void checkBufferData() throws InterruptedException{
        System.out.println("<--- BEGIN OF CALLING checkBufferData() methode --->");
        
        // Get the data saved in the buffer
        String [] data_in = SerialPortGPIO.extractBufferData(buffer.toString());
        
        // reset the buffer
        resetBufferReception();
                
        // Extract the first and last data to check the kind of request
        String first_data = data_in[0];
        String last_data  = data_in[data_in.length - 1];
        
        System.out.println("First = " + first_data);
        System.out.println("Last  = " + last_data);
        
        // Extract only the data about the request
        String [] data_temp = new String[data_in.length -2];
        for (int i=0; i<data_temp.length; i++){
            data_temp[i] = data_in[i+1];
            System.out.println("Valide data["+i+"] = " + data_temp[i]);
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
    private boolean checkLinkingConfirmation(String [] data) throws InterruptedException {
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

        // Check if the received id is correct and then send the key
        if (flag){
            // Get a valued key from a table which containing all keys. 
            // Deserialization 
            try (FileInputStream fileIn = new FileInputStream(file); ObjectInputStream in = new ObjectInputStream(fileIn)) {
                all_key = (CreateAccesKey) in.readObject();
                key = all_key.getOneValideKey();
                System.out.println("Identifiant = " + identifiant);
                System.out.println("Key Gotten = " + key);
                
                // Make the Serialization before closing the windows
                try{
                    try (FileOutputStream fileOut = new FileOutputStream(file); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                        out.writeObject(all_key);
                        System.out.println("all_key_saved.ser file is created correctly");
                        
                        // Arrange the data about the key and calculate the checksum value
                        int key_size = key.length();
                        int checksum = 0;
                        String [] key_data = new String[4];
                        checksum  = (key_size*128) + (key_size/2)*64 + (key_size/4)*32; 
                        checksum += (key_size/8)*16 + (key_size/16)*8 + (key_size/32)*4;
                        checksum += (key_size/64)*2;
                        
                        // Arrange the key data in a tab for sending
                        key_data[0] = DEBUT_ENVOIE_KEY_LINK;
                        key_data[1] = key;
                        key_data[2] = Integer.toString(checksum);
                        key_data[3] = FIN_ENVOIE_KEY_LINK;
                        

                        // Send the Key tab to the device
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
            System.out.println("The received id is not correct ");
        }

        return flag;
    }
    
    /**
     * Methode : saveAndCloseTheLink() allow you to finish the linking procedure between the SESAME and Device
     * @return true if the linking procedure is succesfully ran or false if else where
     * @throws java.lang.InterruptedException
     */
    private boolean saveAndCloseTheLink() throws InterruptedException{
        System.out.println("<--- BEGIN : saveAndCloseTheLink --->");
        
        boolean flag_extract_owner = false;
        boolean flag_extract_device_linking = false; 
        
        boolean flag_creating = false;
        boolean flag_adding_table = false;
        boolean flag_end = false;
        
        OwnerInformation owner = null;
        DeviceLinkingData device_linking = null;
        DeviceLinkedData device_linked = null;
        
        IdentifiantAndKeyTable table_temp = null;
        
        String new_identifiant = identifiant;
        String new_key = key;
        
        // Make the Deserialization of owner_information.ser
        File file = new File("owner_information.ser");
        try (FileInputStream fileIn = new FileInputStream(file); ObjectInputStream in = new ObjectInputStream(fileIn)) {
            owner = (OwnerInformation) in.readObject();
            flag_extract_owner = true;             
        }catch(IOException i){
            flag_extract_owner = false;
            System.out.println("IOException : " + i.getMessage());
        }catch(ClassNotFoundException c){
            flag_extract_owner = false;
            System.out.println("OwnerInformation class not found");
        }
        

        // Make the Deserialization of linking_data.ser
        file = new File("device_to_link.ser");
        try (FileInputStream fileIn = new FileInputStream(file); ObjectInputStream in = new ObjectInputStream(fileIn)) {
            device_linking = (DeviceLinkingData) in.readObject();
            flag_extract_device_linking = true;             
        }catch(IOException i){
            flag_extract_device_linking = false;
            System.out.println("IOException : " + i.getMessage());
        }catch(ClassNotFoundException c){
            flag_extract_device_linking = false;
            System.out.println("DeviceLinkingData class not found");
        }
        
        // Check if the two files are extracted correctly, then continue the process
        if (flag_extract_owner && flag_extract_device_linking){
            // Create a new object containing device_info,id and key
            device_linked = new DeviceLinkedData(owner, device_linking, new_identifiant, new_key);
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
                table_temp.addDeviceForLink(device_linked);
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
}