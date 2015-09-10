package ServidorDeEcoVersao2;

import java.io.*;  
import java.net.*;

public class ServidorDeEco extends Thread{  
    public static void main(String args[]) 
    {        
        try { 
            //meu ip: 192.168.137.26
            ServerSocket s = new ServerSocket(7000);
  
            while (true) 
            {
                System.out.print("AGUARDANDO CONEXAO");
                Socket conexao = s.accept();
                System.out.println("\nCliente Conectado");
                
                Connection c = new Connection(conexao);
            }
        }
        catch (IOException e) 
        {  
            System.out.println("IOException: " + e);
        }
    }
}