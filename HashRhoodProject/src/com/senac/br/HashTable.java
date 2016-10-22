
package com.senac.br;
import java.io.*;
import javax.swing.JOptionPane;
import javax.*;

public class HashTable
{   /**
     * Matriz para armazenar os NOs
     */
    private  Node[] table;
    /**
     *Tamanho da tabela hash
     */
    private int size;
    /**
     * Numero de elementos inseridos
     */
    private int inserir=0;

    /**
     * Construtor que cria a matriz com determinado tamanho
     * @param O tamanho matriz a ser criado
     */
    public HashTable(int dado)
    {
        table = new Node[dado];
        size = dado;
    }

    /**
     * Construtor que le a partir de determinado arquivo. O formato do arquivo e: Primeira linha
     * Indica o tamanho da tabela, e cada outra linha contem o NO
     * Valores. Assim, cada linha deve ter apenas um valor inteiro, ou qualquer valor que usando o método Integer.parseInt (String).
     * @param Nome do arquivo para a construção de tabela de hash
     */
    public HashTable(String nomeDoArquivo)
    {
        FileOutputStream saida;
        PrintStream OrgOut = System.out;
        PrintStream arquivo;

        try
        {
            FileInputStream stream = new FileInputStream(nomeDoArquivo);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(stream));
            saida = new FileOutputStream("output.txt");
            arquivo = new PrintStream(saida);
            System.setOut(arquivo); 
            String linha = entrada.readLine();
            int counter = 0;
            if(linha!=null)
            {   int dados=Integer.parseInt(linha);
                table=new Node[dados];
                size=dados;
            }
            linha = entrada.readLine();
            while(linha !=null)
            {
                Node ler = new Node();
                ler.setValor(Integer.parseInt(linha));
                insert(ler);
                counter++;
                linha = entrada.readLine();
            }
            System.setOut(OrgOut);
            arquivo.close();
            String Message = counter+" NO (s) inserido na tabela de Hash : "+this.toString()+"\n"
                    +"Verifique dados.txt para registro detalhado\n";
            JOptionPane.showMessageDialog(null,Message,"Robin Hood teste para ver se funciona",3);
            System.out.printf("%d node(s) inserted into Hash table : %s\n",counter,this.toString());
        }
        catch (FileNotFoundException ioexc)
        {
            System.out.println(ioexc.getMessage());
        }
        catch (IOException ioexc)
        {
            System.out.println(ioexc.getMessage());
        }

    }


    /**
     * Método para inserir NO em tabela hash, esse metodo verifica o
     * Status de insercao e pode chamar outra insercao instância (x) se necessario.
     *  Então pode ocorrer o estouro da  pilha por má utilização deste método.
     * @ Param x NO a ser inserido na tabela hash
     * @return Verdadeiro se a inserção e bem sucedida, caso contrário falso
     */
    private final boolean insert(Node x)
    {
        int index;
        if(inserir==size)
        {
            System.out.println("Table is full!");
            return false;
        }
        index = x.getTargetLocation(size);
        if(table[index]==null)
        {
            table[index]=x;
            inserir++;
            System.out.printf("Node %d Inserted @ room %d - collision count for this node is %d\r\n",x.getValue(),index,x.getContagemColisao());
            return true;
        }
        else if(table[index].getContagemColisao()<x.getContagemColisao())
        {
            table[index].incrementaColisao();
            x.incrementaColisao();
            System.out.printf("Node %d collided with node %d while inserting - " +
                    " collision count for the former node is %d\r\nReplacing node %d " +
                    "- collision count for this node is %d\r\n",
                    x.getValue(),table[index].getValue(),
                    x.getContagemColisao(),table[index].getValue(),
                    table[index].getContagemColisao());
            Node previous;
            previous=table[index];
            table[index]=x;
            System.out.printf("Node %d Inserted @ room %d - collision count for this node is %d\r\n",x.getValue(),index,x.getContagemColisao());

            return insert(previous);
        }
        else if(table[index].getContagemColisao()>=x.getContagemColisao())
        {
            x.incrementaColisao();
            System.out.printf("Node %d collided with node %d while inserting - " +
                    " collision count for the former node is %d\r\n",
                    x.getValue(),table[index].getValue(),
                    x.getContagemColisao());
            return insert(x);
        }
        return false;
        
    }

    /**
     * Método de impressão para a saida padrao, imprime o valor de cada NO em cada quarto
     * Para a saida padrao, impressoes "vazio" se a sala esta cheia de qualquer NO.
     * Nao no modo final, fins de teste
     */
    public void printTable()
    {
        for(int i=0;i<table.length;i++)
        {
            if(table[i]==null)
                System.out.println("empty");
            else System.out.println(table[i].getValue());
        }
    }
}
