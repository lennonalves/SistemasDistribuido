package ServidorDeEcoVersao1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

   public static void main(String[] args) throws IOException {

       String mensagem;
       String mensagemModificada;
       
       int flag = -1;
       
       try {
           
           // Socket
            Socket cliente = new Socket("localhost", 40000);
           
            while (flag != 0)
            {
           
            // Buffer com entrada do usuario
            BufferedReader bufferUsuario = new BufferedReader(new InputStreamReader(System.in));
    
            // Stream para envio de informações
            DataOutputStream paraServidor = new DataOutputStream(cliente.getOutputStream());
   
            // Buffer com informações retornadas do servidor
            BufferedReader bufferServidor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
    
            // Armazena buffer de entrada
            mensagem = bufferUsuario.readLine();
            System.out.println("Cliente: " + mensagem);
    
            // Stream de saída
            paraServidor.writeBytes(mensagem + "\n");
    
            // Armazena retorno do servidor
            mensagemModificada = bufferServidor.readLine();
    
            // Imprime mensagem recebida
            System.out.println("Servidor: " + mensagemModificada);
            
            // Loop
            System.out.println("Deseja continuar ? [1] Sim [0] Nao");
            flag = Integer.parseInt(bufferUsuario.readLine());
            
            }
            
            // Fecha o socket
            cliente.close();

       } catch (UnknownHostException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
   }
}