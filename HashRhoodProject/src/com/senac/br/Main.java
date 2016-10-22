/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.senac.br;


public class Main {

  
    public static void main(String[] args)
    {
        HashTable hashTable;
       if(ToolBox.validacaoParaoHash("input.txt"))
               hashTable = new HashTable("input.txt");
    }

}
