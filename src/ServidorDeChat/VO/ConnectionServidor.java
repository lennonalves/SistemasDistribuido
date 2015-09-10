/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorDeChat.VO;

import ServidorDeChat.RN.ClientesRN;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lennonalves
 */

public class ConnectionServidor extends Thread {
    
    @Override
    public void run()
    {
        ClientesVO cvo = ClientesVO.getInstancia();
        ClientesRN crn = ClientesRN.getInstancia();
        
        DatagramSocket s = null;
        try {
            s = new DatagramSocket(Integer.parseInt(cvo.getHostPort()));
        } catch (SocketException ex) {
            Logger.getLogger(ConnectionServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(true) {
                    
            byte[] buffer = new byte[256];
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            try {
                s.receive(request);
            } catch (IOException ex) {
                Logger.getLogger(ConnectionServidor.class.getName()).log(Level.SEVERE, null, ex);
            }

            String m = new String(request.getData());
            InetAddress endereco = request.getAddress();

            //cvo.setHostId(null); cvo.setHostName(null); cvo.setHostAddress(null);

            if (m.substring(0, 1).equals("1")) 
            {
                cvo.setHostId(m.substring(0, 2).trim());
                cvo.setHostName(m.substring(2).trim());
                cvo.setHostAddress(endereco.getHostAddress());
                cvo.setHostPort(Integer.toString(request.getPort()));
                System.out.println(crn.adicionaCliente(cvo));
                System.out.println(crn.listarClientes(cvo));
                
            }

            if (m.substring(0, 1).equals("5")) 
            { 
                cvo.setHostId(m.substring(0, 2).trim());
                cvo.setHostName(m.substring(2).trim());
                cvo.setHostAddress(endereco.getHostAddress());
                System.out.println(crn.removeCliente(cvo));
                System.out.println(crn.listarClientes(cvo));
            }
            
            if (m.substring(0, 1).equals("3")) 
            { //ip //porta //mensagem
                int auxIni, auxFim;
                cvo.setHostId(m.substring(0, 2).trim());
                cvo.setHostAddress(endereco.getHostAddress());
                auxIni = m.indexOf('#', 2);
                auxFim = m.lastIndexOf('#');
                cvo.setPortaDestino(m.substring(auxIni+1, auxFim));
                cvo.setMensagem(m.substring(auxFim+1));
                auxIni = m.indexOf('#');
                auxFim = m.indexOf('#', 2);
                cvo.setIpDestino(m.substring(auxIni+1, auxFim));
                
                System.out.println(m);
                
                if(cvo.getPortaDestino().equals("99999") && cvo.getIpDestino().equals("999.999.999.999"))
                {
                    try {
                        //                    enviar mensagem para todos os clientes (datagrama 4)
                        crn.broadcast(cvo);
                    } catch (IOException ex) {
                        Logger.getLogger(ConnectionServidor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else
                {
                    DatagramSocket conexao = null;

                    try 
                    { //ipcliente //portacliente //mensagem
//                                  ip origem                   porta origem                mensagem para destino
                        String mensagem = "4#" + cvo.getHostAddress() + "#" + cvo.getHostPort() + "#" + cvo.getMensagem();

                        conexao = new DatagramSocket();
                        byte[] mc = mensagem.getBytes();

                        InetAddress aHost = InetAddress.getByName(cvo.getIpDestino());
                        int serverPort = Integer.parseInt(cvo.getPortaDestino());

                        request = new DatagramPacket(mc, mc.length, aHost, serverPort);
                        conexao.send(request);

                    } catch (IOException e)
                    {  
                        System.out.println("IOException: " + e);
                    }
                }
            }
        }
    }
}
