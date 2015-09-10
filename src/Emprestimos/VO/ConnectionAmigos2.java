/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emprestimos.VO;

import Emprestimos.Telas.TelaConsultaItens;
import Emprestimos.Telas.TelaLogin;
import Emprestimos.Telas.TelaMenu;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author lennonalves
 */

public class ConnectionAmigos2 extends Thread {

    DatagramSocket s = null;
    String portaCliente;
    int aux;
    
    public ConnectionAmigos2(String porta, DatagramSocket conexao) {
        this.s = conexao;
        this.portaCliente = porta;
    }
    
    @Override
    public void run()
    {
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
            
            LoginVO lvo = LoginVO.getInstancia();
            ItemVO ivo = ItemVO.getInstancia();
            
            if (m.substring(0, 5).equals("3_1_1")) {
                JOptionPane.showMessageDialog(null, "Login realizado com sucesso.", "Seja Bem Vindo || Empréstimo entre Amigos", 
                        JOptionPane.INFORMATION_MESSAGE);
                
                TelaMenu tm = TelaMenu.getInstancia();
                tm.setVisible(true);
            }
            
            if (m.substring(0, 5).equals("3_1_2")) {
                JOptionPane.showMessageDialog(null, "Erro ao realizar o login.", "Erro na Autenticação || Empréstimo entre Amigos",
                        JOptionPane.ERROR_MESSAGE);
                
                TelaLogin tl = TelaLogin.getInstancia();
                tl.setVisible(true);
            }
            
            if (m.substring(0, 5).equals("3_2_1")) {
                JOptionPane.showMessageDialog(null, "Obrigado por utilizar nossos serviços, volte sempre!", "Até Breve || Empréstimo entre Amigos",
                        JOptionPane.INFORMATION_MESSAGE);
                
                TelaLogin tl = TelaLogin.getInstancia();
                tl.setVisible(true);
            }
            
            if (m.substring(0, 5).equals("3_3_1")) {
                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso.", "Cadastro Realizado || Empréstimo entre Amigos", 
                            JOptionPane.INFORMATION_MESSAGE);
            }
            
            if (m.substring(0, 5).equals("3_3_2")) {
                JOptionPane.showMessageDialog(null, "Erro ao realizar o cadastro, tente novamente. Se o erro permanecer, entre em contato com o administrador do sistema.", "Erro ao Cadastrar || Empréstimo entre Amigos",
                            JOptionPane.ERROR_MESSAGE);
            }
            
            if (m.substring(0, 5).equals("3_4_1")) {
                JOptionPane.showMessageDialog(null, "Atualização realizada com sucesso.", "Atualização Realizada || Empréstimo entre Amigos", 
                            JOptionPane.INFORMATION_MESSAGE);
            }
            
