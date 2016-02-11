// SESAME

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

import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UARTListener implements SerialDataListener, ConstantsConfiguration {
	
    SerialPortGPIO uart;
    private String received_data;

    public UARTListener(SerialPortGPIO uart){
        this.uart = uart;
        //received_data = "Raspberry";
    }

    @Override
    public void dataReceived(SerialDataEvent event) {

        if (event.getData() != null && !event.getData().isEmpty()){ 
            received_data = event.getData();
            char[] charArray = received_data.toCharArray();
            int size = received_data.length();
            received_data = String.valueOf(charArray[0]);
            for (int i=1; i<size - 2; i++){
                received_data = received_data + String.valueOf(charArray[i]); 
                received_data = received_data.replace("\n", "").replace("\r", "");
            }
            System.out.println("Received Data = [" + received_data + "]");
            
            /* 
             * Check if the rattachement flag is true. if the flag is true, copy the RX Data on the table
             * to save the information about the Owner of the Sesame. 
             */

            uart.setLastReceivedData(received_data);
            try {
                uart.analyzeDataReceived();
            } catch (InterruptedException ex) {
                Logger.getLogger(UARTListener.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Start to write the Owner information in the buffer which allocated
            if (uart.getSavingFlag()){
                if(received_data == null ? BEGIN != null : !received_data.equals(BEGIN)){
                    uart.setBufferData(received_data);
                }
                else if (received_data == null ? END != null : received_data.equals(END)){
                    uart.setSavingFlag(false);
                }
                else{
                    // nothing
                }
            }
            else{
                /*System.out.println("Else de l'UART SESAME DOORS and received data = [" + received_data + "]");
                uart.setDataBufferReception(received_data);
                try {
                    uart.analyzeDataReceived();
                } catch (InterruptedException ex) {
                    Logger.getLogger(UARTListener.class.getName()).log(Level.SEVERE, null, ex);
                }*/
            }
        }
        else{
            System.out.println("No data available on the GPIO Buffer");    
        }
    }	
}