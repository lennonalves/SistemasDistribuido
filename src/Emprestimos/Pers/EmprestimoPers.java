/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emprestimos.Pers;

import Emprestimos.VO.ItemVO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author lennonalves
 */
public class EmprestimoPers {
    
    public static EmprestimoPers instancia;
    
    protected EmprestimoPers() {}
    
    public static EmprestimoPers getInstancia() {
        if (instancia == null)
            instancia = new EmprestimoPers();
        return instancia;
    }
    
    public boolean emprestaItem (int id, String nomeUsuario) {
        
        Conexao cx = Conexao.getInstancia();
        try {
            Connection con = cx.conectar();
            Statement query = con.createStatement();
            query.executeUpdate("UPDATE ITENS SET "
                    + "ITEM_STATUS = 'Emprestado', "
                    + "ITEM_EMPRESTADOPOR = '" + nomeUsuario + "' "
                    + "WHERE ITEM_ID = " + id);
            cx.desconectar();
            return true;
            
        } catch (SQLException e){
            System.out.println("ERRO: " + e.getMessage());
            return false;
        }
        
    }
    
    public boolean devolveItem (int id) {
        
        Conexao cx = Conexao.getInstancia();
        try {
            Connection con = cx.conectar();
            Statement query = con.createStatement();
            query.executeUpdate("UPDATE ITENS SET "
                    + "ITEM_STATUS = 'Disponível', "
                    + "ITEM_EMPRESTADOPOR = 'Nenhum' "
                    + "WHERE ITEM_ID = " + id);
            cx.desconectar();
            return true;
            
        } catch (SQLException e){
            System.out.println("ERRO: " + e.getMessage());
            return false;
        }
        
    }
    
}
