/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emprestimos.VO;

import Emprestimos.RN.ConsultaRN;
import Emprestimos.RN.EmprestimoRN;
import Emprestimos.RN.ItemRN;
import Emprestimos.RN.LoginRN;
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
    
    static Integer portaCliente = 0;
    static String enderecoCliente = "lennonevictor";
    
    @Override
    public void run()
    {
        ServidorVO svo = ServidorVO.getInstancia();
        int id = 0;
        
        DatagramSocket s = null;
        try {
            s = new DatagramSocket(Integer.parseInt(svo.getPortaServidor()));
        } catch (SocketException ex) {
            Logger.getLogger(ConnectionServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DatagramSocket conexao = null;
        try {
            conexao = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(ConnectionServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(true) {
            
            byte[] buffer = new byte[2000];
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            
            try {
                s.receive(request);
            } catch (IOException ex) {
                Logger.getLogger(ConnectionServidor.class.getName()).log(Level.SEVERE, null, ex);
            }

            String m = new String(request.getData());
            System.out.println("RECEBEU: " + m);
            InetAddress endereco = request.getAddress();
            
            if (portaCliente == 0)
                portaCliente = request.getPort();
            
            
            if (enderecoCliente.trim().equals("lennonevictor"))
                enderecoCliente = request.getAddress().toString().substring(1).trim();
            
            LoginVO lvo = LoginVO.getInstancia();
            ItemVO ivo = ItemVO.getInstancia();
            
            String nomeUsuario;
            
            if (m.substring(0, 4).equals("1_1_")) { /* realizar login */
                
                String[] corte = m.split("_");
                
                lvo.setUser(corte[2].trim());
                lvo.setSenha(corte[3].trim());
                
                LoginRN lrn = LoginRN.getInstancia();

                if (lrn.autenticaLogin(lvo) == true)
                    retornaCliente("3_1_1", conexao, lvo);
                else
                    retornaCliente("3_1_2", conexao, lvo);
                
            }
            
            if (m.substring(0, 4).equals("1_2_")) /* realizar logout */
                retornaCliente("3_2_1", conexao, lvo);
            
            if (m.substring(0, 4).equals("1_3_")) { /* cadastrar item */
                
                String[] corte = m.split("_");
                
                ivo.setItemNome(corte[2].trim());
                ivo.setItemDesc(corte[3].trim());
                ivo.setItemTipo(corte[4].trim());
                
                ivo.setDiaInicial(corte[5].trim());
                ivo.setMesInicial(corte[6].trim());
                ivo.setAnoInicial(corte[7].trim());
                
                ivo.setDiaFinal(corte[8].trim());
                ivo.setMesFinal(corte[9].trim());
                ivo.setAnoFinal(corte[10].trim());
                
                ivo.setItemDono(corte[11].trim());
                                
                ItemRN crn = ItemRN.getInstancia();
                id += 1;
                
                if (crn.autenticaCadastro(ivo, id) == true)
                    retornaCliente("3_3_1", conexao, lvo);
                else
                    retornaCliente("3_3_2", conexao, lvo);
            }
            
            if (m.substring(0, 4).equals("1_4_")) { /* atualizar item */
                
                String[] corte = m.split("_");
                
                id = (Integer.parseInt(corte[2].trim())); /* id item */
                ivo.setItemNome(corte[3].trim()); /* nome */
                ivo.setItemDesc(corte[4].trim()); /* descricao */
                
                ItemRN crn = ItemRN.getInstancia();
                
                if (crn.autenticaAtualizacao(ivo, id) == true) /*atualizacao com sucesso*/
                    retornaCliente("3_4_1", conexao, lvo);
                else /*atualizacao sem sucesso */
                    retornaCliente("3_4_2", conexao, lvo);
            }
            
            if (m.substring(0, 4).equals("1_5_")) { /* remover item */
                
                String[] corte = m.split("_");
                
                id = (Integer.parseInt(corte[2].trim())); /* id item */
                
                ItemRN crn = ItemRN.getInstancia();
                
                if (crn.autenticaRemocao(ivo, id) == true) /*remocao com sucesso*/
                    retornaCliente("3_5_1", conexao, lvo);
                else /*remocao sem sucesso */
                    retornaCliente("3_5_2", conexao, lvo);
                
            }
            
            if (m.substring(0, 4).equals("1_6_")) { /* consultar meus itens */
                
                String[] corte = m.split("_");
                
                nomeUsuario = corte[2].trim();
                
                ConsultaRN crn = ConsultaRN.getInstancia();
                String resultado = crn.consultaMeus(nomeUsuario);
                
                if (!(resultado.substring(0, 1).equals("E"))) {
                    String mensagem = resultado.substring(4);
                    retornaCliente("4_1_" + mensagem, conexao, lvo);
                }
            }
            
            if (m.substring(0, 4).equals("1_7_")) { /* consultar itens dos meus amigos */
                
                String[] corte = m.split("_");
                
                nomeUsuario = corte[2].trim();
                
                ConsultaRN crn = ConsultaRN.getInstancia();
                String resultado = crn.consultaAmigos(nomeUsuario);
                
                if (!(resultado.substring(0, 1).equals("E"))) {
                    String mensagem = resultado.substring(4);
                    retornaCliente("4_1_" + mensagem, conexao, lvo);
                }
            }
            
            if (m.substring(0, 4).equals("2_1_")) { /* dados de um item */
                
                String[] corte = m.split("_");
                
                id = (Integer.parseInt(corte[2].trim())); /* id item */
                
                ConsultaRN crn = ConsultaRN.getInstancia();
                String resultado = crn.consultaItem(id);
                        
                if (!(resultado.substring(0, 1).equals("E"))) {
                    String mensagem = resultado.substring(4);
                    retornaCliente("5_1_" + mensagem, conexao, lvo);
                }
            }
            
            if (m.substring(0, 4).equals("9_3_")) { /* consultar itens disponiveis para emprestimo */
                
                String[] corte = m.split("_");
                
                nomeUsuario = corte[2].trim();
                
                ConsultaRN crn = ConsultaRN.getInstancia();
                String resultado = crn.consultaAmigosDisponiveis(nomeUsuario);
                if (!(resultado.substring(0, 1).equals("E"))) {
                    String mensagem = resultado.substring(4);
                    retornaCliente("4_1_" + mensagem, conexao, lvo);
                }
            }
            
            if (m.substring(0, 4).equals("1_9_")) { /* consulta itens que solicitei empréstimo */
                
                String[] corte = m.split("_");
                
                nomeUsuario = corte[2].trim();
                
                ConsultaRN crn = ConsultaRN.getInstancia();
                String resultado = crn.consultaEmprestados(nomeUsuario);
                if (!(resultado.substring(0, 1).equals("E"))) {
                    String mensagem = resultado.substring(4);
                    retornaCliente("4_1_" + mensagem, conexao, lvo);
                }
            }
            
            if (m.substring(0, 4).equals("1_8_")) { /* empréstimo */
                String[] corte = m.split("_");
                
                id = Integer.parseInt(corte[2].trim());
                nomeUsuario = corte[3].trim();
            
                EmprestimoRN ern = EmprestimoRN.getInstancia();
                
                if (ern.emprestaItem(id, nomeUsuario) == true)
                    retornaCliente("3_8_1", conexao, lvo);
                else
                    retornaCliente("3_8_2", conexao, lvo);
            }
            
            if (m.substring(0, 4).equals("9_1_")) { /* devolução */
                String[] corte = m.split("_");
                
                id = Integer.parseInt(corte[2].trim());
            
                EmprestimoRN ern = EmprestimoRN.getInstancia();
                
                if (ern.devolveItem(id) == true)
                    retornaCliente("9_2_1", conexao, lvo);
                else
                    retornaCliente("9_2_2", conexao, lvo);
            }
            
        }
    }
    
    public void retornaCliente (String mensagem, DatagramSocket conexao, LoginVO lvo) {
        try {
            byte[] mc = mensagem.getBytes();
            
            InetAddress aHost = InetAddress.getByName(enderecoCliente);
            int serverPort = portaCliente;
            
            System.out.println("ENVIOU: " + mensagem);
            
            DatagramPacket request = new DatagramPacket(mc, mc.length, aHost, serverPort);
            conexao.send(request);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    } 
}