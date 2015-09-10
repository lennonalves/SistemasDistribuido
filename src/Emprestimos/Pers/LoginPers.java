/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emprestimos.Pers;

import Emprestimos.VO.LoginVO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author lennonalves
 */
public class LoginPers {
    
    public static LoginPers instancia;
    
    protected LoginPers() {}
    
    public static LoginPers getInstancia() {
        if (instancia == null)
            instancia = new LoginPers();
        return instancia;
    }
    
    //conexões
    
    public boolean fazerLogin (LoginVO lvo) {
        
        Conexao cx = Conexao.getInstancia();
        String mensagem = null;

        try {

            Connection con = cx.conectar();
            Statement consulta = con.createStatement();
            ResultSet resultado = consulta.executeQuery("SELECT * FROM USUARIOS WHERE NOME_USER = '"
            + lvo.getUser() + "' AND SENHA_USER = '" + lvo.getSenha() + "'");

            while(resultado.next()){
                return true;
            }
            
            cx.desconectar();
            
        } catch (SQLException e){
            System.out.println("ERRO: " + e.getMessage());
            return false;
        }
        return false;
    }
}