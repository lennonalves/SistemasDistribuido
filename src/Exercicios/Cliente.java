/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Exercicios;

import java.util.Scanner;

/**
 *
 * @author lennonalves
 */
public class Cliente {
    
    void ler(int flag)
    {
        Mensagem m = new Mensagem();
        Scanner ler = new Scanner(System.in);
        m.setPalavra(ler.nextLine());
        
        Servidor serv = new Servidor();
        serv.modifica();
        serv.imprime(flag);
    }
}
