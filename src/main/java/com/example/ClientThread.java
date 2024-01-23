package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class ClientThread extends Thread{
    private Socket socket;
    private XmlMapper mapper = new XmlMapper();
    private ArrayList<Aula> aula = new ArrayList<Aula>();

    public ClientThread(Socket socket){
        this.socket = socket;
    }

    public String getStudenti(){
        String string = "";
        for(Aula a : aula){
            string += a.getNumero() + " " + a.getSezione() + " " + a.getAula() + ":\n";
            for(Studente s : a.getStudente()){
                string += s.getNome() + "\t\t" + s.getCognome() + "\t\t"+ s.getNascita()+ "\n";
            }
        }
        return string;
    }

    @Override
    public void run(){
        String input;
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            do{
                input = in.readLine();
                if(input == null || input.isEmpty()){
                    socket.close();
                    break;
                }else{
                    System.out.println("\n" + input);
                    Aula a = mapper.readValue(input, Aula.class);
                    aula.add(a);
                    System.out.println(getStudenti());
                }
            }while(true);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
