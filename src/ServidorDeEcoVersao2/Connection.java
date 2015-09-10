package ServidorDeEcoVersao2;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Connection extends Thread
{
    BufferedReader entrada;
    PrintStream saida;
    Socket conexao2;
    
    public Connection(Socket conexao) throws IOException
    {
        try 
        {
            conexao2 = conexao;
            entrada = new BufferedReader(new InputStreamReader(conexao2.getInputStream()));
            saida = new PrintStream(conexao2.getOutputStream());
            this.start();
        }
        catch (IOException e)
        {
            System.out.println("Connection:"+e.getMessage());
        }
    }
    
    public void run()
    {
        try
        {
            String mensagem = entrada.readLine();

            while (mensagem != null && !(mensagem.trim().equals("")))
            { 
                saida.println(mensagem.toUpperCase());
                mensagem = entrada.readLine();
            }
        }
        catch(EOFException e) 
        {
            System.out.println("EOF: "+e.getMessage());
        } 
        catch(IOException e) 
        {
            System.out.println("IO: "+e.getMessage());
        }
        finally
        { 
            try 
            {
                conexao2.close();
            }
            catch (IOException e)
            {/*close failed*/}
        }
    }
}