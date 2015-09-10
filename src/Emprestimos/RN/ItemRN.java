/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emprestimos.RN;

import Emprestimos.Pers.ItemPers;
import Emprestimos.VO.ItemVO;

/**
 *
 * @author lennonalves
 */
public class ItemRN {
    
    public static ItemRN instancia;
    
    protected ItemRN(){}
    
    public static ItemRN getInstancia() {
        if (instancia == null)
            instancia = new ItemRN();
        return instancia;
    }
    
    public boolean autenticaRemocao(ItemVO ivo, int id) {
        return removeItem(ivo, id) == true;
    }
    
    public boolean autenticaCadastro(ItemVO ivo, int id) {
        if (camposVazios(ivo) == true) {
            return false;
        }
        else {
            return cadastraItem(ivo, id) == true;
        }
    }
    
    public boolean autenticaAtualizacao(ItemVO ivo, int id) {
        return atualizaItem(ivo, id) == true;
    }
    
    public boolean camposVazios(ItemVO ivo) {
        return ivo.getItemNome().trim().equals("") || ivo.getItemDesc().trim().equals("")
                || ivo.getItemNome() == null || ivo.getItemDesc() == null
                || ivo.getDiaInicial() == null || ivo.getMesInicial() == null || ivo.getAnoInicial() == null
                || ivo.getDiaFinal() == null || ivo.getMesFinal() == null || ivo.getAnoFinal() == null;
    }
    
    public boolean cadastraItem(ItemVO ivo, int id) {
        ItemPers cpers = ItemPers.getInstancia();
        return cpers.cadastrarItem(ivo, id);
    }
    
    public boolean atualizaItem(ItemVO ivo, int id) {
        ItemPers cpers = ItemPers.getInstancia();
        return cpers.atualizaItem(ivo, id);
    }
    
    public boolean removeItem(ItemVO ivo, int id) {
        ItemPers cpers = ItemPers.getInstancia();
        return cpers.removeItem(ivo, id);
    }
    
}
