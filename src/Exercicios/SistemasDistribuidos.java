/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Exercicios;

/**
 *
 * @author lennonalves
 */
public class SistemasDistribuidos {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        int flag = 0;
        
        Mensagem m = new Mensagem();
        
        Cliente c1 = new Cliente();
        Cliente c2 = new Cliente();
        
        while (!" ".equals(m.getPalavra()))
        {
            c1.ler(1);
            if (" ".equals(m.getPalavra()))
                break;
            c2.ler(2);
        }
    }
    
}
