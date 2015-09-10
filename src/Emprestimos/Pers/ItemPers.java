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
public class ItemPers {
    
    public static ItemPers instancia;
    
    protected ItemPers() {}
    
    public static ItemPers getInstancia() {
        if (instancia == null)
            instancia = new ItemPers();
        return instancia;
    }
    
    //conexões
    
    public boolean cadastrarItem (ItemVO ivo, int id) {
        
        Conexao cx = Conexao.getInstancia();
        
        try {
            Connection con = cx.conectar();
            Statement query = con.createStatement();
            query.executeUpdate("INSERT INTO ITENS VALUES "
                    + "(" + id + ", "
                    + "'" + ivo.getItemNome().trim() + "', "
                    + "'" + ivo.getItemTipo().trim() + "', "
                    + "'" + ivo.getItemDesc().trim() + "', "
                    + "'" + ivo.getItemDono().trim() + "', "
                    + "'" + ivo.getDiaInicial().trim() + "', "
                    + "'" + ivo.getMesInicial().trim() + "', "
                    + "'" + ivo.getAnoInicial().trim() + "', "
                    + "'" + ivo.getDiaFinal().trim() + "', "
                    + "'" + ivo.getMesFinal().trim() + "', "
                    + "'" + ivo.getAnoFinal().trim() + "', "
                    + "'Disponível', "
                    + "'Nenhum')");
            
            cx.desconectar();
            return true;
            
        } catch (SQLException e){
            System.out.println("ERRO: " + e.getMessage());
            return false;
        }
    }
    
    public boolean atualizaItem (ItemVO ivo, int id) {
        
        Conexao cx = Conexao.getInstancia();
        try {
            Connection con = cx.conectar();
            Statement query = con.createStatement();
            query.executeUpdate("UPDATE ITENS SET "
                    + "ITEM_NOME = '" + ivo.getItemNome() + "', "
                    + "ITEM_DESC = '" + ivo.getItemDesc() + "' "
                    + "WHERE ITEM_ID = " + id);
            cx.desconectar();
            return true;
            
        } catch (SQLException e){
            System.out.println("ERRO: " + e.getMessage());
            return false;
        }
    }
    
    public boolean removeItem (ItemVO ivo, int id) {
        
        Conexao cx = Conexao.getInstancia();
        
        try {
            Connection con = cx.conectar();
            Statement query = con.createStatement();
            query.executeUpdate("DELETE FROM ITENS "
                    + "WHERE ITEM_ID = " + id);
            cx.desconectar();
            return true;
            
        } catch (SQLException e){
            System.out.println("ERRO: " + e.getMessage());
            return false;
        }
    }
}
