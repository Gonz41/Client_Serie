package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            Socket s = new Socket("localhost", 3000);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            String line = in.readLine();
            String[] array = line.split(",");
            int i=0;
            do{
                System.out.println(array[i]);
                i++;
            }while(array[i]!=null);  
        
            s.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
