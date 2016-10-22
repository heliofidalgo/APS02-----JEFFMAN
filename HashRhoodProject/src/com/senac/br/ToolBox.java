
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
     * Caixa de ferramentas que contém todos os métodos estáticos, por isso não há necessidade de criar uma instância.
     */
	
    private ToolBox()
    {
        // não vamos criar uma instância
    }
    /**
    * Método que verifica se o número dado é primo
    * @ Param n inteiro a ser testado
    * @return True se o número é um número primo, caso contrário retorna um false
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
     * Método que encontra e devolve o número primo anterior
     * @ Param o valor inteiro n mais próximo e o anterior será encontrado
     * @return O valor do primo mais próximo menor que n
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
    * Método que verifica se o arquivo de entrada é adequado para hash, como verificação
    * O dado tamanho mesa e dado número de nós. Devemos chamar este
    * Método antes de criar a tabela hash. Informa usuário.
    * Nome arquivo @param do arquivo para valiadação
    * @return True se o arquivo é bom para o hashing, caso contrário false
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
                mensagem = "Validação de arquivo completa!\n"+
                    "Tamanho da tabela: "+defineTamanho+"\n"+
                    "Nós para ser inserido : "+counter+"\n";
                status = true;
            }
            else
            {
                mensagem = "Falha na validação do arquivo!\n"+
                    "O tamanho da tabela não é suficiente! Por favor, aumente o tamanho da tabela!"+
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
