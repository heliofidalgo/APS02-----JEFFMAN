
package com.senac.br;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;


public class ToolBox
{
	/**
     * Caixa de ferramentas que cont�m todos os m�todos est�ticos, por isso n�o h� necessidade de criar uma inst�ncia.
     */
	
    private ToolBox()
    {
        // n�o vamos criar uma inst�ncia
    }
    /**
    * M�todo que verifica se o n�mero dado � primo
    * @ Param n inteiro a ser testado
    * @return True se o n�mero � um n�mero primo, caso contr�rio retorna um false
    */
    public static boolean ePrimo(int n)
    {
        boolean primo = true;
	for (int i = 3; i <= Math.sqrt(n); i += 2)
            if (n % i == 0)
            {
		primo = false;
		break;
            }
        if (( n%2 !=0 && primo && n > 2) || n == 2)
            return true;
	else return false;
    }

    /**
     * M�todo que encontra e devolve o n�mero primo anterior
     * @ Param o valor inteiro n mais pr�ximo e o anterior ser� encontrado
     * @return O valor do primo mais pr�ximo menor que n
     */
    public static int primoAnterior(int n)
    {
        n--;
        while(!ePrimo(n))
            n--;
        return n;
    }
    public static int hash1(int value,int tableSize)
    {
        return value%tableSize;
    }
    public static int hash2(int value,int tableSize)
    {
        return primoAnterior(tableSize) - (value%primoAnterior(tableSize));
    }
    
    /**
    * M�todo que verifica se o arquivo de entrada � adequado para hash, como verifica��o
    * O dado tamanho mesa e dado n�mero de n�s. Devemos chamar este
    * M�todo antes de criar a tabela hash. Informa usu�rio.
    * Nome arquivo @param do arquivo para valiada��o
    * @return True se o arquivo � bom para o hashing, caso contr�rio false
    */
    
    public static boolean validacaoParaoHash(String nomrDoArquivo)
    {
        try
        {
            FileInputStream Stream = new FileInputStream(nomrDoArquivo);
            BufferedReader input = new BufferedReader(new InputStreamReader(Stream));


            String linha = input.readLine();
            int counter = 0;
            int defineTamanho = Integer.parseInt(linha);
            linha = input.readLine();
            while(linha!=null)
            {
                counter++;
                linha = input.readLine();
            }
            String mensagem;
            boolean status;
            if(defineTamanho>=counter)
            {
                mensagem = "Valida��o de arquivo completa!\n"+
                    "Tamanho da tabela: "+defineTamanho+"\n"+
                    "N�s para ser inserido : "+counter+"\n";
                status = true;
            }
            else
            {
                mensagem = "Falha na valida��o do arquivo!\n"+
                    "O tamanho da tabela n�o � suficiente! Por favor, aumente o tamanho da tabela!"+
                    "Editando a primeira linha do arquivo de entrada.\n"+
                    "Tamanho minimo da tabela: "+counter+"\n";
                status = false;
            }
            input.close();
            JOptionPane.showMessageDialog(null,mensagem,"Robin Hood Hashing testando",3);
            return status;

        }
        catch (FileNotFoundException ioexc)
        {
            System.out.println(ioexc.getMessage());
        }
        catch (IOException ioexc)
        {
            System.out.println(ioexc.getMessage());
        }
        return false;
    }



}
