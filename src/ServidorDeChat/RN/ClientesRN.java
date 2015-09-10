/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorDeChat.RN;

import ServidorDeChat.Pers.ClientesPers;
import ServidorDeChat.VO.ClientesVO;
import java.io.IOException;
import java.net.UnknownHostException;

/**
 *
 * @author lennonalves
 */
public class ClientesRN {
    
    public static ClientesRN instancia;
    
    protected ClientesRN(){}
    
    public static ClientesRN getInstancia() {
        if (instancia == null)
            instancia = new ClientesRN();
        return instancia;
    }
    
    public String adicionaCliente (ClientesVO cvo) {
        ClientesPers cpers = ClientesPers.getInstancia();
        String mensagem = cpers.adicionaCliente(cvo);
        return mensagem;
    }
    
    public String removeCliente (ClientesVO cvo) {
        ClientesPers cpers = ClientesPers.getInstancia();
        String mensagem = cpers.removeCliente(cvo);
        return mensagem;
    }
    
    public String atualizaClientes (ClientesVO cvo) {
        ClientesPers cpers = ClientesPers.getInstancia();
        String mensagem = cpers.atualizaClientes(cvo);
        return mensagem;
    }    
    
    public void zerarClientes (ClientesVO cvo) {
        ClientesPers cpers = ClientesPers.getInstancia();
        cpers.zerarClientes(cvo);
    }  
    
    public String listarClientes (ClientesVO cvo) {
        ClientesPers cpers = ClientesPers.getInstancia();
        String mensagem = cpers.listarClientes(cvo);
        if(!mensagem.equals("2")) return mensagem;
        return null;
    }
    
    public String clienteGeral (ClientesVO cvo) {
        ClientesPers cpers = ClientesPers.getInstancia();
        String mensagem = cpers.clienteGeral(cvo);
        return mensagem;
    }
    
    public String broadcast (ClientesVO cvo) throws UnknownHostException, IOException {
        ClientesPers cpers = ClientesPers.getInstancia();
        String mensagem = cpers.broadcast(cvo);
        return mensagem;
    }
    
}
