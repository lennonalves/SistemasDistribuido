/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emprestimos.VO;

/**
 *
 * @author lennonalves
 */
public class ServidorVO {
    
    public static ServidorVO instancia;
    
    protected ServidorVO() {}
    
    public static ServidorVO getInstancia() {
        if (instancia == null)
            instancia = new ServidorVO();
        return instancia;
    }
    
    private String portaServidor;

    public String getPortaServidor() {
        return portaServidor;
    }

    public void setPortaServidor(String portaServidor) {
        this.portaServidor = portaServidor;
    }
    
}
