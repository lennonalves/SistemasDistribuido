/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emprestimos.Pers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author lennonalves
 */
public class Conexao {
    
    Connection retornaConexao = null;
    public static Conexao instancia;
    
    protected Conexao(){}
    
    public static Conexao getInstancia() {
        if (instancia == null)
            instancia = new Conexao();
        return instancia;
    }

    public Connection conectar() {
        try {
            //registra e carrega o driver JDBC do Firebird
            Class.forName("org.firebirdsql.jdbc.FBDriver");

            //estabelecendo conexão com o BD
            retornaConexao = DriverManager.getConnection("jdbc:firebirdsql:localhost:/var/firebird/emprestimosBD.fdb",
                    "SYSDBA", "masterkey");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "CLASSE NAO ENCONTRADA");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO CONECTAR: " + e.getMessage());
        }
        return retornaConexao;
    }

    public void desconectar() {
        try {
            retornaConexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO AO DESCONECTAR: " + e.getMessage());
        }
    }
    
}