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
public class Servidor {
    
    void modifica()
    {
        Mensagem m = new Mensagem();
        m.setPalavra((m.getPalavra()).toUpperCase());
    }
    
    void imprime(int flag)
    {
        Mensagem m = new Mensagem();
        System.out.println("cliente: " + flag + " // mensagem: " + m.getPalavra());
    }
}
