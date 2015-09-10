package ServidorDeEcoVersao2;

import java.io.*;  
import java.net.*;  
  
public class ClienteDeEco {  
  
    public static void main(String args[]) {  
    try {  
        Socket conexao = new Socket("localhost", 7000);
        
        BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));  
        PrintStream saida = new PrintStream(conexao.getOutputStream());  
        String mensagem;
        
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));  
        
        while (true) 
        {
            System.out.print("> ");
            mensagem = teclado.readLine();
            
            saida.println(mensagem);
            mensagem = entrada.readLine();  
            
            if (mensagem == null) 
            {  
                System.out.println("\nConexão encerrada!");
                break;  
            }  
            
            System.out.println(mensagem);             
        }
    } catch (IOException e)
    {  
        System.out.println("IOException: " + e);  
    }
    }  
}