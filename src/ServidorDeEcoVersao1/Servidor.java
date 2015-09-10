package ServidorDeEcoVersao1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

   public static void main(String[] args) {

       String mensagemCliente;
       String mensagemModificada;
       
       //String flag;
  
       try {
 
           // Cria um SocketServer
           ServerSocket socket = new ServerSocket(40000);
           
           //while(flag != 0)
           //{
           
           // Abre a porta e aguarda conexão de um cliente
           Socket connectionSocket = socket.accept();
           System.out.println("Conectado com: " + connectionSocket.getInetAddress().getHostAddress());
           
           
           while(true) {    
    
               // Buffer com informações do cliente
               BufferedReader doCliente = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
    
               // Stream para retorno de informações
               DataOutputStream paraCliente = new DataOutputStream(connectionSocket.getOutputStream());
                   
               // Armazena conteúdo recebido
               mensagemCliente = doCliente.readLine();
    
               // Modifica a mensagem
               mensagemModificada = mensagemCliente.toUpperCase() + "\n";
    
               // Imprime a mensagem modificada
               System.out.println(mensagemModificada);
    
               // Retorna a mensagem modificada
               paraCliente.writeBytes(mensagemModificada);    
               
           }
           
           //flag = mensagemCliente;
           
           //}
      
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }  
   }
}