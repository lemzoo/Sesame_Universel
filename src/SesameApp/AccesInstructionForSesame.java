/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SesameApp;

import static SesameApp.ConstantsConfiguration.BONJOUR;

/**
 * Class : AccesInstructionForSesame () which manage all instruction about the acces
 * @author LamineBA
 */
public class AccesInstructionForSesame implements ConstantsConfiguration{
   
    private String id_sesame = "";
    private String key_acces = "";
    SerialPortGPIO port = null;
    
    /**
     * Consructor AccesInstructionForSesame() 
     * @param id_sesame_arg
     * @param key_acces_arg 
     */
    public AccesInstructionForSesame (SerialPortGPIO com_port, String id_sesame_arg, String key_acces_arg){
       this.id_sesame = id_sesame_arg;
       this.key_acces = key_acces_arg;
       port = com_port;
       //port = new SerialPortGPIO(115200);
    }
    
    public void sendAccesData(String acces_request) throws InterruptedException{
        boolean first_flag = false;
        boolean second_flag = false;
        
        String [] frame_acces = new String[6];
        // Check the answer of the device in the while loop
        boolean flag = true;
        int tempo = 0;
        
        // SEND "BONJOUR" and wait the answer of the device. The expected answer is "BONJOUR"  
        try {
            port.sendData(BONJOUR);
        }catch(InterruptedException ex){
            System.out.println("Impossible to the send the following message : BONJOUR " + ex.getMessage());
        }
        
        while (flag){
            if (tempo>1000){
                flag=false;
                System.out.println("Tempo d'attente de la réponse du périphérique a expiré");
                tempo = 0;
            }
            else{
                flag = (port.getLastReceivedData()!=null && !port.getLastReceivedData().equals(BONJOUR));
                if (!flag){
                    System.out.println("|BONJOUR| sur PeripheriqueSummaryInformation");
                }
                tempo = tempo + 10;
                Thread.sleep(10);
            }
        }

        // Wait 10 secondes
        tempo = 0;
        int seconde = 1000;
        while (tempo<5*seconde){
            
            //System.out.println("Tempo attente |BONJOUR| = " + tempo);
            tempo = tempo + seconde;
            Thread.sleep(seconde);
        }
        
        // SEND "DEMANDE_ETABLISSEMENT_CONNEXION_ACCES" and wait the answer of the device. 
        // The expected answer is "ETABLISSEMENT_CONNEXION_ACCES_ETABLIE"  
        try {
            // Check the answer of the device in the while loop 
            flag = true;
            tempo = 0;
            port.sendData(DEMANDE_ETABLISSEMENT_CONNEXION_ACCES);
        }catch(InterruptedException ex){
            System.out.println("Impossible to the send the following message : DEMANDE_ETABLISSEMENT_CONNEXION_ACCES " + ex.getMessage());
        }
        
        while (flag){
            if (tempo>1000){
                flag=false;
                System.out.println("Tempo d'attente de la réponse du périphérique a expiré");
                tempo = 0;
            }
            else{
                flag = (port.getLastReceivedData()!=null && !port.getLastReceivedData().equals(ETABLISSEMENT_CONNEXION_ACCES_ETABLIE));
                if (!flag){
                    System.out.println("|ETABLISSEMENT_CONNEXION_ACCES_ETABLIE| sur AccesInstructionForSesame");
                    first_flag = true;
                }
                tempo = tempo + 10;
                Thread.sleep(10);
            }
        }
        first_flag = true;
        // Check if the DEVICE is on line by checking it's last response
        if (first_flag){
            
            // calcul the checksum
            int checksum = id_sesame.length()*1024 + key_acces.length()*512 + acces_request.length()*256;
            
            // Arrange information
            frame_acces[0] = DEBUT_ENVOIE_DONNEES_ACCES;
            frame_acces[1] = id_sesame;
            frame_acces[2] = key_acces;
            frame_acces[3] = acces_request;
            frame_acces[4] = Integer.toString(checksum);
            frame_acces[5] = FIN_ENVOIE_DONNEES_ACCES;
            
            // Send Data
            /*port.sendData(BEGIN);
            Thread.sleep(200);
            // Send Data
            //port.sendData(BEGIN);
            Thread.sleep(200);
            // Send Data
            //port.sendData(BEGIN);
            Thread.sleep(200);*/

            
            // Check the answer of the device in the while loop 
            flag = true;
            tempo = 0;      
            port.sendData(frame_acces);
        }
        else{
            // Nothing
        }
        
        while (flag){
            if (tempo>5000){
                flag=false;
                System.out.println("Tempo d'attente de la réponse du périphérique a expiré");
                tempo = 0;
            }
            else{
                flag = (port.getLastReceivedData()!=null && !port.getLastReceivedData().equals(DEMANDE_ACCES_AUTORISEE));
                if (!flag){
                    System.out.println("|DEMANDE_ACCES_AUTORISEE| sur AccesInstructionForSesame");
                    second_flag = true;
                }
                tempo = tempo + 10;
                Thread.sleep(10);
            }
        }
        
        if (first_flag && second_flag){
            System.out.println("Status acces '" +acces_request+ "' = " + second_flag );
        }
        
    }
    
    public void updataAcces(){
        
    }
    
    public void checkStatusAcces(){
        
    }
    
    /**
     * Main methode
     * @param args 
     */
    public static void main(String[] args) {
        
    }
    
}


