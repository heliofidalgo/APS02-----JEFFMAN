package com.senac.br;

public class Node
{   /**
     * Valor inicial do no recem criado
     */
    private static int VALOR_INICIAL = -1;
    /**
     * valor armazenado no n�
     */
    private int valor;
    /**
     * O numero de sondagem feita para alocar o elemento
     */
    private int contagemProbe;
    /**
     *  O status mostra se o no esta sendo usado ou suprimido
     */
    private boolean estaAtivo;
    /**
     *  Numero de colisoes que ocorreram para este no
     */
    private int contagemDeColisao;
    /**
     * Construtor padr�o que inicializa os campos para os valores
     * @see #valor
     * @see #estaAtivo
     * @see #contagemProbe
     */
    public Node()
    {
        valor = VALOR_INICIAL;
        estaAtivo = false;
        contagemProbe = 0;
        contagemDeColisao=0;
    }
    /**
     * Retorna o numero de colisoes
     * @return numero de colisoes para este no
     * @see #contagemDeColisao
     * @see #incrementaColisao()
     */
    public int getContagemColisao()
    {
        return contagemDeColisao;
    }
    /**
     * Incrementa a colisao em 1 para que este metodo seja chamado depois
     * cada colisao
     * @see #contagemDeColisao
     * @see #getContagemColisao()
     */
    public void incrementaColisao()
    {
        contagemDeColisao++;
    }
    /**
     * Define o valor do no
     * @param x valor que sera armazenado no NO
     * @see #getValue() 
     */
    public final void setValor(int x)
    {
        valor=x;
    }
    /**
     * Pega o valor do NO
     * @return valor armazenado no NO
     * @see #setValor(int)
     */
    public final int getValue()
    {
        return valor;
    }
    /**
     * Ativa o n� -e  Declara que o NO e alcan�avel na tabela hash
     * @see #estaAtivo
     * @see #desativado()
     */
    public final void ativado()
    {
        estaAtivo=true;
    }
    /**
     * Desactiva o n� - Declara que o NO n�o esta acess�vel na tabela hash
     * @see #estaAtivo
     * @see #ativado()
     */
    public final void desativado()
    {
        estaAtivo=false;
    }
    /**
     * M�todo para obter o numero de sondagem que ja foi feito
     * @return o numero de sondagem  feito
     */
    public final int getProbeContagem()
    {
        return contagemProbe;
    }

    /**
     * M�todo que incrementa a contagem da sondagem  1. Este metodo
     * � a assinatura para cada sondagem feita.
     */
    private final void probed()
    {
        contagemProbe++;
    }

    /**
     * Este m�todo foi implementado por um amigo que esta me ajudando.. ainda estou estudando para entender o mesmo
     * O m�todo que retorna a posi��o a ser sondada. Para Robin Hood hashing,
     * Usamos o m�todo de hash dupla, onde "hash1 (x) = x tableSize mod" e
     * "Hash2 (x) = R - (x mod R)". R � a principal mais pr�xima menos de tamanho da tabela.
     * Para cada chamada de retorno ha altera��es de valor devido a mudan�a no valor na contagem de colisao.
     * O tamanho @param tamanho da tabela da tabela de hash
     * @return Lugar a ser sondado na tabela
     * @throws InitialNodeHashingException
     *
     */
    public final int getTargetLocation(int tableSize)
    {  if(valor==VALOR_INICIAL)
       {
           throw new InitialNodeHashingException();
       }
       int hashCode = ToolBox.hash1(valor, tableSize)+contagemDeColisao*ToolBox.hash2(valor,tableSize);
       probed();
       return hashCode%tableSize;
    }

}
