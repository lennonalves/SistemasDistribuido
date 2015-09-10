/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emprestimos.VO;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lennonalves
 */
public class EmprestimoVO {
 
    public static EmprestimoVO instancia;
    
    protected EmprestimoVO(){}
    
    public static EmprestimoVO getInstancia() {
        if (instancia == null)
            instancia = new EmprestimoVO();
        return instancia;
    }
    
    public DefaultTableModel tableIds = new DefaultTableModel();
    public DefaultTableModel tableItens = new DefaultTableModel();
    
    private int linhaId;

    public int getLinhaId() {
        return linhaId;
    }

    public void setLinhaId(int linhaId) {
        this.linhaId = linhaId;
    }

    
}
