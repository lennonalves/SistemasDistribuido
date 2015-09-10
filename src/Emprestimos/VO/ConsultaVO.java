/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emprestimos.VO;

import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lennonalves
 */
public class ConsultaVO {
    
    public static ConsultaVO instancia;
    
    protected ConsultaVO(){}
    
    public static ConsultaVO getInstancia() {
        if (instancia == null)
            instancia = new ConsultaVO();
        return instancia;
    }
    
    public DefaultTableModel tableIds = new DefaultTableModel();
    public DefaultTableModel tableItens = new DefaultTableModel();
    private boolean flag;
    private int linhaId, linhaItem;
    
    private Date data = new Date();
    private java.sql.Date dataSQL = new java.sql.Date(data.getTime());

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public java.sql.Date getDataSQL() {
        return dataSQL;
    }

    public void setDataSQL(java.sql.Date dataSQL) {
        this.dataSQL = dataSQL;
    }    

    public int getLinhaId() {
        return linhaId;
    }

    public void setLinhaId(int linhaId) {
        this.linhaId = linhaId;
    }

    public int getLinhaItem() {
        return linhaItem;
    }

    public void setLinhaItem(int linhaItem) {
        this.linhaItem = linhaItem;
    }
}
