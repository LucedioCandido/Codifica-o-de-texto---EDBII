import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.*;


public class Compressor {
    private Map<Character,Integer> tabelaFrequencia = new HashMap<Character, Integer>();//dicionario para a tabela de frequencia das letras no txt
    private PriorityQueue<Node> minHeap = new PriorityQueue<Node>(); //arvore de codificação
    int tamanhoMaximo = 0; //Conta o tamanho máximo da árvore

    public void criaTabelaFrequencia(String entrada) throws IOException{
        File file = new File(entrada);
        FileReader input = new FileReader(file);
        BufferedReader leitura  = new BufferedReader(input);
        String linha = leitura.readLine();
        MinHeap chaveOrdenada = new MinHeap();

        while(linha!=null){
            char[] linha_vez = linha.toCharArray();
            for (int i = 0; i<linha.length();i++){
                verificExistenciaLetraNoDicionario(linha_vez[i]);
            }
            linha = leitura.readLine();
            //Identifica se existe uma nova linha, se existir, adiciona a quebra de linha a Tabela de Frequencia
            if(linha!=null){
                int quebraLinha = 13; //Número referente a quebra de linha na tabela ASCII
                verificExistenciaLetraNoDicionario((char)quebraLinha); //Forçando o número da quebra de linha a virar um char
            }
        }
        chaveOrdenada.MinHeap(tamanhoMaximo); //Passa o tamanho máximo da árvore para o método minHeap
        Set<Character> chaves = tabelaFrequencia.keySet();
        for (Character chave : chaves) {
            if (chave != null){
                chaveOrdenada.insert(tabelaFrequencia.get(chave)); //Insere os caracteres na árvore
            }
        }
        chaveOrdenada.minHeap(); //Inicia a ordenação do método MinHeap
        chaveOrdenada.print(); //Imprime o MinHeap ordenado
    }

    public void verificExistenciaLetraNoDicionario(Character c){
        if (tabelaFrequencia.containsKey(c)) {
            tabelaFrequencia.put(c,tabelaFrequencia.get(c).intValue()+1);
        }else{
            tabelaFrequencia.put(c,1);
            tamanhoMaximo++; //Adiciona 1 ao tamanho máximo a cada novo nó da árvore
        }

    }


    public void criaArvoreCodificacao(){
        Set<Character> chaves = tabelaFrequencia.keySet();
        for (Character chave : chaves) {
            tabelaFrequencia.get(chave);
            Node no = new Node();
            minHeap.add(no);
        }

    }

    public void criaTabelaCodificacao(){

    }

    public void codificaTexto(String txt) {

    }

    public void print(){
        Set<Character> chaves = tabelaFrequencia.keySet();
        for (Character chave : chaves) {
            if (chave != null){
                System.out.println(chave + tabelaFrequencia.get(chave).toString());
            }
        }
    }

}
