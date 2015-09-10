/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emprestimos.VO;

/**
 *
 * @author lennonalves
 */
public class ItemVO {
    
    public static ItemVO instancia;
    
    protected ItemVO() {}
    
    public static ItemVO getInstancia() {
        if (instancia == null)
            instancia = new ItemVO();
        return instancia;
    }
    
    private int itemID;
    private String itemNome, itemDesc, itemTipo, itemDono, 
            diaInicial, mesInicial, anoInicial, diaFinal, mesFinal, anoFinal,
            mensagem;
    private boolean itemStatus;

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemNome() {
        return itemNome;
    }

    public void setItemNome(String itemNome) {
        this.itemNome = itemNome;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getItemTipo() {
        return itemTipo;
    }

    public void setItemTipo(String itemTipo) {
        this.itemTipo = itemTipo;
    }

    public String getItemDono() {
        return itemDono;
    }

    public void setItemDono(String itemDono) {
        this.itemDono = itemDono;
    }

    public boolean isItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(boolean itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getDiaInicial() {
        return diaInicial;
    }

    public void setDiaInicial(String diaInicial) {
        this.diaInicial = diaInicial;
    }

    public String getMesInicial() {
        return mesInicial;
    }

    public void setMesInicial(String mesInicial) {
        this.mesInicial = mesInicial;
    }

    public String getAnoInicial() {
        return anoInicial;
    }

    public void setAnoInicial(String anoInicial) {
        this.anoInicial = anoInicial;
    }

    public String getDiaFinal() {
        return diaFinal;
    }

    public void setDiaFinal(String diaFinal) {
        this.diaFinal = diaFinal;
    }

    public String getMesFinal() {
        return mesFinal;
    }

    public void setMesFinal(String mesFinal) {
        this.mesFinal = mesFinal;
    }

    public String getAnoFinal() {
        return anoFinal;
    }

    public void setAnoFinal(String anoFinal) {
        this.anoFinal = anoFinal;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
}
