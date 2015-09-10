/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emprestimos.Pers;

import Emprestimos.VO.ConsultaVO;
import Emprestimos.VO.LoginVO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author lennonalves
 */
public class ConsultaPers {
    
    public static ConsultaPers instancia;
    
    protected ConsultaPers(){}
    
    public static ConsultaPers getInstancia() {
        if (instancia == null)
            instancia = new ConsultaPers();
        return instancia;
    }
    
    public String consultaMeus(String nomeUsuario) {
        Conexao cx = Conexao.getInstancia();
        
        String mensagem = "4_1";
        
        try {
            Connection con = cx.conectar();
            Statement consulta = con.createStatement();
            ResultSet resultado = consulta.executeQuery("SELECT ITEM_ID FROM ITENS WHERE ITEM_DONO = '" + nomeUsuario + "'");
            
            while (resultado.next()){
                mensagem = mensagem + "_" + resultado.getString("ITEM_ID");
            }
            cx.desconectar();
            if (mensagem.equals("4_1"))
                mensagem = "ERRO: Não existem cadastros para consulta.";
            return mensagem;
        } catch (SQLException e){
            System.out.println("ERRO: " + e.getMessage());
        }
        return "ERRO";
    }
    
    public String consultaAmigos(String nomeUsuario) {
        Conexao cx = Conexao.getInstancia();
        
        String mensagem = "4_1";
        
        try {
            Connection con = cx.conectar();
            Statement consulta = con.createStatement();
            ResultSet resultado = consulta.executeQuery("SELECT ITEM_ID FROM ITENS WHERE ITEM_DONO <> '" + nomeUsuario + "'");
            
            while (resultado.next()){
                mensagem = mensagem + "_" + resultado.getString("ITEM_ID");
            }
            cx.desconectar();
            if (mensagem.equals("4_1"))
                mensagem = "ERRO: Não existem cadastros para consulta.";
            return mensagem;
        } catch (SQLException e){
            System.out.println("ERRO: " + e.getMessage());
        }
        return "ERRO";
    }
    
    public String consultaItem(Integer id) {
        Conexao cx = Conexao.getInstancia();
        
        String mensagem = "5_1";
        
        try {
            Connection con = cx.conectar();
            Statement consulta = con.createStatement();
            ResultSet resultado = consulta.executeQuery("SELECT * FROM ITENS WHERE ITEM_ID = '" + id + "'");
            
            while (resultado.next()){
                mensagem = mensagem + "_" + resultado.getString("ITEM_ID") + "_" + resultado.getString("ITEM_NOME")
                        + "_" + resultado.getString("ITEM_DESC") + "_" + resultado.getString("ITEM_TIPO")
                        + "_" + resultado.getString("ITEM_DI") + "_" + resultado.getString("ITEM_MI")
                        + "_" + resultado.getString("ITEM_AI") + "_" + resultado.getString("ITEM_DF")
                        + "_" + resultado.getString("ITEM_MF") + "_" + resultado.getString("ITEM_AF")
                        + "_" + resultado.getString("ITEM_DONO") + "_" + resultado.getString("ITEM_STATUS");
            }
            cx.desconectar();
            if (mensagem.equals("5_1"))
                mensagem = "ERRO: Não existem cadastros para consulta.";
            return mensagem;
        } catch (SQLException e){
            System.out.println("ERRO: " + e.getMessage());
        }
        return "ERRO";
    }
    
    public String consultaAmigosDisponiveis(String nomeUsuario) {
        Conexao cx = Conexao.getInstancia();
        
        String mensagem = "4_1";
        
        try {
            Connection con = cx.conectar();
            Statement consulta = con.createStatement();
            ResultSet resultado = consulta.executeQuery("SELECT ITEM_ID FROM ITENS WHERE ITEM_DONO <> '" + nomeUsuario + "' "
                    + "AND ITEM_STATUS = 'Disponível'");
            
            while (resultado.next()){
                mensagem = mensagem + "_" + resultado.getString("ITEM_ID");
            }
            cx.desconectar();
            if (mensagem.equals("4_1"))
                mensagem = "ERRO: Não existem cadastros para consulta.";
            return mensagem;
        } catch (SQLException e){
            System.out.println("ERRO: " + e.getMessage());
        }
        return "ERRO";
    }
    
    public String consultaEmprestados(String nomeUsuario) {
        Conexao cx = Conexao.getInstancia();
        
        String mensagem = "4_1";
        
        try {
            Connection con = cx.conectar();
            Statement consulta = con.createStatement();
            ResultSet resultado = consulta.executeQuery("SELECT * FROM ITENS WHERE ITEM_STATUS = 'Emprestado' "
                    + "AND ITEM_DONO <> '" + nomeUsuario +"' "
                    + "AND ITEM_EMPRESTADOPOR = '" + nomeUsuario + "'");
            
            while (resultado.next()){
                mensagem = mensagem + "_" + resultado.getString("ITEM_ID");
            }
            cx.desconectar();
            if (mensagem.equals("4_1"))
                mensagem = "ERRO: Não existem cadastros para consulta.";
            return mensagem;
        } catch (SQLException e){
            System.out.println("ERRO: " + e.getMessage());
        }
        return "ERRO";
    }
    
    public String preencheCampos(int id) {
        Conexao cx = Conexao.getInstancia();
        
        String mensagem = "2_1";
        
        try {
            Connection con = cx.conectar();
            Statement consulta = con.createStatement();
            ResultSet resultado = consulta.executeQuery("SELECT * FROM ITENS WHERE ITEM_ID = " + id);
            
            while (resultado.next()){
               mensagem = mensagem + "_" + resultado.getString("ITEM_NOME")
                        + "_" + resultado.getString("ITEM_TIPO")
                        + "_" + resultado.getString("ITEM_DESC")
                        + "_" + resultado.getString("ITEM_DI")
                        + "_" + resultado.getString("ITEM_MI")
                        + "_" + resultado.getString("ITEM_AI")
                        + "_" + resultado.getString("ITEM_DF")
                        + "_" + resultado.getString("ITEM_MF")
                        + "_" + resultado.getString("ITEM_AF");            
            }
            cx.desconectar();
        } catch (SQLException e){
            System.out.println("ERRO: " + e.getMessage());
        }
        return mensagem;
    }
}