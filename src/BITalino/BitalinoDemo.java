package BITalino;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.bluetooth.RemoteDevice;


import java.util.logging.Level;
import java.util.logging.Logger;

public class BitalinoDemo {

    public static Frame[] frame;

    public static List <Integer> arrayECG = new ArrayList <Integer>();
    
    public static List<Integer> main() {

        BITalino bitalino = null;
        try {
            bitalino = new BITalino();
            // find devices
            //Only works on some OS
            Vector<RemoteDevice> devices = bitalino.findDevices();
            System.out.println(devices);

            //You need TO CHANGE THE MAC ADDRESS
            String macAddress = "98:D3:C1:FD:2F:EC";
            int SamplingRate = 10;
            bitalino.open(macAddress, SamplingRate);

            // start acquisition on analog channels A2 and A6
            //If you want A1, A3 and A4 you should use {0,2,3}
            int[] channelsToAcquire = {1}; //A1 --> EMG, A2-->ECG  
            bitalino.start(channelsToAcquire);
            

            String data=null;
            //List <Integer> arrayEMG = new ArrayList <Integer>();
            //ArList <Objeto> arrayEMG_rec;
            
            //List <Integer> arrayECG = new ArrayList <Integer>();
            //ArrayList <Objeto> arrayECG_rec;
            
            
            //read 10000 samples
            for (int j = 0; j < 10; j++) {

                //Read a block of 100 samples 
                frame = bitalino.read(10);

                System.out.println("size block: " + frame.length);

                //Print the samples

                for (int i = 0; i < frame.length; i++) {
                   
                    arrayECG.add(frame[i].analog[1]);
                    System.out.println(frame[i].analog[1]);
                    
                    

                    
                }
            }
                       
            
            //stop acquisition
            bitalino.stop();
            
            
        } catch (BITalinoException ex) {
            Logger.getLogger(BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Throwable ex) {
            Logger.getLogger(BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                //close bluetooth connection
                if (bitalino != null) {
                    bitalino.close();
                }
            } catch (BITalinoException ex) {
                Logger.getLogger(BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }return arrayECG;
        

    }

    
    

}
