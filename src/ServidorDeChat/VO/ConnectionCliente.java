/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorDeChat.VO;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lennonalves
 */

public class ConnectionCliente extends Thread {

    DatagramSocket s = null;
    int aux;
    
    public ConnectionCliente(DatagramSocket conexao) {
        this.s = conexao;
    }
    
    @Override
    public void run()
    {
        while(true) {
            byte[] buffer = new byte[256];
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            try {
//                System.out.println("try");
                s.receive(request);
            } catch (IOException ex) {
//                System.out.println("catch");
                Logger.getLogger(ConnectionCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
//            System.out.println("fora");
            String m = new String(request.getData()); 
            System.out.println(m);
            aux = m.lastIndexOf("#");
            ClientesVO.getInstancia().setAparecerNaTela("CHAT:\t" + m.substring(aux+1).trim());
        }
    }
}
