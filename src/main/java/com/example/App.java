package com.example;

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

            ClientThread thread = new ClientThread(s);
            thread.start();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
