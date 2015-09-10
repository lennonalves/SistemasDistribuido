/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServidorDeChat.VO;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lennonalves
 */
public class ClientesVO {
    
    public static ClientesVO instancia;
    
    protected ClientesVO(){}
    
    public static ClientesVO getInstancia() {
        if (instancia == null)
            instancia = new ClientesVO();
        return instancia;
    }
    
    private String clienteatual, hostId, hostName, hostAddress, hostPort, mensagem,
            ipDestino, portaDestino, conversa, aparecerNaTela;
    
    public DefaultTableModel dtm = new DefaultTableModel();

    public String getClienteatual() {
        return clienteatual;
    }

    public void setClienteatual(String clienteatual) {
        this.clienteatual = clienteatual;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public String getHostPort() {
        return hostPort;
    }

    public void setHostPort(String hostPort) {
        this.hostPort = hostPort;
    }
    
    public DefaultTableModel getDtm() {
        return dtm;
    }

    public void setDtm(DefaultTableModel dtm) {
        this.dtm = dtm;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getIpDestino() {
        return ipDestino;
    }

    public void setIpDestino(String ipDestino) {
        this.ipDestino = ipDestino;
    }

    public String getPortaDestino() {
        return portaDestino;
    }

    public void setPortaDestino(String portaDestino) {
        this.portaDestino = portaDestino;
    }

    public String getConversa() {
        return conversa;
    }

    public void setConversa(String conversa) {
        this.conversa = conversa;
    }

    public String getAparecerNaTela() {
        return aparecerNaTela;
    }

    public void setAparecerNaTela(String aparecerNaTela) {
        this.aparecerNaTela = aparecerNaTela;
    }
    
}
