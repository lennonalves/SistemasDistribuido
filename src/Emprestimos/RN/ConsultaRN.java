/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emprestimos.RN;

import Emprestimos.Pers.ConsultaPers;
import Emprestimos.VO.LoginVO;

/**
 *
 * @author lennonalves
 */
public class ConsultaRN {
    
    public static ConsultaRN instancia;
    
    protected ConsultaRN(){}
    
    public static ConsultaRN getInstancia() {
        if (instancia == null)
            instancia = new ConsultaRN();
        return instancia;
    }
    
    private String mensagem = null;
    
    /* TELA DE CONSULTA */
    
    public String consultaMeus(String nomeUsuario) {
        ConsultaPers cpers = ConsultaPers.getInstancia();
        mensagem = cpers.consultaMeus(nomeUsuario);
        if (mensagem.substring(0, 1).equals("4"))
            return mensagem;
        else
            return "ERRO: Não existem cadastros para consulta.";
    }
    
    public String consultaAmigos(String nomeUsuario) {
        ConsultaPers cpers = ConsultaPers.getInstancia();
        mensagem = cpers.consultaAmigos(nomeUsuario);
        if (mensagem.substring(0, 1).equals("4"))
            return mensagem;
        else
            return "ERRO: Não existem cadastros para consulta.";
    }
    
    public String consultaItem(Integer id) {
        ConsultaPers cpers = ConsultaPers.getInstancia();
        mensagem = cpers.consultaItem(id);
        if (mensagem.substring(0, 1).equals("5"))
            return mensagem;
        else
            return "ERRO: Não existem cadastros para consulta.";
    }
    
    /* TELA DE EMPRESTIMOS */
    
    public String consultaAmigosDisponiveis(String nomeUsuario) {
        ConsultaPers cpers = ConsultaPers.getInstancia();
        mensagem = cpers.consultaAmigosDisponiveis(nomeUsuario);
        if (mensagem.substring(0, 1).equals("4"))
            return mensagem;
        else
            return "ERRO: Não existem cadastros para consulta.";
    }
    
    public String consultaEmprestados(String nomeUsuario) {
        ConsultaPers cpers = ConsultaPers.getInstancia();
        mensagem = cpers.consultaEmprestados(nomeUsuario);
        if (mensagem.substring(0, 1).equals("4"))
            return mensagem;
        else
            return "ERRO: Não existem cadastros para consulta.";
    }
    
    /* ATUALIZA CAMPOS DO GERENCIAR ITENS */
    
    public String preencheCampos(int id) {
        ConsultaPers cpers = ConsultaPers.getInstancia();
        return cpers.preencheCampos(id);
    }
    
}