            if (m.substring(0, 5).equals("3_4_2")) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar, tente novamente. Se o erro permanecer, entre em contato com o administrador do sistema.", "Erro ao Atualizar || Empréstimo entre Amigos",
                            JOptionPane.ERROR_MESSAGE);
            }
            
            if (m.substring(0, 5).equals("3_5_1")) {
                JOptionPane.showMessageDialog(null, "Remoção realizada com sucesso.", "Cadastro Removido || Empréstimo entre Amigos", 
                            JOptionPane.INFORMATION_MESSAGE);
            }
            
            if (m.substring(0, 5).equals("3_5_2")) {
                JOptionPane.showMessageDialog(null, "Erro ao remover, tente novamente. Se o erro permanecer, entre em contato com o administrador do sistema.", "Erro ao Remover || Empréstimo entre Amigos",
                            JOptionPane.ERROR_MESSAGE);
            }
            
            if (m.substring(0, 3).equals("4_1")) { /* recebe lista de id's */
                ConsultaVO cvo = ConsultaVO.getInstancia();
                EmprestimoVO evo = EmprestimoVO.getInstancia();

                String mensagem = m.substring(4);
                String[] corte = mensagem.split("_");

                for (int i = 0; i < (corte.length); i++) {
                    cvo.tableIds.addRow(new String [] {corte[i].trim()});
                    evo.tableIds.addRow(new String [] {corte[i].trim()});
                }
            }
            
            if (m.substring(0, 3).equals("5_1")) { /* dados de um item */
                TelaConsultaItens tci = TelaConsultaItens.getInstancia();
                
                if (m.trim().equals("5_1")) { tci.setVisible(true); }
                else {
                    ConsultaVO cvo = ConsultaVO.getInstancia();
                    EmprestimoVO evo = EmprestimoVO.getInstancia();
                    
                    String mensagem = m.substring(4);
                    String[] corte = mensagem.split("_");
                    
                    cvo.tableItens.addRow(new String [] {"ID", corte[0].trim()});
                    cvo.tableItens.addRow(new String [] {"Nome", corte[1].trim()});
                    cvo.tableItens.addRow(new String [] {"Descrição", corte[2].trim()});
                    cvo.tableItens.addRow(new String [] {"Tipo", corte[3].trim()});
                    cvo.tableItens.addRow(new String [] {"Dia Inicial", corte[4].trim()});
                    cvo.tableItens.addRow(new String [] {"Mês Inicial", corte[5].trim()});
                    cvo.tableItens.addRow(new String [] {"Ano Inicial", corte[6].trim()});
                    cvo.tableItens.addRow(new String [] {"Dia Final", corte[7].trim()});
                    cvo.tableItens.addRow(new String [] {"Mes Final", corte[8].trim()});
                    cvo.tableItens.addRow(new String [] {"Ano Final", corte[9].trim()});
                    cvo.tableItens.addRow(new String [] {"Dono", corte[10].trim()});
                    cvo.tableItens.addRow(new String [] {"Status", corte[11].trim()});
                    
                    evo.tableItens.addRow(new String [] {"ID", corte[0].trim()});
                    evo.tableItens.addRow(new String [] {"Nome", corte[1].trim()});
                    evo.tableItens.addRow(new String [] {"Descrição", corte[2].trim()});
                    evo.tableItens.addRow(new String [] {"Tipo", corte[3].trim()});
                    evo.tableItens.addRow(new String [] {"Dia Inicial", corte[4].trim()});
                    evo.tableItens.addRow(new String [] {"Mês Inicial", corte[5].trim()});
                    evo.tableItens.addRow(new String [] {"Ano Inicial", corte[6].trim()});
                    evo.tableItens.addRow(new String [] {"Dia Final", corte[7].trim()});
                    evo.tableItens.addRow(new String [] {"Mes Final", corte[8].trim()});
                    evo.tableItens.addRow(new String [] {"Ano Final", corte[9].trim()});
                    evo.tableItens.addRow(new String [] {"Dono", corte[10].trim()});
                    evo.tableItens.addRow(new String [] {"Status", corte[11].trim()});
                }
            }
            
            if (m.substring(0, 5).equals("3_8_1")) {
                JOptionPane.showMessageDialog(null, "Empréstimo realizado com sucesso.", "Empréstimo Realizado || Empréstimo entre Amigos", 
                            JOptionPane.INFORMATION_MESSAGE);
            }
            
            if (m.substring(0, 5).equals("3_8_2")) {
                JOptionPane.showMessageDialog(null, "Erro ao realizar empréstimo, tente novamente. Se o erro permanecer, entre em contato com o administrador do sistema.", "Erro ao Emprestar || Empréstimo entre Amigos",
                            JOptionPane.ERROR_MESSAGE);
            }
            
            if (m.substring(0, 5).equals("9_2_1")) {
                JOptionPane.showMessageDialog(null, "Devolução realizada com sucesso.", "Devolução Realizada || Empréstimo entre Amigos", 
                            JOptionPane.INFORMATION_MESSAGE);
            }
            
            if (m.substring(0, 5).equals("9_2_2")) {
                JOptionPane.showMessageDialog(null, "Erro ao realizar devolução. Se o erro permanecer, entre em contato com o administrador do sistema.", "Erro ao Devolver || Empréstimo entre Amigos",
                            JOptionPane.ERROR_MESSAGE);
            }
            
        }
    }
}