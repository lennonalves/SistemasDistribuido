/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emprestimos.RN;

import Emprestimos.Pers.EmprestimoPers;

/**
 *
 * @author lennonalves
 */
public class EmprestimoRN {
    
    public static EmprestimoRN instancia;
    
    protected EmprestimoRN(){}
    
    public static EmprestimoRN getInstancia() {
        if (instancia == null)
            instancia = new EmprestimoRN();
        return instancia;
    }
    
    private String mensagem = null;
    
    public boolean emprestaItem(int id, String nomeUsuario) {
        EmprestimoPers epers = EmprestimoPers.getInstancia();
        return epers.emprestaItem(id, nomeUsuario);
    }
    
    public boolean devolveItem(int id) {
        EmprestimoPers epers = EmprestimoPers.getInstancia();
        return epers.devolveItem(id);
    }
    
}
